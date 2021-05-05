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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon_list);

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
        cartoonBook.setcId(1);
        cartoonBook.setcName("斗破苍穹");

        List<CartoonChapter> cartoonChapters = new ArrayList<>();

        CartoonChapter cartoonChapter1 = new CartoonChapter();
        cartoonChapter1.setChatOrderID(1);
        cartoonChapters.add(cartoonChapter1);

        CartoonChapter cartoonChapter2 = new CartoonChapter();
        cartoonChapter2.setChatOrderID(2);
        cartoonChapters.add(cartoonChapter2);

        System.out.println(cartoonChapters.get(0).getChatOrderID());
        System.out.println(cartoonChapters.get(1).getChatOrderID());

        cartoonBook.setBookContent(cartoonChapters);

        return cartoonBook;
    }
}