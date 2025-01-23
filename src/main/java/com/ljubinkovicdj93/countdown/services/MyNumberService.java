package com.ljubinkovicdj93.countdown.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljubinkovicdj93.countdown.model.MyNumberPayload;
import com.ljubinkovicdj93.countdown.model.MyNumberServerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyNumberService {
    private final Logger logger = LoggerFactory.getLogger(MyNumberService.class);

    private final ObjectMapper objectMapper;

    // TODO: Temp
    private String solution;
    private List<Integer> digits = new ArrayList<>();

    public MyNumberService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String makeMessage(MyNumberPayload payload) {
        logger.info("Making a new message MY_NUMBER");

        // TODO: Create a static like error response, ie: String.error("LongestWord")
        String jsonResponse = "{ \"error\": \"Unable to convert to MY_NUMBER\" }";

        try {
            if (payload.getDigit() != null) {
                if (digits.size() > 6) {
                    return jsonResponse;
                }
                digits.add(payload.getDigit());

                jsonResponse = objectMapper.writeValueAsString(new MyNumberServerMessage(payload.getPlayerId(), payload.getDigit()));
            } else {
                assert payload.getGuess() != null;

                // TODO: Convert to Operand Operation Operand format then compute and make sure that it gets the correct number
                int points = payload.getGuess().equals("validSolution") ? 100 : 50;

                MyNumberServerMessage serverMessage = new MyNumberServerMessage(points, payload.getPlayerId(), payload.getGuess(), "computer solution");
                jsonResponse = objectMapper.writeValueAsString(serverMessage);
            }
            return jsonResponse;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}