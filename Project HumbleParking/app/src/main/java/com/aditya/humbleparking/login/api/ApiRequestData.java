package com.aditya.humbleparking.login.api;

import com.aditya.humbleparking.login.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiRequestData {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponsModel> loginAkun(
            @Field("username") String username,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponsModel> signupAkun(
            @Field("username") String username,
            @Field("password") String password,
            @Field("repassword") String repassword
    );

}
