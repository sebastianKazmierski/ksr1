package featuresModels.keyWords;

import data.Article;
import grouping.Label;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordHolder<T extends Label<T>> {
    private Map<String, Word<T>> keywords;
    @Getter
    private boolean isReady;
    Class<T> tClass;

    public WordHolder( Class<T> tClass) {
        this.tClass = tClass;
        this.keywords = new HashMap<>();
        this.isReady = false;
    }

    public void train(Article<T> article) {
        T place = article.getLabel();
        for (String word : article.getContentTokensAfterStemming()) {
            if (this.keywords.containsKey(word)) {
                this.keywords.get(word).train(place);
            } else {
                Word<T> keyWord = new Word<>(word, tClass);
                keyWord.train(place);
                this.keywords.put(word, keyWord);
            }
        }
    }

    public Map<String, Word<T>> getKeywords() {
        return this.keywords;
    }

    public Word<T> getKeyWord(String word) {
        return this.keywords.get(word);
    }

    public void train(List<Article<T>> articles) {
        articles.forEach(this::train);
    }

    public void trainDone() {
        this.isReady = true;
        this.keywords.values().forEach(Word::trainDone);
    }
}
