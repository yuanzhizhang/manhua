package com.example.manhua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhua.R;
import com.example.manhua.domain.CartoonBook;
import com.example.manhua.domain.CartoonChapter;

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.PageViewHolder>{
    Context context;
    CartoonBook cartoonBook;

    public ChapterListAdapter(Context context, CartoonBook cartoonBook) {
        this.context = context;
        this.cartoonBook = cartoonBook;
    }

    public ChapterListAdapter(Context context) {
        this.context = context;
    }

    public void setData(CartoonBook cartoonBook) {
        this.cartoonBook = cartoonBook;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_chapter, parent, false);
        return new PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
        CartoonChapter cartoonChapter = cartoonBook.getBookContent().get(position);
        System.out.println(cartoonChapter.getChatOrderID());

        holder.chapterNO.setText("第"+cartoonChapter.getChatOrderID()+"话");
    }

    @Override
    public int getItemCount() {
        return cartoonBook.getBookContent() == null ? 0: cartoonBook.getBookContent().size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder{
        TextView chapterNO;
        TextView chapterName;

        public PageViewHolder(@NonNull View itemView) {
            super(itemView);


            chapterNO = itemView.findViewById(R.id.chapter_NO);
        }
    }
}
