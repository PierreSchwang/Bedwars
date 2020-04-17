package de.pierreschwang.bedwars;

import de.pierreschwang.bedwars.config.BedwarsConfig;
import de.pierreschwang.bedwars.game.BedwarsGame;
import de.pierreschwang.bedwars.hook.PermissionHook;
import de.pierreschwang.bedwars.hook.hooks.FallbackHook;
import de.pierreschwang.bedwars.hook.hooks.LuckPermsHook;
import de.pierreschwang.bedwars.i18n.LanguageHandler;
import de.pierreschwang.bedwars.listener.PlayerJoinListener;
import de.pierreschwang.bedwars.listener.ServerPingListener;
import de.pierreschwang.bedwars.player.BedwarsPlayer;
import lombok.Getter;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@Getter
public class BedwarsPlugin extends JavaPlugin {

    private BedwarsConfig bedwarsConfig;
    private LanguageHandler languageHandler;
    private PermissionHook permissionHook;

    private BedwarsGame game;

    private final Map<Player, BedwarsPlayer> players = new HashMap<>();

    @Override
    public void onLoad() {
        languageHandler = new LanguageHandler(this);
    }

    @Override
    public void onEnable() {
        bedwarsConfig = new BedwarsConfig(this);
        try {
            bedwarsConfig.init();
        } catch (InvalidConfigurationException e) {
            getLogger().log(Level.SEVERE, "Your config.yml seems to be broken!", e);
        }
        setupHooks();
        registerListener();
        game = new BedwarsGame(this);
    }

    @Override
    public void onDisable() {

    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new ServerPingListener(this), this);
    }

    private void setupHooks() {
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            permissionHook = new LuckPermsHook();
        }
        if (permissionHook == null) {
            permissionHook = new FallbackHook();
        }
    }

    public BedwarsPlayer getPlayer(Player player) {
        return players.get(player);
    }

    public Collection<BedwarsPlayer> getOnlinePlayers() {
        return players.values();
    }

}