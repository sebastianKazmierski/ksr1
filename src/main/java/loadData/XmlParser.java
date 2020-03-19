package loadData;

import data.Article;
import data.ArticleStore;
import loadData.articleCratorsFromXml.ArticleReader;
import loadData.tagsFilter.TagFilter;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.nio.CharBuffer;

public class XmlParser {
    public void readArticles(CharBuffer charBuffer, ArticleStore articleStore, ArticleReader articleReader, TagFilter placeFilter) {
        try {
            InputStream inputStream = IOUtils.toInputStream(charBuffer, "UTF-8");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("REUTERS");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Article article = articleReader.read(eElement, placeFilter);
                    if (article != null) {
                        articleStore.add(article);
                    }
                }
            }
        } catch (Exception e) {
            System.err.print(e);
        }
    }

}
