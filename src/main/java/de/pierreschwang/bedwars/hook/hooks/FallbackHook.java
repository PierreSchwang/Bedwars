package de.pierreschwang.bedwars.hook.hooks;

import de.pierreschwang.bedwars.hook.PermissionHook;
import org.bukkit.entity.Player;

public class FallbackHook implements PermissionHook {

    @Override
    public String getPrefix(Player player) {
        return "§7";
    }

}