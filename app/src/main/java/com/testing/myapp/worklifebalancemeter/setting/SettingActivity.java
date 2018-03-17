package com.testing.myapp.worklifebalancemeter.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.testing.myapp.worklifebalancemeter.R;

/**
 * Created by harsha on 1/14/18.
 */

public class SettingActivity extends AppCompatActivity{

    public static String TAG = "SettingActivity:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageView backArrow = (ImageView) findViewById(R.id.back_setting);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                finish();
            }
        });

    }
}
