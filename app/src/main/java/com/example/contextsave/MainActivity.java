package com.example.contextsave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contextsave.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static String LOGINSTR = "login:";
    public static String PASSWORDSTR = "password:";
    public static String[] CORRECT = {"Illidan", "10000"};
    public static SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        Toast.makeText(this, pref.getString(LOGINSTR, "no value"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, pref.getString(PASSWORDSTR, "no value"), Toast.LENGTH_SHORT).show();

        if(pref.getString(LOGINSTR, "no value").equals(CORRECT[0]) &&
                pref.getString(PASSWORDSTR, "no value").equals(CORRECT[1])) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        binding.Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = binding.ETl.getText().toString();
                String password = binding.ETp.getText().toString();

                if (login.equals(CORRECT[0]) && password.equals(CORRECT[1])) {
                    edit.putString(LOGINSTR, login);
                    edit.putString(PASSWORDSTR, password);
                    edit.commit();

                    binding.TV.setText("Login completed :3");
                    // Toast.makeText(MainActivity.this, "LC", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else {
                    binding.TV.setText("Login failed :(");
                    // Toast.makeText(MainActivity.this, "LF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}