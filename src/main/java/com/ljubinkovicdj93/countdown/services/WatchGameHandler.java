package com.ljubinkovicdj93.countdown.services;

import com.ljubinkovicdj93.countdown.model.AbstractPayload;
import com.ljubinkovicdj93.countdown.model.WatchGameGamePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WatchGameHandler implements GameInitHandler {
    private static final Logger logger = LoggerFactory.getLogger(WatchGameHandler.class);

    private final WatchGameService service;

    public WatchGameHandler(WatchGameService service) {
        this.service = service;
    }

    @Override
    public String makeMessage(AbstractPayload payload, WebSocketSession session) {
        logger.info("Watching game: {}", payload);
        return service.makeMessage((WatchGameGamePayload) payload, session);
    }

    @Override
    public Class<?> payloadClass() {
        return WatchGameGamePayload.class;
    }
}
