package com.premjithsiddharth.quizappcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText name;
    Button start;
    TextView player;
    Button buttonCont;
    String[][] quiz;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        name = findViewById(R.id.name);
        start = findViewById(R.id.start);
        counter = 0;
        quiz = new String[][] {
                {"Question 1. What is the capital of Russia?", "Berlin", "Kiev", "Moscow", "Ottawa"},
                {"Question 2. What is the capital of Washington?", "Seattle", "Olympia", "Walla Walla", "Spokane"},
                {"Question 3. What is the capital of Bulgaria?", "Sofia", "Belgrade", "Brussels", "Valletta"},
                {"Question 4. What country has the capital city of Stockholm?", "Norway", "Finland", "Denmark", "Sweden"}
        };
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                String str = "Player: " + name.getText();
                setContentView(R.layout.main);
                player = findViewById(R.id.name_container);
                player.setText(str);
                buttonCont = findViewById(R.id.button_continue);
                BlankFragment.newInstance(quiz[counter][0], quiz[counter][1], quiz[counter][2], quiz[counter][3], quiz[counter][4]);
                BlankFragment fragment = new BlankFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.questions_fragment, fragment);
            }
        });
    }
    public void nextPage(View v){
        counter++;
        if(counter >= 5){
            setContentView(R.layout.final_page);
            }
        else {
            BlankFragment.newInstance(quiz[counter][0], quiz[counter][1], quiz[counter][2], quiz[counter][3], quiz[counter][4]);
            BlankFragment fragment = new BlankFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.questions_fragment, fragment);
            }
        }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
