package featuresModels;

import data.Article;
import featuresModels.keyWords.WordHolder;
import featuresModels.keyWords.NumberOfKeyWords;

import java.util.List;

public class NumberOfKeyWordsInWholeText<T extends Enum<T>> implements FeatureExtractor<T> {
    private WordHolder<T> wordHolder;
    NumberOfKeyWords<T> numberOfKeyWords;

    public NumberOfKeyWordsInWholeText(WordHolder<T> wordHolder, Class<T> tClass) {
        this.wordHolder = wordHolder;
        this.numberOfKeyWords = new NumberOfKeyWords<>(tClass);
    }

    @Override
    public double extract(Article<T> article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        return  numberOfKeyWords.countAllKeyWords(contentTokensAfterStemming, this.wordHolder);
    }

        @Override
            public String description() {
                return "Liczba wszystkich słów kluczowych w całym tekście";
            }
}
