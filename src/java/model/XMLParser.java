package model;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.logging.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 *
 * @author Rafael Avilar
 */
public class XMLParser
{
    
    public ArrayList<Task> stringToTaskList(String xml) {
	
	// inicializa fábricas e objeto Document para tratamento do XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
	
	// transforma string tasks no objeto Document
        try {
            builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8")));
            doc = builder.parse(is);
	    doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
	
	// navega pelo Document e obtem dados das tags para gerar o novo ArrayList<Task>
        NodeList nl = doc.getElementsByTagName("task");
        ArrayList<Task> rs = new ArrayList<>();
        for (int i=0; i< nl.getLength(); i++) {
            
            Node node = nl.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Task t = new Task();
                t.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                t.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                boolean stat = false;
                if (element.getElementsByTagName("stat").item(0).getTextContent().equals("true")) {
                    stat = true;
                }
                t.setStat(stat);
                rs.add(t);
            }
        }
	
	return rs;
    }
}
