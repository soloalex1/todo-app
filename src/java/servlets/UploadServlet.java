package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import model.User;
import model.UserDAO;


// servlet que gerencia o upload de arquivos (ainda precisa de uma página de upload)
public class UploadServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        UserDAO dao = new UserDAO();
        User u = dao.getUser(username);
        
        boolean success = false;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	
        if (isMultipart) {
	    
            try {
		
                DiskFileItemFactory factory = new DiskFileItemFactory();
                String userHome = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "GitHub" + File.separator + "todo-app" + File.separator + "web" + File.separator + "picture";
		
                factory.setRepository(new File(userHome));
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = upload.parseRequest(new ServletRequestContext(request));
                Iterator iter = items.iterator();
		
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {

                    String itemPath = userHome + File.separator + item.getName();
                        String pathChange = "./picture/" + itemPath.substring(itemPath.lastIndexOf(File.separator) + 1);
                        item.write(new File(itemPath));
                        success = true;
                        dao.setPicture(u,pathChange);
                    }
                }
                
            } catch (Exception ex) {
                success = false;
            }
            if (success) {
                request.setAttribute("mensagem", "Arquivos recebido com sucesso");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/list.jsp");
                dispatcher.forward(request, response);
		
            } else {
                request.setAttribute("mensagem", "Não foi possível receber o arquivo");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/list.jsp");
                dispatcher.forward(request, response);
		
            }
        }
    }
}