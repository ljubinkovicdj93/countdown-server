package com.ljubinkovicdj93.countdown.model;

import org.springframework.web.socket.WebSocketSession;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class GameSession {
    private String gameId;
    private Set<WebSocketSession> sessions;

    public GameSession(String gameId) {
        this.gameId = gameId;
        this.sessions = new CopyOnWriteArraySet<>();
    }

    public boolean addSession(WebSocketSession session) {
        return this.sessions.add(session);
    }
}