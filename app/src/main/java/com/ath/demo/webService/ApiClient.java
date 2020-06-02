package com.ath.demo.webService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient instance;
    private static Retrofit retrofit;
    private static ApiEndpoints endpoints;
    private static final String BASE_URL = "https://tv-zapping.herokuapp.com/v2/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            endpoints = ApiClient.getRetrofitInstance().create(ApiEndpoints.class);
        }
        return instance;
    }

    public void getTv(Callback<ServerResponseDTO> listener) {
        Call<ServerResponseDTO> call = endpoints.getTv();
        call.enqueue(listener);
    }
}
