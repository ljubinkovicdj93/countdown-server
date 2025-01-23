package com.ljubinkovicdj93.countdown.services;

import com.ljubinkovicdj93.countdown.model.AbstractPayload;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageDispatcher {
    private final Map<Class<?>, Object> handlerMap = new HashMap<>();

    public MessageDispatcher(List<GameInitHandler> gameInitHandlers, List<SubGameHandler> subGameHandlers) {
        for (GameInitHandler gameInitHandler : gameInitHandlers) {
            assert !handlerMap.containsKey(gameInitHandler.payloadClass());
            handlerMap.put(gameInitHandler.payloadClass(), gameInitHandler);
        }

        for (SubGameHandler subGameHandler : subGameHandlers) {
            assert !handlerMap.containsKey(subGameHandler.payloadClass());
            handlerMap.put(subGameHandler.payloadClass(), subGameHandler);
        }
    }

    public String dispatch(AbstractPayload payload, WebSocketSession session) {
        Object handler = handlerMap.get(payload.getClass());
        if (handler instanceof GameInitHandler) {
            return ((GameInitHandler) handler).makeMessage(payload, session);
        } else if (handler instanceof SubGameHandler) {
            return ((SubGameHandler) handler).makeMessage(payload);
        }
        throw new IllegalArgumentException("Unsupported payload type: " + payload.getClass());
    }
}