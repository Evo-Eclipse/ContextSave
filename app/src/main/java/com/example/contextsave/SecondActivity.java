package com.example.contextsave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contextsave.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ActivitySecondBinding binding = ActivitySecondBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        pref = MainActivity.pref;
        SharedPreferences.Editor edit = pref.edit();

        binding.ETlc.setText(MainActivity.CORRECT[0]);
        binding.ETpc.setText(MainActivity.CORRECT[1]);



        binding.RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString(MainActivity.LOGINSTR, "login:");
                edit.putString(MainActivity.PASSWORDSTR, "password:");
                edit.commit();
                finish();

            }
        });

        binding.SB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.CORRECT[0] = binding.ETlc.getText().toString();
                MainActivity.CORRECT[1] = binding.ETpc.getText().toString();
            }
        });

        binding.ACT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
