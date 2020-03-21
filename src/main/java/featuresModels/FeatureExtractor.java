package featuresModels;

import data.Article;
import interfaceModule.ElementSelectedByUser;


public interface FeatureExtractor extends ElementSelectedByUser {
    double extract(Article article);

    default String wordToInsertIntoQuestionAboutThisObject () {
        return "cechę";
    }
}
