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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class NumberOfUniqueKeyWordsInRelationToLengthOfTextTest {
    @Mock
    WordHolder wordHolder;

    @BeforeEach
    public void init() {
        Mockito.when(wordHolder.getKeyWord(anyString())).thenAnswer(
                (InvocationOnMock invocation) -> getKeyWord((String) invocation.getArguments()[0]));
    }

    @Test
    void extract() {
        String content = "work work blank nothing something paper sister pc sister sister ";
        String contentDuplicatedTenTimes = duplicateTenTimes(content);

        Article article = new Article(contentDuplicatedTenTimes, Place.UK);

        LengthOfText lengthOfText = new LengthOfText();
        NumberOfUniqueKeyWordsInRelationToLengthOfText numberOfUniqueKeyWordsInRelationToLengthOfText = new NumberOfUniqueKeyWordsInRelationToLengthOfText(wordHolder,lengthOfText);

        assertEquals(3/lengthOfText.extract(article), numberOfUniqueKeyWordsInRelationToLengthOfText.extract(article),"0.001");


        String content2 = "Here is 0 key words... !!! ";

        Article article2 = new Article(content2, Place.UK);
        assertEquals(0/lengthOfText.extract(article), numberOfUniqueKeyWordsInRelationToLengthOfText.extract(article2),"0.001");
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