package com.example.jiexia.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.example.jiexia.demo.Adapter.MyAdapter;
import com.example.jiexia.demo.Other.GridItem;
import com.example.jiexia.demo.Other.SpinnerItem;

import java.util.ArrayList;

public class Main7Activity extends AppCompatActivity {

    private GridView gridView; //网格布局
    private BaseAdapter mAdapter = null; //适配器
    private ArrayList<GridItem> mData = null; //数据

    private Spinner spinnerView;
    private BaseAdapter mAdapter1 = null;
    private ArrayList<SpinnerItem> mData1 = null;

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

        spinnerView = (Spinner)findViewById(R.id.spinnerView);
        mData1 = new ArrayList<SpinnerItem>();
        mData1.add(new SpinnerItem(R.mipmap.animal_icon1,"迅捷斥候：提莫（Teemo）"));
        mData1.add(new SpinnerItem(R.mipmap.animal_icon1,"诺克萨斯之手：德莱厄斯（Darius）"));
        mData1.add(new SpinnerItem(R.mipmap.animal_icon1,"无极剑圣：易（Yi）"));
        mData1.add(new SpinnerItem(R.mipmap.animal_icon1,"荣耀行刑官：德莱文（Draven）"));
        mData1.add(new SpinnerItem(R.mipmap.animal_icon1,"德邦总管：赵信（XinZhao）"));
        mData1.add(new SpinnerItem(R.mipmap.animal_icon1,"狂战士：奥拉夫（Olaf）"));

        mAdapter1 = new MyAdapter<SpinnerItem>(mData1,R.layout.item_spinner_icon) {

            @Override
            public void bindView(ViewHolder holder, SpinnerItem obj) {
                holder.setImageResource(R.id.img_icon_spinner,obj.gethIcon());
                holder.setText(R.id.txt_name_spinner,obj.gethName());
            }
        };
        spinnerView.setAdapter(mAdapter1);
        spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView txt_name = (TextView) view.findViewById(R.id.txt_name_spinner);
                Toast.makeText(getApplicationContext(),"你选择了" + txt_name.getText().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
