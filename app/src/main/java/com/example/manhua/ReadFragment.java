package com.example.manhua;

import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhua.adapter.CartoonChapterAdapter;
import com.example.manhua.api.CartoonService;
import com.example.manhua.api.ChapterService;
import com.example.manhua.api.PageService;
import com.example.manhua.domain.CartoonBook;
import com.example.manhua.domain.CartoonChapter;
import com.example.manhua.domain.PageItem;
import com.example.manhua.response.CartoonBooksResponse;
import com.example.manhua.response.ChapterResponse;
import com.example.manhua.response.PageResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadFragment extends Fragment {
    Context context;
    RecyclerView recyclerView;
    Button list_btn;
    Button up_btn;
    Button down_btn;
    TextView textView;
    CartoonChapterAdapter adapter;

    List<PageItem> pages;
    List<CartoonChapter> cartoonChapters;
    int cId;
    int chapterOrderId;
    CartoonChapter cartoonChapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_read,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        recyclerView = view.findViewById(R.id.read_list);
        list_btn = view.findViewById(R.id.list_btn);
        up_btn = view.findViewById(R.id.up_btn);
        down_btn = view.findViewById(R.id.down_btn);
        textView = view.findViewById(R.id.chapter_name);


        Intent intent = getActivity().getIntent();

        cId = intent.getIntExtra("cId",-1);
        if(chapterOrderId == 0){
            chapterOrderId = intent.getIntExtra("chapterOrderId",-1);
        }
        //布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        //设置适配器
        adapter = new CartoonChapterAdapter(context);
        recyclerView.setAdapter(adapter);

        getChapters();

        bindEvents();

    }

    void bindEvents(){

        //目录
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartoonList.class);
                intent.putExtra("cartoonChapters", (Serializable) cartoonChapters);
                startActivity(intent);
            }
        });
        //上一章的按钮绑定事件
        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果是第一个章节 给上一章绑定事件不能再按， 按了弹出已经是第一个章节了
                if(chapterOrderId==1) {
                    Toast.makeText(context,"这已经是第一话了",Toast.LENGTH_SHORT).show();
                }else {
                    //不是第一章
                    chapterOrderId -= 1;
                    cartoonChapter = cartoonChapters.get(chapterOrderId-1);
                    getPages(cartoonChapter);
//                    getChapters();
//                    bookContent.setText("本一章的内容" + chatContent.getChatOrderID()+ "书ID是："+ getActivity().getIntent().getExtras().getInt("bookId"));
//                    textView.setText(cartoonChapter.getChapterTitle());
                }
            }
        });
        //下一章的按钮绑定事件
        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartoonChapters.size()==chapterOrderId){
                    Toast.makeText(context,"最后一话了",Toast.LENGTH_SHORT).show();
                }else {
                    chapterOrderId = chapterOrderId+ 1;
                    Log.v("btn_chapterOrderId",String.valueOf(chapterOrderId));
                    cartoonChapter = cartoonChapters.get(chapterOrderId-1);
                    getPages(cartoonChapter);
                }

                //在这里刷新整个页面 用loadData发送请求拿数据替换

//                getChapters();
//                textView.setText(cartoonChapter.getChapterTitle());
//                bookContent.setText("本一章的内容" + chatContent.getChatOrderID()+ "书ID是："+ getActivity().getIntent().getExtras().getInt("bookId"));
            }
        });
    }

    private void getChapters() {
        Log.v("chapterOrderId",String.valueOf(chapterOrderId));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getActivity().getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChapterService api = retrofit.create(ChapterService.class);
        Call<ChapterResponse> call = api.getCartoonChapter(cId);

        call.enqueue(new Callback<ChapterResponse>() {
            @Override
            public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response)
            {

                ChapterResponse chapterResponse = response.body();
                cartoonChapters =  chapterResponse.getChapters();
                cartoonChapter = cartoonChapters.get(chapterOrderId-1);
                getPages(cartoonChapter);
            }

            @Override
            public void onFailure(Call<ChapterResponse> call, Throwable t)
            {
                Log.e("onFailure", t.getMessage());
                t.printStackTrace();
            }
        });
    }
    private void getPages(CartoonChapter cartoonChapter) {
        Log.v("cartoonChapter",cartoonChapter.toString());
        int chapterId = cartoonChapter.getChapterId();
        textView.setText(cartoonChapter.getChapterTitle());

        //获取Pages
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PageService api = retrofit.create(PageService.class);
        Call<PageResponse> call = api.getPages(chapterId);

        call.enqueue(new Callback<PageResponse>() {
            @Override
            public void onResponse(Call<PageResponse> call, Response<PageResponse> response)
            {
                PageResponse pageResponse = response.body();

                pages =  pageResponse.getPages();
                adapter.setData(pages);
            }

            @Override
            public void onFailure(Call<PageResponse> call, Throwable t)
            {
                Log.e("onFailure", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
