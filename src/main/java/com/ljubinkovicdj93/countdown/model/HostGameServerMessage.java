package com.ljubinkovicdj93.countdown.model;

public class HostGameServerMessage {
    ResponseEvent event;
    String joinKey;
    String watchKey;

    public HostGameServerMessage(String joinKey, String watchKey) {
        this.event = ResponseEvent.INIT;
        this.joinKey = joinKey;
        this.watchKey = watchKey;
    }

    public ResponseEvent getEvent() {
        return event;
    }

    public void setEvent(ResponseEvent event) {
        this.event = event;
    }

    public String getJoinKey() {
        return joinKey;
    }

    public void setJoinKey(String joinKey) {
        this.joinKey = joinKey;
    }

    public String getWatchKey() {
        return watchKey;
    }

    public void setWatchKey(String watchKey) {
        this.watchKey = watchKey;
    }
}
