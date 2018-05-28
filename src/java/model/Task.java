package model;

import java.io.Serializable;

public class Task implements Serializable {
    
    private String title;
    private String desc;
    private boolean isOk;
    
    Task(String title, String desc){
        this.title = title;
        this.desc = desc;
        this.isOk = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getStat() {
        return isOk;
    }

    public void setStat(boolean isOk) {
        this.isOk = isOk;
    }
}