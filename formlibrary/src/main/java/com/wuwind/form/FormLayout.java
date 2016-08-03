package com.wuwind.form;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wuhf on 2016/7/29.
 * Description ：表单，获取表单里所有的edittext，tag 为"null"不是必填字段（默认都是必填字段）
 * 必填字段为空则弹出“请输入xxx”提示, @link(checkEdit)。
 * 调用getResult 返回结果map,key为设置的tag，如果tag为空的话 依次从1开始递增。
 * 调用reset 清空表单。
 */
public class FormLayout extends FrameLayout {

    List<EditText> editTexts = new ArrayList<>();

    Map<String, String> results = new HashMap<>();

    private int key;

    public FormLayout(Context context) {
        super(context);
    }

    public FormLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void getEditTexts(ViewGroup vg) {
        int childCount = vg.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = vg.getChildAt(i);
            if (view instanceof EditText)
                editTexts.add((EditText) view);
            else if (view instanceof ViewGroup) {
                getEditTexts((ViewGroup) view);
            }
        }
    }

    /**
     * 获取所有edittext的值
     *
     * @return 如果有必填字段为空，则返回null
     * 否则返回map：key为设置的tag，value为内容
     */
    public Map<String, String> getResults() {
        key = 0;
        results.clear();
        for (EditText editText : editTexts) {
            Object tag = editText.getTag();
            String r = editText.getText().toString();
            if ((tag == null || !tag.equals("null")) && TextUtils.isEmpty(r)) {
                checkEdit(editText.getHint().toString());
                return null;
            }
            if (tag == null || tag.equals("null")) {
                key++;
                results.put(key + "", r);
            } else {
                results.put(tag.toString(), r);
            }
        }
        return results;
    }

    /**
     * 清空表单
     */
    public void reset() {
        for (EditText editText : editTexts) {
            editText.setText("");
        }
    }

    /**
     * 初始化表单，默认不手动调用。除非动态添加EditText,需要重新初始化
     */
    public void initEditText() {
        editTexts.clear();
        getEditTexts(this);
    }

    /**
     * 必填字段为空时，弹toast提示。
     *
     * @param content
     */
    protected void checkEdit(String content) {
        if (!content.contains("请输入")) {
            content = "请输入" + content;
        }
        Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initEditText();
    }
}
