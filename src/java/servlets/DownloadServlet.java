package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // pegando o context desse servlet (url e os caralhos)
        ServletContext context = this.getServletContext();
        
        // caminho default - depois verificar se dá pra fazer com REST
        String filePath = "c:/Users/Alexandre/Desktop/database.sql";
        
        // criando a FileInputStream pra enviar o arquivo pro cliente e recebendo o mimeType
        File downloadFile = new File(filePath);
        FileInputStream in = new FileInputStream(downloadFile);
        String mimeType = context.getMimeType(filePath);
        
        // se o mimeType for nulo, seta isso aí que eu não sei o que é
        if(mimeType == null){
            mimeType = "application/octet-stream";
        }
        
        // setando o mimeType e o tamanho do conteúdo
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        
        // configurando o cabeçalho da resposta HTTP
        String key = "Content-Disposition";
        String value = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(key, value);
        
        // abrindo o streaming de saída pra resposta
        OutputStream out = response.getOutputStream();
        
        // configurando o tamanho do buffer (deixa assim, tá tranquilo assim)
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        
        // mandando o arquivo pela OutputStream usando o limite estabelecido no buffer
        while((bytesRead = in.read(buffer)) != -1){
            out.write(buffer, 0, bytesRead);
        }
        
        // encerrando o streaming
        in.close();
        out.close();
    }
}