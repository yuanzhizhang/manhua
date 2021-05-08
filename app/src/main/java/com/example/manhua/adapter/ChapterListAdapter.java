package com.example.manhua.adapter;

import android.content.Context;
import android.util.Log;
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
    private ChapterListAdapter.ChatOnClickListener chatOnClickListener;

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

        holder.chapterNO.setText(cartoonChapter.getChapterTitle());

        holder.chapterNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (chatOnClickListener != null)
                {
                    chatOnClickListener.onChatClick(cartoonBook, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartoonBook.getBookContent() == null ? 0: cartoonBook.getBookContent().size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder{
        TextView chapterNO;

        public PageViewHolder(@NonNull View itemView) {
            super(itemView);


            chapterNO = itemView.findViewById(R.id.chapter_NO);
        }
    }

    public void setChatOnClickListener(ChapterListAdapter.ChatOnClickListener onClickListener)
    {
        this.chatOnClickListener = onClickListener;
    }

    public interface ChatOnClickListener
    {
        void onChatClick(CartoonBook cartoonBook, int position);
    }
}
