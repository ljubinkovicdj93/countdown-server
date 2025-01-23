package com.ljubinkovicdj93.countdown.services;

import com.ljubinkovicdj93.countdown.model.AbstractPayload;
import org.springframework.web.socket.WebSocketSession;

public interface GameInitHandler {
    String makeMessage(AbstractPayload payload, WebSocketSession session);

    Class<?> payloadClass();
}