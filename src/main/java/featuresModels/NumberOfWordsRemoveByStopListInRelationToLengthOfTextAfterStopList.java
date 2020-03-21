package featuresModels;

import data.Article;

public class NumberOfWordsRemoveByStopListInRelationToLengthOfTextAfterStopList<T extends Enum<T>> implements FeatureExtractor<T> {
    @Override
    public double extract(Article<T> article) {
        int lengthOfText = article.getContentTokens().size();
        FeatureExtractor<T> featureExtractor = new LengthOfText<>();
        double lengthOfTextAfterStopList = featureExtractor.extract(article);
        return (lengthOfText - lengthOfTextAfterStopList) / lengthOfTextAfterStopList;
    }

    @Override
    public String description() {
        return "Liczba słów usuniętych przez stop listę w stosunku do długości tekstu";
    }
}
