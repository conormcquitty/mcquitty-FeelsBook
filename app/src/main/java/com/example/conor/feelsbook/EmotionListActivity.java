package com.example.conor.feelsbook;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import java.util.ArrayList;



public class EmotionListActivity extends Activity {

    //code derived from lab activity LonelyTwitter
    private static final String FILENAME = "EmotionFile3.sav";
    private ListView oldEmotionList;
    Emotion emotion;
    ArrayList<Emotion> emotionList;
    //ArrayList<Emotion> emotionList = new ArrayList<Emotion>();
    ArrayAdapter<Emotion> adapter;
    String action;
    //String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_list);
        oldEmotionList = (ListView) findViewById(R.id.EmotionListView);



        //Returns to home screen on press
        Button returnButton = (Button) findViewById(R.id.ReturnToHomeButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                saveInFile();
                Intent i = new Intent(v.getContext(), MainScreen.class);
                startActivity(i);
            }
        });

        Button clearButton = (Button) findViewById(R.id.ClearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                emotionList.clear();
                saveInFile();
                adapter.notifyDataSetChanged();
            }
        });


        //Takes you to edit emotion details when an emotion on list view is clicked
        oldEmotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object object = oldEmotionList.getItemAtPosition(position);
                    Emotion emotion = Emotion.class.cast(object);
                    String SelectedEmotion = emotion.getEmotionName();
                    String comment = emotion.getComment();
                    Intent i = new Intent(view.getContext(), EditEmotion.class);
                    i.putExtra("SelectedEmotion", SelectedEmotion);
                    i.putExtra("Emotion", emotion);
                    i.putExtra("Comment", comment);
                    startActivity(i);
                    //getItemAtPosition(position);
                }
        });
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();

        //TODO: take this out of on start and make work with Main Page 'View History' Button
        //saving the emotion from previous screen
        Intent i = getIntent();
        Log.d("intent", "reached");
        emotion = (Emotion)i.getSerializableExtra("Emotion");
        action = (String)i.getSerializableExtra("Action");
        if (action == null){
            action = " ";
        }



        if (action.equals("Delete")){
            //Log.d("inside","if delete");
            String comment = emotion.getComment();
            Log.d("Comment", comment);

            emotionList.remove(emotion);
        }

        if ((action.equals("ViewHistory")) == false && (action.equals("Delete")) == false) {
            emotionList.add(emotion);
        }

        //adapter is basically an interface which connects your listView with your data
        //data can come from database or from file
        //adapter takes list view and binds it to data
        adapter = new ArrayAdapter<Emotion>(this,
                R.layout.list_item, emotionList);
        adapter.notifyDataSetChanged();
        oldEmotionList.setAdapter(adapter);
    }

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

    //takes a text and date and saves it to our file.
    private void saveInFile() {
        try {
            //creates a file with FILENAME and tells it what it will say in java syntax
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(emotionList, out);

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
    }

/*
    void delete(Emotion emotion){
        //Log.d("Delete", "Reached");
        emotionList.remove(emotion);
        saveInFile();
        //adapter.notifyDataSetChanged();
    }
*/
}
