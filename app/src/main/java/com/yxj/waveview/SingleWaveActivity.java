package com.yxj.waveview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yxj.waveview.widget.WaveView;

/**
 * Author:  Yxj
 * Time:    2019/1/16 下午1:13
 * -----------------------------------------
 * Description:
 */
public class SingleWaveActivity extends AppCompatActivity {

    private WaveView waveView;
    private TextView tvHorizontalLine;
    private TextView tvWaveHeight;
    private TextView tvDuration;
    private CheckBox cbDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_wave);

        waveView = findViewById(R.id.wave_view);

        waveView.setDuration(3)
                .setColor(getResources().getColor(R.color.color_blue))
                .setDirection(true)
                .setWaveHeight(100)
                .setOffset(300)
                .setHorizontalLine(200)
                .move();

        setDirection();
        setDuration();
        setHorizontalLine();
        setWaveHeight();

        tvHorizontalLine.setText(String.format("horizontal line: %d", 200));
        tvDuration.setText(String.format("duration: %d s", 3));
        tvWaveHeight.setText(String.format("wave height: %d", 100));
        cbDirection.setChecked(true);
    }

    private void setHorizontalLine() {
        AppCompatSeekBar seekBar = findViewById(R.id.seekbar_horizontal_line);
        tvHorizontalLine = findViewById(R.id.tv_horizontal_line);
        seekBar.setMax(400);
        seekBar.setMin(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvHorizontalLine.setText(String.format("horizontal line: %d", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                waveView.setHorizontalLine(seekBar.getProgress());
            }
        });
    }

    private void setWaveHeight() {
        AppCompatSeekBar seekBar = findViewById(R.id.seekbar_wave_height);
        tvWaveHeight = findViewById(R.id.tv_wave_height);
        seekBar.setMax(400);
        seekBar.setMin(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvWaveHeight.setText(String.format("wave height: %d", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                waveView.setWaveHeight(seekBar.getProgress());
            }
        });
    }

    private void setDuration() {
        AppCompatSeekBar seekBar = findViewById(R.id.seekbar_duration);
        tvDuration = findViewById(R.id.tv_duration);
        seekBar.setMax(10);
        seekBar.setMin(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvDuration.setText(String.format("duration: %d s", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                waveView.setDuration(seekBar.getProgress())
                        .move();
            }
        });
    }

    private void setDirection() {
        cbDirection = findViewById(R.id.cb);
        cbDirection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setText(isChecked ? "向左" : "向右");
                waveView.setDirection(isChecked);
            }
        });
    }


}
