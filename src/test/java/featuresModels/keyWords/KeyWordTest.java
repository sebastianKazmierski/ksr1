package featuresModels.keyWords;

import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyWordTest {

    @Test
    void trainDone() {
        KeyWord keyWord1 = new KeyWord("empty");
        keyWord1.trainDone();
        assertFalse(keyWord1.isKeyWord());
        assertNull(keyWord1.getKeyWordFor());

        KeyWord keyWord2 = new KeyWord("empty");
        keyWord2.train(Place.UK);
        keyWord2.train(Place.UK);
        keyWord2.train(Place.CANADA);
        keyWord2.train(Place.USA);
        keyWord2.train(Place.JAPAN);
        keyWord2.trainDone();
        assertFalse(keyWord2.isKeyWord());
        assertNull(keyWord2.getKeyWordFor());

        KeyWord keyWord3 = new KeyWord("empty");
        keyWord3.train(Place.UK);
        keyWord3.train(Place.UK);
        keyWord3.train(Place.UK);
        keyWord3.train(Place.USA);
        keyWord3.train(Place.JAPAN);
        keyWord3.trainDone();
        assertTrue(keyWord3.isKeyWord());
        assertEquals(Place.UK,keyWord3.getKeyWordFor());

        KeyWord keyWord4 = new KeyWord("empty");
        keyWord4.train(Place.UK);
        keyWord4.train(Place.UK);
        keyWord4.train(Place.USA);
        keyWord4.train(Place.JAPAN);
        keyWord4.trainDone();
        assertFalse(keyWord4.isKeyWord());
        assertNull(keyWord4.getKeyWordFor());



    }
}