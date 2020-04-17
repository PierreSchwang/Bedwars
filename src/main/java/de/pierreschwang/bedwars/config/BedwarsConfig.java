package de.pierreschwang.bedwars.config;

import de.pierreschwang.bedwars.BedwarsPlugin;
import lombok.Getter;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;

import java.io.File;

@Getter
public class BedwarsConfig extends Config {

    public BedwarsConfig(BedwarsPlugin plugin) {
        CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
        CONFIG_HEADER = new String[] {
            "BEDWARS v" + plugin.getDescription().getVersion()
        };
    }

    @Comment("Defines how many players are allowed to be online")
    private int maxPlayers = 12;

    @Comment("Defines how many players must be online to start the countdown")
    private int requiredPlayers = 6;



}