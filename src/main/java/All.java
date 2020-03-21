import data.Article;
import data.ArticleStore;
import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;
import grouping.Place;
import interfaceModule.UserInterface;
import knn.Knn;
import loadData.articleCratorsFromXml.ArticleReader;
import loadData.articleCratorsFromXml.ArticleReaderWithPlaces;
import loadData.tagsFilter.BasePlaceFilter;
import loadData.tagsFilter.TagFilter;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public class All {
    UserInterface userInterface;
    private List<FeatureExtractor> availableFeatureExtractors;
    private List<DistanceMeasurement> availableDistanceMeasurements;
    Work work;

    // zmieniane jeżeli zmieniamy etykiete według której grupujemy
    private TagFilter tagFilter;
    private ArticleReader articleReader;

    // potrzebny jest plik z podziałem na testowy i treningowy
    private ArticleStore articleStore;

    //potrzebne sa cechy
    private List<FeatureExtractor> featureExtractorList;
    List<Pair<Double, Double>> minMaxOfTrainSet;
    private Knn knn;

    // potrzebny jest miara
    private DistanceMeasurement distanceMeasurement;
    //potrzebna liczba sasiadów
    private int numberOfNeighbours;

    //tutaj jest testowanie które zawsze się zmienia


    public All(UserInterface userInterface, List<FeatureExtractor> availableFeatureExtractors, List<DistanceMeasurement> availableDistanceMeasurements) {
        this.userInterface = userInterface;
        this.availableFeatureExtractors = availableFeatureExtractors;
        this.availableDistanceMeasurements = availableDistanceMeasurements;

        this.work = new Work();
    }

    private void setLabel() {
        String userChoice = this.userInterface.getLabel();
        if (userChoice.equals("p")) {
            this.tagFilter = new BasePlaceFilter();
            this.articleReader = new ArticleReaderWithPlaces();
        }
    }

    private void setFileWithDataSplit() {
        String fileName = this.userInterface.getFileWithDataSplit();
        this.articleStore = new ArticleStore(fileName);
    }

    private void setFeatureExtractors() {
        this.featureExtractorList = this.userInterface.getFeatureExtractors(this.availableFeatureExtractors);
        this.knn = new Knn(normalizeTrainData());
    }

    private void setDistanceMeasurement() {
        this.distanceMeasurement = this.userInterface.getDistanceMeasurement(this.availableDistanceMeasurements);
    }

    private void setNumberOfNeighbours() {
        this.numberOfNeighbours = this.userInterface.getNumberOfNeighbours();
    }

    private Map<Article, List<Double>> normalizeTrainData() {
        Map<Article, List<Double>> trainSetFeatures = this.work.trainKNN(this.articleStore.getTrainSet(), this.featureExtractorList);
        this.minMaxOfTrainSet = this.work.getMinMaxOfTrainSet(trainSetFeatures);
        return this.work.normalize(trainSetFeatures, this.minMaxOfTrainSet);
    }

    private Result test() {
        Map<Article, List<Double>> testSetFeatures = this.work.trainKNN(this.articleStore.getTestSet(), this.featureExtractorList);
        Map<Article, List<Double>> testSetFeaturesAfterNormalization = this.work.normalize(testSetFeatures, this.minMaxOfTrainSet);

        int correct =0;
        int incorrect =0;
        for (Map.Entry<Article, List<Double>> articleListEntry : testSetFeaturesAfterNormalization.entrySet()) {
            Place place = this.knn.getResult(articleListEntry.getValue(), this.distanceMeasurement, this.numberOfNeighbours);
            if (place == articleListEntry.getKey().getPlace()) {
                correct++;
            } else {
                incorrect++;
            }
        }
        return new Result(correct, incorrect);
    }


    class Result {
        @Getter
        int correct;
        @Getter
        int incorrect;

        public Result(int correct, int wrong) {
            this.correct = correct;
            this.incorrect = wrong;
        }
    }


}

