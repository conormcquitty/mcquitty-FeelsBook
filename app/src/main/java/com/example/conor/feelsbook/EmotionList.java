package com.example.conor.feelsbook;

//import android.util.Log;

import java.util.ArrayList;


public class EmotionList {

    //EmotionList emotionList = new EmotionList();


    //creating Array list which contains type Emotion
    public  ArrayList<Emotion> emotionlist;

    //Constructor
    public EmotionList(){
        emotionlist = new ArrayList<Emotion>();
    }

    //List of Recorded Emotions
    /*
    public Collection<Emotion> getEmotion(){
        return emotionlist;
    }
*/

    //Add an emotion to emotionList
    public void addEmotion (Emotion emotion){
        emotionlist.add(emotion);
       //Log.d("Emotion", "Added");
    }

    //Print emotionList - for testing purposes
    public void printEmotionList(){

    }
/*
    public void removeEmotion (Emotion emotion){
        emotionlist.remove(emotion);
    }

//    public Emotion chooseEmotion(){
//        //come up with a way to index
//    }
*/

}
