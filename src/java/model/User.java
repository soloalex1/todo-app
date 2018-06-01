package model;

import java.util.ArrayList;

public class User {
    
    private String name;
    private String login;
    private String password;
    private String email;
    private static int generalID = 1;
    private int id;
    protected ArrayList<Task> taskList;
            
    User(){
        this.id = ++generalID;
        this.taskList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return this.login;
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
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getID(){
        return Integer.toString(this.id);
    }

}