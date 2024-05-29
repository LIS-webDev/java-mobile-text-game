package ru.samsung.itschool.hello.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.samsung.itschool.hello.javaproject.classes.Settings;
public class WinScreen extends AppCompatActivity {

    Button goNextBtn;
    Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_win_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prepareSettings();
        prepareHandlers();
    }

    void prepareSettings() {
        goNextBtn = findViewById(R.id.gonext);

        Bundle arguments = getIntent().getExtras();
        settings = new Settings();
        if(arguments!=null){
            settings = (Settings) arguments.getSerializable("settings");
        }
    }

    void prepareHandlers() {
        goNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.nextStage();
                // Создание Intent для перехода на экран с основной игрой (GameActivity)
                Intent gameIntent = new Intent(WinScreen.this, StoryScreen.class);
                gameIntent.putExtra("settings", settings);
                startActivity(gameIntent);
            }
        });
    }
}