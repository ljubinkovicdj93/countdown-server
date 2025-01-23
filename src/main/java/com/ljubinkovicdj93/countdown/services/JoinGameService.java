package com.ljubinkovicdj93.countdown.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljubinkovicdj93.countdown.model.GameSession;
import com.ljubinkovicdj93.countdown.model.JoinGameGamePayload;
import com.ljubinkovicdj93.countdown.model.JoinGameServerMessage;
import com.ljubinkovicdj93.countdown.model.SubGame;
import com.ljubinkovicdj93.countdown.repositories.GameSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class JoinGameService {
    private final Logger logger = LoggerFactory.getLogger(JoinGameService.class);

    private final ObjectMapper objectMapper;
    private final GameSessionRepository gameSessionRepository;

    public JoinGameService(GameSessionRepository gameSessionRepository, ObjectMapper objectMapper) {
        this.gameSessionRepository = gameSessionRepository;
        this.objectMapper = objectMapper;
    }

    public String makeMessage(JoinGameGamePayload payload, WebSocketSession session) {
        logger.info("Making a new message JOIN_GAME");

        String joinKey = payload.getJoinKey();
        GameSession joinedGameSession = gameSessionRepository.getGameSession(joinKey);

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
            return objectMapper.writeValueAsString(joinGameServerMessage);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect to a specified game, make sure your joinKey is a valid one");
        }
    }
}
