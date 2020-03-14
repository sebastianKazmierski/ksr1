package featuresModels;

import data.Article;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AverageLengthOfProperNameTest {

    @Test
    void extract() {
        String contetn = "  Alice has a cat whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        Article article = new Article(contetn, List.of("USA"));
        FeatureExtractor featureExtractor = new AverageLengthOfProperName();
        assertEquals(featureExtractor.extract(article), (double)("Bob".length() + "Alice".length() + "Eiffel".length() + "Tower".length())/4, "0.0001");
    }
}