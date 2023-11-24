package com.example.contextsave;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contextsave.databinding.CoolMarketBinding;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CoolMarketBinding binding = CoolMarketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + binding.phone.getText().toString()));
                startActivity(intent);
            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] menu = {"Фрукты", "Овощи", "Суши",
                                    "Молоко", "Выпечка", "Кофе"};
                ArrayList<Integer> orderid = new ArrayList<>();
                boolean delivery = false;

                for (int i = 0; i < binding.LL1.getChildCount(); i++) {
                    CheckBox c = (CheckBox) binding.LL1.getChildAt(i);
                    if (c.isChecked()) orderid.add(i);
                }
                for (int i = 0; i < binding.LL2.getChildCount(); i++) {
                    CheckBox c = (CheckBox) binding.LL2.getChildAt(i);
                    if (c.isChecked()) orderid.add(3+i);
                }

                if (binding.RB1.isChecked()) delivery = true;

                StringBuilder subjecttext = new StringBuilder();
                int ind = orderid.size();
                subjecttext.append("Заказ: ");
                for (int i: orderid) {
                    subjecttext.append(menu[i]);
                    ind--;
                    if (ind == 0) {
                        subjecttext.append(".");
                    } else {
                        subjecttext.append(", ");
                    }
                }
                subjecttext.append("\nТип: ").append(delivery? "С доставкой" : "Самовывозом");
                subjecttext.append("\nКомментарий заказчика: ");
                subjecttext.append(binding.ETcm.getText());

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"my@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Доставка продуктов");
                intent.putExtra(Intent.EXTRA_TEXT, subjecttext.toString());
                Toast.makeText(ThirdActivity.this, "Перенаправление в почту",
                        Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}
