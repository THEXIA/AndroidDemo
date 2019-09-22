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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    //保持所启动的Service的IBinder对象,同时定义一个ServiceConnection对象
    MyService1.MyBinder binder;

    Intent it2;

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
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        it2 = getIntent();
        TextView textView = (TextView)findViewById(R.id.textView2);
        textView.setText("你输入的是:"+ it2.getStringExtra("text") );

        final Intent intent = new Intent();
        intent.setAction("com.example.jiexia.demo.MyService1");
        intent.setPackage(getPackageName());//不加这句话5.0以上系统会报错Service Intent must be explicit

        Button button_bind = (Button)findViewById(R.id.button_bind);
        button_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a = bindService(intent, conn, Service.BIND_AUTO_CREATE); //绑定Service
                System.out.println("绑定结果"+a);
            }
        });

        Button button_canclebind = (Button)findViewById(R.id.button_canclebind);
        button_canclebind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn); //解除service绑定
            }
        });

        Button button_getvule = (Button)findViewById(R.id.button_getvule);

        button_getvule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView2 = (TextView) findViewById(R.id.textView3);
                textView2.setText(String.valueOf(binder.getCount()));
                Toast.makeText(Main2Activity.this,"Service的值为"+binder.getCount(),Toast.LENGTH_LONG).show();//弹出提示 LONG 3.5秒
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
