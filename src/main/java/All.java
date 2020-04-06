import changeSettings.*;
import data.Article;
import data.ArticleStore;
import distanceMetrics.*;
import featuresModels.*;
import featuresModels.keyWords.WordHolder;
import featuresModels.keyWords.WordHolderProvider;
import grouping.Label;
import interfaceModule.UserInterface;
import knn.Knn;
import loadData.FileOpener;
import loadData.XmlParser;
import loadData.articleCratorsFromXml.ArticleReader;
import loadData.dataValidators.DataValidator;
import loadData.dataValidators.InvalidFilesException;
import loadData.filesTransformer.FileTransformer;
import loadData.tagsFilter.TagFilter;
import org.apache.commons.lang3.tuple.Pair;
import other.CaseDescription;
import other.Result;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class All<T extends Label<T>> {
    UserInterface<T> userInterface;
    private List<FeatureExtractor<T>> availableFeatureExtractors;
    private List<DistanceMeasurement> availableDistanceMeasurements;
    private Class<T> tClass;
    T[] enumConstants;
    private FileTransformer fileValidator;
    private XmlParser xmlParser;
    private DataValidator dataValidator;

    Work<T> work;

    // zmieniane jeżeli zmieniamy etykiete według której grupujemy
    private TagFilter tagFilter;
    private ArticleReader articleReader;

    // potrzebny jest plik z podziałem na testowy i treningowy
    private String fileName;
    private ArticleStore<T> articleStore;
    private WordHolderProvider<T> wordHolderProvider;

    //potrzebne sa cechy
    private List<FeatureExtractor<T>> featureExtractorList;
    List<Pair<Double, Double>> minMaxOfTrainSet;
    private Knn<T> knn;

    // potrzebny jest miara
    private DistanceMeasurement distanceMeasurement;
    //potrzebna liczba sasiadów
    private int numberOfNeighbours;

    //tutaj jest testowanie które zawsze się zmienia

    private boolean mustRebuildKnn;
    private boolean mustReadData;


    public All(UserInterface<T> userInterface, Class<T> tClass, FileTransformer fileValidator, XmlParser xmlParser, DataValidator dataValidator, TagFilter tagFilter, ArticleReader articleReader) {
        this.fileValidator = fileValidator;
        this.xmlParser = xmlParser;
        this.dataValidator = dataValidator;
        this.tagFilter = tagFilter;
        this.articleReader = articleReader;
        this.tClass = tClass;
        this.enumConstants = tClass.getEnumConstants();

        this.work = new Work<>();
        this.userInterface = userInterface;
        this.availableDistanceMeasurements = getListOfAvailableDistanceMeasurement();
        this.wordHolderProvider = new WordHolderProvider<>();
        this.availableFeatureExtractors = getListOfAvailableFeatureExtractors();
        this.mustRebuildKnn = true;
        this.mustReadData = true;
    }

    private void setFileWithDataSplit() {
        this.fileName = this.userInterface.getFileWithDataSplit();
        this.mustReadData = true;
        this.mustRebuildKnn = true;
    }

    private void setFeatureExtractors() {
        this.featureExtractorList = this.userInterface.getFeatureExtractors(this.availableFeatureExtractors);
        this.mustRebuildKnn = true;
    }

    private void setDistanceMeasurement() {
        this.distanceMeasurement = this.userInterface.getDistanceMeasurement(this.availableDistanceMeasurements);
    }

    private void setNumberOfNeighbours() {
        this.numberOfNeighbours = this.userInterface.getNumberOfNeighbours();
    }

    private void readData() {
        if (this.mustReadData) {
            this.articleStore = readArticles(this.fileValidator, this.xmlParser, this.dataValidator, this.tagFilter, this.articleReader, this.fileName);
            this.wordHolderProvider.setWordHolder(createSetOfKeyWord(this.articleStore));
            this.mustReadData = false;
        }
    }

    private Map<Article<T>, List<Double>> normalizeTrainData() {
        Map<Article<T>, List<Double>> trainSetFeatures = this.work.trainKNN(this.articleStore.getTrainSet(), this.featureExtractorList);
        this.minMaxOfTrainSet = this.work.getMinMaxOfTrainSet(trainSetFeatures);
        return this.work.normalize(trainSetFeatures, this.minMaxOfTrainSet);
    }

    private void train() {
        readData();
        if (this.mustRebuildKnn) {
            this.knn = new Knn<>(normalizeTrainData());
            this.mustRebuildKnn = false;
        }
    }

    public boolean work() {
        setFileWithDataSplit();
        setFeatureExtractors();
        setDistanceMeasurement();
        setNumberOfNeighbours();
        //  this.userInterface.displayResult(test());
        this.userInterface.displayResult(prepareResult());
        do {
            List<ChangeSettings> changeSettingsList = Arrays.asList(new ChangeAllSettings(),
                    new ChangeFeatureExtractors(), new ChangeDistanceMeasurement(), new ChangeNumberOfNeighbours(),
                    new ChangeSplitData(), new CreateSequence(), new ChangeLabel(), new StopProgram());
            ChangeSettingsType changeSettings = this.userInterface.getChangeSettings(changeSettingsList);

            Boolean x = changeSettings(changeSettings);
            if (x != null) return x;
            prepareResult();
            this.userInterface.displayResult(prepareResult());
        } while (true);
    }

    private CaseDescription<T> prepareResult() {
        train();
        return new CaseDescription<>(this.tClass, this.fileName, this.featureExtractorList, this.distanceMeasurement, this.numberOfNeighbours, test());
//        this.userInterface.displayResultInColumn(caseDescription);
    }

    private Boolean changeSettings(ChangeSettingsType changeSettings) {
        switch (changeSettings) {
            case ALL_SETTINGS:
                setFileWithDataSplit();
                setFeatureExtractors();
                setDistanceMeasurement();
                setNumberOfNeighbours();
                break;
            case FEATURE_EXTRACTORS_SETTINGS:
                setFeatureExtractors();
                break;
            case DISTANCE_MEASUREMENT_SETTINGS:
                setDistanceMeasurement();
                break;
            case NUMBER_OF_NEIGHBOURS_SETTINGS:
                setNumberOfNeighbours();
                break;
            case DATA_SPLIT:
                setFileWithDataSplit();
                break;
            case CREATE_SEQUENCE:
                createSequence();
                break;
            case LABEL_SETTINGS:
                return true;
            case STOP_PROGRAM:
                return false;
        }
        return null;
    }

    private void createSequence() {
        List<ChangeSettings> changeSettingsList = Arrays.asList(new ChangeSplitData(), new ChangeFeatureExtractors(),
                new ChangeDistanceMeasurement(), new ChangeNumberOfNeighbours());
        ChangeSettingsType changeSettings = this.userInterface.getChangeSettings(changeSettingsList);
        int numberOfSequence = this.userInterface.getNumberOfSequence();
        List<CaseDescription<T>> result = new ArrayList<>();
        switch (changeSettings) {
            case DATA_SPLIT:
                List<String> fileNames = new ArrayList<>();
                for (int i = 0; i < numberOfSequence; i++) {
                    fileNames.add(this.userInterface.getFileWithDataSplit());
                }
                for (String fileName : fileNames) {
                    this.fileName = fileName;
                    this.mustReadData = true;
                    this.mustRebuildKnn = true;
                    result.add(prepareResult());
                    System.out.println("work");
                }
                break;
            case FEATURE_EXTRACTORS_SETTINGS:
                List<List<FeatureExtractor<T>>> featureExtractorList = new ArrayList<>();
                for (int i = 0; i < numberOfSequence; i++) {
                    featureExtractorList.add(this.userInterface.getFeatureExtractors(this.availableFeatureExtractors));
                }
                for (List<FeatureExtractor<T>> featureExtractor : featureExtractorList) {
                    this.featureExtractorList = featureExtractor;
                    this.mustRebuildKnn = true;
                    result.add(prepareResult());
                    System.out.println("work");
                }
                break;
            case DISTANCE_MEASUREMENT_SETTINGS:
                List<DistanceMeasurement> distanceMeasurements = new ArrayList<>();
                for (int i = 0; i < numberOfSequence; i++) {
                    distanceMeasurements.add(this.userInterface.getDistanceMeasurement(this.availableDistanceMeasurements));
                }
                for (DistanceMeasurement distanceMeasurement : distanceMeasurements) {
                    this.distanceMeasurement = distanceMeasurement;
                    result.add(prepareResult());
                    System.out.println("work");
                }
                break;
            case NUMBER_OF_NEIGHBOURS_SETTINGS:
                List<Integer> numbersOfNeighbours = new ArrayList<>();
                for (int i = 0; i < numberOfSequence; i++) {
                    numbersOfNeighbours.add(this.userInterface.getNumberOfNeighbours());
                }
                for (Integer numberOfNeighbours : numbersOfNeighbours) {
                    this.numberOfNeighbours = numberOfNeighbours;
                    result.add(prepareResult());
                    System.out.println("work");
                }
                break;
        }
        try {
            this.userInterface.displayResultInColumn1(result, changeSettings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Result<T> test() {
        Map<Article<T>, List<Double>> testSetFeatures = this.work.trainKNN(this.articleStore.getTestSet(), this.featureExtractorList);
        Map<Article<T>, List<Double>> testSetFeaturesAfterNormalization = this.work.normalize(testSetFeatures, this.minMaxOfTrainSet);

        Result<T> result = new Result<>(this.tClass);
        for (Map.Entry<Article<T>, List<Double>> articleListEntry : testSetFeaturesAfterNormalization.entrySet()) {
            T label = this.knn.getResult(articleListEntry.getValue(), this.distanceMeasurement, this.numberOfNeighbours);
            result.addResult(articleListEntry.getKey().getLabel(), label);
        }
        return result;
    }

    private WordHolder<T> createSetOfKeyWord(ArticleStore<T> articleStore) {
        WordHolder<T> wordHolder = new WordHolder<T>(this.tClass);
        wordHolder.train(articleStore.getTrainSet());
        wordHolder.trainDone();
        return wordHolder;
    }

    private List<FeatureExtractor<T>> getListOfAvailableFeatureExtractors() {
        List<FeatureExtractor<T>> featureExtractorList = new ArrayList<>();

        featureExtractorList.add(new AverageLengthOfParagraph<>(new NumberOfParagraphsInRelationToLengthOfText<T>(new LengthOfText<>())));
        featureExtractorList.add(new AverageLengthOfProperName<>());
        featureExtractorList.add(new AverageLengthOfSentences<>());
        featureExtractorList.add(new LengthOfText<>());
        featureExtractorList.add(new NumberOfKeyWordsInTenFirstPercentOfText<T>(this.wordHolderProvider, this.tClass));
        featureExtractorList.add(new NumberOfKeyWordsInWholeText<T>(this.wordHolderProvider, this.tClass));
        featureExtractorList.add(new NumberOfParagraphsInRelationToLengthOfText<>(new LengthOfText<T>()));
        featureExtractorList.add(new NumberOfProperNameInRelationToLengthOfText<>());
        featureExtractorList.add(new NumberOfUniqueKeyWordsInRelationToLengthOfText<T>(this.wordHolderProvider, this.tClass, new LengthOfText<T>()));
        featureExtractorList.add(new NumberOfWordsRemoveByStopListInRelationToLengthOfTextAfterStopList<>());
        featureExtractorList.add(new NumberOfWordsWhichAreMultipleTimesInTextInRelationToLengthOfText<>());

        for (T label : this.enumConstants) {
            featureExtractorList.add(new NumberOfKeyWordsInLabel<T>(label, this.wordHolderProvider, this.tClass));
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

    private ArticleStore<T> readArticles(FileTransformer fileValidator, XmlParser xmlParser, DataValidator dataValidator, TagFilter tagFilter, ArticleReader articleReader, String fileWithSplitName) {
        ArticleStore<T> articleStore = new ArticleStore<>(fileWithSplitName);
        List<Path> paths;
        try {
            paths = getPaths(dataValidator);
            for (Path path : paths) {
                try {
                    CharBuffer charBuffer = fileValidator.transform(path);
                    xmlParser.readArticles(charBuffer, articleStore, articleReader, tagFilter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvalidFilesException e) {
            e.printStackTrace();
        }
        return articleStore;
    }

    private List<Path> getPaths(DataValidator dataValidator) throws InvalidFilesException {
        FileOpener fileOpener = new FileOpener();
        return fileOpener.loadArticlesFromDirectory(dataValidator);
    }
}

