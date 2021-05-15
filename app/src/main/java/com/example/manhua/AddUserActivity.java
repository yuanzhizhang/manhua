package com.example.manhua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhua.api.UserService;
import com.example.manhua.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView addBtn = findViewById(R.id.add_appuser);

        addBtn.setOnClickListener(new View.OnClickListener() {
            //重写点击事件的处理方法onClick()
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddUserActivity.this, LoginActivity.class);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.url))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserService api = retrofit.create(UserService.class);
                Call<Integer> call = api.addUser(username.getText().toString(),password.getText().toString());

                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response)
                    {
                        Integer res = response.body();
                        if(res == null){
                            Log.v("8888888888","11111111111");
                        }
                        int re = res;
                        if (re == 1){
                            Toast.makeText(AddUserActivity.this,"注册成功",Toast.LENGTH_SHORT).show();

                            startActivity(intent);
                        }else {
                            Context context = AddUserActivity.this;
                            Toast.makeText(context,"注册失败",Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t)
                    {
                        Log.e("onFailure", t.getMessage());
                        t.printStackTrace();
                    }
                });

            }
        });
    }
}