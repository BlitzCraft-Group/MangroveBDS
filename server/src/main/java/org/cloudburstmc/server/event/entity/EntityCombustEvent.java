package org.cloudburstmc.server.event.entity;

import org.cloudburstmc.server.entity.Entity;
import org.cloudburstmc.server.event.Cancellable;
import org.cloudburstmc.server.event.HandlerList;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class EntityCombustEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    protected int duration;

    public EntityCombustEvent(Entity combustee, int duration) {
        this.entity = combustee;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
