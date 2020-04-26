package de.pierreschwang.bedwars.player;

import de.pierreschwang.bedwars.BedwarsPlugin;
import de.pierreschwang.spigotlib.user.User;
import org.bukkit.entity.Player;

public class BedwarsPlayer extends User {

    private final BedwarsPlugin plugin;
    private final Player player;

    public BedwarsPlayer(BedwarsPlugin plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
        this.player = player;
    }

    public String getPrefix() {
        return plugin.getPermissionHook().getHook().getPrefix(player);
    }

    public String getColoredName() {
        return getPrefix() + player.getName();
    }

    public Player getPlayer() {
        return player;
    }
}
