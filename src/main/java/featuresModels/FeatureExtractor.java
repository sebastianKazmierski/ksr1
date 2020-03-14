package featuresModels;

import data.Article;

@FunctionalInterface
public interface FeatureExtractor {
    double extract(Article article);
}
