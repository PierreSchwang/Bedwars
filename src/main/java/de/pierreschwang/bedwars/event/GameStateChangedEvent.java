package de.pierreschwang.bedwars.event;

import de.pierreschwang.bedwars.game.GameState;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStateChangedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final GameState newState;

    public GameStateChangedEvent(GameState newState) {
        this.newState = newState;
    }

    public GameState getNewState() {
        return newState;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
