package featuresModels.keyWords;

import data.Article;
import featuresModels.FeatureExtractor;

import java.util.List;

public class NumberOfKeyWordsInWholeText implements FeatureExtractor {
    private KeyWordHolder keyWordHolder;

    public NumberOfKeyWordsInWholeText(KeyWordHolder keyWordHolder) {
        this.keyWordHolder = keyWordHolder;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        NumberOfKeyWords.count(contentTokensAfterStemming, keyWordHolder);
        return 0.0;
    }
}
