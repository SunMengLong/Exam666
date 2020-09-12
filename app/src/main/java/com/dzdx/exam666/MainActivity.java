package com.dzdx.exam666;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private EditText edit;
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = findViewById(R.id.tv);
        edit = findViewById(R.id.edit);
        but = findViewById(R.id.ok);
        but.setOnClickListener(this);
    }

    public String getString(String fileName) {
        InputStreamReader inputStreamReader = null;
        try {
            InputStream inputStream = getResources().getAssets().open(fileName);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (TextUtils.isEmpty(line)) {
                    sb.append(">>>");
                } else {
                    sb.append(line);
                }
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public void onClick(View view) {
        // 获取用户输入的值
        String editStr = edit.getText().toString().trim();
        if (TextUtils.isEmpty(editStr)) {
            Toast.makeText(this, "请输入题目中的关键字", Toast.LENGTH_SHORT).show();
        } else {
            // 筛选出的题目
            StringBuffer selectSubject = new StringBuffer();
            // 读取选择题题库
            String str = getString("exam.txt");
            String[] arr = str.split(">>>");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].contains(editStr)) {
                    selectSubject.append(arr[i] + "\n");
                }
            }
            tv.setText(selectSubject.toString());
        }
    }
}
