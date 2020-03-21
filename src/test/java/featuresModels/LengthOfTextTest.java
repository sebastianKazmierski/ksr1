package featuresModels;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LengthOfTextTest {

    @Test
    void extract() {
        String contetn1 = "  Alice HAS A cat, whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        String contetn2 = "    It is this.";
        String contetn3 = "  There is a lot ...\nAlice has a dog!!! Whose is this cat?!";
        Article<Place> article1 = new Article<>(contetn1, Place.UK);
        Article<Place> article2 = new Article<>(contetn2, Place.UK);
        Article<Place> article3 = new Article<>(contetn3, Place.UK);
        FeatureExtractor featureExtractor = new LengthOfText();
        assertEquals(13,featureExtractor.extract(article1),"0,.001");
        assertEquals(0,featureExtractor.extract(article2),"0,.001");
        assertEquals(5,featureExtractor.extract(article3),"0,.001");

    }
}