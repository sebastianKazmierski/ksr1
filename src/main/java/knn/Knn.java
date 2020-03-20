package knn;

import data.Article;
import distanceMetrics.DistanceMeasurement;
import grouping.Place;

import java.util.*;
import java.util.stream.Collectors;

public class Knn {
    Map<Article, List<Double>> articlesPosition;

    public Knn(Map<Article, List<Double>> articlesPosition) {
        this.articlesPosition = articlesPosition;
    }

    public Place getResult(List<Double> testElementPosition, DistanceMeasurement distanceMeasurement, int numberOfNeighbours) {
        HashMap<Article, Double> distanceMap = new HashMap<>();

        for (Map.Entry<Article, List<Double>> articleListEntry : articlesPosition.entrySet()) {
            double distance = distanceMeasurement.count(testElementPosition, articleListEntry.getValue());
            distanceMap.put(articleListEntry.getKey(), distance);
        }

        final LinkedHashMap<Article, Double> sortedByCount = distanceMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Set<Map.Entry<Article, Double>> neighboursSortedByCountEntries = sortedByCount.entrySet();
        int checkNumberOfNeighbours = Math.min(neighboursSortedByCountEntries.size(), numberOfNeighbours);

        List<Article> nearestNeighbours = new ArrayList<>();
        sortedByCount.entrySet().stream().limit(checkNumberOfNeighbours).forEach(articleDoubleEntry -> {
            nearestNeighbours.add(articleDoubleEntry.getKey());
        });

        HashMap<Place, Integer> placeOfNeighbours = new HashMap<>();
        for (Article nearestNeighbour : nearestNeighbours) {
            if (placeOfNeighbours.containsKey(nearestNeighbour.getPlace())) {
                placeOfNeighbours.put(nearestNeighbour.getPlace(), placeOfNeighbours.get(nearestNeighbour.getPlace()) + 1);
            } else {
                placeOfNeighbours.put(nearestNeighbour.getPlace(), 1);
            }
        }

        Place placeToReturn = null;
        int max = -1;

        for (Map.Entry<Place, Integer> place : placeOfNeighbours.entrySet()) {
            if (max < place.getValue()) {
                placeToReturn = place.getKey();
                max = place.getValue();
            }
        }

        return placeToReturn;
    }
}
