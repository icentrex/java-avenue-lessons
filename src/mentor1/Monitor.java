package mentor1;

public class Monitor extends Equipment {
    private String name;
    private int itemNumber;

    public Monitor(String name, int itemNumber) {
        super(name, itemNumber);
        this.name = name;
        this.itemNumber = itemNumber;
    }
}
