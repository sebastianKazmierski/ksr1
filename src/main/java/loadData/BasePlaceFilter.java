package loadData;

import constants.Constants;

import java.util.List;

public class BasePlaceFilter implements PlacesFilter {
    @Override
    public boolean isCorrect(List<String> places) {
        if (places.size() > 1) {
            return false;
        }
        return Constants.ALLOWED_PLACES.contains(places.get(0));
    }
}
