package com.example.checkpoint;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button esqsenha;
    private Button teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return insets;
        });
        esqsenha = findViewById(R.id.jButtonEsqSenha);
        esqsenha.setOnClickListener(v -> {
            Intent intent = new Intent(this, EsqSenhaActivity.class);
            startActivity(intent);
        });
        teste = findViewById(R.id.jButtonTeste);
        teste.setOnClickListener(v -> {
            Intent intent = new Intent(this, TesteActivity.class);
            startActivity(intent);
        });
    }
}