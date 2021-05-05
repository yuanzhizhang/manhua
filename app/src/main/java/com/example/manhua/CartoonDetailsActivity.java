package com.example.manhua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manhua.domain.CartoonBook;

public class CartoonDetailsActivity extends AppCompatActivity {
    CartoonBook cartoonBook;
    TextView bookName;
    ImageView image;
    TextView author;
    TextView introduction;
    TextView type;
    Context context;
    Button startReadBtn;
    Button addBookshefBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon_details);

        context = this;
        cartoonBook = (CartoonBook) getIntent().getSerializableExtra("cartoonbook");

        bookName = findViewById(R.id.cartoon_name);
        bookName.setText(cartoonBook.getcName());

        image = findViewById(R.id.details_img);
        Glide.with(context)
                .load(cartoonBook.getCartoonCoverUrl())
                .into(image);

        introduction = findViewById(R.id.brief_introduction);
        introduction.setText(cartoonBook.getIntroduction());

        startReadBtn = findViewById(R.id.start_read_btn);
        startReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadActivity.class);
                startActivity(intent);
            }
        });

    }
}