package cn.nukkit.level.generator.standard.gen.decorator;

import cn.nukkit.level.chunk.IChunk;
import cn.nukkit.level.generator.standard.StandardGenerator;
import cn.nukkit.level.generator.standard.gen.noise.NoiseGenerator;
import cn.nukkit.level.generator.standard.misc.AbstractGenerationPass;
import cn.nukkit.utils.Identifier;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.daporkchop.lib.noise.NoiseSource;
import net.daporkchop.lib.random.PRandom;
import net.daporkchop.lib.random.impl.FastPRandom;

import java.util.Objects;

/**
 * Similar to {@link SurfaceDecorator}, but switches between two different decorators based on the output of a noise function.
 *
 * @author DaPorkchop_
 */
@JsonDeserialize
public class NoiseSelectionDecorator extends AbstractGenerationPass implements Decorator {
    public static final Identifier ID = Identifier.fromString("nukkitx:noise");

    protected NoiseSource selector;

    @JsonProperty
    protected double min = 0.0d;

    @JsonProperty
    protected double max = Double.MAX_VALUE;

    @JsonProperty
    protected Decorator[] in = Decorator.EMPTY_ARRAY;

    @JsonProperty
    protected Decorator[] out = Decorator.EMPTY_ARRAY;

    @JsonProperty("selector")
    protected NoiseGenerator selectorNoise;

    @Override
    protected void init0(long levelSeed, long localSeed, StandardGenerator generator) {
        this.selector = Objects.requireNonNull(this.selectorNoise, "selector must be set!").create(new FastPRandom(localSeed));
        this.selectorNoise = null;

        for (Decorator decorator : Objects.requireNonNull(this.in, "in must be set!"))  {
            decorator.init(levelSeed, localSeed, generator);
        }
        for (Decorator decorator : Objects.requireNonNull(this.out, "out must be set!"))  {
            decorator.init(levelSeed, localSeed, generator);
        }
    }

    @Override
    public void decorate(IChunk chunk, PRandom random, int x, int z) {
        double noise = this.selector.get((chunk.getX() << 4) + x, (chunk.getZ() << 4) + z);
        for (Decorator decorator : noise >= this.min && noise <= this.max ? this.in : this.out) {
            decorator.decorate(chunk, random, x, z);
        }
    }

    @Override
    public Identifier getId() {
        return ID;
    }
}
