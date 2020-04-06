package featuresModels;

import data.Article;
import interfaceModule.ElementSelectedByUser;


public interface FeatureExtractor<T> extends ElementSelectedByUser {
    double extract(Article<T> article);

    default String wordToInsertIntoQuestionAboutThisObject () {
        return "cechę";
    }

    int getNumber();
}
