package com.example.jiexia.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;
import com.example.jiexia.demo.Adapter.MyAdapter;
import com.example.jiexia.demo.Other.GridItem;

import java.util.ArrayList;

public class Main7Activity extends AppCompatActivity {

    private GridView gridView; //网格布局
    private BaseAdapter mAdapter = null; //适配器
    private ArrayList<GridItem> mData = null; //数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        gridView = (GridView)findViewById(R.id.gridView);

        mData = new ArrayList<GridItem>();
        mData.add(new GridItem(R.mipmap.animal_icon1,"图标1"));
        mData.add(new GridItem(R.mipmap.animal_icon1,"图标2"));
        mData.add(new GridItem(R.mipmap.animal_icon1,"图标3"));
        mData.add(new GridItem(R.mipmap.animal_icon1,"图标4"));
        mData.add(new GridItem(R.mipmap.animal_icon1,"图标5"));
        mData.add(new GridItem(R.mipmap.animal_icon1,"图标6"));
        mData.add(new GridItem(R.mipmap.animal_icon1,"图标7"));
        mData.add(new GridItem(R.mipmap.animal_icon1,"图标8"));

        mAdapter = new MyAdapter<GridItem>(mData,R.layout.item_grid_icon) { //调用可复用adapter
            @Override
            public void bindView(ViewHolder holder, GridItem obj) { //必须实现的绑定holder方法， 将GridItem对象的数据和xml的view绑定起来
                holder.setImageResource(R.id.img_icon,obj.getiId());//绑定图片类型
                holder.setText(R.id.txt_icon,obj.getiName());//绑定文字类型
            }
        };

        gridView.setAdapter(mAdapter);//把适配器给gridLayout
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "你点击了~" + (++position) + "~项", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
