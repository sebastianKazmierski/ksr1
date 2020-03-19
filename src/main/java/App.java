import data.ArticleStore;
import featuresModels.FeatureExtractor;
import loadData.*;
import loadData.articleCratorsFromXml.ArticleReader;
import loadData.articleCratorsFromXml.ArticleReaderWithPlaces;
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

        FileOpener fileOpener = new FileOpener();
        FileTransformer fileValidator = new XmlTransformer();
        XmlParser xmlParser = new XmlParser();

        DataValidator dataValidator = new AllDataValidator();

        List<Path> paths = fileOpener.loadArticlesFromDirectory(dataValidator);
        ArticleStore articleStore = new ArticleStore();
        TagFilter tagFilter = new BasePlaceFilter();
        ArticleReader articleReader = new ArticleReaderWithPlaces();

        for (Path path : paths) {
            CharBuffer charBuffer;
            try {
                charBuffer = fileValidator.validate(fileOpener.getCharsFromFile(path));
                xmlParser.readArticles(charBuffer,articleStore,articleReader, tagFilter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(articleStore);
        System.out.println(articleStore.getTestSet());

        List<FeatureExtractor> featureExtractorList = new ArrayList<>();

    }
}
