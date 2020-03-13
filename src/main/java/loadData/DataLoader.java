package loadData;
import all.ArticleStore;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.List;

public class DataLoader {


    public static void main(String args[]) {
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
    }

}