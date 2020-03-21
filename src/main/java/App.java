import data.Article;
import data.ArticleStore;
import distanceMetrics.*;
import featuresModels.*;
import featuresModels.keyWords.WordHolder;
import grouping.Place;
import interfaceModule.ConsoleInterface;
import knn.Knn;
import loadData.FileOpener;
import loadData.XmlParser;
import loadData.articleCratorsFromXml.ArticleReader;
import loadData.articleCratorsFromXml.ArticleReaderWithPlaces;
import loadData.dataValidators.AllDataValidator;
import loadData.dataValidators.DataValidator;
import loadData.dataValidators.InvalidFilesException;
import loadData.filesTransformer.FileTransformer;
import loadData.filesTransformer.XmlTransformer;
import loadData.tagsFilter.BasePlaceFilter;
import loadData.tagsFilter.TagFilter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws InvalidFilesException {






        //nic tu nie jest potrzebne nigdy to nie jest powtarzane
        ConsoleInterface consoleInterface = new ConsoleInterface();
        getListOfAvailableFeatureExtractors(wordHolder)


        ArticleStore articleStore = readArticles(fileValidator, xmlParser, paths, tagFilter, articleReader, fileWithSplitName);

        WordHolder wordHolder = new WordHolder();
        createSetOfKeyWord(articleStore, wordHolder);




        FileTransformer fileValidator = new XmlTransformer();
        XmlParser xmlParser = new XmlParser();

        DataValidator dataValidator = new AllDataValidator();

        List<Path> paths = getPaths(dataValidator);

        // zmieniane jeżeli zmieniamy rodzaj cechy według której szukamy
        TagFilter tagFilter = new BasePlaceFilter();
        ArticleReader articleReader = new ArticleReaderWithPlaces();


    // potrzebny jest plik z podziałem na testowy i treningowy
        String fileWithSplitName = consoleInterface.getFileWithDataSplit();









        //potrzebne sa cechy
        List<FeatureExtractor> featureExtractorList = consoleInterface.getFeatureExtractors(getListOfAvailableFeatureExtractors(wordHolder));


        // powtarzane jezeli coś wyżej sie zmeini
        //train
        Work work = new Work();

        Map<Article, List<Double>> trainSetFeatures = work.trainKNN(articleStore.getTrainSet(), featureExtractorList);
        List<Pair<Double, Double>> minMaxOfTrainSet = work.getMinMaxOfTrainSet(trainSetFeatures);
        Map<Article, List<Double>> trainSetFeaturesAfterNormalization = work.normalize(trainSetFeatures,minMaxOfTrainSet);

        Knn knn = new Knn(trainSetFeaturesAfterNormalization);






        //potrzebna liczba sasiadów
        int numberOfNeighbours = consoleInterface.getNumberOfNeighbours();
        // potrzebny jest miara
        DistanceMeasurement distanceMeasurement = consoleInterface.getDistanceMeasurement(getListOfAvailableDistanceMeasurement());


        //test
        Map<Article, List<Double>> testSetFeatures = work.trainKNN(articleStore.getTestSet(), featureExtractorList);
        Map<Article, List<Double>> testSetFeaturesAfterNormalization = work.normalize(testSetFeatures,minMaxOfTrainSet);

        int good =0;
        int wrong =0;
        for (Map.Entry<Article, List<Double>> articleListEntry : testSetFeaturesAfterNormalization.entrySet()) {
            Place place = knn.getResult(articleListEntry.getValue(), distanceMeasurement, numberOfNeighbours);
            if (place == articleListEntry.getKey().getLabel()) {
                good++;
            } else {
                wrong++;
            }
        }


        //potrzebny wynik zawsze powtarzane
        System.out.println("good = "+ good);
        System.out.println("wrong = "+ wrong);
    }

    private static void createSetOfKeyWord(ArticleStore articleStore, WordHolder wordHolder) {
        wordHolder.train(articleStore.getTrainSet());
        wordHolder.trainDone();
    }

    private static ArticleStore readArticles(FileTransformer fileValidator, XmlParser xmlParser, List<Path> paths, TagFilter tagFilter, ArticleReader articleReader, String fileWithSplitName) {
        ArticleStore articleStore = new ArticleStore(fileWithSplitName);
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

    private static List<Path> getPaths(DataValidator dataValidator) throws InvalidFilesException {
        FileOpener fileOpener = new FileOpener();
        return fileOpener.loadArticlesFromDirectory(dataValidator);
    }




}
