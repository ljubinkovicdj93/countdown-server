package com.ljubinkovicdj93.countdown.services;

import com.ljubinkovicdj93.countdown.model.AbstractPayload;
import com.ljubinkovicdj93.countdown.model.HostGameGamePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class HostGameHandler implements GameInitHandler {
    private static final Logger logger = LoggerFactory.getLogger(HostGameHandler.class);

    private final HostGameService service;

    public HostGameHandler(HostGameService service) {
        this.service = service;
    }

    @Override
    public String makeMessage(AbstractPayload payload, WebSocketSession session) {
        logger.info("Hosting game: {}", payload);
        return service.makeMessage((HostGameGamePayload) payload, session);
    }

    @Override
    public Class<?> payloadClass() {
        return HostGameGamePayload.class;
    }
}

