package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public final class HostGameGamePayload extends AbstractPayload {
    private UUID playerId;

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}

