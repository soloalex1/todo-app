package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


// servlet que gerencia o upload de arquivos (ainda precisa de uma página de upload)
public class UploadServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        if (isMultipart) {
            try {
                
                // cria um factory para arquivos no disco
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File("C:/temp"));
                ServletFileUpload upload = new ServletFileUpload();
                
                // cria a lista de FileItem pra receber os arquivos enviados na requisição
                List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
                Iterator<FileItem> iter = items.iterator();
                
                // iterando em cima da lista pra ir criando os arquivos
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField()) {
                        item.write(new File("C:/temp/" + item.getName()));
                    }
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}