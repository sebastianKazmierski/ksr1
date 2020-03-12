package loadData;

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
    public void parse(CharBuffer charBuffer) {
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
                    String content, place;
                    try {
                        content = eElement.getElementsByTagName("BODY").item(0).getTextContent();
                        place = eElement.getElementsByTagName("PLACES").item(0).getTextContent();
                    } catch (NullPointerException e) {
                        continue;
                    }
                    System.out.println(place);
                    System.out.println(content);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
