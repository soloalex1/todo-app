package model;

import java.util.ArrayList;

public class User {
    
    private String name;
    private String login;
    private String password;
    private String email;
    private static int generalID = 1;
    private int id;
    private ArrayList<Task> taskList;
            
    User(){
        this.id = ++generalID;
        this.taskList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}