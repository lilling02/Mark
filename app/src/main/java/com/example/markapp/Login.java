package com.example.markapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        SqlOpt sqlOpt=new SqlOpt(this);

        // 获取 页面上的两个输入框
        EditText username = findViewById(R.id.usernameEditText);
        EditText password = findViewById(R.id.passwordEditText);
//
//        // 点击按钮获取文本框的值
//        String usernameValue = username.getText().toString().trim();
//        String passwordValue = password.getText().toString().trim();

        // 获取按钮
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击按钮获取文本框的值
                String usernameValue = username.getText().toString().trim();
                String passwordValue = password.getText().toString().trim();

                Log.i("TAG", "onClick: "+ usernameValue + passwordValue);

                // 用户注册

                Cursor cursor = sqlOpt.queryAllUser();
                cursor.moveToFirst();
                String uname;
                Boolean isExist = true;

                for (int j = 0; j < cursor.getCount(); j++) {
                    uname = cursor.getString(1);
                    if (usernameValue.equals(uname)) {
                        isExist = false;
                        Toast.makeText(Login.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    cursor.moveToNext();
                }
                if (isExist) {
                    // 如果不存在添加进入数据库
                    Toast.makeText(Login.this, "注册成功", Toast.LENGTH_SHORT).show();
                    sqlOpt.insertUserDate(usernameValue,passwordValue);
                }
            }
        });

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击按钮获取文本框的值
                String usernameValue = username.getText().toString().trim();
                String passwordValue = password.getText().toString().trim();


                // 登录验证逻辑：
                boolean isExistUser = false;
                boolean isLogin = false;

                Cursor cursor = sqlOpt.queryAllUser();
                cursor.moveToFirst();
                String uname;
                String uPassword;

                for (int j = 0; j < cursor.getCount(); j++) {
                    uname = cursor.getString(1);
                    uPassword = cursor.getString(2);

                    if (usernameValue.equals(uname)) {
                        isExistUser = true;
                    }

                    Log.i("TAG1", "username:"+usernameValue);
                    Log.i("TAG1", "uname:"+uname);
                    Log.i("TAG1", "password:"+passwordValue);
                    Log.i("TAG1", "u_Password:"+uPassword);
                    if (usernameValue.equals(uname) && passwordValue.equals(uPassword)) {

                        isLogin = true;
                    }
                    cursor.moveToNext();
                }

                if (!isExistUser) {
                    Toast.makeText(Login.this, "用户未注册", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (isLogin){
                    // 登录成功跳转页面逻辑

                    // 创建一个Intent对象，指定从ActivityA跳转到ActivityB
                    Intent intent = new Intent(Login.this, MainActivity.class);

                    // 启动ActivityB
                    startActivity(intent);
                }

            }
        });



    }


}