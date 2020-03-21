package loadData.filesTransformer;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Path;

@FunctionalInterface
public interface FileTransformer {
    CharBuffer transform(Path path) throws IOException;
}

