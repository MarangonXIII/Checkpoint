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
    private Button facial;
    private Button emp;
    private Button dep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teste);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        esqsenha = findViewById(R.id.jButtonEsqSenha2);
        esqsenha.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, EsqSenhaActivity.class);
            startActivity(intent);
        });

        teste = findViewById(R.id.jButtonCadastrar2);
        teste.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, CadastroUsuarioADM.class);
            startActivity(intent);
        });

        login = findViewById(R.id.jLogin2);
        login.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, MainActivity.class);
            startActivity(intent);
        });

        usu = findViewById(R.id.jUsuario2);
        usu.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, UserActivity.class);
            startActivity(intent);
        });

        adm = findViewById(R.id.jADM2);
        adm.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, ADMActivity.class);
            startActivity(intent);
        });

        facial = findViewById(R.id.jFacial2);
        facial.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, FacialActivity.class);
            startActivity(intent);
        });

        emp = findViewById(R.id.jEmp2);
        emp.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, CadastroEmpresaActivity.class);
            startActivity(intent);
        });

        dep = findViewById(R.id.jDepartamento);
        dep.setOnClickListener(v -> {
            Intent intent = new Intent(TesteActivity.this, CadastrarDepartamentoActivity.class);
            startActivity(intent);
        });
    }
}