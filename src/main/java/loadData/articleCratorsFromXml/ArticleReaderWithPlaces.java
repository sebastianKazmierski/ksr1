package loadData.articleCratorsFromXml;

import data.Article;
import grouping.Place;
import loadData.tagsFilter.TagFilter;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ArticleReaderWithPlaces implements ArticleReader {
    @Override
    public Article<Place> read(Element eElement, TagFilter placeFilter) {
        String content;
        List<String> places = new ArrayList<>();
        try {
            content = eElement.getElementsByTagName("BODY").item(0).getTextContent();
            for (int i = 0; i < eElement.getElementsByTagName("PLACES").item(0).getChildNodes().getLength(); i++) {
                places.add(eElement.getElementsByTagName("PLACES").item(0).getChildNodes().item(i).getTextContent());
            }
        } catch (NullPointerException e) {
            return null;
        }
        if (placeFilter.isCorrect(places)) {
            return new Article<>(content, Place.valueOfLabel(places.get(0)));
        }
        return null;
    }
}
