package com.yxj.waveview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Author:  Yxj
 * Time:    2019/1/16 下午1:13
 * -----------------------------------------
 * Description:
 */
public class SingleWaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_wave);

        final WaveView waveView = findViewById(R.id.wave_view);

        waveView.setDuration(3000)
                .setColor(getResources().getColor(R.color.color_blue))
                .setDirection(true)
                .setWaveHeight(100)
                .setOffset(300)
                .setHorizontalLine(200)
                .move();

    }
}
