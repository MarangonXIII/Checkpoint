package com.example.checkpoint;

import com.example.checkpoint.model.Empresa;
import com.example.checkpoint.ADMAct.ADMActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroEmpresaActivity extends AppCompatActivity {

    private EditText editTextNomeFantasia, editTextRazaoSocial, editTextCNPJ, editTextEndereco, editTextDepartamento, editTextSenha;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Inicializando os campos
        editTextNomeFantasia = findViewById(R.id.editTextText7);
        editTextRazaoSocial = findViewById(R.id.editTextText);
        editTextCNPJ = findViewById(R.id.editTextText2);
        editTextEndereco = findViewById(R.id.editTextText4);
        editTextDepartamento = findViewById(R.id.editTextText5);
        editTextSenha = findViewById(R.id.editTextTextPassword2);
        buttonCadastrar = findViewById(R.id.CadastroADM);

        buttonCadastrar.setOnClickListener(v -> {
            String nomeFantasia = editTextNomeFantasia.getText().toString();
            String razaoSocial = editTextRazaoSocial.getText().toString();
            String cnpj = editTextCNPJ.getText().toString();
            String endereco = editTextEndereco.getText().toString();
            String departamento = editTextDepartamento.getText().toString();
            String senha = editTextSenha.getText().toString();

            Empresa empresa = new Empresa(nomeFantasia, razaoSocial, cnpj, endereco, departamento, senha);
            new CadastrarEmpresaTask().execute(empresa);
        });
    }

    // Classe interna para executar a requisição HTTP em background
    private class CadastrarEmpresaTask extends AsyncTask<Empresa, Void, String> {
        @Override
        protected String doInBackground(Empresa... empresas) {
            try {
                Empresa empresa = empresas[0];
                URL url = new URL("https://firmiano.dev.br/empresa");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("nomeFantasia", empresa.getNomeFantasia());
                jsonParam.put("razaoSocial", empresa.getRazaoSocial());
                jsonParam.put("cnpj", empresa.getCnpj());
                jsonParam.put("endereco", empresa.getEndereco());
                jsonParam.put("departamento", empresa.getDepartamento());
                jsonParam.put("senha", empresa.getSenha());

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonParam.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();
                return responseCode == HttpURLConnection.HTTP_CREATED ? "Empresa cadastrada com sucesso!" : "Erro ao cadastrar empresa!";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro de conexão!";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(CadastroEmpresaActivity.this, result, Toast.LENGTH_LONG).show();
            if (result.equals("Empresa cadastrada com sucesso!")) {
                // Redireciona para a ADMActivity
                Intent intent = new Intent(CadastroEmpresaActivity.this, ADMActivity.class);
                startActivity(intent);
                finish(); // Finaliza a atividade atual
            }
        }
    }
}