package com.ljubinkovicdj93.countdown.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljubinkovicdj93.countdown.model.*;
import com.ljubinkovicdj93.countdown.repositories.GameSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatchGameService {
    private final Logger logger = LoggerFactory.getLogger(WatchGameService.class);

    private final ObjectMapper objectMapper;
    private final GameSessionRepository gameSessionRepository;

    public WatchGameService(ObjectMapper objectMapper, GameSessionRepository gameSessionRepository) {
        this.objectMapper = objectMapper;
        this.gameSessionRepository = gameSessionRepository;
    }

    public String makeMessage(WatchGameGamePayload payload, WebSocketSession session) {
        logger.info("Making a new message JOIN_GAME");

        // On WATCH_GAME, replay all the moves so far.
        // TODO: List<String> previousMoves = game.getAllMovesSoFar();

        try {
            String watchKey = payload.getWatchKey();
            GameSession watchingGameSession = gameSessionRepository.getGameSession(watchKey);
            if (watchingGameSession == null) {
                throw new Exception(String.format("Game session not found"));
            }

            watchingGameSession.addSession(session);

            // TODO: Find the current sub-game
            List<String> tempPreviousMovesSimulation = new ArrayList<>();
            tempPreviousMovesSimulation.add("Player 1 selected letter A in sub-game: LONGEST_WORD");
            tempPreviousMovesSimulation.add("Player 2 selected letter B in sub-game: LONGEST_WORD");
            tempPreviousMovesSimulation.add("Player 1 selected letter C in sub-game: LONGEST_WORD");
            tempPreviousMovesSimulation.add("Player 2 selected letter D in sub-game: LONGEST_WORD");
            tempPreviousMovesSimulation.add("Player 1 won by guessing the word \"ABRACADABRA\" in sub-game: LONGEST_WORD");
            WatchGameServerMessage watchGameServerMessage = new WatchGameServerMessage(ResponseEvent.WATCH, tempPreviousMovesSimulation, SubGame.LONGEST_WORD);

            return objectMapper.writeValueAsString(watchGameServerMessage);
        } catch (Exception e) {
            throw new RuntimeException("Unable to watch to a specified game, make sure your watchKey is a valid one");
        }
    }
}
