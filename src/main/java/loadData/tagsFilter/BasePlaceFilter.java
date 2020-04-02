package loadData.tagsFilter;

import grouping.Place;

import java.util.ArrayList;
import java.util.List;

public class BasePlaceFilter implements TagFilter {

    public final List<String> ALLOWED_PLACES;

    public BasePlaceFilter() {
        this.ALLOWED_PLACES = new ArrayList<>();
        for (Place value : Place.values()) {
            this.ALLOWED_PLACES.add(value.label);
        }
    }

    @Override
    public boolean isCorrect(List<String> places) {
        if (places.size() != 1) {
            return false;
        }
        return this.ALLOWED_PLACES.contains(places.get(0));
    }
}
