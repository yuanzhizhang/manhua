package com.example.manhua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.manhua.adapter.ChapterListAdapter;
import com.example.manhua.domain.CartoonBook;
import com.example.manhua.domain.CartoonChapter;

import java.util.ArrayList;
import java.util.List;

public class CartoonList extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    List<CartoonChapter> cartoonChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon_list);

        cartoonChapters = (List<CartoonChapter>) getIntent().getSerializableExtra("cartoonChapters");

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

    }

    private CartoonBook initData() {
        CartoonBook cartoonBook = new CartoonBook();
        cartoonBook.setBookContent(cartoonChapters);

        return cartoonBook;
    }
}