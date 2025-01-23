package com.ljubinkovicdj93.countdown.model;

import java.util.UUID;

public class MyNumberServerMessage {
    Integer points;
    UUID playerId;
    Integer digit;
    ResponseEvent event;
    SubGame currGame;
    String playerSolution;
    String computerSolution;

    /**
     * Constructor to use when the My Number game is finished and the solution is found.
     *
     * @param points           Awarded to the specified player
     * @param playerId         ID of the player who won
     * @param playerSolution   Solution that the player provided
     * @param computerSolution Solution that the computer came up with
     */
    public MyNumberServerMessage(Integer points, UUID playerId, String playerSolution, String computerSolution) {
        this.points = points;
        this.playerId = playerId;
        this.playerSolution = playerSolution;
        this.computerSolution = computerSolution;
    }

    /**
     * Constructor to use while the player is selecting digits.
     *
     * @param playerId
     * @param digit
     */
    public MyNumberServerMessage(UUID playerId, Integer digit) {
        this.playerId = playerId;
        this.digit = digit;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public Integer getDigit() {
        return digit;
    }

    public void setDigit(Integer digit) {
        this.digit = digit;
    }

    public ResponseEvent getEvent() {
        return event;
    }

    public void setEvent(ResponseEvent event) {
        this.event = event;
    }

    public SubGame getCurrGame() {
        return currGame;
    }

    public void setCurrGame(SubGame currGame) {
        this.currGame = currGame;
    }

    public String getPlayerSolution() {
        return playerSolution;
    }

    public void setPlayerSolution(String playerSolution) {
        this.playerSolution = playerSolution;
    }

    public String getComputerSolution() {
        return computerSolution;
    }

    public void setComputerSolution(String computerSolution) {
        this.computerSolution = computerSolution;
    }
}
