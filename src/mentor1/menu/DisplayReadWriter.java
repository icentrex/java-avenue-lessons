package mentor1.menu;

import java.util.List;
import java.util.Scanner;

public interface DisplayReadWriter {
    void write(List<String> text);

    String writeAndRead(List<String> text);
}
