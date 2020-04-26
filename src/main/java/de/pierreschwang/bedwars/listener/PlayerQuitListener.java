package de.pierreschwang.bedwars.listener;

import de.pierreschwang.bedwars.BedwarsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final BedwarsPlugin plugin;

    public PlayerQuitListener(BedwarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        plugin.getUserRepository().getUsers().values().forEach(player -> player.sendMessage("player-quit", plugin.getUser(event.getPlayer()).getColoredName()));
    }

}
