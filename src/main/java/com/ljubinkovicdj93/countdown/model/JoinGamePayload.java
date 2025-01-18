package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public class JoinGamePayload extends AbstractPayload {
    private UUID playerId;
    private String joinKey;

    public String getJoinKey() {
        return joinKey;
    }

    public void setJoinKey(String joinKey) {
        this.joinKey = joinKey;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}