package data;

import constants.Constants;
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

    public ArticleStore(String fileWithSplitName) {
        this.trainSet = new ArrayList<>();
        this.testSet = new ArrayList<>();
        this.articleSets = new ArrayList<>();
        this.iterator = 0;

        try (InputStream fin = Files.newInputStream(Paths.get(Constants.PATH_TO_DIRECTORY_WITH_DATA_SPLIT_ON_TEST_AMD_TRAIN_SETS + "\\"
                + fileWithSplitName))) {
            int i = fin.read();
            while (i != -1) {
                this.articleSets.add(ArticleSets.values()[i]);
                i = fin.read();
            }
        } catch (InvalidPathException e) {
            System.err.println("Path error: " + e);
        } catch (IOException e) {
            System.err.println("In-out error: " + e);
        }
    }

    public Article add(Article article) {
        if (this.articleSets.get(this.iterator) == ArticleSets.TRAIN) {
            this.trainSet.add(article);
        } else {
            this.testSet.add(article);
        }
        this.iterator++;
        return article;
    }

    @Override
    public String toString() {
        return "ArticleStore{" +
                "Number of articles=" + this.iterator +
                ", Size of trainSet=" + this.trainSet.size() +
                ", Size of testSet=" + this.testSet.size() +
                '}';
    }

    public void addTrainArticle(Article article) {
        this.trainSet.add(article);
    }

    public void addTestArticle(Article article) {
        this.testSet.add(article);
    }
}
