package com.example.checkpoint;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;

import com.example.checkpoint.ADMAct.ADMActivity;
import com.example.checkpoint.UserAct.UserActivity;

public class TesteActivity extends AppCompatActivity {
    private Button esqsenha;
    private Button teste;
    private Button login;
    private Button usu;
    private Button adm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teste);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return insets;
        });

        esqsenha = findViewById(R.id.jButtonEsqSenha2);
        teste = findViewById(R.id.jButtonCadastrar2);
        login = findViewById(R.id.jLogin2);
        usu = findViewById(R.id.jUsuario2);
        adm = findViewById(R.id.jADM2);

        esqsenha.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, EsqSenhaActivity.class);
            startActivity(intent);
        });

        teste.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, CadastroUsuario.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, MainActivity.class);
            startActivity(intent);
        });

        usu.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, UserActivity.class);
            startActivity(intent);
        });

        adm.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, ADMActivity.class);
            startActivity(intent);
        });
    }
}