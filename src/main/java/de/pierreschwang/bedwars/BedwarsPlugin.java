package de.pierreschwang.bedwars;

import de.pierreschwang.bedwars.config.BedwarsConfig;
import de.pierreschwang.bedwars.game.BedwarsGame;
import de.pierreschwang.bedwars.hooks.PermissionHook;
import de.pierreschwang.bedwars.listener.*;
import de.pierreschwang.bedwars.player.BedwarsPlayer;
import de.pierreschwang.spigotlib.AbstractJavaPlugin;
import lombok.Getter;
import net.cubespace.Yamler.Config.InvalidConfigurationException;

import java.util.logging.Level;

@Getter
public class BedwarsPlugin extends AbstractJavaPlugin<BedwarsPlayer> {

    @Getter
    private static BedwarsPlugin plugin;

    private BedwarsConfig bedwarsConfig;
    private PermissionHook permissionHook;

    private BedwarsGame game;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        bedwarsConfig = new BedwarsConfig(this);
        try {
            bedwarsConfig.init();
        } catch (InvalidConfigurationException e) {
            getLogger().log(Level.SEVERE, "Your config.yml seems to be broken!", e);
        }
        setUserFactory(player -> new BedwarsPlayer(this, player));
        permissionHook = new PermissionHook();
        registerListener();
        game = new BedwarsGame(this);
    }

    @Override
    public void onDisable() {

    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new ServerPingListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
    }

}