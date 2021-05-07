package com.example.manhua.api;

import com.example.manhua.response.CartoonBooksResponse;
import com.example.manhua.response.ChapterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChapterService {
    @GET("chapter/getChapters")
    Call<ChapterResponse> getCartoonChapter(@Query("cId") Integer cId);
}
