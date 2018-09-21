package com.example.conor.feelsbook;

//import android.util.Log;

import java.io.Serializable;
import java.util.Date;

public class Emotion implements Serializable {

    private String emotion;
    private Date date;
    private String comment;
    private static final Integer MAX_COMMENT_LENGTH = 100;




    public Emotion(String emotion){
        this.date = new Date();
        this.comment = null;
        this.emotion = emotion;
    }

    public String getEmotion(){
        return this.emotion = emotion;
    }

    public Date getDate() {
        return this.date;
    }

    public String getComment(){
        return this.comment;
    }
}