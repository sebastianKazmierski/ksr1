package featuresModels;

import data.Article;

public class AverageLengthOfParagraph<T> implements FeatureExtractor<T> {
    NumberOfParagraphsInRelationToLengthOfText numberOfParagraphs;

    public AverageLengthOfParagraph(NumberOfParagraphsInRelationToLengthOfText numberOfParagraphs) {
        this.numberOfParagraphs = numberOfParagraphs;
    }

    @Override
    public double extract(Article<T> article) {
        int numberOfWords = article.getContentTokens().size();
        return (double)numberOfWords / this.numberOfParagraphs.getNumberOfParagraphs(article);
    }

    @Override
    public String description() {
        return "Średnia długość akapitu";
    }


}
