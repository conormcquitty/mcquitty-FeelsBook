package com.example.conor.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;

public class EditEmotion extends AppCompatActivity {

    String SelectedEmotion;
    ArrayList<Emotion> emotionList;
    private static final String FILENAME = "EmotionFile.sav";

    //TODO: Remove functionality from onCreate function and move to seperate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emotion);

        Button saveButton = (Button) findViewById(R.id.SaveButtonEditEmotionPage);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                saveValues();
            }
        });

        //Getting Intent from previous page. Selecting Emotion object to work with.
        Intent i = getIntent();

        SelectedEmotion = (String)i.getSerializableExtra("SelectedEmotion");
        emotionList = (ArrayList<Emotion>)i.getSerializableExtra("ArrayList");
        //Getting the header text
       // String selectedEmotion = emotion.getEmotionName();
        TextView HeaderText = (TextView) findViewById(R.id.EmotionHeaderEditEmotionPage);
        HeaderText.setText(SelectedEmotion);

        //Getting the date
        Date date = new Date();
        //Date date = emotion.getDate();
        // taken from https://mincong-h.github.io/2017/02/16/convert-date-to-string-in-java/#javautildate
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String datestring = sdf.format(date);
        EditText DateText = (EditText) findViewById(R.id.DateEditTextBoxEditEmotionPage);
        DateText.setText(datestring);
        //TODO: Throw exceptions when date isn't entered correctly!

        //TODO: Throw exceptions when comment is too long.
        //Getting the comment
        //String comment = emotion.getComment();
        String comment = null;
        if (comment!=null){
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setText(comment);
        }

        else {
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setText("Enter Comment");
        }
    }


    //Saving entered comments and date
    public void saveValues(){

        Emotion emotion = new Emotion (SelectedEmotion);

        //Save comment
        EditText commentText = (EditText) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
        String commentToSave = commentText.getText().toString();
        emotion.comment = commentToSave;


        //Save date
        EditText dateText = (EditText) findViewById(R.id.DateEditTextBoxEditEmotionPage);
        String dateToSave = dateText.getText().toString();

        //https://www.mkyong.com/java/how-to-convert-string-to-date-java/
        //Below code taken from above link
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            Date date = sdf.parse(dateToSave);
            emotion.date = date;
        }
        catch (ParseException a){
            a.printStackTrace();
        }

        emotionList.add(emotion);

        saveInFile();
    }


    //takes a text and date and saves it to our file.
    private void saveInFile() {

        try {
            //creates a file with FILENAME and tells it what it will say in java syntax
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(emotionList, out);

            //String jsonEmotionList =  gson.toJson(emotionList, out);

            //important to flush otherwise you will print garbage
            out.flush();
            fos.close();



        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Intent intent = new Intent(this, EmotionListActivity.class);
        intent.putExtra("EmotionList", emotionList);
       // intent.putExtra("Emotion", emotion);
        startActivity(intent);
    }
}
