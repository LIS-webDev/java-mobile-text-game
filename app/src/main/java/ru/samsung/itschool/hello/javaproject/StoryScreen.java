package ru.samsung.itschool.hello.javaproject;

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

import ru.samsung.itschool.hello.javaproject.classes.Settings;

public class StoryScreen extends AppCompatActivity {

    TextView storyText;
    TextView storyTitle;
    ImageView image;
    Button nextBtn;
    Settings settings;

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

        switch (settings.getStage()) {
            case 0:
                image.setImageResource(R.drawable.caesar);
                storyTitle.setText("Пролог");
                storyText.setText("Спартак, молодой и сильный раб, слышит о турнире, который организует жестокий и влиятельный римский сенатор. Раздираемый желанием свободы для себя и своих товарищей, он решает участвовать в турнире в надежде на победу и шанс начать переговоры о своей свободе.");
                break;
            case 1:
                image.setImageResource(R.drawable.coliseum);
                storyTitle.setText("Глава 1: Первый Бой - Испытание");
                storyText.setText("Перед первым боем Спартак встречает других участников турнира, узнает о своих соперниках и устанавливает первые стратегические связи с союзниками. Он узнает о прошлом бойцов, их мечтах и надеждах, и обретает боевой опыт, который станет определяющим в течение турнира.");
                break;
            case 2:
                image.setImageResource(R.drawable.girl);
                storyTitle.setText("Глава 2: Второй Бой - На пути к Победе");
                storyText.setText("Спартак успешно выходит из второго поединка с ранением, но также с заслуженным уважением и поддержкой от других рабов и гладиаторов. Вне арены он знакомится с таинственной женщиной, которая подает надежду и вдохновение на борьбу за свободу.");
                break;
            case 3:
                image.setImageResource(R.drawable.cultist);
                storyTitle.setText("Глава 3: Третий Бой - Столкновение с Главным Соперником");
                storyText.setText("Третий бой против самого опасного соперника оказывается самым сложным испытанием для Спартака. В процессе он сталкивается с предательством, но находит внутреннюю силу и решимость встать против несправедливости.");
                break;
            case 4:
                image.setImageResource(R.drawable.friends);
                storyTitle.setText("Глава 4: Четвертый Бой - Настоящая Дружба");
                storyText.setText("В четвертом бою он объединяется с оставшимися соперниками и создает союз для противостояния коррумпированной системе турнира и сенатора. Они обнаруживают общие цели и действуют вместе для достижения свободы и справедливости.");
                break;
            case 5:
                image.setImageResource(R.drawable.rally_the_troops);
                storyTitle.setText("Глава 5: Финальный Бой - Восстание Гладиаторов");
                storyText.setText("В конечном сражении, Спартак и его товарищи сталкиваются с армией сенатора. Их месть, смешанная с надеждой на свободу, превращается в эпическую битву, которая становится символом восстания против угнетения и борьбы за лучшее будущее.");
                break;
            case 6:
                image.setImageResource(R.drawable.medieval_gate);
                storyTitle.setText("Эпилог: Начало Новой Эпохи");
                storyText.setText("Победа в турнире открывает двери к аудиенции с императором. Спартак видит это как возможность начать переговоры о свободе для рабов. Наполненный надеждой, он становится символом и лидером восстания для многих, и его история остается запечатленной во всей истории человечества.");
                break;
        }

    }

    void prepareSettings() {
        storyText = findViewById(R.id.storyText);
        storyTitle = findViewById(R.id.storyTitle);
        image = findViewById(R.id.storyImg);
        nextBtn = findViewById(R.id.storyNextBtn);

        storyText.setMovementMethod(new ScrollingMovementMethod());

        Bundle arguments = getIntent().getExtras();
        settings = new Settings();
        if(arguments!=null){
            settings = (Settings) arguments.getSerializable("settings");
        }
    }
    void prepareHandlers() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание Intent для перехода на экран с основной игрой (GameActivity)
                Intent gameIntent = new Intent(StoryScreen.this, GameScreen.class);
                gameIntent.putExtra("settings", settings);
                startActivity(gameIntent);
            }
        });
    }
}