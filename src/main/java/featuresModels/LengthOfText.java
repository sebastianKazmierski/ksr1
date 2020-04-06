package featuresModels;

import constants.Constants;
import data.Article;

public class LengthOfText<T> implements FeatureExtractor<T> {
    @Override
    public double extract(Article<T> article) {
        int counter = 0;
        for (String word : article.getContentTokensAfterStopList()) {
            if (!Constants.END_WORD_PUNCTUATION.contains(word.substring(word.length() - 1))) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public int getNumber() {
        return 5;
    }

    @Override
            public String description() {
                return "Długość tekstu";
            }
}
