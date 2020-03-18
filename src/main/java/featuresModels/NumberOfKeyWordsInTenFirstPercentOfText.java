package featuresModels;

import data.Article;
import featuresModels.FeatureExtractor;
import featuresModels.keyWords.KeyWordHolder;
import featuresModels.keyWords.NumberOfKeyWords;

import java.util.List;

public class NumberOfKeyWordsInTenFirstPercentOfText implements FeatureExtractor {
    private KeyWordHolder keyWordHolder;

    public NumberOfKeyWordsInTenFirstPercentOfText(KeyWordHolder keyWordHolder) {
        this.keyWordHolder = keyWordHolder;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        List<String> contentTokensAfterStemmingTenPercent = contentTokensAfterStemming.subList(0, (int) ( contentTokensAfterStemming.size() / 10.0));
        return NumberOfKeyWords.countAllKeyWords(contentTokensAfterStemmingTenPercent, keyWordHolder);
    }
}
