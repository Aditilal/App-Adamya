package com.example.vitality;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.File;
import java.util.ArrayList;

public class PlayerAcitivity extends AppCompatActivity {
   ImageView playbtn,btnnext,btnprev;
   TextView txtsn,textstart,textstop;
   SeekBar seekbar;
   BarVisualizer visualizer;
   ImageView imageView;

   String sname;
   public static final String EXTRA_NAME="song_name";
   static MediaPlayer mediaPlayer;
   int position;
   ArrayList<File> mySongs;
   Thread UpdateSeekbar;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_player_acitivity);

       btnprev = findViewById(R.id.btnprev);
       btnnext = findViewById(R.id.btnnext);
       playbtn = findViewById(R.id.playbtn);

       txtsn = findViewById(R.id.txtsn);
       textstart = findViewById(R.id.textstart);
       textstop = findViewById(R.id.textstop);
       seekbar = findViewById(R.id.seekbar);

       imageView = findViewById(R.id.imageView);

       if (mediaPlayer != null) {
           mediaPlayer.stop();
           mediaPlayer.release();
       }
       Intent i = getIntent();
       Bundle bundle = i.getExtras();

       mySongs = (ArrayList) bundle.getParcelableArrayList("songs");
       String songName = i.getStringExtra("songname");
       position = bundle.getInt("pos", 0);
       txtsn.setSelected(true);
       Uri uri = Uri.parse(mySongs.get(position).toString());
       sname = mySongs.get(position).getName();
       txtsn.setText(sname);

       mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
       mediaPlayer.start();
       UpdateSeekbar=new Thread()
       {
           @Override
           public void run() {
               int totalDuration = mediaPlayer.getDuration();
               int currentposition=0;
               while (currentposition<totalDuration)
               {
                   try {
                       sleep(500);
                       currentposition=mediaPlayer.getCurrentPosition();
                       seekbar.setProgress(currentposition);
                   }
                   catch (InterruptedException | IllegalStateException e)
                   {
                       e.printStackTrace();
                   }
               }
           }
       };
       seekbar.setMax(mediaPlayer.getDuration());
       UpdateSeekbar.start();
       seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
       seekbar.getThumb().setColorFilter(getResources().getColor(R.color.colorPrimary),PorterDuff.Mode.SRC_IN);
       seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
           }
       });

       String endTime=createTime(mediaPlayer.getDuration());

       textstop.setText(endTime);
       final Handler handler=new Handler();
       final int delay = 1000;

       handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   String currentTime=createTime(mediaPlayer.getCurrentPosition());
                                   textstart.setText(currentTime);
                                   handler.postDelayed(this,delay);
                               }
                           },delay);


               playbtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if (mediaPlayer.isPlaying()) {
                           playbtn.setBackgroundResource(R.drawable.ic_play);
                           mediaPlayer.pause();
                       } else {
                           playbtn.setBackgroundResource(R.drawable.ic_pause);
                           mediaPlayer.start();
                       }
                   }
               });

       //nextlistener

       mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mediaPlayer) {
               btnnext.performClick();
           }
       });
       btnnext.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mediaPlayer.stop();
               mediaPlayer.release();
               position = ((position + 1) % mySongs.size());
               Uri u = Uri.parse(mySongs.get(position).toString());
               mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
               sname = mySongs.get(position).getName();
               txtsn.setText(sname);
               mediaPlayer.start();
               playbtn.setBackgroundResource(R.drawable.ic_pause);
               startAnimation(imageView);

           }
       });
   btnprev.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           mediaPlayer.stop();
           mediaPlayer.release();
           position=((position-1)<0)?(mySongs.size()-1):(position-1);
           Uri u=Uri.parse(mySongs.get(position).toString());
           mediaPlayer=MediaPlayer.create(getApplicationContext(),u);
           sname=mySongs.get(position).getName();
           txtsn.setText(sname);
           mediaPlayer.start();
           playbtn.setBackgroundResource(R.drawable.ic_pause);
           startAnimation(imageView);

       }
   });
    }
    public void startAnimation(View view)
    {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"rotation",0f,360f);
        animator.setDuration(1000);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(animator);
        animatorSet.start();
    }
    public String createTime(int duration)
    {
        String time="";
        int min=duration/1000/60;
        int sec=duration/1000%60;

        time+=min+":";
        if(sec<10)
        {
            time+="0";
        }
        time+=sec;
        return time;

    }
}