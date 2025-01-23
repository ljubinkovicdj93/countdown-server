package com.ljubinkovicdj93.countdown.services;

import com.ljubinkovicdj93.countdown.model.AbstractPayload;
import com.ljubinkovicdj93.countdown.model.MyNumberPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyNumberHandler implements SubGameHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyNumberHandler.class);

    private final MyNumberService service;

    public MyNumberHandler(MyNumberService service) {
        this.service = service;
    }

    @Override
    public String makeMessage(AbstractPayload payload) {
        logger.info("My number: {}", payload);
        return service.makeMessage((MyNumberPayload) payload);
    }

    @Override
    public Class<?> payloadClass() {
        return MyNumberPayload.class;
    }
}
