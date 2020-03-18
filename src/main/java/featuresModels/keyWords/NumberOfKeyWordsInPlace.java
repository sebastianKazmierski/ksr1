package featuresModels.keyWords;

import data.Article;
import featuresModels.FeatureExtractor;
import grouping.Place;

import java.lang.management.PlatformLoggingMXBean;
import java.util.HashMap;
import java.util.List;

public class NumberOfKeyWordsInPlace implements FeatureExtractor {
    private Place place;
    private KeyWordHolder keyWordHolder;

    public NumberOfKeyWordsInPlace(Place place, KeyWordHolder keyWordHolder) {
        this.place = place;
        this.keyWordHolder = keyWordHolder;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        List<String> contentTokensAfterStemmingTenPercent = contentTokensAfterStemming.subList(0, (int) (contentTokensAfterStemming.size() / 10.0));
        HashMap<Place, Integer> placeOccurrenceMap = NumberOfKeyWords.count(contentTokensAfterStemmingTenPercent, keyWordHolder);
        return placeOccurrenceMap.get(place);
    }
}
