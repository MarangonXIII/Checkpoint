package com.example.checkpoint.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    private String token;

    @SerializedName("tipoUsuario") // Substitua por "tipoUsuario" ou o nome exato do campo retornado pela API
    private String tipoUsuario;

    // Getter para o token
    public String getToken() {
        return token;
    }

    // Setter para o token
    public void setToken(String token) {
        this.token = token;
    }

    // Getter para tipoUsuario
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    // Setter para tipoUsuario
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}