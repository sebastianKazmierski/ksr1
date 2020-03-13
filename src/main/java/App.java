import all.ArticleStore;
import all.GenerateSplitOfData;
import loadData.FileOpener;
import loadData.FileValidator;
import loadData.XmlParser;
import loadData.XmlValidator;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) {
/*        GenerateSplitOfData generateSplitOfData = new GenerateSplitOfData();
        generateSplitOfData.generate(1000,30);*/

        FileOpener fileOpener = new FileOpener();
        FileValidator fileValidator = new XmlValidator();
        XmlParser xmlParser = new XmlParser();

        List<Path> paths = fileOpener.loadArticlesFromDirectory();
        ArticleStore articleStore = new ArticleStore();

        for (Path path : paths) {
            CharBuffer charBuffer;
            try {
                charBuffer = fileValidator.validate(fileOpener.getCharsFromFile(path));
                xmlParser.parse(charBuffer,articleStore);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(articleStore);
    }
}
