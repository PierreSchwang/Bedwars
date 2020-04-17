package de.pierreschwang.bedwars.stats;

import org.bukkit.entity.Player;

public interface StatsRepository {

    void addKill(Player player);

    void addDestroyedBed(Player player);

    void addDeath(Player player);

}
