package com.example.jiexia.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.example.jiexia.demo.Adapter.AnimalAdapter;
import com.example.jiexia.demo.Other.Animal;

import java.util.LinkedList;
import java.util.List;

public class Main6Activity extends AppCompatActivity implements View.OnClickListener {

    private AnimalAdapter mAdapter;

    private ListView list_test6;

    private Button button_additem;
    private Button button_delend;
    private Button button_delall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        list_test6 = (ListView)findViewById(R.id.list_test6);

        /*
        addHeaderView(View v)：添加headView(表头),括号中的参数是一个View对象
        addFooterView(View v)：添加footerView(表尾)，括号中的参数是一个View对象
        addHeaderView(headView, null, false)：和前面的区别：设置Header是否可以被选中
        addFooterView(View,view,false)：同上
        必须放在setAdapter()前面，否则会报错。
        */

        List<Animal> mData = new LinkedList<Animal>();
        mData.add(new Animal("狗说", "你是狗么?", R.mipmap.animal_icon1));
        mData.add(new Animal("牛说", "你是牛么?", R.mipmap.animal_icon1));
        mData.add(new Animal("鸭说", "你是鸭么?", R.mipmap.animal_icon1));
        mData.add(new Animal("鱼说", "你是鱼么?", R.mipmap.animal_icon1));
        mData.add(new Animal("马说", "你是马么?", R.mipmap.animal_icon1));

        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, Main6Activity.this);

        list_test6.setAdapter(mAdapter);

        list_test6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        button_additem = (Button)findViewById(R.id.button_additem);
        button_additem.setOnClickListener(this);

        button_delend = (Button)findViewById(R.id.button_delend);
        button_delend.setOnClickListener(this);

        button_delall = (Button)findViewById(R.id.button_delall);
        button_delall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_additem://添加一行
                mAdapter.addItem(new Animal("马说", "你是马么?", R.mipmap.animal_icon1));//这里无须担心mAdapter未初始化
                break;
            case R.id.button_delend://删除最后一行
                mAdapter.removeItemEnd();
                break;
            case R.id.button_delall://删除所有
                mAdapter.removeItemAll();
                break;
        }
    }
}
