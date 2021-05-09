package com.example.manhua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.manhua.adapter.CartoonDashboardAdapter;
import com.example.manhua.adapter.ChapterListAdapter;
import com.example.manhua.domain.CartoonBook;
import com.example.manhua.domain.CartoonChapter;

import java.util.ArrayList;
import java.util.List;

public class CartoonList extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    List<CartoonChapter> cartoonChapters;
    int cId;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon_list);

        cartoonChapters = (List<CartoonChapter>) getIntent().getSerializableExtra("cartoonChapters");
        cId = getIntent().getIntExtra("cId",-1);


        context = this;
        recyclerView = findViewById(R.id.chapter_list);

        //布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        //设置适配器
        ChapterListAdapter adapter = new ChapterListAdapter(context);
        recyclerView.setAdapter(adapter);

        CartoonBook cartoonBook = initData();
        adapter.setData(cartoonBook);

        adapter.setChatOnClickListener(new ChapterListAdapter.ChatOnClickListener() {
            @Override
            public void onChatClick(CartoonBook cartoonBook, int position)
            {
                sharedPreferences = getSharedPreferences("bookRecord", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putInt(String.valueOf(cId), cartoonBook.getBookContent().get(position).getChapterOrderId());
                editor.commit();


                fun();
                Intent intent = new Intent(context, ReadActivity.class);
                intent.putExtra("chapterOrderId", cartoonBook.getBookContent().get(position).getChapterOrderId());
                intent.putExtra("cId",cId);
                startActivity(intent);
                finish();
            }
        });
    }

    private CartoonBook initData() {
        CartoonBook cartoonBook = new CartoonBook();
        cartoonBook.setBookContent(cartoonChapters);

        return cartoonBook;
    }

    private void fun()
    {

    }
}