package org.cloudburstmc.server.event.entity;

import org.cloudburstmc.api.entity.Entity;
import org.cloudburstmc.api.event.Cancellable;
import org.cloudburstmc.api.event.entity.EntityEvent;

public class EntityPortalEnterEvent extends EntityEvent implements Cancellable {

    private final PortalType type;

    public EntityPortalEnterEvent(Entity entity, PortalType type) {
        this.entity = entity;
        this.type = type;
    }

    public PortalType getPortalType() {
        return type;
    }

    public enum PortalType {
        NETHER,
        END
    }
}
