package featuresModels;

import data.Article;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfProperNameInRelationToContentLengthTest {

    @org.junit.jupiter.api.Test
    void extract() {
        String contetn = "  Alice has a cat whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        Article article = new Article(contetn, List.of("USA"));
        FeatureExtractor featureExtractor = new NumberOfProperNameInRelationToContentLength();
        assertEquals(featureExtractor.extract(article), 8.0/article.getContentTokens().size(), "0.0001");

    }
}