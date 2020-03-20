package featuresModels;

import data.Article;

public class NumberOfParagraphsInRelationToLengthOfText implements FeatureExtractor {
    LengthOfText lengthOfText;

    public NumberOfParagraphsInRelationToLengthOfText(LengthOfText lengthOfText) {
        this.lengthOfText = lengthOfText;
    }

    @Override
    public double extract(Article article) {
        return getNumberOfParagraphs(article)/lengthOfText.extract(article);
    }

    public int getNumberOfParagraphs(Article article) {
        int counter = 1;
        for (String line : article.getContent().split("\n")) {
            if (line.length()>0 && line.substring(0, 1).isBlank()) {
                counter++;
            }
        }
        return counter;
    }

        @Override
            public String description() {
                return "Liczba akapitów w stosunku do długości tekstu";
            }
}
