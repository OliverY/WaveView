package com.yxj.waveview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final WaveView waveView = findViewById(R.id.wave_view);
        final WaveView waveView2 = findViewById(R.id.wave_view2);
        final WaveView waveView3 = findViewById(R.id.wave_view3);

        waveView.setDuration(3000)
                .setColor(getResources().getColor(R.color.color_blue))
                .setDirection(true)
                .setWaveHeight(100)
                .setOffset(300)
                .setHorizontalLine(200)
                .build();

        waveView2.setDuration(4000)
                .setColor(getResources().getColor(R.color.color_blue))
                .setDirection(false)
                .setWaveHeight(100)
                .setHorizontalLine(200)
                .build();

        waveView3.setDuration(5000)
                .setColor(getResources().getColor(R.color.color_blue))
                .setDirection(true)
                .setHorizontalLine(200)
                .setOffset(100)
                .setWaveHeight(100)
                .build();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveView.move();
                waveView2.move();
                waveView3.move();
            }
        });

    }
}
