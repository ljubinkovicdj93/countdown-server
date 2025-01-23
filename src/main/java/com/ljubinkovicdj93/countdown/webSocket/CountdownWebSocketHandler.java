package com.ljubinkovicdj93.countdown.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljubinkovicdj93.countdown.model.AbstractPayload;
import com.ljubinkovicdj93.countdown.model.PayloadWrapper;
import com.ljubinkovicdj93.countdown.services.MessageDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.List;

@Component
public class CountdownWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {
    private static final Logger logger = LoggerFactory.getLogger(CountdownWebSocketHandler.class);

    private final ObjectMapper objectMapper;
    private final MessageDispatcher messageDispatcher;

    public CountdownWebSocketHandler(ObjectMapper objectMapper, MessageDispatcher messageDispatcher) {
        this.objectMapper = objectMapper;
        this.messageDispatcher = messageDispatcher;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("One-time message from the server."));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Received text message: {}", message.getPayload());

        try {
            AbstractPayload payload = objectMapper.readValue(message.getPayload(), PayloadWrapper.class).getPayload();

            String jsonResponse = messageDispatcher.dispatch(payload, session);

            session.sendMessage(new TextMessage(jsonResponse));
        } catch (Exception e) {
            session.sendMessage(new TextMessage(e.getMessage()));
        }
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.countdown.websocket");
    }
}
