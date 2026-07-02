package mentor1;

import java.util.Objects;

public abstract class Equipment {
    private final String name;
    private final int id;
    private int userId;

    public Equipment(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Equipment{type=\'" + this.getClass().getSimpleName() +
                "\', name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return id == equipment.id && Objects.equals(name, equipment.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
