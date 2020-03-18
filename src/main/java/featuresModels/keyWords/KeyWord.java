package featuresModels.keyWords;

import grouping.Place;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class KeyWord {
    private static final double PERCENT_OF_ALL_OCCURRENCES_WHEN_WORD_IS_KEY_WORD = 0.51;
    @Getter
    private String word;
    @Getter
    private boolean isReady;
    @Getter
    private Place keyWordFor;
    @Getter
    private boolean isKeyWord;
    private HashMap<String, Integer> placeToOccurrenceMap;

    public KeyWord(String word) {
        this.word = word;
        placeToOccurrenceMap = new HashMap<>();
        for (Place place : Place.values()) {
            placeToOccurrenceMap.put(place.label, 0);
        }
        this.isReady = false;
        this.isKeyWord = false;
    }

    public void trainDone() {
        isReady = true;
        long allOccurrences = placeToOccurrenceMap.values().stream().mapToInt(Integer::intValue).sum();
        for (Map.Entry<String, Integer> entry : placeToOccurrenceMap.entrySet()) {
            if (entry.getValue() > 0 && entry.getValue() > PERCENT_OF_ALL_OCCURRENCES_WHEN_WORD_IS_KEY_WORD * allOccurrences) {
                keyWordFor = Place.valueOfLabel(entry.getKey());
                this.isKeyWord = true;
            }
        }
    }

    public void train(Place place) {
        placeToOccurrenceMap.put(place.label, placeToOccurrenceMap.get(place.label) + 1);
    }
}

