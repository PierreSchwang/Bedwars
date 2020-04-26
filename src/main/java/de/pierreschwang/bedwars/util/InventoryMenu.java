package de.pierreschwang.bedwars.util;

import de.pierreschwang.bedwars.BedwarsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryMenu implements Listener {

    private final Inventory inventory;
    private final Map<Integer, Consumer<Integer>> clickListener = new HashMap<>();

    public InventoryMenu(String title, int size) {
        inventory = Bukkit.createInventory(null, size, title);
        Bukkit.getServer().getPluginManager().registerEvents(this, BedwarsPlugin.getPlugin());
    }

    public InventoryMenu setItem(int slot, ItemStack stack) {
        inventory.setItem(slot, stack);
        return this;
    }

    public InventoryMenu setItem(int slot, ItemStack stack, Consumer<Integer> clickListener) {
        inventory.setItem(slot, stack);
        this.clickListener.put(slot, clickListener);
        return this;
    }

    public void show(Player player) {
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getClickedInventory().equals(inventory))
            return;
        event.setCancelled(true);
        if(clickListener.containsKey(event.getSlot()))
            clickListener.get(event.getSlot()).accept(event.getSlot());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if(!event.getInventory().equals(inventory))
            return;
        HandlerList.unregisterAll(this);
    }

}
