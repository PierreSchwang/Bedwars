package de.pierreschwang.bedwars.player;

import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Team {

    private final ChatColor color;
    private final String name;
    private final List<BedwarsPlayer> members;
    private final int maxPlayers;
    private boolean bedDestroyed = false;

    public Team(ChatColor color, String name, int maxPlayers) {
        this.color = color;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.members = new ArrayList<>();
    }

    public Team setBedDestroyed(boolean bedDestroyed) {
        this.bedDestroyed = bedDestroyed;
        return this;
    }
}
