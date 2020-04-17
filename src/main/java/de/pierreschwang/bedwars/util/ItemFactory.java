package de.pierreschwang.bedwars.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemFactory {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemFactory(Material material, int amount, short damage) {
        this.itemStack = new ItemStack(material, amount, damage);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemFactory(Material material) {
        this(material, 1, (short) 0);
    }

    public ItemFactory name(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    public ItemStack apply() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
