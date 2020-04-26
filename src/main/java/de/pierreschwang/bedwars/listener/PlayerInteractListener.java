package de.pierreschwang.bedwars.listener;

import de.pierreschwang.bedwars.BedwarsPlugin;
import de.pierreschwang.bedwars.game.GameState;
import de.pierreschwang.bedwars.game.Teams;
import de.pierreschwang.bedwars.player.BedwarsPlayer;
import de.pierreschwang.bedwars.util.InventoryMenu;
import de.pierreschwang.bedwars.util.ItemFactory;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private final BedwarsPlugin plugin;

    public PlayerInteractListener(BedwarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        BedwarsPlayer player = plugin.getUser(event.getPlayer());
        if (plugin.getGame().getCurrentState() != GameState.INGAME) {
            event.setCancelled(true);
        }
        if (plugin.getGame().getCurrentState() == GameState.LOBBY) {
            if (event.getItem() != null && event.getItem().getType() == Material.BED) {
                InventoryMenu menu = new InventoryMenu(player.getMessage("inventory-choose-team-title"), 27);
                for (int i = 0; i < plugin.getBedwarsConfig().getTeamAmount(); i++) {
                    Teams teams = Teams.values()[i];
                    menu.setItem(i, new ItemFactory(Material.WOOL, 1, teams.getDyeColor().getWoolData())
                        .name(player.getMessage("inventory-choose-team-item-title", teams.getChatColor() + player.getMessage(teams.getLocale())))
                        .apply());
                }
                menu.show(event.getPlayer());
            }
        }
    }

}
