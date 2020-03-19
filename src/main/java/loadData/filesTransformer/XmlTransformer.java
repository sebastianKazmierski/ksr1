package loadData.filesTransformer;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.file.Path;

public class XmlTransformer implements FileTransformer {
    @Override
    public CharBuffer transform(Path path) throws IOException {
        Reader fileReader = new FileReader(path.toFile());
        char[] chars = IOUtils.toCharArray(fileReader);
        CharBuffer charBuffer = CharBuffer.allocate(chars.length);

        char current;
        for (char aChar : chars) {
            current = aChar;
            if (current == 0x9 || current == 0xA || current == 0xD || current >= 0x20 && current <= 0xD7FF || current >= 0xE000 && current <= 0xFFFD)
                charBuffer.append(current);
        }

        charBuffer.limit(charBuffer.position() - 1);
        charBuffer.position(0);

        return charBuffer;
    }
}
