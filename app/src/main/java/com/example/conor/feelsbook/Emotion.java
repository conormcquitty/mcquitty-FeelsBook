package com.example.conor.feelsbook;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 */
public class Emotion implements Serializable, Comparable<Emotion> {

    private String emotion;
    public Date date;
    public String comment;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);

    public Emotion(String emotion){
        this.date = new Date();
        this.comment = null;
        this.emotion = emotion;
        this.sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    }


    public String toString(){
        return String.format(emotion + "\n" + sdf.format(this.date));
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

    @Override
    public int compareTo(@NonNull Emotion emotion) {
        return date.compareTo(emotion.getDate());
    }
}
