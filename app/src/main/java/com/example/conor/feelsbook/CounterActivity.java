package com.example.conor.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CounterActivity extends AppCompatActivity {

    private ArrayList<Emotion> emotionList;
    private int loveCount = 0;
    private int joyCount = 0;
    private int surpriseCount = 0;
    private int angerCount = 0;
    private int sadnessCount = 0;
    private int fearCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        Intent i = getIntent();
        emotionList = (ArrayList<Emotion>) i.getSerializableExtra("ArrayList");
        getCount(emotionList);
        displayCount();

        //Saves the emotion to the array list
        Button returnButton = (Button) findViewById(R.id.returnButtonCountPage);
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(v.getContext(), EmotionListActivity.class);
                intent.putExtra("Action", "ViewHistory");
                startActivity(intent);
            }
        });
    }

    /**
     * Returns the number of objects of each emotion type that exist in the emotion list
     * @param emotionList
     */
    public void getCount(ArrayList<Emotion> emotionList){
        for (Emotion each:emotionList){
            String emotionName = each.getEmotionName();
            if (emotionName.matches("Love")){
                loveCount = loveCount + 1;
            }
            else if (emotionName.matches("Joy")){
                joyCount = joyCount + 1;
            }
            else if (emotionName.matches("Surprise")){
                surpriseCount = surpriseCount + 1;
            }
            else if (emotionName.matches("Anger")){
                angerCount = angerCount + 1;
            }
            else if (emotionName.matches("Sadness")){
                sadnessCount = sadnessCount + 1;
            }
            else if (emotionName.matches("Fear")){
                fearCount = fearCount + 1;
            }
        }
    }

    /**
     * Displays the count of emotions
     */
    public void displayCount(){
        //Love
        TextView loveCountText = (TextView) findViewById(R.id.loveCountDisplay);
        loveCountText.setText("Love Count" + " = " + loveCount);

        //Joy
        TextView joyCountText = (TextView) findViewById(R.id.joyCountDisplay);
        joyCountText.setText("Joy Count" + " = " + joyCount);

        //Surprise
        TextView surpriseCountText = (TextView) findViewById(R.id.surpriseCountDisplay);
        surpriseCountText.setText("Surprise Count" + " = " + surpriseCount);

        //Anger
        TextView angerCountText = (TextView) findViewById(R.id.angerCountDisplay);
        angerCountText.setText("Anger Count" + " = " + angerCount);

        //Sadness
        TextView sadnessCountText = (TextView) findViewById(R.id.sadnessCountDisplay);
        sadnessCountText.setText("Sadness Count" + " = " + sadnessCount);

        //Fear
        TextView fearCountText = (TextView) findViewById(R.id.fearCountDisplay);
        fearCountText.setText("Fear Count" + " = " + fearCount);
    }
}
