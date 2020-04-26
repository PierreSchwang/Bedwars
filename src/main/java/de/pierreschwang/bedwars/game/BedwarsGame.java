package de.pierreschwang.bedwars.game;

import de.pierreschwang.bedwars.BedwarsPlugin;
import de.pierreschwang.bedwars.event.GameStateChangedEvent;
import de.pierreschwang.bedwars.player.Team;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class BedwarsGame {

    private final BedwarsPlugin plugin;
    private GameState currentState = GameState.LOBBY;
    private final Map<Teams, Team> teams = new HashMap<>();

    public BedwarsGame(BedwarsPlugin plugin) {
        this.plugin = plugin;
        for (int i = 0; i < plugin.getBedwarsConfig().getTeamAmount(); i++) {
            Teams t = Teams.values()[i];
            teams.put(t, new Team(t.getChatColor(), t.getLocale(), plugin.getBedwarsConfig().getPlayersPerTeam()));
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public BedwarsGame setCurrentState(GameState newState) {
        this.currentState = newState;
        Bukkit.getPluginManager().callEvent(new GameStateChangedEvent(newState));
        return this;
    }

    public Map<Teams, Team> getTeams() {
        return teams;
    }

}
