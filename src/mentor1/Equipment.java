package mentor1;

public abstract class Equipment {
    private String name;
    private int itemNumber;

    public Equipment(String name, int itemNumber) {
        this.name = name;
        this.itemNumber = itemNumber;
    }

    public String getName() {
        return this.name;
    }

    public int getItemNumber() {
        return itemNumber;
    }
    
    public int getIdTest() {
        return itemNumber;
    }

}
