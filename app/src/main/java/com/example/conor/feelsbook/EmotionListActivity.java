package com.example.conor.feelsbook;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class EmotionListActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_list);
        Log.d("EmotionListPage", "Reached");
    }


}
