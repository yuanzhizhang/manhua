package com.example.manhua.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.manhua.domain.CartoonBook;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private Context context;



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
        CartoonBookshelfAdapter adapter = new CartoonBookshelfAdapter(context);
        recyclerView.setAdapter(adapter);

        List<CartoonBook> cartoonBooks = initData();
        adapter.setData(cartoonBooks);

        adapter.setChatOnClickListener(new CartoonBookshelfAdapter.ChatOnClickListener() {
            @Override
            public void onChatClick(CartoonBook cartoonBook, int position)
            {
                fun();
//                contacts.get(position);
                Intent intent = new Intent(context, ReadActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<CartoonBook> initData()
    {
        String url;
        List<CartoonBook> cartoonBooks = new ArrayList<>();
        for (int i = 0; i < 20; i++)
        {
            url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fyouimg1.c-ctrip.com%2Ftarget%2Ftg%2F035%2F063%2F726%2F3ea4031f045945e1843ae5156749d64c.jpg&refer=http%3A%2F%2Fyouimg1.c-ctrip.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1622120210&t=11652c22b5dbc94c8863c86fc0fea1b1";
            CartoonBook cartoonBook = new CartoonBook();
            cartoonBook.setCartoonCoverUrl(url);
            cartoonBook.setcName("123");
            cartoonBook.setCartoonLastChat("16话");
            cartoonBook.setCartoonUpdateTime("2021-4-27");
            cartoonBooks.add(cartoonBook);
        }

        return cartoonBooks;
    }

    private void fun()
    {

    }
}