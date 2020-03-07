package com.premjithsiddharth.quizappcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText name;
    Button start;
    TextView player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        name = findViewById(R.id.name);
        start = findViewById(R.id.start);
        player = findViewById(R.id.name_container);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                String str = "Player: " + name.getText();
                player.setText(str);
            }
        });
    }
}
