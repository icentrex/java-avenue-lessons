package lesson12.hw;

public abstract class Duck {
    private String name;
    private int age;
    private int weight;
    private Color color;

    public enum Сolor {WHITE, GREY}

    public Duck(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
}
