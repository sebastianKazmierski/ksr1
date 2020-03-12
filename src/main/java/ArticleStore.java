import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArticleStore {
    private List<Article> trainSet;
    private List<Article> testSet;

    public ArticleStore() {
        trainSet = new ArrayList<>();
        testSet = new ArrayList<>();
    }

    public void addTrainArticle(Article article ) {
        trainSet.add(article);
    }

    public void addTestArticle(Article article ) {
        testSet.add(article);
    }


}
