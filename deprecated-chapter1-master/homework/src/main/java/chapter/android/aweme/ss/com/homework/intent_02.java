package chapter.android.aweme.ss.com.homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class intent_02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_02);
        Button mbt = findViewById(R.id.button);

        mbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String  data = getIntent().getStringExtra("data");
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
            }
        });
    }
}
