package com.example.manhua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

public class ReadActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        //fragment的
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_read_content,ReadFragment.class,null)
                .commit();

    }
}