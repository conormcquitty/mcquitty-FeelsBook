package com.example.conor.feelsbook;

import java.io.Serializable;
import java.util.Date;

public class Emotion implements Serializable {

    private String emotion;
    public Date date;
    public String comment;

    public Emotion(String emotion){
        this.date = new Date();
        this.comment = null;
        this.emotion = emotion;
    }

    public String toString(){
        return emotion + "\n" + date;
    }

    public String getEmotionName(){
        return this.emotion = emotion;
    }

    public Date getDate() {
        return this.date;
    }

    public String getComment(){
        return this.comment;
    }

}
