package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public final class MyNumberPayload extends AbstractPayload {
    private UUID playerId;
    private Integer digit;
    private String guess;

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public Integer getDigit() {
        return digit;
    }

    public void setDigit(Integer digit) {
        this.digit = digit;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}
