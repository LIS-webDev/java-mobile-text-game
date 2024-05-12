package ru.samsung.itschool.hello.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StoryScreen extends AppCompatActivity {

    int stage = 1;
    TextView storyText;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prepareSettings();
        prepareHandlers();

        switch (stage) {
            case 1:
                storyText.setText("Узнав о турнире, который организует жестокий и влиятельный римский сенатор, он решает участвовать. Победа в турнире дает право на аудиенцию с императором, и Спартак видит в этом шанс начать переговоры о свободе для рабов");
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }

    }

    void prepareSettings() {
        storyText = findViewById(R.id.storyText);
        nextBtn = findViewById(R.id.storyNextBtn);
    }
    void prepareHandlers() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание Intent для перехода на экран с основной игрой (GameActivity)
                Intent gameIntent = new Intent(StoryScreen.this, GameScreen.class);
                startActivity(gameIntent);
            }
        });
    }
}