package de.pierreschwang.bedwars.listener;

import de.pierreschwang.bedwars.BedwarsPlugin;
import de.pierreschwang.bedwars.game.GameState;
import de.pierreschwang.bedwars.game.Teams;
import de.pierreschwang.bedwars.player.BedwarsPlayer;
import de.pierreschwang.bedwars.util.InventoryMenu;
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
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        BedwarsPlayer player = bedwarsPlugin.getUser(event.getPlayer());
        bedwarsPlugin.getGame().getTeams().get(Teams.BLUE).getMembers().add(bedwarsPlugin.getUser(event.getPlayer()));
        bedwarsPlugin.getGame().setCurrentState(GameState.INGAME);
        if (bedwarsPlugin.getGame().getCurrentState() == GameState.LOBBY) {
            bedwarsPlugin.getUserRepository().getUsers().values().forEach(players -> players.sendMessage("player-join", player.getColoredName()));
            event.getPlayer().getInventory().setItem(0, new ItemFactory(Material.BED).name(player.getMessage("item-team-chooser-title")).apply());
        }
    }

}