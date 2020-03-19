package loadData.tagsFilter;

import constants.Constants;

import java.util.List;

public class BasePlaceFilter implements TagFilter {
    @Override
    public boolean isCorrect(List<String> places) {
        if (places.size() > 1) {
            return false;
        }
        return Constants.ALLOWED_PLACES.contains(places.get(0));
    }
}
