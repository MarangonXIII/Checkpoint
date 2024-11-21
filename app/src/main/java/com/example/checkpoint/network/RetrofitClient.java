package com.example.checkpoint.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
            .baseUrl("https://firmiano.dev.br/")  // URL base da API
            .addConverterFactory(GsonConverterFactory.create())  // Para converter JSON em objetos Java
            .build();
        }
        return retrofit;
    }
}