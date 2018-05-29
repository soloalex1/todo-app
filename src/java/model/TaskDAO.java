package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class TaskDAO {
    
    public ArrayList<Task> getTask(User u){
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            
            //configurando a conexão com o banco de dados: url, usuário do BD e senha
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/todoapp";
            String usuario = "bogosort";
            String senha = "avilar123";
            Connection c = DriverManager.getConnection(url, usuario, senha);
            
            /* 
                utilizando PreparedStatement pra dinamizar a consulta, dessa vez com o id do usuário
                que foi retornado em UserDAO.getUser();
            */
            
            PreparedStatement s = c.prepareStatement("SELECT * FROM tasks WHERE user_id = ?");
            s.setString(1, u.getID());
            ResultSet rs = s.executeQuery();
            
            //aqui eu pego todas as tasks, com titulo, descrição e status, e adiciono pro array de retorno
            while (rs.next()) {
              Task t = new Task();
              t.setTitle(rs.getString("title"));
              t.setDesc(rs.getString("desc"));
              t.setStat(rs.getBoolean("is_ok"));
              taskList.add(t);
            }
            
            rs.close();
            s.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //retorno o array, e no servlet eu substituo User.taskList por essa taskList aqui :)
        return taskList;
        
    } 
}