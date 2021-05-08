package com.example.manhua;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhua.adapter.CartoonDashboardAdapter;
import com.example.manhua.api.CartoonService;
import com.example.manhua.domain.CartoonBook;
import com.example.manhua.response.CartoonBooksResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RimanFragment extends Fragment {
    Context context;
    RecyclerView recyclerView;
    CartoonBook cartoonBook;
    List<CartoonBook> cartoonBooks;
    CartoonDashboardAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_riman,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        recyclerView = view.findViewById(R.id.read_list_riman);

        //布局管理器
//        LinearLayoutManager manager = new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(manager);
//        GridLayoutManager layoutManager = new GridLayoutManager(context,3);
        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));


        //设置适配器
        adapter = new CartoonDashboardAdapter(context);
        recyclerView.setAdapter(adapter);

        initData();
        adapter.setChatOnClickListener(new CartoonDashboardAdapter.ChatOnClickListener() {
            @Override
            public void onChatClick(CartoonBook cartoonBook, int position)
            {
                fun();
                Intent intent = new Intent(context, CartoonDetailsActivity.class);
                intent.putExtra("cartoonbook", cartoonBook);
                startActivity(intent);
            }
        });

    }

    private void initData()
    {
        String url;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartoonService api = retrofit.create(CartoonService.class);
        Call<CartoonBooksResponse> call = api.getAllCartoonBook("日漫");

        call.enqueue(new Callback<CartoonBooksResponse>() {
            @Override
            public void onResponse(Call<CartoonBooksResponse> call, Response<CartoonBooksResponse> response)
            {

                CartoonBooksResponse cartoonBooksResponse = response.body();
                cartoonBooks = (List<CartoonBook>) cartoonBooksResponse.getCartoonBooks();
                adapter.setData(cartoonBooks);
            }

            @Override
            public void onFailure(Call<CartoonBooksResponse> call, Throwable t)
            {
                Log.e("onFailure", t.getMessage());
                t.printStackTrace();
            }
        });
    }
    private void fun()
    {

    }
}