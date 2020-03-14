import data.ArticleStore;
import data.GenerateSplitOfData;
import loadData.*;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) throws InvalidFilesException {
        GenerateSplitOfData generateSplitOfData = new GenerateSplitOfData();
        generateSplitOfData.generate(21000,30);

        FileOpener fileOpener = new FileOpener();
        FileTransformer fileValidator = new XmlTransformer();
        XmlParser xmlParser = new XmlParser();

        DataValidator dataValidator = new AllDataValidator();

        List<Path> paths = fileOpener.loadArticlesFromDirectory(dataValidator);
        ArticleStore articleStore = new ArticleStore();

        for (Path path : paths) {
            CharBuffer charBuffer;
            try {
                charBuffer = fileValidator.validate(fileOpener.getCharsFromFile(path));
                xmlParser.readArticles(charBuffer,articleStore);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(articleStore);
    }
}
