package com.ljubinkovicdj93.countdown.services;

import com.ljubinkovicdj93.countdown.model.AbstractPayload;

public interface SubGameHandler {
    String makeMessage(AbstractPayload payload);

    Class<?> payloadClass();
}
