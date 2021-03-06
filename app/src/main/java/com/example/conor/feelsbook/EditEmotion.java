package com.example.conor.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EditEmotion extends AppCompatActivity {

    private static final String FILENAME = "Emotion.sav";
    private ArrayList<Emotion> emotionList;
    private String selectedEmotion;
    private String commentString;
    private String action;
    private int index;
    private Date date;

    //TODO: Remove functionality from onCreate function and move to other function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emotion);
        loadFromFile();

        Intent i = getIntent();
        selectedEmotion = (String)i.getSerializableExtra("SelectedEmotion");
        final Emotion emotion = (Emotion)i.getSerializableExtra("Emotion");
        index = i.getIntExtra("Index", -1);

        //Saves the emotion to the array list
        Button saveButton = (Button) findViewById(R.id.SaveButtonEditEmotionPage);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                saveValues(emotion);
            }
        });

        //Deletes the emotion from the emotion list
        Button deleteButton = (Button) findViewById(R.id.DeleteButtonEditEmotionPage);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                delete(index);
            }
        });
        displayValues(selectedEmotion, emotion);
    }

    /**
     * Displays the values (Emotion name, comment, time) on the Edit Emotion page
     * @param selectedEmotion
     * @param emotion
     */
    void displayValues(String selectedEmotion, Emotion emotion){
        //Displaying the Emotion name in the header text
        TextView HeaderText = (TextView) findViewById(R.id.EmotionHeaderEditEmotionPage);
        HeaderText.setText(selectedEmotion);

        //Displaying the date
        //from https://mincong-h.github.io/2017/02/16/convert-date-to-string-in-java/#javautildate
        //TODO: Throw exceptions when date isn't entered correctly!
        if (emotion == null){
            date = new Date();
        }
        else{
            date = emotion.getDate();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateString = sdf.format(date);
        EditText DateText = (EditText) findViewById(R.id.DateEditTextBoxEditEmotionPage);
        DateText.setText(dateString);

        //Getting the comment
        //TODO: Throw exception when comment is too long
        if (emotion == null) {
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            CommentText.setHint("Enter Comment");
        }

        else {
            TextView CommentText = (TextView) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
            commentString = emotion.getComment();
            CommentText.setHint("Enter Comment");
            CommentText.setText(commentString);
        }
    }


    /**
     * Saves the user entered values (comment and date) on the edit page to the Emotion object
     * @param emotion
     */
    private void saveValues(Emotion emotion){
        if (emotion == null) {
            action = "NewSave";
            emotion  = new Emotion(selectedEmotion);
        }
        else{
            action = "OldSave";
        }

        //Save comment
        EditText commentText = (EditText) findViewById(R.id.CommentEditTextBoxEditEmotionPage);
        String commentToSave = commentText.getText().toString();
        emotion.comment = commentToSave;

        //Save date
        EditText dateText = (EditText) findViewById(R.id.DateEditTextBoxEditEmotionPage);
        String dateToSave = dateText.getText().toString();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = sdf.parse(dateToSave);
            emotion.date = date;
            //Adds a new Emotion to the array list, or updates and old one
            if (action.equals("NewSave")){
                emotionList.add(emotion);
            }
            if(action.equals("OldSave")){
                emotionList.set(index,emotion);
            }
            saveInFile();
            Intent intent = new Intent(this, EmotionListActivity.class);
            startActivity(intent);
        }
        catch (ParseException e){
            e.printStackTrace();
            Toast.makeText(EditEmotion.this, "Invalid Date String Entered", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Deletes an emotion object from the array list. If it is a new emotion entry, upon delete press
     * the app will return to the main screen.
     * @param index
     */
    void delete (int index){
        if (index == -1){
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
        else {
            emotionList.remove(index);
            saveInFile();
            Intent intent = new Intent(this, EmotionListActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Loads an array list from a file
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson(); //library to save objects
            Type listType = new TypeToken<ArrayList<Emotion>>() {
            }.getType();
            emotionList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            emotionList = new ArrayList<Emotion>();
        }
    }

    /**
     * Saves an array list to a file
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(emotionList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
