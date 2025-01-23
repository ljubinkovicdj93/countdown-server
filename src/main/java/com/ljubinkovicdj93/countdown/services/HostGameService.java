package com.ljubinkovicdj93.countdown.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljubinkovicdj93.countdown.model.GameSession;
import com.ljubinkovicdj93.countdown.model.HostGameGamePayload;
import com.ljubinkovicdj93.countdown.model.HostGameServerMessage;
import com.ljubinkovicdj93.countdown.model.SecretKeyType;
import com.ljubinkovicdj93.countdown.repositories.GameSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

@Service
public class HostGameService {
    private final Logger logger = LoggerFactory.getLogger(HostGameService.class);

    private final ObjectMapper objectMapper;
    private final GameSessionRepository gameSessionRepository;

    public HostGameService(ObjectMapper objectMapper, GameSessionRepository gameSessionRepository) {
        this.objectMapper = objectMapper;
        this.gameSessionRepository = gameSessionRepository;
    }

    public String makeMessage(HostGameGamePayload payload, WebSocketSession session) {
        logger.info("Making a new message HOST_GAME");

        UUID hostId = payload.getPlayerId();

        GameSession newGameSession = new GameSession(hostId);
        newGameSession.addSession(session);

        String joinKey = gameSessionRepository.storeSecret(SecretKeyType.JOIN_KEY, newGameSession);
        String watchKey = gameSessionRepository.storeSecret(SecretKeyType.WATCH_KEY, newGameSession);

        HostGameServerMessage hostGameServerMessage = new HostGameServerMessage(joinKey, watchKey);
        String jsonResponse = "{ \"error\": \"Unable to convert to HostGameServerMessage\" }";
        try {
            jsonResponse = objectMapper.writeValueAsString(hostGameServerMessage);

            return jsonResponse;
        } catch (JsonProcessingException e) {
            return jsonResponse;
        }
    }
}

