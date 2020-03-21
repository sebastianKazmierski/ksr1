package featuresModels;

import data.Article;
import featuresModels.keyWords.Word;
import featuresModels.keyWords.WordHolder;
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
class NumberOfKeyWordsInLabelTest {
    @Mock
    WordHolder<Place> wordHolder;

    @BeforeEach
    public void init() {
        Mockito.when(this.wordHolder.getKeyWord(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> getKeyWord((String) invocation.getArguments()[0]));
    }

    @Test
    void extract() {
        String content = "work work blank nothing something error sister pc sister sister paper ";
        String contentDuplicatedTenTimes = duplicateTenTimes(content);

        Article<Place> article = new Article<>(contentDuplicatedTenTimes, Place.UK);

        FeatureExtractor<Place> numberOfKeyWordsInTenFirstPercentOfTextFr = new NumberOfKeyWordsInLabel<Place>(Place.FRANCE, this.wordHolder,Place.class);
        FeatureExtractor<Place> numberOfKeyWordsInTenFirstPercentOfTextUk = new NumberOfKeyWordsInLabel<Place>(Place.UK, this.wordHolder,Place.class);
        FeatureExtractor<Place> numberOfKeyWordsInTenFirstPercentOfTextUsa = new NumberOfKeyWordsInLabel<Place>(Place.USA, this.wordHolder,Place.class);
        FeatureExtractor<Place> numberOfKeyWordsInTenFirstPercentOfTextJa = new NumberOfKeyWordsInLabel<Place>(Place.JAPAN, this.wordHolder,Place.class);
        FeatureExtractor<Place> numberOfKeyWordsInTenFirstPercentOfTextCa = new NumberOfKeyWordsInLabel<Place>(Place.CANADA, this.wordHolder,Place.class);

        assertEquals(2, numberOfKeyWordsInTenFirstPercentOfTextFr.extract(article),"0.001");
        assertEquals(3, numberOfKeyWordsInTenFirstPercentOfTextUk.extract(article),"0.001");
        assertEquals(1, numberOfKeyWordsInTenFirstPercentOfTextUsa.extract(article),"0.001");
        assertEquals(0, numberOfKeyWordsInTenFirstPercentOfTextJa.extract(article),"0.001");
        assertEquals(0, numberOfKeyWordsInTenFirstPercentOfTextCa.extract(article),"0.001");
    }

    private String duplicateTenTimes(String content) {
        String contentDuplicatedTenTimes = content;
        for (int i = 0; i < 9; i++) {
            contentDuplicatedTenTimes = contentDuplicatedTenTimes.concat(content);
        }
        return contentDuplicatedTenTimes;
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

        Word<Place> noWord = new Word<Place>("blank",Place.class);
        noWord.trainDone();
        keyWords.put(noWord.getWord(), noWord);
        return keyWords.get(word);
    }
}