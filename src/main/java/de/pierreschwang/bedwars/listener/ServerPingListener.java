package de.pierreschwang.bedwars.listener;

import de.pierreschwang.bedwars.BedwarsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {

    private final BedwarsPlugin plugin;

    public ServerPingListener(BedwarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMotd(plugin.getGame().getCurrentState().name());
        event.setMaxPlayers(plugin.getBedwarsConfig().getMaxPlayers());
    }

}
