package featuresModels;

import data.Article;
import featuresModels.keyWords.KeyWordHolder;
import featuresModels.keyWords.NumberOfKeyWords;

import java.util.List;
import java.util.stream.Collectors;

public class NumberOfUniqueKeyWordsInRelationToLengthOfText implements FeatureExtractor {

    private KeyWordHolder keyWordHolder;
    private LengthOfText lengthOfText;

    public NumberOfUniqueKeyWordsInRelationToLengthOfText(KeyWordHolder keyWordHolder, LengthOfText lengthOfText) {
        this.keyWordHolder = keyWordHolder;
        this.lengthOfText = lengthOfText;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();

        List<String> uniqueTokensAfterStemming = contentTokensAfterStemming.stream().distinct().collect(Collectors.toList());

        double numberOfUniqueKeyWords = NumberOfKeyWords.countAllKeyWords(uniqueTokensAfterStemming, keyWordHolder);
        double lengthOfText = this.lengthOfText.extract(article);

        return numberOfUniqueKeyWords / lengthOfText;
    }

        @Override
            public String description() {
                return "Liczba unikalnych słów kluczowych w stosunku do długości tekstu";
            }
}
