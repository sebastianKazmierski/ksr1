package featuresModels.keyWords;

import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyWordTest {

    @Test
    void trainDone() {
        Word<Place> word1 = new Word<>("empty",Place.class);
        word1.trainDone();
        assertFalse(word1.isKeyWord());
        assertNull(word1.getKeyWordFor());

        Word<Place> word2 = new Word<Place>("empty",Place.class);
        word2.train(Place.UK);
        word2.train(Place.UK);
        word2.train(Place.CANADA);
        word2.train(Place.USA);
        word2.train(Place.JAPAN);
        word2.trainDone();
        assertFalse(word2.isKeyWord());
        assertNull(word2.getKeyWordFor());

        Word<Place> word3 = new Word<Place>("empty",Place.class);
        word3.train(Place.UK);
        word3.train(Place.UK);
        word3.train(Place.UK);
        word3.train(Place.USA);
        word3.train(Place.JAPAN);
        word3.trainDone();
        assertTrue(word3.isKeyWord());
        assertEquals(Place.UK, word3.getKeyWordFor());

        Word<Place> word4 = new Word<Place>("empty",Place.class);
        word4.train(Place.UK);
        word4.train(Place.UK);
        word4.train(Place.USA);
        word4.train(Place.JAPAN);
        word4.trainDone();
        assertFalse(word4.isKeyWord());
        assertNull(word4.getKeyWordFor());



    }
}