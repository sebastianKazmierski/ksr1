package featuresModels;

import data.Article;


public interface FeatureExtractor {
    double extract(Article article);

    String description();
}
