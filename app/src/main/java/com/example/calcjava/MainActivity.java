package com.example.calcjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.text.DecimalFormat;
import net.objecthunter.exp4j.ExpressionBuilder;
import com.example.calcjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализируем binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Настраиваем обработчики кликов
        binding.buttonClear.setOnClickListener(v -> {
            binding.input.setText(" ");
            binding.output.setText(" ");
        });

        binding.buttonBracketLeft.setOnClickListener(v -> addToInputText("("));
        binding.buttonBracketRight.setOnClickListener(v -> addToInputText(")"));
        binding.button0.setOnClickListener(v -> addToInputText("0"));
        binding.button1.setOnClickListener(v -> addToInputText("1"));
        binding.button2.setOnClickListener(v -> addToInputText("2"));
        binding.button3.setOnClickListener(v -> addToInputText("3"));
        binding.button4.setOnClickListener(v -> addToInputText("4"));
        binding.button5.setOnClickListener(v -> addToInputText("5"));
        binding.button6.setOnClickListener(v -> addToInputText("6"));
        binding.button7.setOnClickListener(v -> addToInputText("7"));
        binding.button8.setOnClickListener(v -> addToInputText("8"));
        binding.button9.setOnClickListener(v -> addToInputText("9"));
        binding.buttonDot.setOnClickListener(v -> addToInputText("."));
        binding.buttonDivision.setOnClickListener(v -> addToInputText("÷"));
        binding.buttonMultiply.setOnClickListener(v -> addToInputText("x"));
        binding.buttonSubtraction.setOnClickListener(v -> addToInputText("-"));
        binding.buttonAddition.setOnClickListener(v -> addToInputText("+"));
        binding.buttonEquals.setOnClickListener(v -> showResult());
        binding.buttonPercent.setOnClickListener(v -> addToInputText("%"));
    }

    private void addToInputText(String value) {
        binding.input.append(value);
    }

    private String getInputExpression() {
        return binding.input.getText().toString();
    }

    private void showResult() {
        try {
            String expression = getInputExpression().replace("%", "/100");
            double result = new ExpressionBuilder(expression).build().evaluate();
            binding.output.setText(new DecimalFormat("0.######").format(result));
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.neon_green));
        } catch (Exception e) {
            binding.output.setText("Ошибка");
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }
}