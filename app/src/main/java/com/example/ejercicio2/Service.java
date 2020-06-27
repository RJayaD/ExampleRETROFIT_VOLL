package com.example.ejercicio2;
import com.example.ejercicio2.Class.Bank;
import com.example.ejercicio2.Class.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface Service {
    public static final String BASE_URL = "https://api-uat.kushkipagos.com/transfer/v1/";
    @GET("bankList")
    Call<List<Bank>> getListBank(@Header("Public-Merchant-Id") String id);
}

