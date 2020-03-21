import data.Article;
import data.ArticleStore;
import distanceMetrics.*;
import featuresModels.*;
import featuresModels.keyWords.WordHolder;
import interfaceModule.UserInterface;
import knn.Knn;
import loadData.FileOpener;
import loadData.XmlParser;
import loadData.articleCratorsFromXml.ArticleReader;
import loadData.dataValidators.DataValidator;
import loadData.dataValidators.InvalidFilesException;
import loadData.filesTransformer.FileTransformer;
import loadData.tagsFilter.TagFilter;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class All<T extends Enum<T>> {
    UserInterface<T> userInterface;
    private List<FeatureExtractor<T>> availableFeatureExtractors;
    private List<DistanceMeasurement> availableDistanceMeasurements;
    private Class<T> tClass;
    T[] enumConstants;

    Work<T> work;

    // zmieniane jeżeli zmieniamy etykiete według której grupujemy
    private TagFilter tagFilter;
    private ArticleReader articleReader;

    // potrzebny jest plik z podziałem na testowy i treningowy
    private ArticleStore<T> articleStore;
    private WordHolder<T> wordHolder;

    //potrzebne sa cechy
    private List<FeatureExtractor<T>> featureExtractorList;
    List<Pair<Double, Double>> minMaxOfTrainSet;
    private Knn<T> knn;

    // potrzebny jest miara
    private DistanceMeasurement distanceMeasurement;
    //potrzebna liczba sasiadów
    private int numberOfNeighbours;

    //tutaj jest testowanie które zawsze się zmienia


    public All(UserInterface<T> userInterface, Class<T> tClass, FileTransformer fileValidator, XmlParser xmlParser,DataValidator dataValidator, TagFilter tagFilter, ArticleReader articleReader) {
        this.userInterface = userInterface;
        this.availableDistanceMeasurements = getListOfAvailableDistanceMeasurement();
        this.tClass = tClass;
        this.enumConstants = tClass.getEnumConstants();

        this.work = new Work<T>();
    }

    private void setFileWithDataSplit() {
        String fileName = this.userInterface.getFileWithDataSplit();
        this.articleStore = new ArticleStore<T>(fileName);
        this.wordHolder = createSetOfKeyWord(this.articleStore);
        this.availableFeatureExtractors = getListOfAvailableFeatureExtractors(this.wordHolder);
    }

    private void setFeatureExtractors() {
        this.featureExtractorList = this.userInterface.getFeatureExtractors(this.availableFeatureExtractors);
        this.knn = new Knn<T>(normalizeTrainData());
    }

    private void setDistanceMeasurement() {
        this.distanceMeasurement = this.userInterface.getDistanceMeasurement(this.availableDistanceMeasurements);
    }

    private void setNumberOfNeighbours() {
        this.numberOfNeighbours = this.userInterface.getNumberOfNeighbours();
    }

    private Map<Article<T>, List<Double>> normalizeTrainData() {
        Map<Article<T>, List<Double>> trainSetFeatures = this.work.trainKNN(this.articleStore.getTrainSet(), this.featureExtractorList);
        this.minMaxOfTrainSet = this.work.getMinMaxOfTrainSet(trainSetFeatures);
        return this.work.normalize(trainSetFeatures, this.minMaxOfTrainSet);
    }

    public boolean work() {
        setFileWithDataSplit();
        setFeatureExtractors();
        setDistanceMeasurement();
        setNumberOfNeighbours();
        test();

    }

    private Result test() {
        Map<Article<T>, List<Double>> testSetFeatures = this.work.trainKNN(this.articleStore.getTestSet(), this.featureExtractorList);
        Map<Article<T>, List<Double>> testSetFeaturesAfterNormalization = this.work.normalize(testSetFeatures, this.minMaxOfTrainSet);

        int correct =0;
        int incorrect =0;
        for (Map.Entry<Article<T>, List<Double>> articleListEntry : testSetFeaturesAfterNormalization.entrySet()) {
            T label = this.knn.getResult(articleListEntry.getValue(), this.distanceMeasurement, this.numberOfNeighbours);
            if (label == articleListEntry.getKey().getLabel()) {
                correct++;
            } else {
                incorrect++;
            }
        }
        return new Result(correct, incorrect);
    }

    private WordHolder<T> createSetOfKeyWord(ArticleStore<T> articleStore) {
        WordHolder<T> wordHolder = new WordHolder<T>(this.tClass);
        wordHolder.train(articleStore.getTrainSet());
        wordHolder.trainDone();
        return wordHolder;
    }

    private List<FeatureExtractor<T>> getListOfAvailableFeatureExtractors(WordHolder<T> wordHolder) {
        List<FeatureExtractor<T>> featureExtractorList = new ArrayList<>();

        featureExtractorList.add(new AverageLengthOfParagraph<>(new NumberOfParagraphsInRelationToLengthOfText<T>(new LengthOfText<>())));
        featureExtractorList.add(new AverageLengthOfProperName<>());
        featureExtractorList.add(new AverageLengthOfSentences<>());
        featureExtractorList.add(new LengthOfText<>());
        featureExtractorList.add(new NumberOfKeyWordsInTenFirstPercentOfText<T>(wordHolder, this.tClass));
        featureExtractorList.add(new NumberOfKeyWordsInWholeText<T>(wordHolder, this.tClass));
        featureExtractorList.add(new NumberOfParagraphsInRelationToLengthOfText<>(new LengthOfText<T>()));
        featureExtractorList.add(new NumberOfProperNameInRelationToLengthOfText<>());
        featureExtractorList.add(new NumberOfUniqueKeyWordsInRelationToLengthOfText<>(wordHolder, new LengthOfText<T>()));
        featureExtractorList.add(new NumberOfWordsRemoveByStopListInRelationToLengthOfTextAfterStopList<>());
        featureExtractorList.add(new NumberOfWordsWhichAreMultipleTimesInTextInRelationToLengthOfText<>());

        for (T label : this.enumConstants) {
            featureExtractorList.add(new NumberOfKeyWordsInLabel<>(label, wordHolder, this.tClass));
        }

        return featureExtractorList;
    }


    private List<DistanceMeasurement> getListOfAvailableDistanceMeasurement() {
        List<DistanceMeasurement> distanceMeasurements = new ArrayList<>();

        distanceMeasurements.add(new AverageMinimumMetric());
        distanceMeasurements.add(new CzebyszewMetric());
        distanceMeasurements.add(new EuclidesMetric());
        distanceMeasurements.add(new MinMaxMetric());
        distanceMeasurements.add(new StreetMetric());

        return distanceMeasurements;
    }

    private ArticleStore<T> readArticles(FileTransformer fileValidator, XmlParser xmlParser,DataValidator dataValidator, TagFilter tagFilter, ArticleReader articleReader, String fileWithSplitName) throws InvalidFilesException {
        ArticleStore<T> articleStore = new ArticleStore<>(fileWithSplitName);
        List<Path> paths = getPaths(dataValidator);
        for (Path path : paths) {
            try {
                CharBuffer charBuffer = fileValidator.transform(path);
                xmlParser.readArticles(charBuffer,articleStore,articleReader, tagFilter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return articleStore;
    }

    private List<Path> getPaths(DataValidator dataValidator) throws InvalidFilesException {
        FileOpener fileOpener = new FileOpener();
        return fileOpener.loadArticlesFromDirectory(dataValidator);
    }




}

