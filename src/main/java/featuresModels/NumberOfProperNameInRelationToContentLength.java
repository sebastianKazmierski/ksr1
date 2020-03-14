package featuresModels;

import data.Article;

import java.util.ArrayList;
import java.util.List;

public class NumberOfProperNameInRelationToContentLength implements FeatureExtractor {
    @Override
    public double extract(Article article) {
        List<String> tokens = article.getContentTokens();
        List<String> properName = new ArrayList<>();
        int counter = 0;
        Functions functions = new Functions();
        for (int i = 1; i < tokens.size(); i++) {
            String word = tokens.get(i);
            if (Character.isUpperCase(word.charAt(0))) {
                if (!tokens.get(i-1).endsWith(".")) {
                    counter++;
                    properName.add(functions.deletePunctuationMarksFromEnd(word));
                }
            }
        }

        for (int i = 1; i < tokens.size(); i++) {
            if (Character.isUpperCase(tokens.get(i).charAt(0))) {
                if (tokens.get(i-1).endsWith(".") && properName.contains(tokens.get(i)) ) {
                    counter++;
                }
            }
        }

        if (properName.contains(tokens.get(0))) {
            counter++;
        }

        return ((double)counter)/tokens.size();
    }

}
