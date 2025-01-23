package com.ljubinkovicdj93.countdown.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljubinkovicdj93.countdown.model.LongestWordPayload;
import com.ljubinkovicdj93.countdown.model.LongestWordServerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LongestWordService {
    private final Logger logger = LoggerFactory.getLogger(LongestWordService.class);

    private final ObjectMapper objectMapper;

    // TODO: Temp
    private String longestWord;
    private Integer count = 0;

    public LongestWordService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String makeMessage(LongestWordPayload payload) {
        logger.info("Making a new message LONGEST_WORD");

        // TODO: Create a static like error response, ie: String.error("LongestWord")
        String jsonResponse = "{ \"error\": \"Unable to convert to LongestWordPayload\" }";

        if (payload.getGuess() != null) {
            int points;
            if (payload.getGuess().equals(longestWord)) {
                points = 100;
            } else {
                double quotient = (double) payload.getGuess().length() / longestWord.length();
                points = (int) (quotient * 100);
            }

            logger.info("Guess: {}", payload.getGuess());
            logger.info("Points: {}", points);

            try {
                jsonResponse = objectMapper.writeValueAsString(new LongestWordServerMessage(payload.getPlayerId(), points));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            assert payload.getLetter() != null;
            if (count == 12) {
                return "{ \"error\": \"Reached character limit (max is 12)\" }";
            }
            count += 1;
            longestWord += payload.getLetter();

            if (count == 12) {
                longestWord = findLongestWord();
            }

            try {
                jsonResponse = objectMapper.writeValueAsString(new LongestWordServerMessage(payload.getPlayerId(), payload.getLetter()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
        return jsonResponse;
    }

    private String findLongestWord() {
        return "123456789012";
    }
}

