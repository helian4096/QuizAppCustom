package com.premjithsiddharth.quizappcustom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.gson.*;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText name;
    Button start;
    Button replay;
    TextView player;
    Button buttonCont;
    String[][] quiz;
    int counter;
    LayoutInflater inflater;
    RadioGroup radio;
    TextView score;
    int correctAnswers;
    TextView distr;
    Gson gson;
    EditText edit;
    TextView text;
    UserScore currentScore;
    ConstraintLayout loo;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        name = findViewById(R.id.name);
        start = findViewById(R.id.start);
        counter = 0;
        inflater = getLayoutInflater();
        correctAnswers = 0;
        gson = new Gson();
        quiz = new String[][] {
                {"Question 1. What is the capital of Russia?", "Berlin", "Kiev", "Moscow", "Ottawa"},
                {"Question 2. What is the capital of Washington?", "Seattle", "Olympia", "Walla Walla", "Spokane"},
                {"Question 3. What is the capital of Bulgaria?", "Sofia", "Belgrade", "Brussels", "Valletta"},
                {"Question 4. What country has the capital city of Stockholm?", "Norway", "Finland", "Denmark", "Sweden"}
        };
    }
    public void startGame (View v){
        counter = 0;
        String str = "Player: " + name.getText();
        setContentView(R.layout.main);
        player = findViewById(R.id.name_container);
        player.setText(str);
        buttonCont = findViewById(R.id.button_continue);
        nextPage(v);
    }
    public void nextPage(View v){
        if(counter != 0){
            BlankFragment fragment = (BlankFragment)(getSupportFragmentManager().findFragmentById(R.id.questions_fragment));
            if(fragment != null) {
                String s = fragment.answer();
                System.out.println(s);
                if(counter == 1){
                    if(s.equals("Moscow")){
                        correctAnswers++;
                    }
                }
                else if(counter == 2){
                    if(s.equals("Olympia")){
                        correctAnswers++;
                    }
                }
                else if(counter == 3){
                    if(s.equals("Sofia")){
                        correctAnswers++;
                    }
                }
                else if(counter == 4){
                    if(s.equals("Sweden")){
                        correctAnswers++;
                    }
                }
            }
        }
        if(counter >= 4){
            setContentView(R.layout.final_page);
            score = findViewById(R.id.final_message);
            replay = findViewById(R.id.replay);
            distr = findViewById(R.id.score_distribution);
            String str = "Congratulations for finishing! You got " + correctAnswers + " right!";
            score.setText(str);
            int newX = sharedPreferences.getInt("" + correctAnswers, 0);
            newX++;
            editor.putInt("" + correctAnswers, newX);
            editor.apply();
            String distribution = "4: " + sharedPreferences.getInt("4", 0) +
                    ", 3: " + sharedPreferences.getInt("3", 0) +
                    ", 2: " + sharedPreferences.getInt("2", 0) +
                    ", 1: " + sharedPreferences.getInt("1", 0) +
                    ", 0: " + sharedPreferences.getInt("0", 0);
            distr.setText(distribution);
            String scoreU = "{\"player\":\"" + player.getText() + "\",\"score\":" + correctAnswers + "}";
            System.out.println(scoreU);
            editor.putString((String)(player.getText()), scoreU);
            editor.apply();
            System.out.println(sharedPreferences.getString((String)(player.getText()), "Not found."));
            replay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    counter = 0;
                    setContentView(R.layout.home_layout);
                }
            });
            edit = findViewById(R.id.find_player);
            Button button = findViewById(R.id.button_find);
            text = findViewById(R.id.player_display);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence s = edit.getText();
                    String ss = "Player: " + s;
                    String found = sharedPreferences.getString(ss, "Not found.");
                    System.out.println(found);
                    if(found.equals("Not found.")){
                        text.setText(found);
                    }
                    else {
                        currentScore = gson.fromJson(found, UserScore.class);
                        System.out.println(currentScore.score);
                        String str = "" + currentScore.score;
                        text.setText(str);
                    }
                }
            });
            }
        else {
            System.out.println(quiz[counter][0]);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.questions_fragment, BlankFragment.newInstance(quiz[counter][0], quiz[counter][1], quiz[counter][2], quiz[counter][3], quiz[counter][4]));
            transaction.addToBackStack(null);
            transaction.commit();
            counter++;
            }
        }

    @Override
    public void onFragmentInteraction(Uri uri) {
        uri.getFragment();
    }
    public class UserScore {
        String player;
        int score;
    }
}
