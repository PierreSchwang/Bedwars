package de.pierreschwang.bedwars.listener;

import de.pierreschwang.bedwars.BedwarsPlugin;
import de.pierreschwang.bedwars.event.BedDestroyedEvent;
import de.pierreschwang.bedwars.game.GameState;
import de.pierreschwang.bedwars.game.Teams;
import de.pierreschwang.bedwars.player.BedwarsPlayer;
import de.pierreschwang.bedwars.player.Team;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Wool;

public class BlockBreakListener implements Listener {

    private final BedwarsPlugin plugin;

    public BlockBreakListener(BedwarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getGame().getCurrentState() != GameState.INGAME) {
            event.setCancelled(true);
            return;
        }
        Block block = event.getBlock();
        BedwarsPlayer player = plugin.getUser(event.getPlayer());
        if (event.getBlock().getType() == Material.BED_BLOCK) {
            Block under = event.getBlock().getLocation().clone().add(0, -1, 0).getBlock();
            if (under.getType() == Material.WOOL) {
                handleBedBreak(event, block, under, player);
                return;
            }
        }
    }

    private void handleBedBreak(BlockBreakEvent event, Block bed, Block wool, BedwarsPlayer player) {
        DyeColor color = getColor(wool);
        Team team = plugin.getGame().getTeams().get(Teams.getByDyeColor(color));
        if (team.isBedDestroyed()) {
            return; // ???????
        }
        if (team.getMembers().contains(player)) {
            event.setCancelled(true);
            player.sendMessage("cant-destroy-own-bed");
            return;
        }
        event.getBlock().getDrops().clear();
        team.setBedDestroyed(true);
        plugin.getUserRepository().getUsers().values().forEach(all -> {
            all.sendMessage("bed-destroyed-global", team.getColor() + all.getMessage(team.getName()));
            all.getPlayer().playSound(all.getPlayer().getLocation(), Sound.WITHER_DEATH, 1, 1);
        });
        team.getMembers().forEach(members -> members.sendMessage("team-bed-destroyed"));
        Bukkit.getPluginManager().callEvent(new BedDestroyedEvent(team, bed.getLocation()));
    }

    private DyeColor getColor(Block wool) {
        return ((Wool) wool.getState().getData()).getColor();
    }

}
