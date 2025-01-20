package com.ljubinkovicdj93.countdown.model;

import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class GameSession {
    private Set<WebSocketSession> sessions;

    // TODO: Move into Game class
    private String createdAtTimestamp;
    private UUID hostId;
    private UUID opponentId = null;

    public GameSession(UUID hostId) {
        this.hostId = hostId;
        this.createdAtTimestamp = LocalDateTime.now().toString();
        this.sessions = new CopyOnWriteArraySet<>();
    }

    public String getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public UUID getHostId() {
        return hostId;
    }

    public UUID getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(UUID opponentId) {
        this.opponentId = opponentId;
    }

    public boolean addSession(WebSocketSession session) {
        return this.sessions.add(session);
    }

    public Set<WebSocketSession> getSessions() {
        return sessions;
    }
}