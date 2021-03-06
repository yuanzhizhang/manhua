package com.example.manhua.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.manhua.CartoonDetailsActivity;
import com.example.manhua.R;
import com.example.manhua.adapter.CartoonDashboardAdapter;
import com.example.manhua.adapter.DashboardViewPagerAdapter;
import com.example.manhua.adapter.ImageAdapter;
import com.example.manhua.api.CartoonService;
import com.example.manhua.domain.CartoonBook;
import com.example.manhua.response.CartoonBooksResponse;
import com.example.manhua.response.CartoonResponse;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;

import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    Banner banner;
    CartoonBook cartoonBook;
    List<CartoonBook> cartoonBooks = new ArrayList<>();
    ImageAdapter imageAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        TabLayout tabLayout = view.findViewById(R.id.tabs);


//      DashboardViewPagerAdapter

        DashboardViewPagerAdapter adapter = new DashboardViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        useBanner();
    }

    public void useBanner() {
        banner = getView().findViewById(R.id.banner);
        imageAdapter = new ImageAdapter(cartoonBooks,getContext());

        banner.addBannerLifecycleObserver(getActivity())//???????????????????????????
                .setAdapter(imageAdapter)
                .setIndicator(new CircleIndicator(getContext()));

        cartoonBooks = getCartoonBooks();
        while (cartoonBooks == null){

        }

        imageAdapter.setChatOnClickListener(new ImageAdapter.ChatOnClickListener() {
            @Override
            public void onChatClick(CartoonBook cartoonBook, int position)
            {
                fun();
                Intent intent = new Intent(getContext(), CartoonDetailsActivity.class);
                intent.putExtra("cartoonbook", cartoonBook);
                startActivity(intent);
            }
        });
    }

    public List<CartoonBook> getCartoonBooks(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartoonService api = retrofit.create(CartoonService.class);

            Call<CartoonBooksResponse> call = api.getAllCartoonBook();

            call.enqueue(new Callback<CartoonBooksResponse>() {
                @Override
                public void onResponse(Call<CartoonBooksResponse> call, Response<CartoonBooksResponse> response)
                {

                    CartoonBooksResponse cartoonBooksResponse = response.body();
                    cartoonBooks = cartoonBooksResponse.getCartoonBooks();
                    imageAdapter.setBooks(cartoonBooks);
                }

                @Override
                public void onFailure(Call<CartoonBooksResponse> call, Throwable t)
                {
                    Log.e("onFailure", t.getMessage());
                    t.printStackTrace();
                }
            });

        return cartoonBooks;
    }

    private void fun(){

    }
}