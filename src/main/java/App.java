import grouping.Place;
import grouping.Topic;
import interfaceModule.*;
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

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws InvalidFilesException {

        Class tClass;
        FileTransformer fileValidator = new XmlTransformer();
        XmlParser xmlParser = new XmlParser();
        DataValidator dataValidator = new AllDataValidator();
        TagFilter tagFilter;
        ArticleReader articleReader;
        Scanner in = new Scanner(System.in);
        List<ElementSelectedByUser> list = Arrays.asList(Place.USA, Topic.COFFEE);

        boolean repeat = false;
        do {
            ChoseElementInterface<ElementSelectedByUser> choseLabel = new ChoseElementInterface<>(in, TypeOfChoice.SINGLE);

            List<ElementSelectedByUser> labels = choseLabel.getAnswer(list);
            ElementSelectedByUser label = labels.get(0);

            ConsoleInterface consoleInterface;

            if (label instanceof Place) {
                tagFilter = new BasePlaceFilter();
                articleReader = new ArticleReaderWithPlaces();
                tClass = Place.class;
                consoleInterface = new ConsoleInterface<>();
                All<Place> all = new All<Place>((UserInterface<Place>) consoleInterface,tClass,fileValidator,xmlParser,dataValidator,tagFilter,articleReader)
                repeat = userInterface.work();
            } else if (label instanceof Topic) {
                tagFilter = new BasePlaceFilter();
                articleReader = new ArticleReaderWithPlaces();
                tClass = Topic.class;
                consoleInterface = new ConsoleInterface<>();
                repeat = userInterface.work();
            }
        } while (repeat)


/*
        ChoseLabel userInterface = new ChoseLabel(in,list);

        userInterface.getLabel();
*/

/*        String userChoice = this.userInterface.getLabel();
        if (userChoice.equals("p")) {
            this.tagFilter = new BasePlaceFilter();
            this.articleReader = new ArticleReaderWithPlaces();
        }




        //nic tu nie jest potrzebne nigdy to nie jest powtarzane

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
        System.out.println("wrong = "+ wrong);*/
    }









}
