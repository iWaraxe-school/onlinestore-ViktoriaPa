package by.issoft.domain.XMLparser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {
    public Map<String, String> parse(){
        Map<String, String> map = new LinkedHashMap<>();
        File file = new File("config.xml");
        DocumentBuilderFactory documentBuilder = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            document = documentBuilder.newDocumentBuilder().parse(file);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        Node root = document.getFirstChild();

        NodeList rootChild = root.getChildNodes();

        Node nodeNameWithNodeText;
        for (int i = 0; i < rootChild.getLength(); i++) {
            if (rootChild.item(i).getNodeType() == Node.ELEMENT_NODE) {
                nodeNameWithNodeText = rootChild.item(i);

                String key = nodeNameWithNodeText.getNodeName();
                String value = nodeNameWithNodeText.getTextContent();
                map.put(key,value);
            }
        }
        return map;
    }
}
