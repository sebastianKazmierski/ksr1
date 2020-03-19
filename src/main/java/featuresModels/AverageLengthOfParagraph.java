package featuresModels;

import data.Article;

public class AverageLengthOfParagraph implements FeatureExtractor {
    NumberOfParagraphsInRelationToLengthOfText numberOfParagraphs;

    public AverageLengthOfParagraph(NumberOfParagraphsInRelationToLengthOfText numberOfParagraphs) {
        this.numberOfParagraphs = numberOfParagraphs;
    }

    @Override
    public double extract(Article article) {
        int numberOfWords = article.getContentTokens().size();
        return (double)numberOfWords / numberOfParagraphs.getNumberOfParagraphs(article);
    }

    @Override
    public String description() {
        return "Średnia długość akapitu";
    }
}
