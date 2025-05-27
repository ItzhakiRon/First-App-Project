package com.example.weaponz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView iv_note = findViewById(R.id.iv_note);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(2000);

        // Set an animation listener to start the next activity after the fade-in animation
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started (no action needed)
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animation ended, start the next activity
                Intent intent = new Intent(SplashActivity.this, IntroActivity.class); // Replace with your target activity
                startActivity(intent);
                finish(); // Optional: Close this activity if needed
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeated (no action needed)
            }
        });

        // Apply the animation to the ImageView
        iv_note.startAnimation(fadeIn);
        iv_note.setVisibility(View.VISIBLE);

    }
}