package all;

import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ArticleStore {
    private final List<ArticleSets> articleSets;
    private int iterator;
    private List<Article> trainSet;
    private List<Article> testSet;

    public ArticleStore() {
        this.trainSet = new ArrayList<>();
        this.testSet = new ArrayList<>();
        this.articleSets = new ArrayList<>();
        this.iterator = 0;

        try (InputStream fin = Files.newInputStream(Paths.get(Constants.PATH_TO_DIRECTORY_WITH_DATA_SPLIT_ON_TEST_AMD_TRAIN_SETS
                + Constants.NAME_OF_FILE_WITH_DATA_SPLIT_ON_TEST_AMD_TRAIN_SETS))) {
            int i;
            do {
                i = fin.read();
                if (i != -1) {
                    articleSets.add(ArticleSets.values()[i]);
                }
            } while (i != -1);
        } catch (InvalidPathException e) {
            System.err.println("Path error: " + e);
        } catch (IOException e) {
            System.err.println("In-out error: " + e);
        }
    }

    public Article add(Article article) {
        if (articleSets.get(iterator) == ArticleSets.TRAIN) {
            trainSet.add(article);
        } else {
            testSet.add(article);
        }
        return article;
    }

    public void addTrainArticle(Article article) {
        trainSet.add(article);
    }

    public void addTestArticle(Article article) {
        testSet.add(article);
    }
}
