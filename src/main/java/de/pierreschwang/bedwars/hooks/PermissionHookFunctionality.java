package de.pierreschwang.bedwars.hooks;

import de.pierreschwang.spigotlib.hook.PluginHookFunctionality;
import org.bukkit.entity.Player;

public interface PermissionHookFunctionality extends PluginHookFunctionality {

    String getPrefix(Player player);

}
