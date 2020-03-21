package featuresModels.keyWords;

import grouping.Place;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Word {
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

    public Word(String word) {
        this.word = word;
        this.placeToOccurrenceMap = new HashMap<>();
        for (Place place : Place.values()) {
            this.placeToOccurrenceMap.put(place.label, 0);
        }
        this.isReady = false;
        this.isKeyWord = false;
    }

    public void trainDone() {
        this.isReady = true;
        long allOccurrences = this.placeToOccurrenceMap.values().stream().mapToInt(Integer::intValue).sum();
        for (Map.Entry<String, Integer> entry : this.placeToOccurrenceMap.entrySet()) {
            if (entry.getValue() > 0 && entry.getValue() > PERCENT_OF_ALL_OCCURRENCES_WHEN_WORD_IS_KEY_WORD * allOccurrences) {
                this.keyWordFor = Place.valueOfLabel(entry.getKey());
                this.isKeyWord = true;
            }
        }
    }

    public void train(Place place) {
        this.placeToOccurrenceMap.put(place.label, this.placeToOccurrenceMap.get(place.label) + 1);
    }
}

