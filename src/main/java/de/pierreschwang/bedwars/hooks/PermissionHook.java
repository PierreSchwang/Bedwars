package de.pierreschwang.bedwars.hooks;

import de.pierreschwang.spigotlib.hook.PluginHook;
import de.pierreschwang.spigotlib.hook.PluginHookImplementation;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.Collection;

public class PermissionHook extends PluginHook<PermissionHookFunctionality> {

    private final Collection<PluginHookImplementation<PermissionHookFunctionality>> hooks;

    public PermissionHook() {
        hooks = new ArrayList<>();
        hooks.add(new PluginHookImplementation<PermissionHookFunctionality>(Bukkit.getPluginManager().getPlugin("LuckPerms")) {
            @Override
            public PermissionHookFunctionality getFunctionality() {
                RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
                final LuckPerms luckPerms = provider.getProvider();
                final QueryOptions queryOptions = luckPerms.getContextManager().getStaticQueryOptions();
                return (player) -> {
                    final User user = luckPerms.getUserManager().getUser(player.getUniqueId());
                    if (user == null)
                        return "§7";
                    String prefix = user.getCachedData().getMetaData(queryOptions).getPrefix();
                    return prefix == null ? "§7" : prefix;
                };
            }
        });
    }

    @Override
    public Collection<PluginHookImplementation<PermissionHookFunctionality>> getPossibleImplementations() {
        return hooks;
    }

    @Override
    public PermissionHookFunctionality getFallback() {
        return player -> player.isOp() ? "§c" : "§7";
    }

}