package com.best.cy.bombgame;

import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static int lapseMin;
    static long startTime;
    static long endTime;
    static int randomMin;

    static SoundPool sdPool;
    static int soundOfBomb;

    static MediaPlayer backMusic;


    Button b1;
    Button b2;

    static int width;
    static int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x; // 화면 가로 크기
        height = size.y;

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);

        sdPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundOfBomb = sdPool.load(this, R.raw.bomb, 2);

        backMusic = MediaPlayer.create(this, R.raw.crack);

        backMusic.setLooping(true);  //노래를 무한반복으로 나오게 하기
        backMusic.start();

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                randomMin = new Random().nextInt(80) + 5;
                startTime = System.currentTimeMillis();
                MyView.gameOver = false;
                MyView.sayBomb = false;
                backMusic.start();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                backMusic.stop();
                finish();
            }
        });


    }

    public static void checkMin() {
        endTime = System.currentTimeMillis();
        lapseMin = (int) endTime - (int) startTime;
        lapseMin = lapseMin / 1000;
    }

    public static void stopBackSound() {
        backMusic.pause();
    }

    public static void sayBomb() {
        sdPool.play(soundOfBomb, 1.0F, 1.0F, 1, 0, 1.0F);
    }

}
