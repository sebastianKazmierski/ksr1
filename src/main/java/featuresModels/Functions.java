package featuresModels;

import java.util.List;

public class Functions {

    public static List<String> deletePunctuationMarksFromEnd(String word) {
        String[] endWordPunctuation = new String[]{"!", "?", ",", ".", ":", ";"};
        StringBuilder punctuationMarksBuffer = new StringBuilder();
        boolean isPunctuationMark;
        do {
            isPunctuationMark = true;
            for (String punctuationMark : endWordPunctuation) {
                if (word.endsWith(punctuationMark)) {
                    isPunctuationMark = false;
                    punctuationMarksBuffer.append(word.charAt(word.length() - 1));
                    word = word.substring(0, word.length() - 1);
                }
            }
        } while (!isPunctuationMark);

        return List.of(word, punctuationMarksBuffer.reverse().toString());
    }
}
