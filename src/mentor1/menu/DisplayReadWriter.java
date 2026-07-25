package mentor1.menu;

import java.util.List;

public interface DisplayReadWriter {
    void write(List<String> text);
    String writeAndRead(List<String> text);
}
