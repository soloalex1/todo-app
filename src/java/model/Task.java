package model;

import java.io.Serializable;

public class Task implements Serializable {
    
    private String title;
    private String description;
    private boolean isOk;
    
    Task(String title, String desc){
        this.title = title;
        this.description = desc;
        this.isOk = false;
    }
    
    Task(){
        this.isOk = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public boolean getStat() {
        return isOk;
    }

    public void setStat(boolean isOk) {
        this.isOk = isOk;
    }
}