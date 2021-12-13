package com.example.testesom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = mediaPlayer.create(getApplicationContext(),R.raw.bach);

        iniciarSeekBar();
    }

    private void iniciarSeekBar(){
        seekBar = findViewById(R.id.seekBar);

        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        seekBar.setMax(volumeMaximo);

        seekBar.setProgress(volumeAtual);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void executarSom(View view){
        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
    public void pausarSom(View view){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    public void stopSom(View view){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer = mediaPlayer.create(getApplicationContext(),R.raw.bach);
        }
    }



}
