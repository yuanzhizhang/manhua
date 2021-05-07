package com.example.manhua.api;

import com.example.manhua.domain.PageItem;
import com.example.manhua.response.PageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PageService {
    @GET("page/getPages")
    Call<PageResponse> getPages(@Query("chapterId")Integer chapterId);
}
