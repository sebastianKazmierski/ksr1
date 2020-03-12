package loadData;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.*;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadXMLFile {

    public static void main(String argv[]) {

        try {

            Reader fileReader = new FileReader("src/main/resources/textXML/reut2-000.xml");

            char[] chars = IOUtils.toCharArray(fileReader);
            FileValidator fileValidator = new XmlValidator();
            CharBuffer charBuffer = fileValidator.validate(chars);

            InputStream inputStream = IOUtils.toInputStream(charBuffer,"UTF-8");

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

                    System.out.println(temp);
                    try {
                        System.out.println("First Name : " + eElement.getElementsByTagName("BODY").item(0).getTextContent());
                    } catch (NullPointerException e) {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}