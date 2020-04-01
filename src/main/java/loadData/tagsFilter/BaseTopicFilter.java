package loadData.tagsFilter;

import constants.Constants;

import java.util.List;

public class BaseTopicFilter implements TagFilter {
    @Override
    public boolean isCorrect(List<String> topics) {
        if (topics.size() != 1) {
            return false;
        }
        return Constants.ALLOWED_TOPICS.contains(topics.get(0));
    }
}
