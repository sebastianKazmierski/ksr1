package featuresModels;

import java.util.List;

public class Functions {

    String deletePunctuationMarksFromEnd(String word) {
        String[] endWordPunctuation = new String[]{"!", "?", ",", ".", ":", ";"};
        boolean isPunctuationMark;
        do {
            isPunctuationMark = true;
            for (String punctuationMark : endWordPunctuation) {
                if (word.endsWith(punctuationMark)) {
                    isPunctuationMark = false;
                    word = word.substring(0, word.length() - 1);
                }
            }
        } while (!isPunctuationMark);

        return word;
    }
}
