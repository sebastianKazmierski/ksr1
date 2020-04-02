package loadData.articleCratorsFromXml;

import data.Article;
import grouping.Place;
import grouping.Topic;
import loadData.tagsFilter.TagFilter;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ArticleReaderWithTopics implements ArticleReader {
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
            return new Article<>(content, Topic.valueOfLabel(topics.get(0)));
        }
        return null;
    }
}
