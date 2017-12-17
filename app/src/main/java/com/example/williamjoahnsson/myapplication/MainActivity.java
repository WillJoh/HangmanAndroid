package com.example.williamjoahnsson.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements  PrintToOutput{

    private Button button;
    private EditText output;
    private EditText input;
    private Interpreter interpreter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        interpreter = new Interpreter(this);
        interpreter.init();
    }

    private void setupUI() {
        button = findViewById(R.id.button);
        output = findViewById(R.id.output);
        input = findViewById(R.id.input);

        button.setOnClickListener(view -> {
            interpreter.handleMsg(input.getText().toString());
            input.setText("");
        });
    }

    @Override
    public void handleMsg(final String txt) {

        runOnUiThread(() -> output.append("\n" + txt));
    }
}
