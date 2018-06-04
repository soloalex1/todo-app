package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    
    public User getUser(String login, String senha) {
        User resultUser = null;
        try {
            
            //configurando a conexão com o banco de dados: url, usuário do BD e senha
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/todoapp";
            String usuarioBD = "bogosort";
            String senhaBD = "avilar123";
            Connection c = DriverManager.getConnection(url, usuarioBD, senhaBD);
            
            //utilizando PreparedStatement pra dinamizar a consulta de acordo com o login do usuário
            PreparedStatement s = c.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");
            s.setString(1, login);
            s.setString(2, senha);
            ResultSet rs = s.executeQuery();
            
            if(rs.next() == false){
            // se a consulta não retornar nenhum usuário, retorna o usuário nulo
                return resultUser;
            } else {
            // se retornar, inicializa um novo usuário e o TaskDAO
           
                resultUser = new User();
                TaskDAO dao = new TaskDAO();
                
                // pega o dado da primeira linha do ResultSet antes de chamar o método next()
                // é uma solução que funciona em todos os tipos de bancos de dados
                
                do {
                    resultUser.setID(rs.getInt("id"));
                    resultUser.setLogin(rs.getString("login"));
                    resultUser.setPassword(rs.getString("senha"));
                    resultUser.taskList = dao.getTask(resultUser);
                } while (rs.next());
            }
            
            rs.close();
            s.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
        return resultUser;
    }
}