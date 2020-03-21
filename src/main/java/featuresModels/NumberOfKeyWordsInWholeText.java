package featuresModels;

import data.Article;
import featuresModels.keyWords.WordHolder;
import featuresModels.keyWords.NumberOfKeyWords;

import java.util.List;

public class NumberOfKeyWordsInWholeText implements FeatureExtractor {
    private WordHolder wordHolder;

    public NumberOfKeyWordsInWholeText(WordHolder wordHolder) {
        this.wordHolder = wordHolder;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        return  NumberOfKeyWords.countAllKeyWords(contentTokensAfterStemming, this.wordHolder);
    }

        @Override
            public String description() {
                return "Liczba wszystkich słów kluczowych w całym tekście";
            }
}
