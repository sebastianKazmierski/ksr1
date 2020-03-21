package featuresModels;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfWordsRemoveByStopListInRelationToLengthOfTextAfterStopListTest {

    @Test
    void extract() {
        String content = "  Alice has a cat whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        Article<Place> article = new Article<>(content, Place.UK);
        FeatureExtractor featureExtractor = new NumberOfWordsRemoveByStopListInRelationToLengthOfTextAfterStopList();
        assertEquals(7.0 / 13, featureExtractor.extract(article), "0.001");
    }
}