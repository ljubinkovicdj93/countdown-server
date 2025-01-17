package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public class HostGameClientMessage {
    private RequestCommand command;
    private UUID playerId;

    public HostGameClientMessage() {}

    public RequestCommand getCommand() {
        return command;
    }

    public void setCommand(RequestCommand command) {
        this.command = command;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}
