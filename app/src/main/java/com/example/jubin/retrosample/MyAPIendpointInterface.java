package com.example.jubin.retrosample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jubin on 2/10/17.
 */

public interface MyAPIendpointInterface {
    @GET("/search/users")
    Call<User> getUserNameTom(@Query("q") String name);
}
