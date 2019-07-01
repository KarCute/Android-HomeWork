package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
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
