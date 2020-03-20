package featuresModels;

import data.Article;
import featuresModels.keyWords.WordHolder;
import featuresModels.keyWords.NumberOfKeyWords;

import java.util.List;

public class NumberOfKeyWordsInTenFirstPercentOfText implements FeatureExtractor {
    private WordHolder wordHolder;

    public NumberOfKeyWordsInTenFirstPercentOfText(WordHolder wordHolder) {
        this.wordHolder = wordHolder;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        List<String> contentTokensAfterStemmingTenPercent = contentTokensAfterStemming.subList(0, (int) ( contentTokensAfterStemming.size() / 10.0));
        return NumberOfKeyWords.countAllKeyWords(contentTokensAfterStemmingTenPercent, this.wordHolder);
    }

        @Override
            public String description() {
                return "Liczba wszystkich słów kluczowych w 10% pierwszych procentach tekstu";
            }
}
