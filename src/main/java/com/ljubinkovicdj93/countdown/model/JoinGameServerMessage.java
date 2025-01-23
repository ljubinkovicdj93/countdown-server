package com.ljubinkovicdj93.countdown.model;

public class JoinGameServerMessage {
    ResponseEvent event;
    SubGame currGame;

    public JoinGameServerMessage(SubGame currGame) {
        this.event = ResponseEvent.START;
        this.currGame = currGame;
    }

    public SubGame getCurrGame() {
        return currGame;
    }

    public void setCurrGame(SubGame timeLeft) {
        this.currGame = timeLeft;
    }

    public ResponseEvent getEvent() {
        return event;
    }

    public void setEvent(ResponseEvent event) {
        this.event = event;
    }
}

