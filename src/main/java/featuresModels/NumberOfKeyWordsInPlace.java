package featuresModels;

import data.Article;
import featuresModels.keyWords.WordHolder;
import featuresModels.keyWords.NumberOfKeyWords;
import grouping.Place;

import java.util.HashMap;
import java.util.List;

public class NumberOfKeyWordsInPlace implements FeatureExtractor {
    private Place place;
    private WordHolder wordHolder;

    public NumberOfKeyWordsInPlace(Place place, WordHolder wordHolder) {
        this.place = place;
        this.wordHolder = wordHolder;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        List<String> contentTokensAfterStemmingTenPercent = contentTokensAfterStemming.subList(0, (int) (contentTokensAfterStemming.size() / 10.0));
        HashMap<Place, Integer> placeOccurrenceMap = NumberOfKeyWords.count(contentTokensAfterStemmingTenPercent, this.wordHolder);
        return placeOccurrenceMap.get(this.place);
    }

        @Override
            public String description() {
                return "Ilość słów kluczowych dla " + this.place.label;
            }
}
