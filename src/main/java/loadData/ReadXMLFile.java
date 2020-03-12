package loadData;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadXMLFile {

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("src/main/resources/textXML/reut2-000.xml");
            Reader fileReader = new FileReader(fXmlFile);
            char[] chars = IOUtils.toCharArray(fileReader);

            CharBuffer charBuffer = CharBuffer.allocate(chars.length);
            char current;
            for (char aChar : chars) {
                current = aChar;
                if (current == 0x9 || current == 0xA || current == 0xD || current >= 0x20 && current <= 0xD7FF || current >= 0xE000 && current <= 0xFFFD)
                    charBuffer.append(current);
            }
            InputStream inputStream = IOUtils.toInputStream(charBuffer.toString(),"UTF-16");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("REUTERS");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("First Name : " + eElement.getElementsByTagName("TITLE").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}