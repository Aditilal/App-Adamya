package com.example.vitality;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    private static int Splash = 5000;

    Animation topanim, bottomanim;
    ImageView imageView4;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splashscreen);

            topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
            bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
            imageView4 = findViewById(R.id.imageView4);
            textView = findViewById(R.id.textView);


            imageView4.setAnimation(topanim);
            textView.setAnimation(bottomanim);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, Splash);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splashscreen);
//        /* New Handler to start the Menu-Activity
//         * and close this Splash-Screen after some seconds.*/
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//                Intent mainIntent = new Intent(Splashscreen.this, MainActivity.class);
//                Splashscreen.this.startActivity(mainIntent);
//                Splashscreen.this.finish();
//            }
//        },2000 );
        }
    }

