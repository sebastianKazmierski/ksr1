package featuresModels;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @Test
    void deletePunctuationMarksFromEnd() {
        assertEquals(List.of("Bob", "."), Functions.deletePunctuationMarksFromEnd("Bob."));
        assertEquals(List.of("Bob", ""), Functions.deletePunctuationMarksFromEnd("Bob"));
        assertEquals(List.of("Bob",".,!?.:;"), Functions.deletePunctuationMarksFromEnd("Bob.,!?.:;"));
        assertEquals(List.of("Bob--Alice",".,?.:;"), Functions.deletePunctuationMarksFromEnd("Bob--Alice.,?.:;"));
        assertEquals(List.of("Bob...a",".,?.:;"), Functions.deletePunctuationMarksFromEnd("Bob...a.,?.:;"));

    }
}