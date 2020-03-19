package loadData.filesTransformer;

import java.nio.CharBuffer;

@FunctionalInterface
public interface FileTransformer {
    CharBuffer validate(char[] chars);
}

