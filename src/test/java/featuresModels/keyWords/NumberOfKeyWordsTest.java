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
    WordHolder<Place> wordHolder;

    @BeforeEach
    public void init() {
        Mockito.when(this.wordHolder.getKeyWord(anyString())).thenAnswer(
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

        NumberOfKeyWords<Place> numberOfKeyWords = new NumberOfKeyWords<>(Place.class);
        assertEquals(placeToOccurrenceMap, numberOfKeyWords.count(contentTokensAfterStemming, this.wordHolder));
    }

    @Test
    void countAllKeyWords() {
        List<String> contentTokensAfterStemming = List.of("work", "work", "empty", "nothing", "something", ".", "...", "computer", "computer", "sister", "paper", "mouse", "bottle");

        NumberOfKeyWords<Place> numberOfKeyWords = new NumberOfKeyWords<>(Place.class);
        assertEquals(8, numberOfKeyWords.countAllKeyWords(contentTokensAfterStemming, this.wordHolder));
    }

    public Word<Place> getKeyWord(String word) {
        Map<String, Word<Place>> keyWords = new HashMap<String, Word<Place>>();

        List<String> words = List.of("work", "computer", "sister", "paper", "mouse", "bottle");
        List<Place> places = List.of(Place.FRANCE, Place.UK, Place.UK, Place.USA, Place.JAPAN, Place.USA);

        for (int i = 0; i < words.size(); i++) {
            Word<Place> keyWord = new Word<Place>(words.get(i),Place.class);
            keyWord.train(places.get(i));
            keyWord.trainDone();
            keyWords.put(keyWord.getWord(), keyWord);
        }

        Word<Place> noWord = new Word<Place>("empty",Place.class);
        noWord.trainDone();
        keyWords.put(noWord.getWord(), noWord);
        return keyWords.get(word);
    }
}
