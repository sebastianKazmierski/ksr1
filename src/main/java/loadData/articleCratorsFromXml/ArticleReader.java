package loadData.articleCratorsFromXml;

import data.Article;
import loadData.tagsFilter.TagFilter;
import org.w3c.dom.Element;

import java.util.HashMap;

@FunctionalInterface
public interface ArticleReader {
    Article read(Element eElement, TagFilter placeFilter);

    default HashMap<String, Integer> getTopicsOccurences() {
        return  null;
    }
}
