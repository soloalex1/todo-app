package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    
    public List<User> getAll() {
        List<User> result = new ArrayList<User>();
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/todoapp";
            String usuariobd = "bogosort";
            String senhabd = "avilar123";
            Connection c = DriverManager.getConnection(url, usuariobd, senhabd);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT login FROM usuario");
            while (rs.next()) {
                User u = new User();
                u.setLogin(rs.getString("login"));
                System.out.println(u.getLogin());
                result.add(u);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}