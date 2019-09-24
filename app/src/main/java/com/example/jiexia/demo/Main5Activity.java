package com.example.jiexia.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main5Activity extends AppCompatActivity {

    private ListView list_test3;

    private Button button_cont3;
    private Button button_back3;

    //list数据
    private String[] names = new String[]{"辉神", "B神", "曹神","白神","基神","酷神","大神","小神"};
    private String[] says = new String[]{"无形被黑，最为致命", "大神好厉害~", "我将带头日狗~","我上我也行","人在码在","冲啊！","...","..."};
    private int[] imgIds = new int[]{R.drawable.head_icon1, R.drawable.head_icon2, R.drawable.head_icon3, R.drawable.head_icon4, R.drawable.head_icon5, R.drawable.head_icon6, R.drawable.head_icon7, R.drawable.head_icon8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        //初始化一个存放 Map对象的 arraylist
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        //循环names says imgIds，封装成map 存入arraylist
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("touxiang", imgIds[i]);
            showitem.put("name", names[i]);
            showitem.put("says", says[i]);
            listitem.add(showitem);
        }

        //创建一个simpleAdapter （简单适配器？？）
        SimpleAdapter myAdapter =
                new SimpleAdapter(getApplicationContext(),//1.当前activity；
                        listitem ,//2.前面的arraylist；
                        R.layout.list_item ,//3.layout/list_item.xml布局；
                        new String[]{"touxiang", "name", "says"},// 4.键字符串数组；
                        new int[]{R.id.imgtouxiang, R.id.name ,R.id.says});//5.item里的 ImageView图像 和 两个TextView

        list_test3 = (ListView)findViewById(R.id.list_test3);
        list_test3.setAdapter(myAdapter); //将myAdapter适配器 给 list_test3

        button_cont3 = (Button)findViewById(R.id.button_cont3);
        button_cont3.setOnClickListener(new View.OnClickListener() { //继续
            @Override
            public void onClick(View v) {
                return;
            }
        });

        button_back3 = (Button)findViewById(R.id.button_back3);
        button_back3.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View v) {
                Main5Activity.this.finish();
            }
        });

    }

}
