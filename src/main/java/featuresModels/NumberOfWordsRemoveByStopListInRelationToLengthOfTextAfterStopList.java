package featuresModels;

import data.Article;

public class NumberOfWordsRemoveByStopListInRelationToLengthOfTextAfterStopList implements FeatureExtractor {
    @Override
    public double extract(Article article) {
        int lengthOfText = article.getContentTokens().size();
        FeatureExtractor featureExtractor = new LengthOfText();
        double lengthOfTextAfterStopList = featureExtractor.extract(article);
        return (lengthOfText - lengthOfTextAfterStopList) / lengthOfTextAfterStopList;
    }

        @Override
            public String description() {
                return "Liczba słów usuniętych przez stop listę w stosunku do długości tekstu";
            }
}
