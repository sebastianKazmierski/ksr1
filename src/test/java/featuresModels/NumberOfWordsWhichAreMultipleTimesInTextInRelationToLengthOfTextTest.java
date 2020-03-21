package featuresModels;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfWordsWhichAreMultipleTimesInTextInRelationToLengthOfTextTest {

    @Test
    void extract() {
        String content = "  Alice has a cat whose name is Bob. Alice like Bob.\nBob likes Alice. They are on the Eiffel Tower. There is a lot of cats. Walking walked";
        Article<Place> article = new Article<>(content, Place.UK);
        FeatureExtractor<Place> featureExtractor = new NumberOfWordsWhichAreMultipleTimesInTextInRelationToLengthOfText<>();
        assertEquals(5.0 / 17, featureExtractor.extract(article), "0.001");
    }

}