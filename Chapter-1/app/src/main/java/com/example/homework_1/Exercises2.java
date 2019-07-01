package com.example.homework_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises2);
        View v = findViewById(R.id.linearlayout_1);
        int num = 0;
        num = getViewCount(v);
        TextView mtv = findViewById(R.id.textview);
        mtv.setText("The number is：" + num);
    }

    public static int getViewCount(View view) {
        //todo 补全你的代码
        int n = 0;
        if(view instanceof ViewGroup){
            ViewGroup vg = (ViewGroup) view;
            for(int i = 0; i < vg.getChildCount(); i++){
                n = n + getViewCount(vg.getChildAt(i));
            }
            return n+1;
        }
        else{
            return 1;
        }
    }
}
