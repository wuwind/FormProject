package com.wuwind.form;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;

/**
 * Created by Wuhf on 2016/7/29.
 * Description ：重写checkEdit  自己做处理
 */
public class MyFormLayout extends FormLayout{

    public MyFormLayout(Context context) {
        super(context);
    }

    public MyFormLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void checkEdit(String content) {
        Toast.makeText(getContext(),content+"M",Toast.LENGTH_SHORT).show();
    }
}
