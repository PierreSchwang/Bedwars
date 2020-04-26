package de.pierreschwang.bedwars.event;

import de.pierreschwang.bedwars.player.Team;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BedDestroyedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Team team;
    private final Location bedLocation;

    public BedDestroyedEvent(Team team, Location bedLocation) {
        this.team = team;
        this.bedLocation = bedLocation;
    }

    public Team getTeam() {
        return team;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
