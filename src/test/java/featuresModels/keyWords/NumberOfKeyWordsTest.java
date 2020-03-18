package featuresModels.keyWords;

import grouping.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class NumberOfKeyWordsTest {

    @Mock
    KeyWordHolder keyWordHolder;

    @BeforeEach
    public void init() {
        Mockito.when(keyWordHolder.getKeyWord(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> getKeyWord((String) invocation.getArguments()[0]));
    }

    @Test
    void count() {
        List<String> contentTokensAfterStemming = List.of("work", "work", "empty", "nothing", "something", ".", "...", "computer", "computer", "sister", "paper", "mouse", "bottle");

        HashMap<Place, Integer> placeToOccurrenceMap = new HashMap<>();
        for (Place place : Place.values()) {
            placeToOccurrenceMap.put(place, 0);
        }

        placeToOccurrenceMap.put(Place.UK, 3);
        placeToOccurrenceMap.put(Place.USA, 2);
        placeToOccurrenceMap.put(Place.FRANCE, 2);
        placeToOccurrenceMap.put(Place.JAPAN, 1);
        placeToOccurrenceMap.put(Place.CANADA, 0);
        placeToOccurrenceMap.put(Place.WEST_GERMANY, 0);

        assertEquals(placeToOccurrenceMap, NumberOfKeyWords.count(contentTokensAfterStemming, keyWordHolder));
    }

    @Test
    void countAllKeyWords() {
        List<String> contentTokensAfterStemming = List.of("work", "work", "empty", "nothing", "something", ".", "...", "computer", "computer", "sister", "paper", "mouse", "bottle");

        assertEquals(8, NumberOfKeyWords.countAllKeyWords(contentTokensAfterStemming, keyWordHolder));
    }

    public KeyWord getKeyWord(String word) {
        Map<String, KeyWord> keyWords = new HashMap<>();

        List<String> words = List.of("work", "computer", "sister", "paper", "mouse", "bottle");
        List<Place> places = List.of(Place.FRANCE, Place.UK, Place.UK, Place.USA, Place.JAPAN, Place.USA);

        for (int i = 0; i < words.size(); i++) {
            KeyWord keyWord = new KeyWord(words.get(i));
            keyWord.train(places.get(i));
            keyWord.trainDone();
            keyWords.put(keyWord.getWord(), keyWord);
        }

        KeyWord noKeyWord = new KeyWord("empty");
        noKeyWord.trainDone();
        keyWords.put(noKeyWord.getWord(), noKeyWord);
        return keyWords.get(word);
    }
}