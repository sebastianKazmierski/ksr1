package featuresModels;

import data.Article;

public class AverageLengthOfParagraph implements FeatureExtractor {
    @Override
    public double extract(Article article) {
        int numberOfWords = article.getContentTokens().size();
        NumberOfParagraphsInRelationToLengthOfText numberOfParagraphs = new NumberOfParagraphsInRelationToLengthOfText();
        return (double)numberOfWords / numberOfParagraphs.getNumberOfParagraphs(article);
    }

    @Override
    public String description() {
        return "Średnia długość akapitu";
    }
}
