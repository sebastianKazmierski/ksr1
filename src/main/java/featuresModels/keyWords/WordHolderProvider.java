package featuresModels.keyWords;

import grouping.Label;
import lombok.Getter;
import lombok.Setter;

public class WordHolderProvider<T extends Label<T>> {
    @Getter
    @Setter
    WordHolder<T> wordHolder;
}
