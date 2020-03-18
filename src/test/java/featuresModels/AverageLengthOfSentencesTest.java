package featuresModels;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AverageLengthOfSentencesTest {

    @Test
    void extract() {
        String content = "  Alice has a cat, whose name is Bob. Alice likes Bob!\nBob likes Alice. They are on the: Eiffel Tower. Are there... a lot of cats?";
        Article article = new Article(content, Place.UK);
        FeatureExtractor featureExtractor = new AverageLengthOfSentences();
        assertEquals((5+3+3+2+2)/5.0,featureExtractor.extract(article));
    }
}