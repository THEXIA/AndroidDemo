package com.example.jiexia.demo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import com.example.jiexia.demo.Service.MyService1;

public class Main2Activity extends AppCompatActivity {

    private TextView textView;

    private Button button_bind;
    private Button button_canclebind;
    private Button button_getvule;

    private RadioGroup radioGroup;
    private static RadioButton radioButton = null; //用于存放临时选择的radioGroup的值
    private Button button_intent;

    private SeekBar seekBar;//拖动条
    private TextView textView_seek;
    private ProgressBar progressBar;

    private RatingBar ratingBar;//评星

    private Button button_cont;//继续

    //---------------------------
    //保持所启动的Service的IBinder对象,同时定义一个ServiceConnection对象
    private MyService1.MyBinder binder;

    private Intent it2; //Intent(意图) ，与 上一个 MainActivity 通信的对象

    private Intent it3; //与MyIntentService1通信的对象

    private ServiceConnection conn = new ServiceConnection() {

        //Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("------Service DisConnected断开连接-------");
        }

        //Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("------Service Connected连接成功-------");
            binder = (MyService1.MyBinder) service;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);//绑定 activity_main2.xml

        it2 = getIntent();
        textView = (TextView)findViewById(R.id.textView2);
        textView.setText("你输入的是:"+ it2.getStringExtra("text") );
        //------------------------------------------------
        //准备绑定Service
        final Intent intent = new Intent(); //初始化 与MyService1通信的 intent对象
        intent.setAction("com.example.jiexia.demo.Service.MyService1");//这里写的是AndroidManifest.xml里的intent-filter的name
        intent.setPackage(getPackageName());  //不加这句话5.0以上系统会报错Service Intent must be explicit

        button_bind = (Button)findViewById(R.id.button_bind);
        button_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a = bindService(intent, conn, Service.BIND_AUTO_CREATE); //绑定Service
                System.out.println("绑定结果"+a);
            }
        });

        button_canclebind = (Button)findViewById(R.id.button_canclebind);
        button_canclebind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);   //解除service绑定
            }
        });

        button_getvule = (Button)findViewById(R.id.button_getvule);
        button_getvule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView2 = (TextView) findViewById(R.id.textView3);
                textView2.setText(String.valueOf(binder.getCount())); //改变文本的值
                Toast.makeText(Main2Activity.this,"Service的值为"+binder.getCount(),Toast.LENGTH_LONG).show();//弹出提示 LONG 3.5秒
            }
        });

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);//单选按钮组 对象
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { //为radioGroup设置一个监听器:setOnCheckedChanged()
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton)findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "按钮组值发生改变,你选了" + radioButton.getText(), Toast.LENGTH_LONG).show();
                Main2Activity.radioButton = radioButton;
            }
        });

        button_intent = (Button)findViewById(R.id.button_intent);
        it3 = new Intent();
        it3.setAction("com.example.jiexia.demo.Service.MyIntentService1");
        it3.setPackage(getPackageName());

        button_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //启动MyIntentService1 ， 根据选择的
                //switch (radioGroup.getCheckedRadioButtonId())
                Toast.makeText(getApplicationContext(), "按钮组选择的按钮ID为" + radioGroup.getCheckedRadioButtonId(), Toast.LENGTH_LONG).show();
                Button bt1 = (Button)findViewById(radioGroup.getCheckedRadioButtonId());
                System.out.println(bt1.getText());
                String param = "s1";
                if("选项1".equals(bt1.getText()))
                    param = "s1";
                else if("选项2".equals(bt1.getText()))
                    param = "s2";
                else if("选项3".equals(bt1.getText()))
                    param = "s3";
                Bundle b1 = new Bundle();
                b1.putString("param",param);
                it3.putExtras(b1);
                startService(it3); //开启 MyIntentService1

            }
        });

        seekBar = findViewById(R.id.seekBar);//拖动条
        textView_seek = findViewById(R.id.textView_seek);
        progressBar = findViewById(R.id.progressBar2);//进度条
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { //进度条发生改变触发 progress进度值
                textView_seek.setText("当前进度值:"+progress);
                progressBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { //按住触发
                Toast.makeText(getApplicationContext(), "按住了进度条", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { //放开触发
                Toast.makeText(getApplicationContext(), "放开了进度条", Toast.LENGTH_SHORT).show();
            }
        });

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //ratingBar.getNumStars();//评星条的最大值
                Toast.makeText(getApplicationContext(), "评星条为:"+ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });

        button_cont = (Button)findViewById(R.id.button_cont);

        button_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));//启动activity3
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);//淡入淡出
                onStop();//停止当前
            }
        });

    }



}
