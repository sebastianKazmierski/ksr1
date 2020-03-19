package loadData.articleCratorsFromXml;

import data.Article;
import loadData.tagsFilter.TagFilter;
import org.w3c.dom.Element;

@FunctionalInterface
public interface ArticleReader {
    Article read(Element eElement, TagFilter placeFilter);
}
