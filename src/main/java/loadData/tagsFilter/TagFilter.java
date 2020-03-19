package loadData.tagsFilter;

import java.util.List;

@FunctionalInterface
public interface TagFilter {
    boolean isCorrect(List<String> tags);
}
