package featuresModels;

import all.Article;

@FunctionalInterface
public interface FeatureExtractor {
    double extract(Article article);
}
