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

            if (label instanceof Place) {
                tagFilter = new BasePlaceFilter();
                articleReader = new ArticleReaderWithPlaces();
                All<Place> all = new All<>(new ConsoleInterface<>(Place.class), Place.class, fileValidator, xmlParser, dataValidator, tagFilter, articleReader);
                repeat = all.work();
            } else if (label instanceof Topic) {
                tagFilter = new BasePlaceFilter();
                articleReader = new ArticleReaderWithPlaces();
                All<Topic> all = new All<>(new ConsoleInterface<>(Topic.class), Topic.class, fileValidator, xmlParser, dataValidator, tagFilter, articleReader);
                repeat = all.work();
            }
        } while (repeat);
    }
}
