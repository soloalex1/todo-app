package model;

import java.util.ArrayList;

public class User {
    
    private String name;
    private String login;
    private String password;
    private String email;
    private String picture;
    private int id;
    protected ArrayList<Task> taskList;
            
    User(){
        this.taskList = new ArrayList<>();
    }
    
    public int getID() {
	return this.id;
    }

    public void setID(int id) {
	this.id = id;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    /**
     * @return the taskList
     */
    public ArrayList<Task> getTaskList()
    {
	return taskList;
    }

}