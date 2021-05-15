package com.example.manhua.api;

import com.example.manhua.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService
{
//    @GET("users/{user}/repos")
//    Call<ResponseBody> listRepos(@Path("user") String user);

    // BaseURL必须是以/结尾
    // baseUrl = https://autumnfish.cn/api/

    @GET("user/login")
    Call<LoginResponse> login(@Query("username")String username, @Query("password")String password);

    @GET("user/addUser")
    Call<Integer> addUser(@Query("username") String username,@Query("password")String password);

    @GET("jokes/joke")
    Call<LoginResponse> getJoke();
}