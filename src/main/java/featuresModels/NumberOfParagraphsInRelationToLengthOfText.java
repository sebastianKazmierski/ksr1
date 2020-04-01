package featuresModels;

import data.Article;
import grouping.Label;

public class NumberOfParagraphsInRelationToLengthOfText<T extends Label<T>> implements FeatureExtractor<T> {
    LengthOfText<T> lengthOfText;

    public NumberOfParagraphsInRelationToLengthOfText(LengthOfText<T> lengthOfText) {
        this.lengthOfText = lengthOfText;
    }

    @Override
    public double extract(Article<T> article) {
        return this.getNumberOfParagraphs(article)/ this.lengthOfText.extract(article);
    }

    public int getNumberOfParagraphs(Article<T> article) {
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
