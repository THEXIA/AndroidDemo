package com.example.jiexia.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

/**
 * 1.Adapter（适配器）是用来帮助填充数据的中间桥梁
 * 2.简单点说就是：将各种数据以合适的形式显示到view上,提供 给用户看！
 * */
public class Main4Activity extends AppCompatActivity {

    private ListView list_test;
    private ListView list_test2;
    private Button button_cont2;//继续
    private Button button_back2;//返回

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //要显示的字符
        String[] strs = {"基神","B神","翔神","曹神","J神"};
        //创建ArrayAdapter ，以下为可选布局：
        // simple_list_item_1 : 单独一行的文本框
        // simple_list_item_2 : 两个文本框组成
        // simple_list_item_multiple_choice : 都带有一个复选框
        // simple_list_item_single_choice : 都带有一个单选钮
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,strs);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        list_test = (ListView) findViewById(R.id.list_test);
        list_test.setAdapter(adapter);//将适配器对象放到ListView控件上
        list_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"你选择了:"+list_test.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();//获取选中的item的内容
            }
        });

        //java代码 绑定values/arrays.xml里的myarry的值到list_test2上
        //android.R.layout.simple_list_item_multiple_choice 后面会带一个勾选
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.myarray ,android.R.layout.simple_expandable_list_item_1 );
        list_test2 = (ListView)findViewById(R.id.list_test2);
        list_test2.setAdapter(adapter2);
        list_test2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"你选择了:"+list_test2.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();//获取选中的item的内容
            }
        });

        button_cont2 = (Button)findViewById(R.id.button_cont2);
        button_cont2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,Main5Activity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);//淡入淡出
                onStop();
            }
        });

        button_back2 = (Button)findViewById(R.id.button_back2);
        button_back2.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View v) {
                Main4Activity.this.finish();
            }
        });
    }

}
