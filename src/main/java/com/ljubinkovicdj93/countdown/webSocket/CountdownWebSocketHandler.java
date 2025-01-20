package com.ljubinkovicdj93.countdown.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljubinkovicdj93.countdown.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CountdownWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {
    private static final Logger logger = LoggerFactory.getLogger(CountdownWebSocketHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentMap<String, GameSession> joinGameSessionsMap = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, GameSession> watchGameSessionsMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("One-time message from the server."));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Received text message: {}", message.getPayload());

        try {
            AbstractPayload payload = objectMapper.readValue(message.getPayload(), PayloadWrapper.class).getPayload();

            if (payload instanceof HostGamePayload) {
                UUID hostId = ((HostGamePayload) payload).getPlayerId();

                String joinKey = generateSecretAccessToken();
                joinGameSessionsMap.put(joinKey, new GameSession(hostId));
                String watchKey = generateSecretAccessToken();
                watchGameSessionsMap.put(watchKey, new GameSession(hostId));

                HostGameServerMessage hostGameServerMessage = new HostGameServerMessage(joinKey, watchKey);

                String jsonResponse = objectMapper.writeValueAsString(hostGameServerMessage);
                session.sendMessage(new TextMessage(jsonResponse));
            } else if (payload instanceof JoinGamePayload) {

                String joinKey = ((JoinGamePayload) payload).getJoinKey();
                GameSession joinedGameSession = joinGameSessionsMap.get(joinKey);

                try {
                    if (joinedGameSession == null) {
                        throw new Exception(String.format("Game session not found"));
                    }

                    joinedGameSession.addSession(session);

                    for (WebSocketSession webSocketSession : joinedGameSession.getSessions()) {
                        webSocketSession.sendMessage(new TextMessage("PLAYERS CONGRATS, CONNECTED"));
                    }

                    // On JOIN_GAME, the currGame is always SubGame.LONGEST_WORD
                    JoinGameServerMessage joinGameServerMessage = new JoinGameServerMessage(SubGame.LONGEST_WORD);
                    String jsonResponse = objectMapper.writeValueAsString(joinGameServerMessage);
                    session.sendMessage(new TextMessage(jsonResponse));
                } catch (Exception e) {
                    throw new RuntimeException("Unable to connect to a specified game, make sure your joinKey is a valid one");
                }
            } else if (payload instanceof WatchGamePayload) {
                // On WATCH_GAME, replay all the moves so far.
                // TODO: List<String> previousMoves = game.getAllMovesSoFar();

                try {
                    String watchKey = ((WatchGamePayload) payload).getWatchKey();
                    GameSession watchingGameSession = watchGameSessionsMap.get(watchKey);
                    if (watchingGameSession == null) {
                        throw new Exception(String.format("Game session not found"));
                    }

                    watchingGameSession.addSession(session);

                    // TODO: Find the current sub-game
                    List<String> tempPreviousMovesSimulation = new ArrayList<>();
                    tempPreviousMovesSimulation.add("Player 1 selected letter A in sub-game: LONGEST_WORD");
                    tempPreviousMovesSimulation.add("Player 2 selected letter B in sub-game: LONGEST_WORD");
                    tempPreviousMovesSimulation.add("Player 1 selected letter C in sub-game: LONGEST_WORD");
                    tempPreviousMovesSimulation.add("Player 2 selected letter D in sub-game: LONGEST_WORD");
                    tempPreviousMovesSimulation.add("Player 1 won by guessing the word \"ABRACADABRA\" in sub-game: LONGEST_WORD");
                    WatchGameServerMessage watchGameServerMessage = new WatchGameServerMessage(ResponseEvent.WATCH, tempPreviousMovesSimulation, SubGame.LONGEST_WORD);

                    String jsonResponse = objectMapper.writeValueAsString(watchGameServerMessage);
                    session.sendMessage(new TextMessage(jsonResponse));
                } catch (Exception e) {
                    throw new RuntimeException("Unable to watch to a specified game, make sure your watchKey is a valid one");
                }
            }
        } catch (Exception e) {
            session.sendMessage(new TextMessage(e.getMessage()));
        }
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.countdown.websocket");
    }

    private String generateSecretAccessToken() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            byte[] randomBytes = new byte[32];
            secureRandom.nextBytes(randomBytes);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(randomBytes);

            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating secure token", e);
        }
    }
}
