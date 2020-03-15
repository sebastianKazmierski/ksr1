package featuresModels;

import data.Article;

public class NumberOfParagraphs implements FeatureExtractor {
    @Override
    public double extract(Article article) {
        int counter = 1;
        for (String line : article.getContent().split("\n")) {
            if (line.substring(0, 1).isBlank()) {
                counter++;
            }
        }
        LengthOfText lengthOfText = new LengthOfText();
        return counter/lengthOfText.extract(article);
    }
}
