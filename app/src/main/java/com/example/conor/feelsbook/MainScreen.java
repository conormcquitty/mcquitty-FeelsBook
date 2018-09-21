/* Feels Book

Copyright (C) 2018  Conor McQuitty mcquitty@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

package com.example.conor.feelsbook;

//import android.util.Log;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainScreen extends AppCompatActivity {

   public String SelectedEmotion;
   EmotionList emotionList = new EmotionList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

        //When emotion button is clicked, takes you to emotion list page and creates Emotion object
        public void chooseEmotion(View view) {
            switch (view.getId()){
                case R.id.MainPageLoveButton:
                    SelectedEmotion = "Love";
                    //Log.d("Love", "Added");
                    break;
                case R.id.MainPageJoyButton:
                    SelectedEmotion = "Joy";
                    //Log.d("Joy", "Added");
                    break;
                case R.id.MainPageSurpriseButton:
                    SelectedEmotion = "Surprise";
                    //Log.d("Surprise", "Added");
                    break;
                case R.id.MainPageAngerButton:
                    SelectedEmotion = "Anger";
                    //Log.d("Anger", "Added");
                    break;
                case R.id.MainPageSadnessButton:
                    SelectedEmotion = "Sadness";
                    //Log.d("Sadness", "Added");
                    break;
                case R.id.MainPageFearButton:
                    SelectedEmotion = "Fear";
                   // Log.d("Fear", "Added");
                    break;
            }

            //Creates an Emotion object, adds it to the EmotionList, creates intent to go to EditEmotion
            //page, and passes the Emotion object to the EditEmotion page.
            Emotion emotion = new Emotion(SelectedEmotion);
            //Log.d("Fear", "Created2");
            emotionList.addEmotion(emotion);
            Intent intent = new Intent(this, EditEmotion.class);
            intent.putExtra("SelectedEmotion", emotion);
            startActivity(intent);
        }
    }

