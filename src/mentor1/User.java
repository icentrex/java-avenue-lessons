package mentor1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int id;

    private User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }


}
