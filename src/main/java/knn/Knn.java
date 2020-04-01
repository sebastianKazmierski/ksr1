package knn;

import data.Article;
import distanceMetrics.DistanceMeasurement;

import java.util.*;
import java.util.stream.Collectors;

public class Knn<T> {
    Map<Article<T>, List<Double>> articlesPosition;

    public Knn(Map<Article<T>, List<Double>> articlesPosition) {
        this.articlesPosition = articlesPosition;
    }

    public T getResult(List<Double> testElementPosition, DistanceMeasurement distanceMeasurement, int numberOfNeighbours) {
        HashMap<Article<T>, Double> distanceMap = new HashMap<>();

        for (Map.Entry<Article<T>, List<Double>> articleListEntry : this.articlesPosition.entrySet()) {
            double distance = distanceMeasurement.count(testElementPosition, articleListEntry.getValue());
            distanceMap.put(articleListEntry.getKey(), distance);
        }

        final LinkedHashMap<Article<T>, Double> sortedByCount = distanceMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Set<Map.Entry<Article<T>, Double>> neighboursSortedByCountEntries = sortedByCount.entrySet();
        int checkNumberOfNeighbours = Math.min(neighboursSortedByCountEntries.size(), numberOfNeighbours);

        List<Article<T>> nearestNeighbours = new ArrayList<>();
        sortedByCount.entrySet().stream().limit(checkNumberOfNeighbours).forEach(articleDoubleEntry -> {
            nearestNeighbours.add(articleDoubleEntry.getKey());
        });

        HashMap<T, Integer> placeOfNeighbours = new HashMap<>();
        for (Article<T> nearestNeighbour : nearestNeighbours) {
            if (placeOfNeighbours.containsKey(nearestNeighbour.getLabel())) {
                placeOfNeighbours.put(nearestNeighbour.getLabel(), placeOfNeighbours.get(nearestNeighbour.getLabel()) + 1);
            } else {
                placeOfNeighbours.put(nearestNeighbour.getLabel(), 1);
            }
        }

        T placeToReturn = null;
        int max = -1;

        for (Map.Entry<T, Integer> place : placeOfNeighbours.entrySet()) {
            if (max < place.getValue()) {
                placeToReturn = place.getKey();
                max = place.getValue();
            }
        }

        return placeToReturn;
    }
}
