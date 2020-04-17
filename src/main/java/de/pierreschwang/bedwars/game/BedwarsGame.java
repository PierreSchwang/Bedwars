package de.pierreschwang.bedwars.game;

import de.pierreschwang.bedwars.BedwarsPlugin;
import de.pierreschwang.bedwars.event.GameStateChangedEvent;
import org.bukkit.Bukkit;

public class BedwarsGame {

    private final BedwarsPlugin plugin;
    private GameState currentState = GameState.LOBBY;

    public BedwarsGame(BedwarsPlugin plugin) {
        this.plugin = plugin;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public BedwarsGame setCurrentState(GameState newState) {
        this.currentState = newState;
        Bukkit.getPluginManager().callEvent(new GameStateChangedEvent(newState));
        return this;
    }
}
