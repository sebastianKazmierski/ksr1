package featuresModels;

import data.Article;
import featuresModels.keyWords.KeyWordHolder;
import featuresModels.keyWords.NumberOfKeyWords;

import java.util.List;

public class NumberOfKeyWordsInWholeText implements FeatureExtractor {
    private KeyWordHolder keyWordHolder;

    public NumberOfKeyWordsInWholeText(KeyWordHolder keyWordHolder) {
        this.keyWordHolder = keyWordHolder;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        return  NumberOfKeyWords.countAllKeyWords(contentTokensAfterStemming, keyWordHolder);
    }
}
