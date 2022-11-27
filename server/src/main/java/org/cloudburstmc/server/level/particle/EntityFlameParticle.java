package org.cloudburstmc.server.level.particle;

import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;

/**
 * Created on 2015/11/21 by xtypr.
 * Package cn.nukkit.level.particle in project Nukkit .
 */
public class EntityFlameParticle extends GenericParticle {
    public EntityFlameParticle(Vector3f pos) {
        super(pos, LevelEventType.PARTICLE_MOB_FLAME);
    }
}
