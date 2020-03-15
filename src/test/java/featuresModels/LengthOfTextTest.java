package featuresModels;

import data.Article;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LengthOfTextTest {

    @Test
    void extract() {
        String contetn1 = "  Alice HAS A cat, whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        String contetn2 = "    It is this.";
        String contetn3 = "  There is a lot ...\nAlice has a dog!!! Whose is this cat?!";
        Article article1 = new Article(contetn1, List.of("USA"));
        Article article2 = new Article(contetn2, List.of("USA"));
        Article article3 = new Article(contetn3, List.of("USA"));
        FeatureExtractor featureExtractor = new LengthOfText();
        assertEquals(13,featureExtractor.extract(article1),"0,.001");
        assertEquals(0,featureExtractor.extract(article2),"0,.001");
        assertEquals(5,featureExtractor.extract(article3),"0,.001");

    }
}