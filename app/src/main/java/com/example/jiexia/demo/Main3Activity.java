package com.example.jiexia.demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView textView_date;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        textView_date = (TextView)findViewById(R.id.textView_date);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView_date.setText("当前选择了"+year+"年"+(++monthOfYear)+"月"+dayOfMonth+"日");
            }
        });

    }

}
