package cn.nukkit;

import cn.nukkit.entity.Human;
import cn.nukkit.event.TextContainer;
import cn.nukkit.event.TranslationContainer;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.utils.TextFormat;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class Player extends Human {

    public static final int SURVIVAL = 0;
    public static final int CREATIVE = 1;
    public static final int ADVENTURE = 2;
    public static final int SPECTATOR = 3;
    public static final int VIEW = SPECTATOR;

    protected String ip;

    protected String displayName;

    public TranslationContainer getLeaveMessage() {
        return new TranslationContainer(TextFormat.YELLOW + "%multiplayer.player.left", this.getDisplayName());
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getAddress() {
        return this.ip;
    }

    public void handleDataPacket(DataPacket packet) {
        //todo
    }

    @Override
    public void close() {
        this.close("");
    }

    public void close(String message) {
        this.close(message, "generic");
    }

    public void close(String message, String reason) {
        this.close(message, reason, true);
    }

    public void close(String message, String reason, boolean notify) {

    }

    public void close(TextContainer message) {
        this.close(message, "generic");
    }

    public void close(TextContainer message, String reason) {
        this.close(message, reason, true);
    }

    public void close(TextContainer message, String reason, boolean notify) {

    }


}
