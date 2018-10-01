package com.example.conor.feelsbook;

//import android.util.Log;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EditEmotion extends AppCompatActivity {

    String SelectedEmotion;
    String commentString;



    //TODO: Remove functionality from onCreate function and move to other function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emotion);

        Intent i = getIntent();
        SelectedEmotion = (String)i.getSerializableExtra("SelectedEmotion");
        final Emotion emotion = (Emotion)i.getSerializableExtra("Emotion");

        //Saves the emotion to the array list
        Button saveButton = (Button) findViewById(R.id.SaveButtonEditEmotionPage);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                saveValues(emotion);
            }
        });

        Button deleteButton = (Button) findViewById(R.id.DeleteButtonEditEmotionPage);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                //delete(emotion);

                Intent intent = new Intent(v.getContext(), EmotionListActivity.class);
                intent.putExtra("Action", "Delete");
                intent.putExtra("Emotion", emotion);
                //String testComment = emotion.getComment();
                //Log.d ("onPress Comment", testComment);
                startActivity(intent);

            }
        });

        //Getting the header text
        TextView HeaderText = (TextView) findViewById(R.id.EmotionHeaderEditEmotionPage);
        HeaderText.setText(SelectedEmotion);

        //Getting the date
        //from https://mincong-h.github.io/2017/02/16/convert-date-to-string-in-java/#javautildate
        Date date = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String datestring = sdf.format(date);
        EditText DateText = (EditText) findViewById(R.id.DateEditTextBoxEditEmotionPage);
        DateText.setText(datestring);
        //TODO: Throw exceptions when date isn't entered correctly!

        //Getting the comment
        if (emotion == null) {
            commentString = null;
        }
        else {
            commentString = emotion.getComment();
            //TODO: Throw exceptions when comment is too long.
        }

        if (commentString!=null){
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setText(commentString);
        }

        else {
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setText("Enter Comment");
        }
    }

/*
    public void onStart(){
        super.onStart();
        //Getting Intent from Emotion List Activity page. Selecting Emotion object to work with.

        /*
        if (emotion != null) {
            String testComment = emotion.getComment();
            Log.d("onStart Comment", testComment);
        }
        //
       // String Comment = (String)i.getSerializableExtra("Comment");

        //Getting the header text
        TextView HeaderText = (TextView) findViewById(R.id.EmotionHeaderEditEmotionPage);
        HeaderText.setText(SelectedEmotion);

        //Getting the date
        //from https://mincong-h.github.io/2017/02/16/convert-date-to-string-in-java/#javautildate
        Date date = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String datestring = sdf.format(date);
        EditText DateText = (EditText) findViewById(R.id.DateEditTextBoxEditEmotionPage);
        DateText.setText(datestring);
        //TODO: Throw exceptions when date isn't entered correctly!

        //Getting the comment
        if (emotion == null) {
            commentString = null;
        }
        else {
            commentString = emotion.getComment();
            //TODO: Throw exceptions when comment is too long.
        }

        if (commentString!=null){
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setText(commentString);
        }

        else {
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setText("Enter Comment");
        }
    }
*/

    //Saving entered comments and date to Emotion object
    private void saveValues(Emotion emotion){

        //if it's a new emotion, make a new Emotion
        if (emotion == null) {
            emotion  = new Emotion(SelectedEmotion);
        }

        //Save comment
        EditText commentText = (EditText) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
        String commentToSave = commentText.getText().toString();

        if (commentToSave == null) {
            commentToSave = " ";
        }

        emotion.comment = commentToSave;

        //Save date
        EditText dateText = (EditText) findViewById(R.id.DateEditTextBoxEditEmotionPage);
        String dateToSave = dateText.getText().toString();

        //formatting date
        // from https://www.mkyong.com/java/how-to-convert-string-to-date-java/
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
           // Log.d("Date", dateToSave);
            Date date = sdf.parse(dateToSave);
            emotion.date = date;
        }
        catch (ParseException a){
            a.printStackTrace();
        }

        Intent intent = new Intent(this, EmotionListActivity.class);
        intent.putExtra("Emotion", emotion);
        startActivity(intent);
    }

    void delete (Emotion emotion){

        Intent intent = new Intent(this, EmotionListActivity.class);
        intent.putExtra("Action", "Delete");
        intent.putExtra("Emotion", emotion);
        //String testComment = emotion.getComment();
        //Log.d ("onPress Comment", testComment);
        startActivity(intent);
    }
}
