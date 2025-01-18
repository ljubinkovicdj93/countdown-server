package com.ljubinkovicdj93.countdown.model;

public abstract class AbstractPayload {
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
