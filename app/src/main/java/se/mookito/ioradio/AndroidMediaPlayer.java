package se.mookito.ioradio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;



public class AndroidMediaPlayer extends Activity {

//    private MediaPlayer mediaPlayer;
    public TextView songName, duration;
    private double timeElapsed = 0, finalTime = 0;
    private int forwardTime = 2000, backwardTime = 2000;
    private Handler durationHandler = new Handler();
    private SeekBar seekbar;
    private String STREAM_URL ="http://208.53.164.181:80";
    private MediaPlayer mPlayer;
    private ImageButton button_pause;
    private ImageButton button_play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the layout of the Activity
        setContentView(R.layout.mediaplayer_view);

        //Get intent data
        Intent i = getIntent();

        //Selected image id
        int position = i.getExtras().getInt("id");
        MyAdapter myAdapter = new MyAdapter(this);

        //Set image id
        ImageView imageView = (ImageView) findViewById(R.id.mp3Image);
        imageView.setImageResource(myAdapter.items.get(position).drawableId);
        //initialize views
        initializeViews();

        button_pause = (ImageButton) findViewById(R.id.media_pause);
        button_play = (ImageButton) findViewById(R.id.media_play);

        mPlayer = new MediaPlayer();

        /**
         * Stream URL Play Button
         */
        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPlayer.reset();
                    mPlayer.setDataSource(STREAM_URL);
                    mPlayer.prepareAsync();
                    mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * Stream URL Pause Button
         */
        button_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.pause();
            }
        });
    }

    public void initializeViews(){
        songName = (TextView) findViewById(R.id.songName);
 //       mediaPlayer = MediaPlayer.create(this, R.raw.sample_song);
 //       finalTime = mediaPlayer.getDuration();
        duration = (TextView) findViewById(R.id.songDuration);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        songName.setText("Sample_Song.mp3");

        seekbar.setMax((int) finalTime);
        seekbar.setClickable(false);
    }

    /**
    // play mp3 song
    public void play(View view) {
        mediaPlayer.start();
        timeElapsed = mediaPlayer.getCurrentPosition();
        seekbar.setProgress((int) timeElapsed);
        durationHandler.postDelayed(updateSeekBarTime, 100);
    } **/

    //handler to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            //get current position
//            timeElapsed = mediaPlayer.getCurrentPosition();
            //set seekbar progress
            seekbar.setProgress((int) timeElapsed);
            //set time remaing
            double timeRemaining = finalTime - timeElapsed;
            duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

            //repeat yourself that again in 100 miliseconds
            durationHandler.postDelayed(this, 100);
        }
    };

    /**
    // pause mp3 song
    public void pause(View view) {
        mediaPlayer.pause();
    }
     **/

    // go forward at forwardTime seconds
    public void forward(View view) {
        //check if we can go forward at forwardTime seconds before song endes
        if ((timeElapsed + forwardTime) <= finalTime) {
            timeElapsed = timeElapsed - backwardTime;

            //seek to the exact second of the track
//            mediaPlayer.seekTo((int) timeElapsed);
        }
    }
}
