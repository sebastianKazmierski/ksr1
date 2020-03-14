package loadData;

import java.nio.CharBuffer;

public class XmlTransformer implements FileTransformer {
    @Override
    public CharBuffer validate(char[] chars) {
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
