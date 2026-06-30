package mentor1;

public class Computer extends Equipment {
    private String name;
    private int itemNumber;

    public Computer(String name, int itemNumber) {
        super(name, itemNumber);
        this.name = name;
        this.itemNumber = itemNumber;
    }
}
