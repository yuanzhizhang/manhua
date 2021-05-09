package com.example.manhua;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhua.api.UserService;

import androidx.appcompat.app.AppCompatActivity;

import com.example.manhua.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            //重写点击事件的处理方法onClick()
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.url))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserService api = retrofit.create(UserService.class);
                Call<LoginResponse> call = api.login(username.getText().toString(),password.getText().toString());

                Call<LoginResponse> call1 = api.getJoke();


                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
                    {
                        LoginResponse loginResponse = response.body();
                        String res = (String)loginResponse.getRes();
                        if (res.equals("true")){
                            sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString("login", "ok");
                            editor.commit();

                            startActivity(intent);
                        }else {
                            Context context = LoginActivity.this;
                            Toast.makeText(context,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t)
                    {
                        Log.e("onFailure", t.getMessage());
                        t.printStackTrace();
                    }
                });

            }
        });

    }
}
