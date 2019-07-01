package com.example.homework_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.EditText;
import android.widget.CompoundButton;
import android.text.method.PasswordTransformationMethod;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_exercises1).setOnClickListener(this);
        findViewById(R.id.btn_exercises2).setOnClickListener(this);
        findViewById(R.id.btn_exercises3).setOnClickListener(this);

//        Button mbt1 = findViewById(R.id.button2);
//        final EditText met1 = findViewById(R.id.editText);
//        final EditText met2 = findViewById(R.id.editText2);
//
//        Switch mst = findViewById(R.id.switch1);
//
//        mbt1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                met1.setText(R.string.blank);
//                met2.setText(R.string.blank);
//                Log.d("MainActivity","log in successed");
//            }
//        });
//
//        mst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton mCompoundButton, boolean isChecked){
//                if(isChecked) {
//                    met2.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    Log.d("MainActivity","hide the password successed");
//                }
//                else
//                    met2.setTransformationMethod(null);
//                    Log.d("MainActivity","show the password successed");
//            }
//        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exercises1:
                startActivity(new Intent(this, Exercises1.class));
                break;
            case R.id.btn_exercises2:
                startActivity(new Intent(this, Exercises2.class));
                break;
            case R.id.btn_exercises3:
                startActivity(new Intent(this, Exercises3.class));
                break;
        }
    }
}
