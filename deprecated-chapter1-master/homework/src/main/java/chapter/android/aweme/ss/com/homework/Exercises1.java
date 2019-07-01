package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 作业1：
 * onPause -> onStop -> onDestroy -> onCreate -> onStart -> onResume
 */
public class Exercises1 extends AppCompatActivity {

    private static String TAG = Exercises1.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises1);
        Log.d(TAG, "onCreate: xx");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: xx");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: xx");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: xx");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: xx");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: xx");
    }
}
