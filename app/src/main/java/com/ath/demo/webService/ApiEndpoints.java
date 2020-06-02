package com.ath.demo.webService;

import com.ath.demo.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoints {

    @GET("/tv")
    Call<ServerResponse> getTv();

    @GET("/cosmote/sport")
    Call<ServerResponse> getCosmoteSport();
}
