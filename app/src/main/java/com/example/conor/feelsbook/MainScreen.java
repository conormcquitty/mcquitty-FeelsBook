package com.example.conor.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {
    private String selectedEmotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //takes you to the Emotion List page
        Button historyButton = (Button) findViewById(R.id.MainPageHistoryButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(v.getContext(), EmotionListActivity.class);
                intent.putExtra("Action", "ViewHistory");
                startActivity(intent);
            }
        });
    }

        //When an emotion button is clicked, takes you to edit emotion page
        public void chooseEmotion(View view) {
            switch (view.getId()){
                case R.id.MainPageLoveButton:
                    selectedEmotion = "Love";
                    break;
                case R.id.MainPageJoyButton:
                    selectedEmotion = "Joy";
                    break;
                case R.id.MainPageSurpriseButton:
                    selectedEmotion = "Surprise";
                    break;
                case R.id.MainPageAngerButton:
                    selectedEmotion = "Anger";
                    break;
                case R.id.MainPageSadnessButton:
                    selectedEmotion = "Sadness";
                    break;
                case R.id.MainPageFearButton:
                    selectedEmotion = "Fear";
                    break;
            }
            Intent intent = new Intent(this, EditEmotion.class);
            intent.putExtra("SelectedEmotion", selectedEmotion);
            startActivity(intent);
        }
    }

