package com.ljubinkovicdj93.countdown.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class PayloadWrapper {
    private String command;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "command", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
    @JsonSubTypes(value = {
            @JsonSubTypes.Type(value = HostGamePayload.class, name = "HOST_GAME"),
            @JsonSubTypes.Type(value = JoinGamePayload.class, name = "JOIN_GAME"),
            @JsonSubTypes.Type(value = WatchGamePayload.class, name = "WATCH_GAME")
    })
    private AbstractPayload payload;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public AbstractPayload getPayload() {
        return payload;
    }

    public void setPayload(AbstractPayload payload) {
        this.payload = payload;
    }
}
