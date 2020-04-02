package grouping;

import java.util.HashMap;
import java.util.Map;

public enum Topic implements Label<Topic> {
    EARN("earn"),
    ACQ("acq");

    public final String label;

    private static final Map<String, Topic> BY_LABEL = new HashMap<>();

    static {
        for (Topic e : values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    Topic(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static Topic valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    @Override
    public String description() {
        return "Topic";
    }
}
