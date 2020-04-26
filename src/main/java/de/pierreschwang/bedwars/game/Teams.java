package de.pierreschwang.bedwars.game;

import de.pierreschwang.bedwars.BedwarsPlugin;
import de.pierreschwang.bedwars.config.BedwarsConfig;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

@Getter
public enum Teams {

    BLUE("team-name-blue", ChatColor.BLUE, DyeColor.BLUE),
    RED("team-name-red", ChatColor.RED, DyeColor.RED),
    YELLOW("team-name-yellow", ChatColor.YELLOW, DyeColor.YELLOW),
    GREEN("team-name-green", ChatColor.GREEN, DyeColor.GREEN);

    private final String locale;
    private final ChatColor chatColor;
    private final DyeColor dyeColor;
    private final int maxPlayers;

    Teams(String locale, ChatColor chatColor, DyeColor dyeColor) {
        this.locale = locale;
        this.chatColor = chatColor;
        this.dyeColor = dyeColor;
        final BedwarsConfig config = BedwarsPlugin.getPlugin().getBedwarsConfig();
        this.maxPlayers = config.getPlayersPerTeam();
    }

    public static Teams getByDyeColor(DyeColor color) {
        for (Teams value : values()) {
            if (value.getDyeColor() == color)
                return value;
        }
        return null;
    }

}
