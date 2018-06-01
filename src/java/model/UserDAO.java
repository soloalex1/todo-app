package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    
    public User getUser(String login) {
        User resultUser = null;
        try {
            
            //configurando a conexão com o banco de dados: url, usuário do BD e senha
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/todoapp";
            String usuario = "bogosort";
            String senha = "avilar123";
            Connection c = DriverManager.getConnection(url, usuario, senha);
            
            //utilizando PreparedStatement pra dinamizar a consulta de acordo com o login do usuário
            PreparedStatement s = c.prepareStatement("SELECT * FROM usuario WHERE login = ?");
            s.setString(1, login);
            ResultSet rs = s.executeQuery();
            
            //o ponteiro de ResultSet aponta pra linha zero, então eu preciso dar um next pra pegar
            while (rs.next()) {
                
              /*
                
              aqui eu inicializo um usuário (já que a consulta só me retorna um), crio um objeto TaskDAO
              e chamo o getTask() pra pegar todas as Tasks daquele usuário, 
                
              */  
                
              resultUser = new User();
              TaskDAO dao = new TaskDAO();
              resultUser.taskList = dao.getTask(resultUser);
            }
            
            rs.close();
            s.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultUser;
    }

}