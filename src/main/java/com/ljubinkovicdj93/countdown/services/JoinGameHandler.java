package com.ljubinkovicdj93.countdown.services;


import com.ljubinkovicdj93.countdown.model.AbstractPayload;
import com.ljubinkovicdj93.countdown.model.JoinGameGamePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class JoinGameHandler implements GameInitHandler {
    private static final Logger logger = LoggerFactory.getLogger(JoinGameHandler.class);

    private final JoinGameService service;

    public JoinGameHandler(JoinGameService service) {
        this.service = service;
    }

    @Override
    public String makeMessage(AbstractPayload payload, WebSocketSession session) {
        logger.info("Joining game: {}", payload);
        return service.makeMessage((JoinGameGamePayload) payload, session);
    }

    @Override
    public Class<?> payloadClass() {
        return JoinGameGamePayload.class;
    }
}
