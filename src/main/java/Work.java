import data.Article;
import featuresModels.FeatureExtractor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Work {
    private final static Pair<Double, Double> NORMALIZE_TO_INTERVAL = new ImmutablePair<>(0.0, 1.0);

    public Map<Article, List<Double>> trainKNN(List<Article> articles, List<FeatureExtractor> featureExtractors) {
        HashMap<Article, List<Double>> articlesPosition = new HashMap<>();

        for (Article article : articles) {
            List<Double> position = new ArrayList<>();
            for (FeatureExtractor featureExtractor : featureExtractors) {
                position.add(featureExtractor.extract(article));
            }
            articlesPosition.put(article, position);
        }
        return articlesPosition;
    }

    public Pair<Double, Double> getMinMax(Map<Article, List<Double>> data, int indexOfFeatureToGetMinMax) {
        double min = data.entrySet().iterator().next().getValue().get(indexOfFeatureToGetMinMax);

        double max = min;
        double temp;
        for (Map.Entry<Article, List<Double>> articleListEntry : data.entrySet()) {
            temp = articleListEntry.getValue().get(indexOfFeatureToGetMinMax);
            if (temp < min) {
                min = temp;
            } else if (temp > max) {
                max = temp;
            }
        }
        return new ImmutablePair<>(min, max);
    }

    public Map<Article, List<Double>> normalize(Map<Article, List<Double>> dataToNormalize) {
        List<Pair<Double, Double>> minMaxList = new ArrayList<>();
        for (int i = 0; i < dataToNormalize.values().iterator().next().size(); i++) {
            minMaxList.add(getMinMax(dataToNormalize, i));
        }

        Map<Article, List<Double>> normalizedData = new HashMap<>();

        for (Map.Entry<Article, List<Double>> articleListEntry : dataToNormalize.entrySet()) {
            List<Double> normalizedValuesOfFeatures = new ArrayList<>();
            List<Double> valuesToNormalized = articleListEntry.getValue();
            for (int i = 0; i < valuesToNormalized.size(); i++) {
                normalizedValuesOfFeatures.add(normalizationMinMax(valuesToNormalized.get(i), minMaxList.get(i), NORMALIZE_TO_INTERVAL));
            }
            normalizedData.put(articleListEntry.getKey(), normalizedValuesOfFeatures);
        }
        return normalizedData;
    }

    public double normalizationMinMax(double valueToNormalize, Pair<Double, Double> beginInterval, Pair<Double, Double> endInterval) {
        return ((valueToNormalize - beginInterval.getLeft()) / (beginInterval.getRight() - beginInterval.getLeft()))
                * (endInterval.getRight() - endInterval.getLeft()) + endInterval.getLeft();
    }
}
