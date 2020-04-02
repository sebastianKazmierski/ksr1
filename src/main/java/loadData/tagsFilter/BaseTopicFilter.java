package loadData.tagsFilter;

import grouping.Topic;

import java.util.ArrayList;
import java.util.List;

public class BaseTopicFilter implements TagFilter {

    public final List<String> ALLOWED_TOPICS;

    public BaseTopicFilter() {
        this.ALLOWED_TOPICS = new ArrayList<>();
        for (Topic value : Topic.values()) {
            this.ALLOWED_TOPICS.add(value.label);
        }
    }

    @Override
    public boolean isCorrect(List<String> topics) {
        if (topics.size() != 1) {
            return false;
        }

        return this.ALLOWED_TOPICS.contains(topics.get(0));
    }
}
