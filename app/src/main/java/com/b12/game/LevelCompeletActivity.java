package com.b12.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.b12.game.activities.GameActivity1;

public class LevelCompeletActivity extends AppCompatActivity {

    private ImageView stars, retry, menu, next;
    private SharedPreferences sharedPreferences;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_complete);
        stars = findViewById(R.id.complete_stars);
        retry = findViewById(R.id.complete_retry);
        menu = findViewById(R.id.complete_home);
        next = findViewById(R.id.complete_next);
        textView = findViewById(R.id.complete_text);
        sharedPreferences = getSharedPreferences("LEVELS", MODE_PRIVATE);
        SharedPreferences.Editor editorLevelNumber = getSharedPreferences("LEVELS", MODE_PRIVATE).edit();
        unLockNextLevel();
        int health = getIntent().getIntExtra("PLAYERHEALTH", 0);
        String level = getIntent().getStringExtra("LEVELNUMBER");
        int minus = Integer.parseInt(level) - 1;
        if (health == 3) {
            stars.setImageResource(R.drawable.complete_star_3);
            editorLevelNumber.putInt(Integer.toString(minus), R.drawable.stars_3);
            textView.setText(R.string.string_congratulations);
        }

        if (health == 2) {
            stars.setImageResource(R.drawable.complete_star_2);
            editorLevelNumber.putInt(Integer.toString(minus), R.drawable.stars_2);
            textView.setText(R.string.string_congratulations);
        }
        if (health == 1) {
            stars.setImageResource(R.drawable.complete_star_1);
            editorLevelNumber.putInt(Integer.toString(minus), R.drawable.stars_1);
            textView.setText(R.string.string_congratulations);
        }

        if (health == 0) {
            stars.setImageResource(R.drawable.complete_star_0);
            editorLevelNumber.putInt(Integer.toString(minus), R.drawable.stars_0);
            textView.setText(R.string.string_failed);
        }


        editorLevelNumber.apply();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editorLevelNumber = getSharedPreferences("LEVELSNUMBER", MODE_PRIVATE).edit();
                editorLevelNumber.putInt("levelCount", 1);
                editorLevelNumber.apply();
                Intent intent = new Intent(LevelCompeletActivity.this, GameActivity1.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void unLockNextLevel() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences.Editor editorLevelNumber = getSharedPreferences("LEVELSNUMBER", MODE_PRIVATE).edit();
        editorLevelNumber.putInt("levelCount", 1);
        editorLevelNumber.apply();
        Intent intent = new Intent(LevelCompeletActivity.this, GameActivity1.class);
        startActivity(intent);
        finish();
    }
}
