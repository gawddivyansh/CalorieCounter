package com.example.caloriecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView calView = findViewById(R.id.textView);
        Button add = findViewById(R.id.button);
        SharedPreferences sharedPreferences = getSharedPreferences("Test", MODE_PRIVATE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText calView = (EditText)findViewById(R.id.editTextTextPersonName);
                int num = Integer.parseInt(calView.getText().toString());
                EditText totalCal = (EditText)findViewById(R.id.editTextTextPersonName2);
                int total = Integer.parseInt(totalCal.getText().toString());
                total += num;
                totalCal.setText(""+total);
                SharedPreferences sharedPreferences = getSharedPreferences("Test", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("totalcal", Integer.parseInt(totalCal.getText().toString()));
                editor.commit();

            }
        });

        SharedPreferences.Editor editor = sharedPreferences.edit();
        int prevDate = sharedPreferences.getInt("day", 0);
        EditText totalCalView = (EditText)findViewById(R.id.editTextTextPersonName2);
        if(prevDate==Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
            int totalCal = sharedPreferences.getInt("totalcal", 0);
            totalCalView.setText(""+totalCal);
            editor.putInt("totalcal", Integer.parseInt(totalCalView.getText().toString()));
            editor.commit();
        }
        else{
            editor.putInt("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            editor.commit();
            totalCalView.setText(""+0);
        }

    }
}