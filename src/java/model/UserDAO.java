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
            String url = "jdbc:postgresql://app.ibituruna.virtual.ufc.br:5432/cadastrousuarios";
            String usuario = "progweb1";
            String senha = "ufc123@";
            Connection c = DriverManager.getConnection(url, usuario, senha);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT id, nome, email, nascimento, login, senha FROM usuario");
            while (rs.next()) {
                User u = new User();
                u.setName(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("senha"));
                result.add(u);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}