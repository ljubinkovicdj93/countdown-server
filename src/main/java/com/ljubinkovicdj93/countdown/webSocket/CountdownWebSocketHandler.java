package com.ljubinkovicdj93.countdown.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljubinkovicdj93.countdown.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.List;

public class CountdownWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {
    private static final Logger logger = LoggerFactory.getLogger(CountdownWebSocketHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

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
                HostGameServerMessage hostGameServerMessage = new HostGameServerMessage("joinKeyRandom", "watchKeyRandom");
                String jsonResponse = objectMapper.writeValueAsString(hostGameServerMessage);
                session.sendMessage(new TextMessage(jsonResponse));
            } else if (payload instanceof JoinGamePayload) {
                // On JOIN_GAME, the currGame is always SubGame.LONGEST_WORD
                JoinGameServerMessage joinGameServerMessage = new JoinGameServerMessage(SubGame.LONGEST_WORD);
                String jsonResponse = objectMapper.writeValueAsString(joinGameServerMessage);
                session.sendMessage(new TextMessage(jsonResponse));
            }
        } catch (Exception e) {
            session.sendMessage(new TextMessage(e.getMessage()));
        }
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.countdown.websocket");
    }
}
