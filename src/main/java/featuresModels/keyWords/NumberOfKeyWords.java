package featuresModels.keyWords;

import grouping.Place;

import java.util.HashMap;
import java.util.List;

public class NumberOfKeyWords {
    public static HashMap<Place, Integer> count(List<String> contentTokensAfterStemming, KeyWordHolder keyWordHolder) {
        HashMap<Place, Integer> placeToOccurrenceMap = new HashMap<>();
        for (Place place : Place.values()) {
            placeToOccurrenceMap.put(place, 0);
        }
        for (String word : contentTokensAfterStemming) {
            KeyWord keyWord = keyWordHolder.getKeyWord(word);
            if (keyWord!=null && keyWord.isKeyWord()) {
                placeToOccurrenceMap.put(keyWord.getKeyWordFor(), placeToOccurrenceMap.get(keyWord.getKeyWordFor()) + 1);
            }
        }
        return placeToOccurrenceMap;
    }

    public static int countAllKeyWords(List<String> contentTokensAfterStemming, KeyWordHolder keyWordHolder) {
        int counter = 0;
        for (String word : contentTokensAfterStemming) {
            KeyWord keyWord = keyWordHolder.getKeyWord(word);
            if (keyWord!=null && keyWord.isKeyWord()) {
                counter++;
            }
        }
        return counter;
    }
}