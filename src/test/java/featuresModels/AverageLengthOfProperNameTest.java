package featuresModels;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageLengthOfProperNameTest {

    @Test
    void extract() {
        String contetn = "  Alice has a cat whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        Article article = new Article(contetn, Place.UK );
        FeatureExtractor featureExtractor = new AverageLengthOfProperName();
        assertEquals((double)("Bob".length() + "Alice".length() + "Eiffel".length() + "Tower".length())/4,featureExtractor.extract(article) , "0.0001");
    }
}