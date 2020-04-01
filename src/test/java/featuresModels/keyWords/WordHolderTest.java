package featuresModels.keyWords;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordHolderTest {


    @Test
    void train() {
        String content1 = "work work blank nothing something error sister pc sister sister work work work work work work work work ";
        String content2 = "sister! pc sister! sister? sister? sister! sister... sister pc sister sister ";
        String content3 = "blank blank blank, blank. blank blank pc sister sister hello ";

        Article<Place> article1 = new Article<>(content1, Place.UK);
        Article<Place> article2 = new Article<>(content2, Place.USA);
        Article<Place> article3 = new Article<>(content3, Place.CANADA);

        WordHolder<Place> wordHolder = new WordHolder<>(Place.class);
        wordHolder.train(List.of(article1, article2, article3));
        wordHolder.trainDone();

        assertTrue(wordHolder.isReady());
        assertTrue(wordHolder.getKeyWord("sister").isReady());
        assertTrue(wordHolder.getKeyWord("sister").isKeyWord());

        assertTrue(wordHolder.getKeyWord("sister").isReady());
        assertFalse(wordHolder.getKeyWord("pc").isKeyWord());

        assertEquals(Place.USA, wordHolder.getKeyWord("sister").getKeyWordFor());
        assertEquals(Place.UK, wordHolder.getKeyWord("work").getKeyWordFor());
        assertEquals(Place.CANADA, wordHolder.getKeyWord("blank").getKeyWordFor());
        assertNull(wordHolder.getKeyWord("pc").getKeyWordFor());
    }

}