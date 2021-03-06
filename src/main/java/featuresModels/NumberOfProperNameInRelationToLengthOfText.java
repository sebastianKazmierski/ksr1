package featuresModels;

import data.Article;
import grouping.Label;

import java.util.ArrayList;
import java.util.List;

public class NumberOfProperNameInRelationToLengthOfText<T extends Label<T>> implements FeatureExtractor<T> {
    @Override
    public double extract(Article<T> article) {
        List<String> tokens = article.getContentTokens();
        List<String> properName = new ArrayList<>();
        int counter = 0;
        for (int i = 1; i < tokens.size(); i++) {
            String word = tokens.get(i);
            if (Character.isUpperCase(word.charAt(0))) {
                if (!tokens.get(i-1).endsWith(".")) {
                    counter++;
                    properName.add(Functions.deletePunctuationMarksFromEnd(word).get(0));
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

    @Override
    public int getNumber() {
        return 3;
    }

    @Override
            public String description() {
                return "Liczba nazw własnych w stosunku do długości tekstu";
            }
}
