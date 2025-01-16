package com.ljubinkovicdj93.countdown.model;

/**
 * All possible commands that can come from a client:
 * <ul>
 *      <li>{@link #INIT}</li>
 *      <li>{@link #ABANDON}</li>
 *      <li>{@link #PLAY}</li>
 * <ul>
 */
public enum RequestCommand {
    /**
     * It can mean <strong>1 of 3</strong> things:
     * <ol>
     *   <li>User created/hosted a new game.</li>
     *   <li>User joined a game.</li>
     *   <li>User is a spectator of a game, ie: watching the game.</li>
     * </ol>
     */
    INIT,
    /**
     * User abandoned a game, thus losing the game.
     */
    ABANDON,
    /**
     * User played, ie: made a move.
     */
    PLAY
}