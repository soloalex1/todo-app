package servlets;

import java.io.*;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.*;
import org.w3c.dom.*;

public class DownloadServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// obtem dados de usuário da session e inicializa variáveis
	HttpSession session = request.getSession();
	String username = (String) session.getAttribute("username");
	
	UserDAO ud = new UserDAO();
	User u = ud.getUser(username);
	
	// cancela operação se usuário não foi encontrado
	if (u == null) {
	    return;
	}
	
	// pega lista de tarefas
	ArrayList<Task> tl = u.getTaskList();
	
	// cria arquivo .txt no caminho especificado (era pre ser dentro do servidor, 
	// mas como o servidor é local então é nos documents mesmo).
	String homeDir = System.getProperty("user.home");
	File f = new File(homeDir + File.separator +"Documents"+ File.separator +"tasks_do_"+ u.getLogin() +".txt");
	
	// abre o FileOutputStream, para depois escrever bytes no arquivo f
	FileOutputStream fout = new FileOutputStream(f);
	
	// abre o PrintStream atraves do fout, para escrever o texto sem precisar manualmente manipular bytes
	PrintStream ps = new PrintStream(fout);
	
	// escreve a Data atual (de quando o arquivo foi criado) e coloca dois newlines
	ps.print(new Date());
	ps.println();
	ps.println();
	
	// escreve as parada
	if (tl.isEmpty()) {
	    ps.print("Não tem nada aqui :)");
	} else {
	    for (Task task : tl) {
		ps.print("[ ");
		ps.print(task.getStat()? "X": " ");
		ps.print(" ] ");
		ps.print(task.getTitle());
		ps.println();
	    }
	}
	
	// fecha o FileOutputStream e o PrintStream (acho que dava pra fechar so o PS sozinho que 
	// ele auto-fecha o fout, mas whatever
	fout.close();
	ps.close();
	
        // pegando o context desse servlet (url e os caralhos)
        ServletContext context = this.getServletContext();
	
        // caminho do arquivo - depois verificar se dá pra fazer com REST
        String filePath = f.getAbsolutePath();
        
        // criando a FileInputStream pra enviar o arquivo pro cliente e recebendo o mimeType
        FileInputStream in = new FileInputStream(f);
        String mimeType = context.getMimeType(filePath);
        
        // se o mimeType for nulo, seta isso aí que eu não sei o que é
        if(mimeType == null){
            mimeType = "application/octet-stream";
        }
        
        // setando o mimeType e o tamanho do conteúdo
        response.setContentType(mimeType);
        response.setContentLength((int) f.length());
        
        // configurando o cabeçalho da resposta HTTP
        String key = "Content-Disposition";
        String value = String.format("attachment; filename=\"%s\"", f.getName());
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