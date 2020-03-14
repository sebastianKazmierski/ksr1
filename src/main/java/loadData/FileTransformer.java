package loadData;

import java.io.Reader;
import java.nio.CharBuffer;

@FunctionalInterface
public interface FileTransformer {
    CharBuffer validate(char[] chars);
}

