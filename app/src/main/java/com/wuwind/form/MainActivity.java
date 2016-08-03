package com.wuwind.form;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wuwind.formproject.R;

import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {

    FormLayout formLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formLayout = (FormLayout) findViewById(R.id.formLayout);
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);
    }

    /**
     * 注册
     */
    public void register(View v) {
        Map<String, String> results = formLayout.getResults();//获取表单中的内容
        if (null == results)
            return;
        Toast.makeText(this, "注册中", Toast.LENGTH_SHORT).show();
    }

    /**
     * 重置
     */
    public void reset(View v) {
        formLayout.reset();//重置，清空
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register)
            register(v);
        else
            reset(v);
    }
}
