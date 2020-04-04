package featuresModels;

import data.Article;
import featuresModels.keyWords.NumberOfKeyWords;
import featuresModels.keyWords.WordHolderProvider;
import grouping.Label;

import java.util.List;
import java.util.stream.Collectors;

public class NumberOfUniqueKeyWordsInRelationToLengthOfText<T extends Label<T>> implements FeatureExtractor<T> {

    private WordHolderProvider<T> wordHolderProvider;
    private LengthOfText<T> lengthOfText;
    private Class<T> tClass;

    public NumberOfUniqueKeyWordsInRelationToLengthOfText(WordHolderProvider<T> wordHolder,Class<T> tClass, LengthOfText<T> lengthOfText) {
        this.wordHolderProvider = wordHolder;
        this.lengthOfText = lengthOfText;
        this.tClass=tClass;
    }

    @Override
    public double extract(Article<T> article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();

        List<String> uniqueTokensAfterStemming = contentTokensAfterStemming.stream().distinct().collect(Collectors.toList());
        NumberOfKeyWords<T> numberOfKeyWords = new NumberOfKeyWords<>(this.tClass);
        double numberOfUniqueKeyWords = numberOfKeyWords.countAllKeyWords(uniqueTokensAfterStemming, this.wordHolderProvider.getWordHolder());
        double lengthOfText = this.lengthOfText.extract(article);

        return numberOfUniqueKeyWords / lengthOfText;
    }

        @Override
            public String description() {
                return "Liczba unikalnych słów kluczowych w stosunku do długości tekstu";
            }
}
