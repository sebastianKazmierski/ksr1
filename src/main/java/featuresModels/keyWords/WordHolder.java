package featuresModels.keyWords;

import data.Article;
import grouping.Place;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordHolder {
    private Map<String, Word> keywords;
    @Getter
    private boolean isReady;

    public WordHolder() {
        this.keywords = new HashMap<>();
        this.isReady = false;
    }

    public void train(Article article) {
        Place place = article.getPlace();
        for (String word : article.getContentTokensAfterStemming()) {
            if (this.keywords.containsKey(word)) {
                this.keywords.get(word).train(place);
            } else {
                Word keyWord = new Word(word);
                keyWord.train(place);
                this.keywords.put(word, keyWord);
            }
        }
    }

    public Map<String, Word> getKeywords() {
        return this.keywords;
    }

    public Word getKeyWord(String word) {
        return this.keywords.get(word);
    }

    public void train(List<Article> articles) {
        articles.forEach(this::train);
    }

    public void trainDone() {
        this.isReady = true;
        this.keywords.values().forEach(Word::trainDone);
    }
}
