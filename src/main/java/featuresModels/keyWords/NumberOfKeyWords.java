package featuresModels.keyWords;

import java.util.HashMap;
import java.util.List;

public class NumberOfKeyWords<T extends Enum<T>> {
    T[] enumConstants;

    public NumberOfKeyWords(Class<T> tClass) {
        this.enumConstants = tClass.getEnumConstants();
    }

    public HashMap<T, Integer> count(List<String> contentTokensAfterStemming, WordHolder<T> wordHolder) {
        HashMap<T, Integer> placeToOccurrenceMap = new HashMap<>();
        for (T place : this.enumConstants) {
            placeToOccurrenceMap.put(place, 0);
        }
        for (String word : contentTokensAfterStemming) {
            Word<T> keyWord = wordHolder.getKeyWord(word);
            if (keyWord!=null && keyWord.isKeyWord()) {
                placeToOccurrenceMap.put(keyWord.getKeyWordFor(), placeToOccurrenceMap.get(keyWord.getKeyWordFor()) + 1);
            }
        }
        return placeToOccurrenceMap;
    }

    public int countAllKeyWords(List<String> contentTokensAfterStemming, WordHolder<T> wordHolder) {
        int counter = 0;
        for (String word : contentTokensAfterStemming) {
            Word<T> keyWord = wordHolder.getKeyWord(word);
            if (keyWord!=null && keyWord.isKeyWord()) {
                counter++;
            }
        }
        return counter;
    }
}