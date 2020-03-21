import data.Article;
import featuresModels.FeatureExtractor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Work<T extends Enum<T>> {
    private final static Pair<Double, Double> NORMALIZE_TO_INTERVAL = new ImmutablePair<>(0.0, 1.0);

    public Map<Article<T>, List<Double>> trainKNN(List<Article<T>> articles, List<FeatureExtractor<T>> featureExtractors) {
        HashMap<Article<T>, List<Double>> articlesPosition = new HashMap<>();

        for (Article<T> article : articles) {
            List<Double> position = new ArrayList<>();
            for (FeatureExtractor<T> featureExtractor : featureExtractors) {
                position.add(featureExtractor.extract(article));
            }
            articlesPosition.put(article, position);
        }
        return articlesPosition;
    }

    public List<Pair<Double,Double>> getMinMaxOfTrainSet(Map<Article<T>, List<Double>> trainSet) {
        List<Pair<Double, Double>> minMaxList = new ArrayList<>();
        for (int i = 0; i < trainSet.values().iterator().next().size(); i++) {
            minMaxList.add(getMinMax(trainSet, i));
        }
        return minMaxList;
    }

    public Pair<Double, Double> getMinMax(Map<Article<T>, List<Double>> data, int indexOfFeatureToGetMinMax) {
        double min = data.entrySet().iterator().next().getValue().get(indexOfFeatureToGetMinMax);

        double max = min;
        double temp;
        for (Map.Entry<Article<T>, List<Double>> articleListEntry : data.entrySet()) {
            temp = articleListEntry.getValue().get(indexOfFeatureToGetMinMax);
            if (temp < min) {
                min = temp;
            } else if (temp > max) {
                max = temp;
            }
        }
        return new ImmutablePair<>(min, max);
    }

    public Map<Article<T>, List<Double>> normalize(Map<Article<T>, List<Double>> dataToNormalize, List<Pair<Double, Double>> minMaxList) {
        Map<Article<T>, List<Double>> normalizedData = new HashMap<>();

        for (Map.Entry<Article<T>, List<Double>> articleListEntry : dataToNormalize.entrySet()) {
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
        if (valueToNormalize > beginInterval.getRight()) {
            return endInterval.getRight();
        }
        if (valueToNormalize < beginInterval.getLeft()) {
            return endInterval.getLeft();
        }
        return ((valueToNormalize - beginInterval.getLeft()) / (beginInterval.getRight() - beginInterval.getLeft()))
                * (endInterval.getRight() - endInterval.getLeft()) + endInterval.getLeft();
    }
}
