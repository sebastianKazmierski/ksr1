package featuresModels.keyWords;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeyWordHolderTest {


    @Test
    void train() {
        String content1 = "work work blank nothing something error sister pc sister sister work work work work work work work work ";
        String content2 = "sister! pc sister! sister? sister? sister! sister... sister pc sister sister ";
        String content3 = "blank blank blank, blank. blank blank pc sister sister hello ";

        Article article1 = new Article(content1, Place.UK);
        Article article2 = new Article(content2, Place.USA);
        Article article3 = new Article(content3, Place.CANADA);

        KeyWordHolder keyWordHolder = new KeyWordHolder();
        keyWordHolder.train(List.of(article1, article2, article3));
        keyWordHolder.trainDone();

        assertTrue(keyWordHolder.isReady());
        assertTrue(keyWordHolder.getKeyWord("sister").isReady());
        assertTrue(keyWordHolder.getKeyWord("sister").isKeyWord());

        assertTrue(keyWordHolder.getKeyWord("sister").isReady());
        assertFalse(keyWordHolder.getKeyWord("pc").isKeyWord());

        assertEquals(Place.USA,keyWordHolder.getKeyWord("sister").getKeyWordFor());
        assertEquals(Place.UK,keyWordHolder.getKeyWord("work").getKeyWordFor());
        assertEquals(Place.CANADA,keyWordHolder.getKeyWord("blank").getKeyWordFor());
        assertNull(keyWordHolder.getKeyWord("pc").getKeyWordFor());
    }

}