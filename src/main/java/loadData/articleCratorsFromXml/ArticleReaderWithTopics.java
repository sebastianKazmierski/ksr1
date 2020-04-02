package loadData.articleCratorsFromXml;

import data.Article;
import grouping.Topic;
import loadData.tagsFilter.TagFilter;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticleReaderWithTopics implements ArticleReader {
    HashMap<String, Integer> topicsOccurences = new HashMap<>();

    public HashMap<String, Integer> getTopicsOccurences() {
        return this.topicsOccurences;
    }

    @Override
    public Article read(Element eElement, TagFilter topicFilter) {
        String content;
        List<String> topics = new ArrayList<>();
        try {
            content = eElement.getElementsByTagName("BODY").item(0).getTextContent();
            for (int i = 0; i < eElement.getElementsByTagName("TOPICS").item(0).getChildNodes().getLength(); i++) {
                topics.add(eElement.getElementsByTagName("TOPICS").item(0).getChildNodes().item(i).getTextContent());
            }
        } catch (NullPointerException e) {
            return null;
        }
        if (topicFilter.isCorrect(topics)) {
            if (this.topicsOccurences.containsKey(topics.get(0))) {
                this.topicsOccurences.put(topics.get(0), this.topicsOccurences.get(topics.get(0)) + 1);
            } else {
                this.topicsOccurences.put(topics.get(0), 1);
            }
            return new Article<>(content, Topic.valueOfLabel(topics.get(0)));
        }
        return null;
    }
}
