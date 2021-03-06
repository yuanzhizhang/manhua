package com.example.manhua.api;

import com.example.manhua.response.CartoonBooksResponse;
import com.example.manhua.response.CartoonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CartoonService {

    @GET("cartoonbook/all")
    Call<CartoonBooksResponse> getAllCartoonBook();

    @GET("cartoonbook/all")
    Call<CartoonBooksResponse> getAllCartoonBook(@Query("cartoonType")String cartoonType);

    @GET("cartoonbook/one")
    Call<CartoonResponse> getOntCartoonBook(@Query("cId") Integer cId);
}
