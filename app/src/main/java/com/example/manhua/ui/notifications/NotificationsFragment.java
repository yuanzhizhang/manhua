package com.example.manhua.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.manhua.LoginActivity;
import com.example.manhua.MainActivity;
import com.example.manhua.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Button button;
    private Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        button = view.findViewById(R.id.login_out_btn);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);

                sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.remove("login");
                editor.commit();

                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}