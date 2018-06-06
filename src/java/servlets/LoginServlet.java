package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MD5;
import model.User;
import model.UserDAO;

public class LoginServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // nesse servlet é feito todo o tratamento de login, com verificação e validação no banco de dados da aplicação.
        
        // pegando os atributos encaminhados pela requisição
        String username = request.getParameter("username");
        String senha = request.getParameter("senha");
        
        
	// criando as instâncias de DAO e MD5
	UserDAO dao = new UserDAO();
        MD5 md5 = new MD5();
        RequestDispatcher rd;
        User u = null;
        
        try {
            senha = md5.getMD5(senha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // se nenhum campo estiver vazio, pesquisa o username fornecido através do getUser()
	if (username != null || senha != null) {
            u = dao.getUser(username, senha);
            if(u == null){
                request.setAttribute("warning", "Usuário ou senha incorretos");
            } else {
                request.setAttribute("user_id", u.getID());
                request.setAttribute("senha", u.getPassword());
            }
	    request.setAttribute("warning", null);
	    rd = request.getRequestDispatcher("WEB-INF/list.jsp");
	    rd.forward(request, response);
	} else {
	    request.setAttribute("user_id", null);
	    request.setAttribute("warning", "Usuário ou senha incorretos");
	    rd = request.getRequestDispatcher("index.jsp");
	    rd.forward(request, response);
        }
    }
}