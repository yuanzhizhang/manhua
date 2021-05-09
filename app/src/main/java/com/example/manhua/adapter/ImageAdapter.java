package com.example.manhua.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.manhua.domain.CartoonBook;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class ImageAdapter extends BannerAdapter<CartoonBook, ImageAdapter.BannerViewHolder> {
    Context context;
    List<CartoonBook> books;
    private ChatOnClickListener chatOnClickListener;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<CartoonBook> getBooks() {
        return books;
    }

    public void setBooks(List<CartoonBook> books) {
        setDatas(books);
        notifyDataSetChanged();
    }

    public ImageAdapter(List<CartoonBook> books, Context context) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(books);
        this.context = context;
        this.books = books;

    }


    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setAdjustViewBounds(true);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, CartoonBook book, int position, int size) {
        Glide.with(context)
                .load(book.getCartoonCoverUrl())
                .into(holder.imageView);
        Log.v("23497239479328","zhixingla");
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (chatOnClickListener != null)
                {
                    chatOnClickListener.onChatClick(book, position);
                }
            }
        });
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
    public void setChatOnClickListener(ChatOnClickListener onClickListener)
    {
        this.chatOnClickListener = onClickListener;
    }
    public interface ChatOnClickListener
    {
        void onChatClick(CartoonBook cartoonBook, int position);
    }
}