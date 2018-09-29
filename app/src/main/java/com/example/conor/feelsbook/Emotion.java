package com.example.conor.feelsbook;

//import android.util.Log;

import java.io.Serializable;
import java.util.Date;

public class Emotion implements Serializable {

    private String emotion;
    public Date date;
    public String comment;
    private static final Integer MAX_COMMENT_LENGTH = 100;




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

/*
    public boolean equals(Emotion emotion) {
        boolean a = false;
        if (this == emotion) {
            a = true;
        }
        if (emotion == null){
            a = false;
        }
        return a;
    }
*/

}
