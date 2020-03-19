package featuresModels;

import data.Article;
import featuresModels.keyWords.WordHolder;
import featuresModels.keyWords.NumberOfKeyWords;

import java.util.List;
import java.util.stream.Collectors;

public class NumberOfUniqueKeyWordsInRelationToLengthOfText implements FeatureExtractor {

    private WordHolder wordHolder;
    private LengthOfText lengthOfText;

    public NumberOfUniqueKeyWordsInRelationToLengthOfText(WordHolder wordHolder, LengthOfText lengthOfText) {
        this.wordHolder = wordHolder;
        this.lengthOfText = lengthOfText;
    }

    @Override
    public double extract(Article article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();

        List<String> uniqueTokensAfterStemming = contentTokensAfterStemming.stream().distinct().collect(Collectors.toList());

        double numberOfUniqueKeyWords = NumberOfKeyWords.countAllKeyWords(uniqueTokensAfterStemming, wordHolder);
        double lengthOfText = this.lengthOfText.extract(article);

        return numberOfUniqueKeyWords / lengthOfText;
    }

        @Override
            public String description() {
                return "Liczba unikalnych słów kluczowych w stosunku do długości tekstu";
            }
}
