package ru.samsung.itschool.hello.javaproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.samsung.itschool.hello.javaproject.classes.Gladiator;
import ru.samsung.itschool.hello.javaproject.classes.GladiatorList;

public class GameScreen extends AppCompatActivity {

    Gladiator character;
    Gladiator enemy;

    GladiatorList enemies;

    int stage = 1;

    TextView name;
    TextView health;
    TextView damage;
    TextView armor;
    TextView mana;

    TextView description;

    Button attackBtn;
    Button blockBtn;
    Button magicBtn;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prepareSettings();
        prepareHandlers();
        prepareCharacters();

        character = new Gladiator("Spartacus", 100, 5, 10, 0);
        enemy = new Gladiator("Corvus", 100, 10, 10, 100);

        name.setText(enemy.getName());
        setHealth(enemy.getHealth());
        setDamage(enemy.getAttack());
        setArmor(enemy.getArmor());
        setMana(enemy.getMana());
    }

    void prepareSettings() {
        name = findViewById(R.id.name);
        health = findViewById(R.id.health);
        damage = findViewById(R.id.damage);
        armor = findViewById(R.id.armor);
        mana = findViewById(R.id.mana);
        attackBtn = findViewById(R.id.attackBtn);
        blockBtn = findViewById(R.id.blockBtn);
        magicBtn = findViewById(R.id.magicBtn);
        description = findViewById(R.id.description);

        description.setMovementMethod(new ScrollingMovementMethod());
    }

    void prepareHandlers() {
        attackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                character.attack(enemy);
                setHealth(enemy.getHealth());
                description.append("Гладиатор " + character.getName() + " нанес " + character.getAttack() + " урона.\n");
                enemy.attack(character);
                description.append("Вы получили " + enemy.getAttack() + " урона от " + enemy.getName() + ".\n");
                checkEndGame();
            }
        });

        blockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEndGame();
            }
        });

        magicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEndGame();
            }
        });
    }

    void prepareCharacters() {

    }

    void checkEndGame() {
        if (character.isAlive() && !enemy.isAlive()) {
            Intent gameIntent = new Intent(GameScreen.this, WinScreen.class);
            startActivity(gameIntent);
        } else if (!character.isAlive()) {
            Intent gameIntent = new Intent(GameScreen.this, LoseScreen.class);
            startActivity(gameIntent);
        }
    }

    void setHealth(int num) {
        health.setText(String.format("HP: %d", num));
    }

    void setArmor(int num) {
        armor.setText(String.format("ARM: %d", num));
    }

    void setDamage(int num) {
        damage.setText(String.format("DMG: %d", num));
    }

    void setMana(int num) {
        mana.setText(String.format("MP: %d", num));
    }
}