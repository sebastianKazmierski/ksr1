package loadData;

import java.io.Reader;
import java.nio.CharBuffer;

@FunctionalInterface
public interface FileValidator {
    CharBuffer validate(char[] chars);
}

