package featuresModels;

import data.Article;
import featuresModels.keyWords.WordHolder;
import featuresModels.keyWords.NumberOfKeyWords;
import grouping.Place;

import java.util.List;
import java.util.stream.Collectors;

public class NumberOfUniqueKeyWordsInRelationToLengthOfText<T extends Enum<T>> implements FeatureExtractor<T> {

    private WordHolder<T> wordHolder;
    private LengthOfText<T> lengthOfText;

    public NumberOfUniqueKeyWordsInRelationToLengthOfText(WordHolder<T> wordHolder, LengthOfText<T> lengthOfText) {
        this.wordHolder = wordHolder;
        this.lengthOfText = lengthOfText;
    }

    @Override
    public double extract(Article<T> article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();

        List<String> uniqueTokensAfterStemming = contentTokensAfterStemming.stream().distinct().collect(Collectors.toList());
        NumberOfKeyWords<T> numberOfKeyWords = new NumberOfKeyWords(Place.class);
        double numberOfUniqueKeyWords = numberOfKeyWords.countAllKeyWords(uniqueTokensAfterStemming, this.wordHolder);
        double lengthOfText = this.lengthOfText.extract(article);

        return numberOfUniqueKeyWords / lengthOfText;
    }

        @Override
            public String description() {
                return "Liczba unikalnych słów kluczowych w stosunku do długości tekstu";
            }
}
