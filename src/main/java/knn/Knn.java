package knn;

import distanceMetrics.DistanceMeasurement;
import data.Article;
import grouping.Place;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Knn {
    HashMap<Article, List<Double>> articlesPosition;

    public Knn(HashMap<Article, List<Double>> articlesPosition) {
        this.articlesPosition = articlesPosition;
    }

    public Place getResult(List<Double> testElementPosition, DistanceMeasurement distanceMeasurement, int numberOfNeighbours) {
        TreeMap<Double, Article> distanceMap = new TreeMap<>();

        articlesPosition.forEach((article, doubles) -> {
            double distance = distanceMeasurement.count(testElementPosition, doubles);
            distanceMap.put(distance, article);
        });

        List<Article> nearestNeighbours = distanceMap.values().stream().limit(numberOfNeighbours).collect(Collectors.toList());

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
