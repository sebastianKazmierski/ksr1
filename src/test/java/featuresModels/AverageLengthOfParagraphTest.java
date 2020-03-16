package featuresModels;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AverageLengthOfParagraphTest {

    @Test
    void extract() {
        String content = "It said the  26,272\n" +
                "    Thailand has shipped 3.43 mln\n" +
                "date, down from \n" +
                "    Chase, a property \n" +
                "    It said agreements\n";
        Article article = new Article(content, Place.UK);
        FeatureExtractor featureExtractor = new AverageLengthOfParagraph();
        assertEquals(18.0 / 4, featureExtractor.extract(article));
    }
}