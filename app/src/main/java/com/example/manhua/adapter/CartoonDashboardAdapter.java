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
import com.example.manhua.domain.CartoonBook;

import java.util.List;

public class CartoonDashboardAdapter  extends RecyclerView.Adapter<CartoonDashboardAdapter.MyViewHolder> {

    private Context context;
    private List<CartoonBook> cartoonBooks;

    private CartoonDashboardAdapter.ChatOnClickListener chatOnClickListener;

    public CartoonDashboardAdapter(Context context) {
        this.context = context;
    }

    public CartoonDashboardAdapter(Context context, List<CartoonBook> cartoonBooks) {
        this.context = context;
        this.cartoonBooks = cartoonBooks;
    }

    public void setData(List<CartoonBook> cartoonBooks){
        this.cartoonBooks = cartoonBooks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartoonDashboardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommand_book, parent, false);
        return new CartoonDashboardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartoonDashboardAdapter.MyViewHolder holder, int position) {
        CartoonBook cartoonBook = cartoonBooks.get(position);

        holder.bookName.setText(cartoonBook.getcName());


        Glide.with(context)
                .load(cartoonBook.getCartoonCoverUrl())
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
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
        ImageView imageView;
        TextView bookName;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            bookName = itemView.findViewById(R.id.book_name);
            imageView = itemView.findViewById(R.id.book);
        }
    }

    public void setChatOnClickListener(CartoonDashboardAdapter.ChatOnClickListener onClickListener)
    {
        this.chatOnClickListener = onClickListener;
    }

    public interface ChatOnClickListener
    {
        void onChatClick(CartoonBook cartoonBook, int position);
    }
}
