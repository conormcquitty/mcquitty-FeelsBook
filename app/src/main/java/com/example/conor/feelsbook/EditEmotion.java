package com.example.conor.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class EditEmotion extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emotion);

        //Getting Intent from previous page. Selecting Emotion object to work with.
        Intent i = getIntent();
        Emotion emotion = (Emotion)i.getSerializableExtra("SelectedEmotion");

        //Getting the header text
        String selectedEmotion = emotion.getEmotion();
        TextView HeaderText = (TextView) findViewById(R.id.EmotionHeaderEditEmotionPage);
        HeaderText.setText(selectedEmotion);

        //Getting the date
        Date date = emotion.getDate();
        // taken from https://mincong-h.github.io/2017/02/16/convert-date-to-string-in-java/#javautildate
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String datestring = sdf.format(date);
        EditText DateText = (EditText) findViewById(R.id.DateEditTextBoxEditEmotionPage);
        DateText.setText(datestring);
        //TODO: Throw exceptions when date isn't entered correctly!

        //Getting the comment
        String comment = emotion.getComment();
        if (comment!=null){
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setText(comment);
        }

        else {
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setText("Enter Comment");
        }



    }








}
