package org.cloudburstmc.server.block.trait;

import org.cloudburstmc.server.utils.IntRange;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public final class IntegerBlockTrait extends BlockTrait<Integer> {
    private final IntRange range;
    private final int defaultValue;

    private IntegerBlockTrait(String name, @Nullable String vanillaName, IntRange range, int defaultValue) {
        super(name, vanillaName, Integer.class, range);
        this.range = range;
        this.defaultValue = defaultValue;
    }

    public static IntegerBlockTrait from(String name) {
        return from(name, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static IntegerBlockTrait from(String name, int length) {
        return from(name, 0, length - 1, 0);
    }

    public static IntegerBlockTrait from(String name, int start, int end) {
        return from(name, start, end, 0);
    }

    public static IntegerBlockTrait from(String name, int start, int end, int defaultValue) {
        return from(name, null, start, end, defaultValue);
    }

    public static IntegerBlockTrait from(String name, @Nullable String vanillaName, int start, int end, int defaultValue) {
        checkNotNull(name, "name");
        checkArgument(defaultValue >= start && defaultValue <= end, "defaultValue is not in range");
        return new IntegerBlockTrait(name, vanillaName, new IntRange(start, end), defaultValue);
    }

    @Override
    public Integer getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public int getIndex(Object value) {
        return getIndex((int) value);
    }

    public IntRange getRange() {
        return range;
    }

    public int getIndex(int value) {
        int start = this.range.getStart();
        int end = this.range.getEnd();
        checkArgument(value >= start && value <= end, "value is not in range");
        return value - start;
    }
}
