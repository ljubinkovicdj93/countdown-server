package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public final class HostGamePayload extends AbstractPayload {
    private UUID playerId;

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}
