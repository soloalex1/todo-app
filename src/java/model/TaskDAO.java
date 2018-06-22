package model;

import java.sql.*;
import java.util.ArrayList;

public class TaskDAO {
    
    public ArrayList<Task> getTask(User u){
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            
            //configurando a conexão com o banco de dados: url, usuário do BD e senha
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/todoapp";
            String usuarioBD = "bogosort";
            String senhaBD = "avilar123";
            Connection c = DriverManager.getConnection(url, usuarioBD, senhaBD);
            
            /* 
                utilizando PreparedStatement pra dinamizar a consulta, dessa vez com o id do usuário
                que foi retornado em UserDAO.getUser();
            */
            
            PreparedStatement s = c.prepareStatement("SELECT * FROM tasks WHERE user_id = ?");
            s.setInt(1, u.getID());
            ResultSet rs = s.executeQuery();
            
            // obtendo todas as tasks, com titulo, descrição e status, e adicionando pro array de retorno
            while (rs.next()) {
              Task t = new Task();
              t.setTitle(rs.getString("title"));
              t.setDescription(rs.getString("description"));
              t.setStat(rs.getBoolean("is_ok"));
              taskList.add(t);
            }
            
            rs.close();
            s.close();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
        
        //retorno o array, e no servlet eu substituo User.taskList por essa taskList aqui :)
        return taskList;        
    }

    public void setTasks(User u, ArrayList<Task> taskList) {
        Connection c = null;
        try {
            //configurando a conexão com o banco de dados: url, usuário do BD e senha
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/todoapp";
            String usuarioBD = "bogosort";
            String senhaBD = "avilar123";
            c = DriverManager.getConnection(url, usuarioBD, senhaBD);
            
            // colocando em modo de transação SQL: assim, eu posso garantir uma maior segurança na atualização do banco
            // as inserções só são validadas se todas obtiverem sucesso, caso contrário, tudo é descartado           
            c.setAutoCommit(false);
            PreparedStatement s;
            Statement st = c.createStatement();
            
            // limpando as tasks do usuário para sobrescrita
            st.executeUpdate("DELETE FROM tasks WHERE user_id = " + u.getID());
                       
            // percorre o ArrayList recebido e insere cada um dos valores
            for (Task task : taskList) {
                s = c.prepareStatement("INSERT INTO tasks (title, description, is_ok, user_id) VALUES (?, ?, ?, ?)");
                s.setString(1, task.getTitle());
                s.setString(2, task.getDescription());
                s.setBoolean(3, task.getStat());
                s.setInt(4, u.getID());
                
                // executo as alterações em memória local - ainda não foi pro banco
                s.executeUpdate();
                s.close();
            }
            
            // aqui eu aplico as alterações no banco e fecho a conexão, se tiver dado certo
            c.commit();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            try {
                
                // se alguma coisa tiver falhado, dá o rollback e descarta tudo
                c.rollback();
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
}