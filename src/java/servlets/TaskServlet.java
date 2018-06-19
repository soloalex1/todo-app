package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jdk.internal.org.xml.sax.InputSource;
import model.Task;
import model.TaskDAO;
import model.User;
import model.UserDAO;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class TaskServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String[] data = request.getParameterValues("data");*/
        String userLogin = request.getParameter("userLogin");
        String tasks = request.getParameter("tasks");
        
        UserDAO ud = new UserDAO();
        User u = ud.getUser(userLogin);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document d = null;
        try {
            
            builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(tasks.getBytes());
            d = builder.parse(is);
        } catch (ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
        
        d.getDocumentElement().normalize();
        NodeList nodeList = d.getElementsByTagName("task");
        
        ArrayList<Task> tl = new ArrayList<>();
        
        for (int i=0; i< nodeList.getLength(); i++) {
            
            Node node = nodeList.item(i);
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
                tl.add(t);
            }
        }
        
        TaskDAO td = new TaskDAO();
        td.setTasks(u, tl);
    }
}