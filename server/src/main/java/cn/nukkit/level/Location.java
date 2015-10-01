package cn.nukkit.level;

import cn.nukkit.math.Vector3;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class Location extends Position {

    public float yaw;
    public float pitch;

    public Location() {
        this(0);
    }

    public Location(int x) {
        this(x, 0);
    }

    public Location(int x, int y) {
        this(x, y, 0);
    }

    public Location(int x, int y, int z) {
        this(x, y, z, 0.0f);
    }

    public Location(int x, int y, int z, float yaw) {
        this(x, y, z, yaw, 0.0f);
    }

    public Location(int x, int y, int z, float yaw, float pitch) {
        this(x, y, z, yaw, pitch, null);
    }

    public Location(int x, int y, int z, float yaw, float pitch, Level level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.level = level;
    }

    public static Location fromObject(Vector3 pos) {
        return fromObject(pos, null, 0.0f, 0.0f);
    }

    public static Location fromObject(Vector3 pos, Level level) {
        return fromObject(pos, level, 0.0f, 0.0f);
    }

    public static Location fromObject(Vector3 pos, Level level, float yaw) {
        return fromObject(pos, level, yaw, 0.0f);
    }

    public static Location fromObject(Vector3 pos, Level level, float yaw, float pitch) {
        return new Location((int) pos.x, (int) pos.y, (int) pos.z, yaw, pitch, (level == null) ? ((pos instanceof Position) ? ((Position) pos).level : null) : level);
    }

    public double getYaw() {
        return this.yaw;
    }

    public double getPitch() {
        return this.pitch;
    }

    @Override
    public String toString() {
        return "Location (level=" + (this.isValid() ? this.getLevel().getName() : "null") + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ")";
    }
}
