package com.example.jiexia.demo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 1.Service(服务)是一个一种可以在后台执行长时间运行操作而没有用户界面的应用组件。
 * 2.服务可以处理网络事务、播放音乐，执行文件 I/O 或与内容提供程序交互，而所有这一切均可在后台进行
 * 3.Service不是一个单独的进程,它和它的应用程序在同一个进程中
 * 4.Service不是一个线程,这样就意味着我们应该避免在Service中进行耗时操作 ，否则使用 IntentService
 * */
public class MyService1 extends Service {

    private final String TAG = "MyService1";
    private int count = 0;
    private boolean quit;

    //定义onBinder方法所返回的对象
    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        public int getCount(){
            return count;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG,"onBind被调用");
        return binder;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    //Service被创建时回调  count的值会每秒 +1
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate方法被调用!");
        //创建一个线程动态地修改count的值
        new Thread()
        {
            public void run()
            {
                while(!quit)
                {
                    try
                    {
                        Thread.sleep(1000);
                    }catch(InterruptedException e){e.printStackTrace();}
                    count++;
                }
            }
        }.start();
    }

    //Service断开连接时回调
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind方法被调用!");
        return true;
    }

    //Service被关闭前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        Log.i(TAG, "onDestroyed方法被调用!");
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind方法被调用!");
        super.onRebind(intent);
    }

}
