package mentor1.utils;

import java.util.List;
import java.util.Optional;

public interface DisplayReadWriter {
    void write(List<String> text);

    String writeAndRead(List<String> text);

    Optional<Integer> writeAndReadInt(List<String> text);
}
