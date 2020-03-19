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
class NumberOfKeyWordsInPlaceTest {
    @Mock
    WordHolder wordHolder;

    @BeforeEach
    public void init() {
        Mockito.when(wordHolder.getKeyWord(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> getKeyWord((String) invocation.getArguments()[0]));
    }

    @Test
    void extract() {
        String content = "work work blank nothing something error sister pc sister sister paper ";
        String contentDuplicatedTenTimes = duplicateTenTimes(content);

        Article article = new Article(contentDuplicatedTenTimes, Place.UK);

        FeatureExtractor numberOfKeyWordsInTenFirstPercentOfTextFr = new NumberOfKeyWordsInPlace(Place.FRANCE, wordHolder);
        FeatureExtractor numberOfKeyWordsInTenFirstPercentOfTextUk = new NumberOfKeyWordsInPlace(Place.UK, wordHolder);
        FeatureExtractor numberOfKeyWordsInTenFirstPercentOfTextUsa = new NumberOfKeyWordsInPlace(Place.USA, wordHolder);
        FeatureExtractor numberOfKeyWordsInTenFirstPercentOfTextJa = new NumberOfKeyWordsInPlace(Place.JAPAN, wordHolder);
        FeatureExtractor numberOfKeyWordsInTenFirstPercentOfTextCa = new NumberOfKeyWordsInPlace(Place.CANADA, wordHolder);

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

    public Word getKeyWord(String word) {
        Map<String, Word> keyWords = new HashMap<>();

        List<String> words = List.of("work", "computer", "sister", "paper", "mouse", "bottle");
        List<Place> places = List.of(Place.FRANCE, Place.UK, Place.UK, Place.USA, Place.JAPAN, Place.USA);

        for (int i = 0; i < words.size(); i++) {
            Word keyWord = new Word(words.get(i));
            keyWord.train(places.get(i));
            keyWord.trainDone();
            keyWords.put(keyWord.getWord(), keyWord);
        }

        Word noWord = new Word("blank");
        noWord.trainDone();
        keyWords.put(noWord.getWord(), noWord);
        return keyWords.get(word);
    }
}