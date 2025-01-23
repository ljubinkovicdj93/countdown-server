package com.ljubinkovicdj93.countdown.services;

import com.ljubinkovicdj93.countdown.model.AbstractPayload;
import com.ljubinkovicdj93.countdown.model.LongestWordPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LongestWordHandler implements SubGameHandler {
    private static final Logger logger = LoggerFactory.getLogger(LongestWordHandler.class);

    private final LongestWordService service;

    public LongestWordHandler(LongestWordService service) {
        this.service = service;
    }

    @Override
    public String makeMessage(AbstractPayload payload) {
        logger.info("Longest word: {}", payload);
        return service.makeMessage((LongestWordPayload) payload);
    }

    @Override
    public Class<?> payloadClass() {
        return LongestWordPayload.class;
    }
}
