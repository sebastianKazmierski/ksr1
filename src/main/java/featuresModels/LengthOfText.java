package featuresModels;

import constants.Constants;
import data.Article;

public class LengthOfText implements FeatureExtractor {
    @Override
    public double extract(Article article) {
        int counter = 0;
        for (String word : article.getContentTokensAfterStopList()) {
            if (!Constants.END_WORD_PUNCTUATION.contains(word.substring(word.length() - 1))) {
                counter++;
            }
        }
        return counter;
    }

        @Override
            public String description() {
                return "Długość tekstu";
            }
}
