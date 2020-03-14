package featuresModels;

import data.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageLengthOfProperName implements FeatureExtractor {
    @Override
    public double extract(Article article) {
        List<String> tokens = article.getContentTokens();
        Map<String, Integer> lengthOfProperNames = new HashMap<>();

        for (int i = 1; i < tokens.size(); i++) {
            String word = tokens.get(i);
            if (Character.isUpperCase(word.charAt(0))) {
                if (!tokens.get(i-1).endsWith(".")) {
                    String wordWIthOutPunctuationMarks = Functions.deletePunctuationMarksFromEnd(word).get(0);
                    lengthOfProperNames.put(wordWIthOutPunctuationMarks, wordWIthOutPunctuationMarks.length());
                }
            }
        }
        int sumOfLengthProperName = 0;
        for(Map.Entry<String, Integer> entry : lengthOfProperNames.entrySet()) {
            sumOfLengthProperName += entry.getValue();
        }
        return ((double) sumOfLengthProperName) / lengthOfProperNames.size();
    }
}
