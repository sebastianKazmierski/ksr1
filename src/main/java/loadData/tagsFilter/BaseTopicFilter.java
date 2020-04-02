package loadData.tagsFilter;

import java.util.List;

public class BaseTopicFilter implements TagFilter {
    @Override
    public boolean isCorrect(List<String> topics) {
        if (topics.size() != 1) {
            return false;
        }
        return true;
    }
}
