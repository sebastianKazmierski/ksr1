import grouping.Place;
import grouping.Topic;
import interfaceModule.ChoseLabel;
import loadData.dataValidators.InvalidFilesException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws InvalidFilesException {

        Scanner in = new Scanner(System.in);

        List<? extends Enum> list = Arrays.asList(Place.USA, Topic.COFFEE);
        ChoseLabel userInterface = new ChoseLabel(in,list);

        userInterface.getLabel();

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
