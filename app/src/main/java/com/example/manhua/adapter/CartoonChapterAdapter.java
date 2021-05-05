package com.example.manhua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.manhua.R;
import com.example.manhua.domain.CartoonChapter;
import com.example.manhua.domain.PageItem;

public class CartoonChapterAdapter extends RecyclerView.Adapter<CartoonChapterAdapter.PageViewHolder> {
    Context context;
    CartoonChapter cartoonChapter;

    public CartoonChapterAdapter(Context context) {
        this.context = context;
    }

    public CartoonChapterAdapter(Context context, CartoonChapter cartoonChapter) {
        this.context = context;
        this.cartoonChapter = cartoonChapter;
    }

    public void setData(CartoonChapter cartoonChapter) {
        this.cartoonChapter = cartoonChapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_page, parent, false);
        return new CartoonChapterAdapter.PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
        PageItem pageItem = cartoonChapter.getChatContent().get(position);


        Glide.with(context)
                .load(pageItem.getImageUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cartoonChapter.getChatContent() == null ? 0:cartoonChapter.getChatContent().size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public PageViewHolder(@NonNull View itemViem){
            super(itemViem);

            imageView = itemViem.findViewById(R.id.manhua_page_img);
        }
    }
}
