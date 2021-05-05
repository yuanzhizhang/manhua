package com.example.manhua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhua.adapter.CartoonChapterAdapter;
import com.example.manhua.domain.CartoonChapter;
import com.example.manhua.domain.PageItem;

import java.util.ArrayList;
import java.util.List;

public class ReadFragment extends Fragment {
    Context context;
    RecyclerView recyclerView;
    Button list_btn;
    Button up_btn;
    Button down_btn;
    TextView textView;

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
        textView.setText("第1话");

        //布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        //设置适配器
        CartoonChapterAdapter adapter = new CartoonChapterAdapter(context);
        recyclerView.setAdapter(adapter);

        bindEvents();

        cartoonChapter = initData();
        adapter.setData(cartoonChapter);



    }

    void bindEvents(){

        //目录
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartoonList.class);
                startActivity(intent);
            }
        });
        //上一章的按钮绑定事件
        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果是第一个章节 给上一章绑定事件不能再按， 按了弹出已经是第一个章节了
                if(cartoonChapter.getChatOrderID()==1) {
                    textView.setText("这是第一话");
                }else {
                    //不是第一章
                    cartoonChapter.setChatOrderID(cartoonChapter.getChatOrderID() - 1);
//                    bookContent.setText("本一章的内容" + chatContent.getChatOrderID()+ "书ID是："+ getActivity().getIntent().getExtras().getInt("bookId"));
                    textView.setText("第"+cartoonChapter.getChatOrderID()+"话");
                }
            }
        });
        //下一章的按钮绑定事件
        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //在这里刷新整个页面 用loadData发送请求拿数据替换
                cartoonChapter.setChatOrderID(cartoonChapter.getChatOrderID() + 1);
                textView.setText("第"+cartoonChapter.getChatOrderID()+"话");
//                bookContent.setText("本一章的内容" + chatContent.getChatOrderID()+ "书ID是："+ getActivity().getIntent().getExtras().getInt("bookId"));
            }
        });
    }

    private CartoonChapter initData() {
        CartoonChapter cartoonChapter  = new CartoonChapter();


        cartoonChapter.setCartoonID(1);
        cartoonChapter.setChatID(1);
        cartoonChapter.setChatOrderID(1);
        cartoonChapter.setChatTitle("第一章");

        List<PageItem> pageItems = new ArrayList<>();

        pageItems.add(new PageItem("http://chapter.cnmanhua.com/comic/D/%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9%E6%8B%86%E5%88%86%E7%89%88/1%E8%AF%9D/1.jpg-kmh.middle.webp?auth_key=1619624291-bf2ee343e5ef4806ae15815af07ced7c-0-0b3adef48ca88b33045f4a41930d1253",1));
        pageItems.add(new PageItem("http://chapter.cnmanhua.com/comic/D/%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9%E6%8B%86%E5%88%86%E7%89%88/1%E8%AF%9D/2.jpg-kmh.middle.webp?auth_key=1619625918-77590a0110d144199b82f05a4166d883-0-7fc4d7edbceeba7013dc69d481f49d8d",2));
        pageItems.add(new PageItem("http://chapter.cnmanhua.com/comic/D/%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9%E6%8B%86%E5%88%86%E7%89%88/1%E8%AF%9D/3.jpg-kmh.middle.webp?auth_key=1619626181-8dd9d2b2da3848639399127a9c702236-0-e5f401153629b7a39d98f598d91a07e6",3));
        pageItems.add(new PageItem("http://chapter.cnmanhua.com/comic/D/%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9%E6%8B%86%E5%88%86%E7%89%88/1%E8%AF%9D/4.jpg-kmh.middle.webp?auth_key=1619626181-85199cadd1324c30add3d519e120bc7b-0-64a5ade3569fdfb389553225197b07f6",4));
        pageItems.add(new PageItem("http://chapter.cnmanhua.com/comic/D/%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9%E6%8B%86%E5%88%86%E7%89%88/1%E8%AF%9D/5.jpg-kmh.middle.webp?auth_key=1619626181-091f6faf133b49448fabcc5e60ea4ee3-0-1fd32acf3d52179471cb5461f8865462",5));
        pageItems.add(new PageItem("http://chapter.cnmanhua.com/comic/D/%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9%E6%8B%86%E5%88%86%E7%89%88/1%E8%AF%9D/6.jpg-kmh.middle.webp?auth_key=1619626181-98721c8321e2415ca69ee627186b5724-0-bbe4e543bf75deb639fce61fb20f11fc",6));

        cartoonChapter.setChatContent(pageItems);

        return cartoonChapter;
    }
}
