package featuresModels.keyWords;

import grouping.Label;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Word<T extends Label<T>> {
    private static final double PERCENT_OF_ALL_OCCURRENCES_WHEN_WORD_IS_KEY_WORD = 0.51;
    @Getter
    private String word;
    @Getter
    private boolean isReady;
    @Getter
    private T keyWordFor;
    @Getter
    private boolean isKeyWord;
    private HashMap<T, Integer> placeToOccurrenceMap;
    T[] enumConstants;

    public Word(String word, Class<T> tClass) {
        this.enumConstants = tClass.getEnumConstants();
        this.word = word;
        this.placeToOccurrenceMap = new HashMap<>();
        for (T place : this.enumConstants) {
            this.placeToOccurrenceMap.put(place, 0);
        }
        this.isReady = false;
        this.isKeyWord = false;
    }

    public void trainDone() {
        this.isReady = true;
        long allOccurrences = this.placeToOccurrenceMap.values().stream().mapToInt(Integer::intValue).sum();
        for (Map.Entry<T, Integer> entry : this.placeToOccurrenceMap.entrySet()) {
            if (entry.getValue() > 0 && entry.getValue() > PERCENT_OF_ALL_OCCURRENCES_WHEN_WORD_IS_KEY_WORD * allOccurrences) {
                this.keyWordFor = entry.getKey();
                this.isKeyWord = true;
            }
        }
    }

    public void train(T place) {
        this.placeToOccurrenceMap.put(place, this.placeToOccurrenceMap.get(place) + 1);
    }
}

