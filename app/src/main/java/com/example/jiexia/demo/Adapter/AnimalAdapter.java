package com.example.jiexia.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jiexia.demo.Other.Animal;
import com.example.jiexia.demo.R;
import java.util.LinkedList;

/**
 * 自定义 BaseAdapter
 * */
public class AnimalAdapter extends BaseAdapter {

    private LinkedList<Animal> mData;//数据
    private Context mContext;

    public AnimalAdapter(LinkedList<Animal> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        //convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item2 ,parent,false); //【优化前】 每一个item都会读取list_item2.xml一次
        if(convertView == null){ //【优化后】 convertView对象可以复用，所以无需再次读取list_item2.xml
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item2,parent,false);

            holder = new ViewHolder();
            holder. img_icon = (ImageView) convertView.findViewById(R.id.aIcon);//动物头像
            holder. txt_aName = (TextView) convertView.findViewById(R.id.aName);//动物名称
            holder. txt_aSpeak = (TextView) convertView.findViewById(R.id.aSpeak);//动物说的话

            convertView.setTag(holder);   //将Holder存储到convertView中

        }else{
            holder = (ViewHolder) convertView.getTag();//从convertView里拿出存储好的Holder
        }

        holder. img_icon.setBackgroundResource( mData.get(position).getAIcon() );
        holder. txt_aName.setText(mData.get(position).getaName());
        holder. txt_aSpeak.setText(mData.get(position).getaSpeak());

        return convertView;
    }

    static class ViewHolder{ //静态类 用于存储从R文件里读取的头像 名称 话 ，这样无需再次读取R文件
        ImageView img_icon;
        TextView txt_aName;
        TextView txt_aSpeak;
    }

    public void addItem(Animal animal){ // 写一个 添加一行的方法
        if(mData == null){
            mData = new LinkedList<Animal>();
        }
        mData.add(animal);
        notifyDataSetChanged();//通过一个外部的方法控制如果适配器的内容改变时需要强制调用getView来刷新有更新的Item的内容。非刷新所有
    }

    public void removeItemEnd(){ //删除最后一行
        if(mData == null){
            mData = new LinkedList<Animal>();
        }
        mData.remove(mData.size()-1);
        notifyDataSetChanged();
    }

    public void removeItemAll(){  //删除所有记录
        if(mData == null){
            return;
        }
        mData.clear();
        notifyDataSetChanged();
    }

}
