package grouping;

import java.util.HashMap;
import java.util.Map;

public enum Place implements Label<Place>{
    WEST_GERMANY("west-germany"),
    USA("usa"),
    FRANCE("france"),
    UK("uk"),
    CANADA("canada"),
    JAPAN("japan");

    public final String label;

    private static final Map<String, Place> BY_LABEL = new HashMap<>();

    static {
        for (Place e: values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    Place(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static Place valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
