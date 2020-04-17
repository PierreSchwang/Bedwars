package de.pierreschwang.bedwars.hook.hooks;

import de.pierreschwang.bedwars.hook.PermissionHook;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class LuckPermsHook implements PermissionHook {

    private LuckPerms luckPerms;

    public LuckPermsHook() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null)
            return;
        System.out.println("========== [ BEDWARS ] ==========");
        System.out.println(" Found: LuckPerms");
        System.out.println(" Using: LuckPermsHook for prefixes");
        System.out.println("=================================");
        luckPerms = provider.getProvider();
    }

    @Override
    public String getPrefix(Player player) {
        final User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null)
            return "§7";
        final QueryOptions queryOptions = luckPerms.getContextManager().getStaticQueryOptions();
        String prefix = user.getCachedData().getMetaData(queryOptions).getPrefix();
        return prefix == null ? "§7" : prefix;
    }

}
