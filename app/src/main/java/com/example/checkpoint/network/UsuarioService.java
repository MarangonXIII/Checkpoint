package com.example.checkpoint.network;

import android.util.Log;

import com.example.checkpoint.model.LoginRequest;
import com.example.checkpoint.model.LoginResponse;
import com.example.checkpoint.model.User;
import com.example.checkpoint.network.UsuarioApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioService {

    private UsuarioApiService apiService;

    public UsuarioService() {
        apiService = RetrofitClient.getRetrofitInstance().create(UsuarioApiService.class);
    }

    public void cadastrarUsuario(User usuario) {
        apiService.cadastrarUsuario(usuario).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("UsuarioService", "Cadastro realizado com sucesso!");
                } else {
                    Log.e("UsuarioService", "Erro no cadastro: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UsuarioService", "Falha na requisição: " + t.getMessage());
            }
        });
    }

    public void loginUsuario(LoginRequest loginRequest) {
        // Envia a requisição e recebe a resposta
        apiService.loginUsuario(loginRequest).enqueue(new Callback<LoginResponse>() {  // Corrigido para LoginResponse
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API Success", "Login bem-sucedido: " + response.body().toString());
                } else {
                    Log.d("API Error", "Erro na resposta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("API Failure", "Falha na requisição: " + t.getMessage());
            }
        });
    }
}