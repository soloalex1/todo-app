package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.MD5;
import model.User;
import model.UserDAO;

public class LoginServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // nesse servlet é feito todo o tratamento de login, com verificação e validação no banco de dados da aplicação.
        // utilizamos cookies para enviar os dados para o cliente de maneira mais prática
	
        // recebendo os atributos encaminhados pela requisição
        String username = request.getParameter("username");
        String senha = request.getParameter("senha");
        String rememberMe = request.getParameter("checkbox");
	RequestDispatcher rd;
        
	// verificando se login e senha foram enviados antes de continuar
	if (username == null | senha == null) {
	    response.sendRedirect("index.jsp");
	    return;
	}
        
        // verificando se a checkbox foi marcada
        if(rememberMe != null && rememberMe.equals("true")){
            
            // se sim, cria dois cookies (um pra checkbox, outro pro login)
            Cookie c1 = new Cookie("remember", "true");
            Cookie c2 = new Cookie("userlogin", username);
            
            // seta a data de expiração dos cookies para o máximo dos inteiros
            c1.setMaxAge(Integer.MAX_VALUE);
            c2.setMaxAge(Integer.MAX_VALUE);
            
            // salva os cookies na memória do navegador do cliente
            response.addCookie(c1);
            response.addCookie(c2);
        }
        
	// criando as instâncias de DAO e MD5
	UserDAO dao = new UserDAO();
        MD5 md5 = new MD5();
        User u = null;
	
        try {
            senha = md5.getMD5(senha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // se nenhum campo estiver vazio, pesquisa o username fornecido através do getUser()
	if (!username.equals("") && !senha.equals("")) {
	    u = dao.getUser(username);
	    
            // se não encontrar o usuário no banco
	    if(u == null){
		request.setAttribute("warning", "combinação de usuário e senha não encontrada");
		rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
              // se encontrar
	    } else {
                
                // se a senha estiver correta
		if (senha.equals(u.getPassword())) {
		    request.setAttribute("warning", null);
		    request.setAttribute("username", u.getLogin());
		    
		    HttpSession session = request.getSession();
		    session.setAttribute("username", username);
		    
		    rd = request.getRequestDispatcher("WEB-INF/list.jsp");
		    rd.forward(request, response);
                  
                  // se não estiver
		} else {
		    request.setAttribute("warning", "senha incorreta.");
		    rd = request.getRequestDispatcher("index.jsp");
		    rd.forward(request, response); 
		}
	    }
	} else {
	    request.setAttribute("userLogin", null);
	    request.setAttribute("warning", "Usuário e senha faltando");
	    rd = request.getRequestDispatcher("index.jsp");
	    rd.forward(request, response);
        }
    }
}