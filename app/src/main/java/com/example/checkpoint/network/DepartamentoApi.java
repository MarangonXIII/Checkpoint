package com.example.checkpoint.network;

import com.example.checkpoint.model.Departamento;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DepartamentoApi {
    @POST("departamentos")  // Endpoint específico da API
    Call<Void> cadastrarDepartamento(@Body Departamento departamento);
}