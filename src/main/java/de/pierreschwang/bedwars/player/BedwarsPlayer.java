package de.pierreschwang.bedwars.player;

import de.pierreschwang.bedwars.BedwarsPlugin;
import org.bukkit.entity.Player;

public class BedwarsPlayer {

    private final BedwarsPlugin plugin;
    private final Player player;
    private final String language;

    public BedwarsPlayer(BedwarsPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        language = player.spigot().getLocale();
    }

    public void sendMessage(String key, Object... params) {
        player.sendMessage(plugin.getLanguageHandler().translate(language, key, params));
    }

    public String getPrefix() {
        return plugin.getPermissionHook().getPrefix(player);
    }

    public String getColoredName() {
        return getPrefix() + player.getName();
    }

}
