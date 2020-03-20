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
        keywords = new HashMap<>();
        isReady = false;
    }

    public void train(Article article) {
        Place place = article.getPlace();
        for (String word : article.getContentTokensAfterStemming()) {
            if (keywords.containsKey(word)) {
                keywords.get(word).train(place);
            } else {
                Word keyWord = new Word(word);
                keyWord.train(place);
                keywords.put(word, keyWord);
            }
        }
    }

    public Map<String, Word> getKeywords() {
        return keywords;
    }

    public Word getKeyWord(String word) {
        return keywords.get(word);
    }

    public void train(List<Article> articles) {
        articles.forEach(this::train);
    }

    public void trainDone() {
        isReady = true;
        keywords.values().forEach(Word::trainDone);
    }
}
