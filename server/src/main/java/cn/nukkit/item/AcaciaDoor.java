package cn.nukkit.item;

public class AcaciaDoor extends Item {
    public AcaciaDoor() {
        this(0, 1);
    }

    public AcaciaDoor(Integer meta) {
        this(meta, 1);
    }

    public AcaciaDoor(Integer meta, int count) {
        super(ACACIA_DOOR, 0, count, "Acacia Door");
        this.block = new cn.nukkit.block.AcaciaDoor();
    }

}
