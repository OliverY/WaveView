package com.yxj.waveview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Author:  Yxj
 * Time:    2019/1/16 下午1:10
 * -----------------------------------------
 * Description:
 */
public class MultiWaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_wave);

        final WaveView waveView = findViewById(R.id.wave_view);
        final WaveView waveView2 = findViewById(R.id.wave_view2);
        final WaveView waveView3 = findViewById(R.id.wave_view3);

        waveView.setDuration(3000)
                .setColor(getResources().getColor(R.color.color_blue))
                .setDirection(true)
                .setWaveHeight(100)
                .setOffset(300)
                .setHorizontalLine(200)
                .move();

        waveView2.setDuration(4000)
                .setColor(getResources().getColor(R.color.color_blue))
                .setDirection(false)
                .setWaveHeight(100)
                .setHorizontalLine(200)
                .move();

        waveView3.setDuration(5000)
                .setColor(getResources().getColor(R.color.color_blue))
                .setDirection(true)
                .setHorizontalLine(200)
                .setOffset(100)
                .setWaveHeight(100)
                .move();

    }
}
