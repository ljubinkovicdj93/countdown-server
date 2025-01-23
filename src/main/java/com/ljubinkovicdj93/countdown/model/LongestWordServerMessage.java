package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public class LongestWordServerMessage {
    Integer points;
    UUID playerId;
    Character letter;
    ResponseEvent event;
    SubGame currGame;

    public LongestWordServerMessage(UUID playerId, Integer points) {
        this.playerId = playerId;
        this.points = points;
        this.event = ResponseEvent.PLAY;
        this.currGame = SubGame.LONGEST_WORD;
    }

    public LongestWordServerMessage(UUID playerId, Character letter) {
        this.playerId = playerId;
        this.letter = letter;
        this.event = ResponseEvent.PLAY;
        this.currGame = SubGame.LONGEST_WORD;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public ResponseEvent getEvent() {
        return event;
    }

    public void setEvent(ResponseEvent event) {
        this.event = event;
    }

    public SubGame getCurrGame() {
        return currGame;
    }

    public void setCurrGame(SubGame currGame) {
        this.currGame = currGame;
    }
}
