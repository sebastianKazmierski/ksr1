package featuresModels.keyWords;

import data.Article;
import grouping.Place;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyWordHolder {
    private Map<String, KeyWord> keywords;
    @Getter
    private boolean isReady;

    public KeyWordHolder() {
        keywords = new HashMap<>();
        isReady = false;
    }

    public void train(Article article) {
        Place place = article.getPlace();
        for (String word : article.getContentTokensAfterStemming()) {
            if (keywords.containsKey(word)) {
                keywords.get(word).add(place);
            } else {
                keywords.put(word, new KeyWord(word));
            }
        }
    }

    public KeyWord getKeyWord(String word) {
        return keywords.get(word);
    }

    public void train(List<Article> articles) {
        articles.forEach(this::train);
    }

    public void trainDone() {
        isReady = true;
        keywords.values().forEach(KeyWord::trainDone);
    }
}
