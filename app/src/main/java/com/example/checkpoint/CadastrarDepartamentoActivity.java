package com.example.checkpoint;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.checkpoint.network.DepartamentoApi;
import com.example.checkpoint.model.Departamento;
import com.example.checkpoint.ADMAct.ADMActivity;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastrarDepartamentoActivity extends AppCompatActivity {

    private EditText editNome, editEmpresa, editUsuario;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_departamento);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Vincular os campos da interface
        editNome = findViewById(R.id.editTextText7);  // Campo "Nome"
        editEmpresa = findViewById(R.id.editTextText);  // Campo "Empresa"
        editUsuario = findViewById(R.id.editTextText3);  // Campo "Usuário"
        btnCadastrar = findViewById(R.id.CadastroADM);  // Botão "Cadastrar"

        // Configurar o clique no botão de cadastro
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString().trim();
                String empresa = editEmpresa.getText().toString().trim();
                String usuario = editUsuario.getText().toString().trim();

                if (nome.isEmpty() || empresa.isEmpty() || usuario.isEmpty()) {
                    Toast.makeText(CadastrarDepartamentoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    cadastrarDepartamento(new Departamento(nome, empresa, usuario));
                }
            }
        });
    }

    private void cadastrarDepartamento(Departamento departamento) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://firmiano.dev.br/")  // URL base da sua API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DepartamentoApi api = retrofit.create(DepartamentoApi.class);
        Call<Void> call = api.cadastrarDepartamento(departamento);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastrarDepartamentoActivity.this, "Departamento cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Redireciona para a ADMActivity
                    Intent intent = new Intent(CadastrarDepartamentoActivity.this, ADMActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza a atividade atual
                } else {
                    Toast.makeText(CadastrarDepartamentoActivity.this, "Erro ao cadastrar: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_ERROR", "Falha na comunicação com a API", t);
                Toast.makeText(CadastrarDepartamentoActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}