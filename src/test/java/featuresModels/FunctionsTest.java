package featuresModels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @Test
    void deletePunctuationMarksFromEnd() {
        Functions functions = new Functions();
        assertEquals(functions.deletePunctuationMarksFromEnd("Bob."), "Bob");
        assertEquals(functions.deletePunctuationMarksFromEnd("Bob.,!?.:;"), "Bob");
        assertEquals(functions.deletePunctuationMarksFromEnd("Bob--Alice.,?.:;"), "Bob--Alice");
        assertEquals(functions.deletePunctuationMarksFromEnd("Bob...a.,?.:;"), "Bob...a");

    }
}