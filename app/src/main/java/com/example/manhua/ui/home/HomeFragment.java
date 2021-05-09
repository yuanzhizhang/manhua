package com.example.manhua.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhua.R;
import com.example.manhua.ReadActivity;
import com.example.manhua.adapter.CartoonBookshelfAdapter;
import com.example.manhua.api.CartoonService;
import com.example.manhua.domain.CartoonBook;
import com.example.manhua.response.CartoonBooksResponse;
import com.example.manhua.response.CartoonResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private Context context;

    Integer chapterOrderId;
    List<CartoonBook> cartoonBooks = new ArrayList<>();
    CartoonBookshelfAdapter adapter;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        recyclerView = view.findViewById(R.id.list);

        //布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        //设置适配器
        adapter = new CartoonBookshelfAdapter(context);
        recyclerView.setAdapter(adapter);

        cartoonBooks = initData();


        adapter.setChatOnClickListener(new CartoonBookshelfAdapter.ChatOnClickListener() {
            @Override
            public void onChatClick(CartoonBook cartoonBook, int position)
            {
                fun();
//                contacts.get(position);
                String cId = String.valueOf(cartoonBook.getcId());
                sharedPreferences = getActivity().getSharedPreferences("bookRecord", Context.MODE_PRIVATE);
                chapterOrderId = sharedPreferences.getInt(cId,1);
                Intent intent = new Intent(context, ReadActivity.class);
                intent.putExtra("cId", cartoonBook.getcId());
                intent.putExtra("chapterOrderId",chapterOrderId);
                startActivity(intent);
            }
        });

    }

    private List<CartoonBook> initData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartoonService api = retrofit.create(CartoonService.class);

        sharedPreferences = getActivity().getSharedPreferences("bookShelf", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        Iterator<? extends Map.Entry<String, ?>> entryIterator = allEntries.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<String, ?> allEntrie = entryIterator.next();
            Call<CartoonResponse> call = api.getOntCartoonBook((Integer) allEntrie.getValue());

            call.enqueue(new Callback<CartoonResponse>() {
                @Override
                public void onResponse(Call<CartoonResponse> call, Response<CartoonResponse> response)
                {

                    CartoonResponse cartoonResponse = response.body();
                    CartoonBook cartoonBook = cartoonResponse.getCartoonBook();
                    adapter.addData(cartoonBook);
                }

                @Override
                public void onFailure(Call<CartoonResponse> call, Throwable t)
                {
                    Log.e("onFailure", t.getMessage());
                    t.printStackTrace();
                }
            });

        }

        return cartoonBooks;
    }

    private void fun()
    {

    }
}