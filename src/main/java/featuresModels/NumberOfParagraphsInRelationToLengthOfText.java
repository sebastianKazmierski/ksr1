package featuresModels;

import data.Article;

public class NumberOfParagraphsInRelationToLengthOfText implements FeatureExtractor {
    @Override
    public double extract(Article article) {
        LengthOfText lengthOfText = new LengthOfText();
        return getNumberOfParagraphs(article)/lengthOfText.extract(article);
    }

    public int getNumberOfParagraphs(Article article) {
        int counter = 1;
        for (String line : article.getContent().split("\n")) {
            if (line.substring(0, 1).isBlank()) {
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
