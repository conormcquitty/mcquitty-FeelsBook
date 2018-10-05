package com.example.conor.feelsbook;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.Collections;


public class EmotionListActivity extends Activity {

    //code derived from lab activity LonelyTwitter
    private static final String FILENAME = "Emotion.sav";
    private ListView oldEmotionList;
    ArrayList<Emotion> emotionList;
    ArrayAdapter<Emotion> adapter;


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

        //Clears the list on press
        Button clearButton = (Button) findViewById(R.id.ClearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                emotionList.clear();
                saveInFile();
                adapter.notifyDataSetChanged();
            }
        });

        //Go to counter screen on press
        Button countButton = (Button) findViewById(R.id.CountButton);
        countButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                saveInFile();
                Intent i = new Intent(v.getContext(), CounterActivity.class);
                i.putExtra("ArrayList", emotionList);
                startActivity(i);
            }
        });

        //Takes you to edit emotion details when an emotion on list view is clicked
        oldEmotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object object = oldEmotionList.getItemAtPosition(position);
                    Emotion emotion = Emotion.class.cast(object);
                    String SelectedEmotion = emotion.getEmotionName();
                    Intent i = new Intent(view.getContext(), EditEmotion.class);
                    i.putExtra("SelectedEmotion", SelectedEmotion);
                    i.putExtra("Emotion", emotion);
                    i.putExtra("Index", position);
                    startActivity(i);
                }
        });
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        sort();
        adapter = new ArrayAdapter<Emotion>(this,
                R.layout.list_item, emotionList);
        adapter.notifyDataSetChanged();
        oldEmotionList.setAdapter(adapter);
    }

    protected void sort(){
        Collections.sort(this.emotionList);
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
}
