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

import ru.samsung.itschool.hello.javaproject.classes.Conclusion;
import ru.samsung.itschool.hello.javaproject.classes.ConclusionList;

public class LoseScreen extends AppCompatActivity {

    Button goBackButton;
    TextView conclusionText;
    TextView conclusionTitle;
    ConclusionList conclusions;
    Conclusion conclusion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lose_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prepareSettings();
        prepareHandlers();
        prepareConclusions();

        conclusion = conclusions.getRandomConclusion();
        conclusionTitle.setText(conclusion.getTitle());
        conclusionText.setText(conclusion.getText());
    }

    void prepareSettings() {
        goBackButton = findViewById(R.id.goback1);
        conclusionText = findViewById(R.id.conclusionText);
        conclusionTitle = findViewById(R.id.conclusionTitle);
    }

    void prepareHandlers() {
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание Intent для перехода на экран с основной игрой (GameActivity)
                Intent gameIntent = new Intent(LoseScreen.this, StartScreen.class);
                startActivity(gameIntent);
            }
        });
    }

    void prepareConclusions() {
        conclusions.addConclusion(
                "Печальная Утрата",
                "Смерть Спартака на арене охватывает все сердца тяжелым горем. Его жертва становится символом отваги и решимости в борьбе за свободу. Рабы, гладиаторы и союзники скорбят об ушедшем герое, мечтая продолжить его дело."
        );
        conclusions.addConclusion(
                "Возрождение Спартака в Легенде",
                "Смелый поединок, в котором Спартак погиб, вписывается в легенду его жизни и борьбы. Его имя становится символом сопротивления и силы, вдохновляя будущие поколения рабов и борцов за свободу бороться за свои права и достоинство смелость."
        );
        conclusions.addConclusion(
                "Месть и Следующий Шаг",
                "Смерть Спартака не остается без ответа. Его товарищи и последователи клянутся отомстить за убитого героя и продолжить бороться за свободу, которую он так страстно отстаивал. Их гнев и решимость растет, а его смерть лишь усиливает их решимость продолжить борьбу."
        );
        conclusions.addConclusion(
                "Наследие Спартака",
                "Хотя тело Спартака может быть погребено, его дух живет в сердцах тех, кто верит в идеалы свободы и справедливости. Его смерть становится толчком к объединению и укреплению борьбы за освобождение рабов и противостоянию угнетению. его смерть положит начало новому этапу в оппозиции к системе рабства и угнетения."
        );
    }
}