package featuresModels;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfProperNameInRelationToLengthOfTextTest {

    @Test
    void extract() {
        String contetn = "  Alice has a cat whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        Article<Place> article = new Article<>(contetn, Place.UK);
        FeatureExtractor<Place> featureExtractor = new NumberOfProperNameInRelationToLengthOfText<>();
        assertEquals(8.0/article.getContentTokens().size(),featureExtractor.extract(article) , "0.0001");

    }
}