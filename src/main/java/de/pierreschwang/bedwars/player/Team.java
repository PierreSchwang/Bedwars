package de.pierreschwang.bedwars.player;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private final ChatColor color;
    private final String name;
    private final List<BedwarsPlayer> members;

    public Team(ChatColor color, String name) {
        this.color = color;
        this.name = name;
        this.members = new ArrayList<>();
    }


}
