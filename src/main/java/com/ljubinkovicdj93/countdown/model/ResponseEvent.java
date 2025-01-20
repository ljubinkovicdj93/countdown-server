package com.ljubinkovicdj93.countdown.model;

/**
 * All possible events that can occur during gameplay:
 * <ul>
 *      <li>{@link #INIT}</li>
 *      <li>{@link #START}</li>
 *      <li>{@link #ABANDON}</li>
 *      <li>{@link #WATCH}</li>
 *      <li>{@link #PLAY}</li>
 *      <li>{@link #WIN}</li>
 *      <li>{@link #WINNER}</li>
 *      <li>{@link #TIE}</li>
 * </ul>
 */
public enum ResponseEvent {
    /**
     * Game created, ie: someone hosted a game.
     */
    INIT,
    /**
     * Game started, ie: second player joined and the timer started.
     */
    START,
    /**
     * Game abandoned by a player.
     */
    ABANDON,
    /**
     * Spectator joined the game
     */
    WATCH,
    /**
     * A player played, ie: made a move.
     */
    PLAY,
    /**
     * A player won at sub-game.
     */
    WIN,
    /**
     * Player won the game!
     */
    WINNER,
    /**
     * Tie game.
     */
    TIE
}
