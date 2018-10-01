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
import android.widget.Button;



public class MainScreen extends AppCompatActivity {
    public String SelectedEmotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        //takes you straight to the Emotion List page
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
                    SelectedEmotion = "Love";
                    break;
                case R.id.MainPageJoyButton:
                    SelectedEmotion = "Joy";
                    break;
                case R.id.MainPageSurpriseButton:
                    SelectedEmotion = "Surprise";
                    break;
                case R.id.MainPageAngerButton:
                    SelectedEmotion = "Anger";
                    break;
                case R.id.MainPageSadnessButton:
                    SelectedEmotion = "Sadness";
                    break;
                case R.id.MainPageFearButton:
                    SelectedEmotion = "Fear";
                    break;
            }

            //creates intent to go to EditEmotion page, passes the Emotion name and ArrayList
            // to the EditEmotion page.
            Intent intent = new Intent(this, EditEmotion.class);
            intent.putExtra("SelectedEmotion", SelectedEmotion);
            startActivity(intent);
        }
    }

