import data.ArticleStore;
import featuresModels.FeatureExtractor;
import featuresModels.keyWords.WordHolder;
import loadData.*;
import loadData.articleCratorsFromXml.ArticleReader;
import loadData.articleCratorsFromXml.ArticleReaderWithPlaces;
import loadData.dataValidators.AllDataValidator;
import loadData.dataValidators.DataValidator;
import loadData.dataValidators.InvalidFilesException;
import loadData.filesTransformer.FileTransformer;
import loadData.filesTransformer.XmlTransformer;
import loadData.tagsFilter.BasePlaceFilter;
import loadData.tagsFilter.TagFilter;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws InvalidFilesException {
/*        GenerateSplitOfData generateSplitOfData = new GenerateSplitOfData();
        generateSplitOfData.generate(21000,30);*/

        FileTransformer fileValidator = new XmlTransformer();
        XmlParser xmlParser = new XmlParser();

        DataValidator dataValidator = new AllDataValidator();

        List<Path> paths = getPaths(dataValidator);

        String fileWithSplitName = "";
        ArticleStore articleStore = new ArticleStore(fileWithSplitName);
        TagFilter tagFilter = new BasePlaceFilter();
        ArticleReader articleReader = new ArticleReaderWithPlaces();

        readArticles(fileValidator, xmlParser, paths, articleStore, tagFilter, articleReader);

        WordHolder wordHolder = new WordHolder();
        createSetOfKeyWord(articleStore, wordHolder);


        System.out.println(articleStore);
        System.out.println(articleStore.getTestSet());

        List<FeatureExtractor> featureExtractorList = new ArrayList<>();
    }

    private static void createSetOfKeyWord(ArticleStore articleStore, WordHolder wordHolder) {
        wordHolder.train(articleStore.getTrainSet());
        wordHolder.trainDone();
    }

    private static void readArticles(FileTransformer fileValidator, XmlParser xmlParser, List<Path> paths, ArticleStore articleStore, TagFilter tagFilter, ArticleReader articleReader) {
        for (Path path : paths) {
            try {
                CharBuffer charBuffer = fileValidator.transform(path);
                xmlParser.readArticles(charBuffer,articleStore,articleReader, tagFilter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Path> getPaths(DataValidator dataValidator) throws InvalidFilesException {
        FileOpener fileOpener = new FileOpener();
        return fileOpener.loadArticlesFromDirectory(dataValidator);
    }
}
