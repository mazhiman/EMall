package com.example.em;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends Activity {
    //若WelcomeActivity继承AppCompatActivity设置全屏，会崩溃
    Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                boolean isFirst = sp.getBoolean("isFirst",true);
                Intent intent = new Intent();
                if (isFirst){
                    sp.edit().putBoolean("isFirst",false).commit();
                    intent.setClass(WelcomeActivity.this,GuideActivity.class);
                }else {
                    intent.setClass(WelcomeActivity.this,MainActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, 5000);

    }
}
