package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animationView;
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);

        animationView = view.findViewById(R.id.animation_view);
        listView = view.findViewById(R.id.listview);

        listView.setAdapter(new ListViewAdapter(this.getActivity()));;

        return view;
    }

    public static class ListViewAdapter extends BaseAdapter {
        private int[] args =
                new int[] {1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12};


        private Context mContext;

        public ListViewAdapter(Context context) {
            mContext = context;
        }

        @Override public int getCount() {
            return args.length;
        }

        @Override public Object getItem(int position) {
            return null;
        }

        @Override public long getItemId(int position) {
            return 0;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(mContext);
            } else {
                textView = (TextView) convertView;
            }

            textView.setText("item" + args[position]);
            textView.setTextSize(18);
            return textView;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(animationView, "alpha", 1f, 0f);
                alphaAnimator.setRepeatCount(0);
                alphaAnimator.setInterpolator(new LinearInterpolator());
                alphaAnimator.setDuration(1500);
                alphaAnimator.start();
                ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(listView, "alpha", 0f, 1f);
                alphaAnimator2.setRepeatCount(0);
                alphaAnimator2.setInterpolator(new LinearInterpolator());
                alphaAnimator2.setDuration(1500);
                alphaAnimator2.start();
                listView.setVisibility(View.VISIBLE);
//            alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
            }
        }, 5000);


    }
}
