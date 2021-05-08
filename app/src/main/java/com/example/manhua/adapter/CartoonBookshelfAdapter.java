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
import com.example.manhua.domain.CartoonBook;

import java.util.ArrayList;
import java.util.List;

public class CartoonBookshelfAdapter extends RecyclerView.Adapter<CartoonBookshelfAdapter.MyViewHolder>{
    private Context context;
    private List<CartoonBook> cartoonBooks = new ArrayList<>();

    private ChatOnClickListener chatOnClickListener;

    public CartoonBookshelfAdapter(Context context) {
        this.context = context;
    }

    public CartoonBookshelfAdapter(Context context, List<CartoonBook> cartoonBooks) {
        this.context = context;
        this.cartoonBooks = cartoonBooks;
    }

    public void setData(List<CartoonBook> cartoonBooks){
        this.cartoonBooks = cartoonBooks;
        notifyDataSetChanged();
    }

    public void addData(CartoonBook cartoonBook){
        this.cartoonBooks.add(cartoonBook);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_manhua_shujia, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartoonBook cartoonBook = cartoonBooks.get(position);
        holder.name.setText(cartoonBooks.get(position).getcName());
        holder.lastChat.setText(cartoonBooks.get(position).getCartoonLastChat());
        holder.updataTime.setText(cartoonBooks.get(position).getCartoonUpdateTime());

        Glide.with(context)
                .load(cartoonBook.getCartoonCoverUrl())
                .into(holder.imageView);

        holder.coverAll.setOnClickListener(new View.OnClickListener() {
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
        return cartoonBooks == null ? 0 : cartoonBooks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        // 初始化布局里的控件
        View coverAll;
        ImageView imageView;
        TextView name;
        TextView lastChat;
        TextView updataTime;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            coverAll = itemView.findViewById(R.id.manhua_cover_all);
            imageView = itemView.findViewById(R.id.manhua_cover_img);
            name = itemView.findViewById(R.id.manhua_cover_manhua_name);
            lastChat = itemView.findViewById(R.id.manhua_cover_last_chat_name);
            updataTime = itemView.findViewById(R.id.manhua_update_time);
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
