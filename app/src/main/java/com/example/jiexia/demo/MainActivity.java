package com.example.jiexia.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editText;
    private Button button_done;

    private Button button_gird;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //创建和初始化
        System.out.println("MainActivity被创建!");
        super.onCreate(savedInstanceState);

        //核心代码.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            //给状态栏设置颜色。我设置透明。
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//
//        }



        setContentView(R.layout.activity_main); //绑定activity和布局

        toolbar = (Toolbar) findViewById(R.id.toolbar);//右上角的
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.editText); //文本输入框对象

        button_done = (Button) findViewById(R.id.button_done); //获取 确定按钮 对象
        button_done.setOnClickListener(new View.OnClickListener() { //绑定点击事件
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("这里是标题") //提示标题
                            .setMessage("确认吗？") //提示内容
                                .setPositiveButton("取消", new DialogInterface.OnClickListener() {  //选项1 和 选择事件
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(MainActivity.this,"你点击了取消",Toast.LENGTH_SHORT).show();//弹出提示 SHORT 2秒
                                    }
                                })
                                    .setNegativeButton("发送", new DialogInterface.OnClickListener() { //选项2
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //这里准备将当前activity的输入框的内容传递到activity2
                                            String text = editText.getText().toString();
                                            Intent it = new Intent(MainActivity.this,Main2Activity.class);//两个参数当前activity ，另外一个activity
                                            Bundle bd = new Bundle(); //新建bundle对象，把数据写入
                                            bd.putCharSequence("text",text);//键值对？
                                            it.putExtras(bd); //将bundle对象绑定给Intent对象
                                            startActivity(it); //启动activity 由于给了第二个activity的class对象 可能用反射启动了第二个activity

                                            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);//淡入淡出

                                            Toast.makeText(MainActivity.this,"你点击了确定",Toast.LENGTH_LONG).show();//弹出提示 LONG 3.5秒
                                            //finish(); //关闭当前activity
                                            onStop();
                                        }
                                    })
                                        .show();
            }
        });

        button_gird = (Button)findViewById(R.id.button_gird);
        button_gird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main7Activity.class));
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);//淡入淡出
                onStop();
            }
        });
    }

    @Override
    protected void onStart() { //启动
        System.out.println("MainActivity启动！");
        super.onStart();
    }

    @Override
    protected void onResume() { //活动
        System.out.println("MainActivity在前台活动！");
        super.onResume();
    }

    @Override
    protected void onPause() { //退出活动
        System.out.println("MainActivity退出前台活动！");
        super.onPause();
    }

    @Override
    protected void onStop() { //停止活动
        System.out.println("MainActivity停止！");
        super.onStop();
    }

    @Override
    protected void onDestroy() { //销毁，被回收
        System.out.println("MainActivity销毁！");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //右上角菜单栏选项 , menu_main.xml
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) { //setting
            startActivity(new Intent(MainActivity.this,Main4Activity.class)); //跳转到Main4Activity
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);//淡入淡出
            onStop();
        }
        if (id == R.id.action_settings2) { //about
            Toast.makeText(getApplicationContext(),"https://github.com/THEXIA/AndroidDemo/tree/master/apk",Toast.LENGTH_LONG).show();
        }
        if (id == R.id.action_settings3){ //exit
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
