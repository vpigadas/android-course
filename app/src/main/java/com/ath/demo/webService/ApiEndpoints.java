package com.ath.demo.webService;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoints {

    @GET("/tv")
    Call<ServerResponseDTO> getTv();

    @GET("/cosmote/sport")
    Call<ServerResponseDTO> getCosmoteSport();
}
