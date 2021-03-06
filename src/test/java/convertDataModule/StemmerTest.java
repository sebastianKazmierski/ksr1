package convertDataModule;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StemmerTest {

    @Test
    void testStem() {
        List<String> content = Arrays.asList("dogs", "cats", "eating", "doing", "SISTERS", "girls");
        assertEquals(List.of("dog","cat","eat","do","sister","girl"), Stemmer.stem(content));
    }
}