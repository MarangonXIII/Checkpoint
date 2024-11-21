package com.example.checkpoint;

import android.content.Intent; // Import necessário para redirecionar
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkpoint.ADMAct.ADMActivity;
import com.example.checkpoint.model.LoginRequest;
import com.example.checkpoint.model.LoginResponse;
import com.example.checkpoint.network.RetrofitClient;
import com.example.checkpoint.network.UsuarioApiService;
import com.example.checkpoint.UserAct.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonLogin;
    private Button buttonTeste;  // Adicionando o botão de teste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Inicializa os componentes da UI
        editTextEmail = findViewById(R.id.editTextNumber);
        editTextSenha = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.button);
        buttonTeste = findViewById(R.id.jButtonTeste); // Inicializa o botão de teste

        // Define a ação do botão de login
        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String senha = editTextSenha.getText().toString().trim();

            if (!email.isEmpty() && !senha.isEmpty()) {
                loginUsuario(email, senha);
            } else {
                Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            }
        });

        // Define a ação do botão de teste para redirecionar para a TesteActivity
        buttonTeste.setOnClickListener(v -> {
            // Redireciona para a TesteActivity
            Intent intent = new Intent(MainActivity.this, TesteActivity.class);
            startActivity(intent);
        });
    }

    private void loginUsuario(String email, String senha) {
        LoginRequest loginRequest = new LoginRequest(email, senha);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        UsuarioApiService usuarioApiService = retrofit.create(UsuarioApiService.class);

        Call<LoginResponse> call = usuarioApiService.loginUsuario(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    // Verifica o tipoUsuario retornado pela API
                    String tipoUsuario = loginResponse.getTipoUsuario();

                    if ("administrador".equalsIgnoreCase(tipoUsuario)) {
                        // Redireciona para a página de administrador
                        Intent intent = new Intent(MainActivity.this, ADMActivity.class);
                        startActivity(intent);
                    } else if ("tester".equalsIgnoreCase(tipoUsuario)) {
                        // Redireciona para a página de teste
                        Intent intent = new Intent(MainActivity.this, TesteActivity.class);
                        startActivity(intent);
                    } else {
                        // Redireciona para a página padrão de usuário
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                    }

                    finish(); // Finaliza a atividade atual
                } else {
                    Toast.makeText(MainActivity.this, "Erro no login: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao tentar se comunicar com a API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}