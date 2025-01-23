package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public final class LongestWordPayload extends AbstractPayload {
    private UUID playerId;
    private Character letter;
    private String guess;

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}

