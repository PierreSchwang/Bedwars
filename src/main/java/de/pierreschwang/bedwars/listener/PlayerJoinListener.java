package de.pierreschwang.bedwars.listener;

import de.pierreschwang.bedwars.BedwarsPlugin;
import de.pierreschwang.bedwars.game.GameState;
import de.pierreschwang.bedwars.player.BedwarsPlayer;
import de.pierreschwang.bedwars.util.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinListener implements Listener {

    private final BedwarsPlugin bedwarsPlugin;

    public PlayerJoinListener(BedwarsPlugin bedwarsPlugin) {
        this.bedwarsPlugin = bedwarsPlugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (Bukkit.getOnlinePlayers().size() >= bedwarsPlugin.getBedwarsConfig().getMaxPlayers()) {
            event.setResult(PlayerLoginEvent.Result.KICK_FULL);
        }
        bedwarsPlugin.getPlayers().put(event.getPlayer(), new BedwarsPlayer(bedwarsPlugin, event.getPlayer()));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        BedwarsPlayer player = bedwarsPlugin.getPlayer(event.getPlayer());
        if (bedwarsPlugin.getGame().getCurrentState() == GameState.LOBBY) {
            bedwarsPlugin.getOnlinePlayers().forEach(players -> players.sendMessage("player-join", player.getColoredName()));
            event.getPlayer().getInventory().setItem(0, new ItemFactory(Material.BED).name("§cTeam wählen").apply());
        }
    }

}