package loadData;

import java.util.List;

@FunctionalInterface
public interface PlacesFilter {
    boolean isCorrect(List<String> places);
}
