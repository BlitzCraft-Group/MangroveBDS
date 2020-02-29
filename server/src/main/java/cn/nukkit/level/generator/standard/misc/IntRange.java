package cn.nukkit.level.generator.standard.misc;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import lombok.NonNull;
import net.daporkchop.lib.common.cache.Cache;
import net.daporkchop.lib.common.cache.ThreadCache;
import net.daporkchop.lib.random.PRandom;

import java.util.regex.Matcher;

/**
 * Represents a numerical range.
 * <p>
 * Note that {@link #max} is always an exclusive bound.
 *
 * @author DaPorkchop_
 */
@JsonDeserialize
public final class IntRange {
    private static final Cache<Matcher> RANGE_MATCHER_CACHE = ThreadCache.regex("^([0-9]+)(?:-([0-9]+))?$");

    public static final IntRange EMPTY_RANGE = new IntRange(0, 0, true);

    public final int min;
    public final int max;

    private IntRange(int min, int max, boolean overloadFlag) {
        this.min = min;
        this.max = max;
    }

    @JsonCreator
    public IntRange(
            @JsonProperty(value = "min", required = true) @JsonAlias({"min"}) int min,
            @JsonProperty(value = "max", required = true) @JsonAlias({"max"}) int max) {
        this.min = min;
        this.max = max + 1; //add 1 to make max exclusive

        this.validate();
    }

    @JsonCreator
    public IntRange(String value) {
        Matcher matcher = RANGE_MATCHER_CACHE.get().reset(value);
        Preconditions.checkArgument(matcher.find(), "Cannot parse range: \"%s\"", value);
        this.min = Integer.parseUnsignedInt(matcher.group(1));
        this.max = (matcher.group(2) == null ? this.min : Integer.parseUnsignedInt(matcher.group(2))) + 1;

        this.validate();
    }

    @JsonCreator
    public IntRange(int value) {
        this.min = value;
        this.max = value + 1;

        this.validate();
    }

    private void validate() {
        Preconditions.checkArgument(this.min >= 0 && this.min < 256, "min (%d) must be in range 0-255!", this.min);
        Preconditions.checkArgument(this.max >= 1 && this.max < 257, "max (%d) must be in range 0-255!", this.max - 1);
        Preconditions.checkArgument(this.min < this.max, "min (%d) may not be greater than max (%d)!", this.min, this.max - 1);
    }

    /**
     * Gets a random value within this {@link IntRange}.
     *
     * @param random an instance of {@link PRandom} to use for generating random numbers
     * @return a random value within this {@link IntRange}
     */
    public int rand(@NonNull PRandom random) {
        return this.empty() ? this.min : random.nextInt(this.min, this.max);
    }

    /**
     * @return whether or not this {@link IntRange} is empty
     */
    public boolean empty() {
        return this == EMPTY_RANGE;
    }

    /**
     * @return the size of this {@link IntRange}
     */
    public int size() {
        return this.max - this.min;
    }

    @Override
    public String toString() {
        return this.empty() ? "empty" : this.size() == 1
                ? String.valueOf(this.min)
                : String.format("(%d-%d)", this.min, this.max - 1);
    }
}
