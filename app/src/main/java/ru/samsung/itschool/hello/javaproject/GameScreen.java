package ru.samsung.itschool.hello.javaproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

import ru.samsung.itschool.hello.javaproject.classes.Gladiator;
import ru.samsung.itschool.hello.javaproject.classes.GladiatorList;
import ru.samsung.itschool.hello.javaproject.classes.Settings;

public class GameScreen extends AppCompatActivity {

    Gladiator character;
    Gladiator enemy;

    GladiatorList enemies;

    Settings settings;

    TextView name;
    TextView health;
    TextView damage;
    TextView armor;
    TextView mana;

    ImageView enemyIcon;
    TextView description;

    Button attackBtn;
    Button blockBtn;

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

        if (Objects.equals(character.getLeftHand(), "shield") || Objects.equals(character.getRightHand(), "shield")) {
            blockBtn.setText("BLOCK");
        } else {
            blockBtn.setText("EVADE");
        }


        name.setText(enemy.getName());
        setHealth(enemy.getHealth());
        setDamage(enemy.getAttack());
        setArmor(enemy.getArmor());
    }

    void prepareSettings() {
        name = findViewById(R.id.name);
        health = findViewById(R.id.health);
        damage = findViewById(R.id.damage);
        armor = findViewById(R.id.armor);
        mana = findViewById(R.id.stamina);
        attackBtn = findViewById(R.id.attackBtn);
        blockBtn = findViewById(R.id.blockBtn);
        description = findViewById(R.id.description);
        enemyIcon = findViewById(R.id.enemy_icon);

        description.setMovementMethod(new ScrollingMovementMethod());

        Bundle arguments = getIntent().getExtras();
        settings = new Settings();
        if(arguments!=null){
            settings = (Settings) arguments.getSerializable("settings");
        }
    }

    void prepareHandlers() {
        attackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                character.attack(enemy);
                if (enemy.evaded()) {
                    description.append("Соперник " + enemy.getName() + " уклонился.\n");
                } else if (enemy.blocked()) {
                    description.append("Соперник заблокировал ваш удар.\n");
                } else {
                    setHealth(enemy.getHealth());
                    setArmor(enemy.getArmor());
                    description.append("Гладиатор " + character.getName() + " нанес " + character.getAttack() + " урона.\n");
                }

                enemy.attack(character);
                if (character.evaded()) {
                    description.append("Вы уклонились от удара врага.\n");
                } else {
                    description.append("Вы получили " + enemy.getAttack() + " урона от " + enemy.getName() + ".\n");
                }
                checkEndGame();
            }
        });

        blockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemy.attack(character);
                if (character.blocked()) {
                    description.append("Вы заблокировали удар врага.\n");
                }
                checkEndGame();
            }
        });
    }

    void prepareCharacters() {
        character = new Gladiator("Spartacus", 100, 5, 0, 20);
        switch (settings.getStage()) {
            case 0:
                enemy = new Gladiator("Slave owner", 100, 3, 0,  0);
                enemyIcon.setImageResource(R.drawable.slavery_whip);
                break;
            case 1:
                enemy = new Gladiator("Corvus", 100, 7, 20,  5);
                enemyIcon.setImageResource(R.drawable.dwarf_helmet);
                character.setRightHand("weapon");
                character.addHealth(10);
                character.addArmor(10);
                character.setAttack(7);
                break;
            case 2:
                enemy = new Gladiator("Taurus", 100, 10, 40, 15);
                enemyIcon.setImageResource(R.drawable.barbute);
                character.setRightHand("weapon");
                character.setLeftHand("shield");
                character.addHealth(10);
                character.addArmor(20);
                character.setAttack(12);
                character.setEvasion(15);
                break;
            case 3:
                enemy = new Gladiator("Drokon", 100, 15, 60, 40);
                enemyIcon.setImageResource(R.drawable.warlord_helmet);
                character.setRightHand("weapon");
                character.setLeftHand("shield");
                character.addHealth(10);
                character.addArmor(20);
                character.setAttack(16);
                character.setEvasion(15);
                break;
            case 4:
                enemy = new Gladiator("Galer", 100, 20, 80, 30);
                enemyIcon.setImageResource(R.drawable.frog_mouth_helm);
                character.setRightHand("weapon");
                character.setLeftHand("weapon");
                character.addHealth(10);
                character.addArmor(10);
                character.setAttack(22);
                character.setEvasion(25);
                break;
            case 5:
                enemy = new Gladiator("Maveck", 100, 30, 100,  20);
                enemyIcon.setImageResource(R.drawable.overlord_helm);
                character.setRightHand("weapon");
                character.setLeftHand("shield");
                character.addHealth(20);
                character.addArmor(10);
                character.setAttack(25);
                break;
        }
    }

    void checkEndGame() {
        if (character.isAlive() && !enemy.isAlive()) {
            Intent gameIntent = new Intent(GameScreen.this, WinScreen.class);
            gameIntent.putExtra("settings", settings);
            startActivity(gameIntent);
        } else if (!character.isAlive()) {
            Intent gameIntent = new Intent(GameScreen.this, LoseScreen.class);
            gameIntent.putExtra("settings", settings);
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

    void setStamina(int num) {
        mana.setText(String.format("MP: %d", num));
    }
}