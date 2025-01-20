package com.ljubinkovicdj93.countdown.model;

import java.util.List;

public class WatchGameServerMessage {
    ResponseEvent event;
    // TODO: Replace with proper element struct instead of String
    List<String> pastMoves;
    SubGame currSubGame;

    public WatchGameServerMessage(ResponseEvent event, List<String> pastMoves, SubGame currSubGame) {
        this.event = event;
        this.pastMoves = pastMoves;
        this.currSubGame = currSubGame;
    }

    public ResponseEvent getEvent() {
        return event;
    }

    public void setEvent(ResponseEvent event) {
        this.event = event;
    }

    public List<String> getPastMoves() {
        return pastMoves;
    }

    public void setPastMoves(List<String> pastMoves) {
        this.pastMoves = pastMoves;
    }

    public SubGame getCurrSubGame() {
        return currSubGame;
    }

    public void setCurrSubGame(SubGame currSubGame) {
        this.currSubGame = currSubGame;
    }
}
