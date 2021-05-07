package com.example.manhua.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.manhua.R;
import com.example.manhua.api.ChapterService;
import com.example.manhua.api.PageService;
import com.example.manhua.domain.CartoonChapter;
import com.example.manhua.domain.PageItem;
import com.example.manhua.response.ChapterResponse;
import com.example.manhua.response.PageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartoonChapterAdapter extends RecyclerView.Adapter<CartoonChapterAdapter.PageViewHolder> {
    Context context;
    List<PageItem> pages;
    CartoonChapter cartoonChapter;

    public CartoonChapterAdapter(Context context) {
        this.context = context;
    }

    public CartoonChapterAdapter(Context context, CartoonChapter cartoonChapter) {
        this.context = context;
        this.cartoonChapter = cartoonChapter;
    }

    public void setData(List<PageItem> pages) {
        this.pages = pages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_page, parent, false);
        return new PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
        PageItem pageItem = pages.get(position);

        Glide.with(context)
                .load(pageItem.getImageUrl())
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return pages == null ? 0:pages.size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public PageViewHolder(@NonNull View itemViem){
            super(itemViem);

            imageView = itemViem.findViewById(R.id.manhua_page_img);
        }
    }
}
