package com.example.checkpoint;

import android.content.Intent; // Import necessário para redirecionamento
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkpoint.model.User;
import com.example.checkpoint.network.RetrofitClient;
import com.example.checkpoint.network.UsuarioApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroUsuarioADM extends AppCompatActivity {

    private EditText nomeEditText, cpfEditText, rgEditText, enderecoEditText, telefoneEditText, emailEditText, departamentoEditText, senhaEditText;
    private Button cadastrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_adm);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Referência aos campos de entrada
        nomeEditText = findViewById(R.id.editTextText7);
        cpfEditText = findViewById(R.id.editTextNumber9);
        rgEditText = findViewById(R.id.editTextNumber12);
        enderecoEditText = findViewById(R.id.editTextTextPostalAddress3);
        telefoneEditText = findViewById(R.id.editTextNumber11);
        emailEditText = findViewById(R.id.editTextTextEmailAddress3);
        departamentoEditText = findViewById(R.id.editTextText8);
        senhaEditText = findViewById(R.id.editTextTextPassword2);
        cadastrarButton = findViewById(R.id.jCadastraDepto);

        // Configura ação do botão de cadastro
        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarCadastro();
            }
        });
    }

    private void realizarCadastro() {
        // Captura os valores dos campos de entrada
        String nome = nomeEditText.getText().toString().trim();
        String cpf = cpfEditText.getText().toString().trim();
        String rg = rgEditText.getText().toString().trim();
        String endereco = enderecoEditText.getText().toString().trim();
        String telefone = telefoneEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String departamento = departamentoEditText.getText().toString().trim();
        String senha = senhaEditText.getText().toString().trim();

        // Valida os campos obrigatórios
        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cria um objeto User para enviar os dados
        User user = new User(nome, email, senha, cpf, rg, endereco, telefone, departamento);

        // Envia os dados para a API usando Retrofit
        UsuarioApiService apiService = RetrofitClient.getRetrofitInstance().create(UsuarioApiService.class);
        Call<Void> call = apiService.cadastrarUsuario(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroUsuarioADM.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                    // Redireciona para a tela principal (ou login) sem precisar do token
                    Intent intent = new Intent(CadastroUsuarioADM.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza a atividade atual
                } else {
                    Toast.makeText(CadastroUsuarioADM.this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("CadastroUsuarioADM", "Erro: " + t.getMessage());
                Toast.makeText(CadastroUsuarioADM.this, "Falha na comunicação com o servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}