package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public class WatchGamePayload extends AbstractPayload {
    private UUID playerId;
    private String watchKey;

    public String getWatchKey() {
        return watchKey;
    }

    public void setWatchKey(String watchKey) {
        this.watchKey = watchKey;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}
