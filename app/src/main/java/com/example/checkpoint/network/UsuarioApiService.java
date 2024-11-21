package com.example.checkpoint.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.example.checkpoint.model.LoginRequest;
import com.example.checkpoint.model.LoginResponse;
import com.example.checkpoint.model.User;

public interface UsuarioApiService {
    @POST("usuario")
    Call<Void> cadastrarUsuario(@Body User user);

    @POST("login")
    Call<LoginResponse> loginUsuario(@Body LoginRequest loginRequest);
}