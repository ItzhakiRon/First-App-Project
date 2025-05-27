package com.example.weaponz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SigncheckActivity extends AppCompatActivity {

    ImageView done;

    private static final String SHARED_PREFS_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signcheck);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        String useremail = sharedPreferences.getString(KEY_EMAIL, "");
        String userusername = sharedPreferences.getString(KEY_USERNAME, "");

        done = findViewById(R.id.done);

        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        TextView thankYouText = findViewById(R.id.thankYouText);

        thankYouText.setVisibility(View.INVISIBLE);
        thankYouText.setAlpha(0f);

        float startY = -thankYouText.getHeight();
        float endY = 0f;

        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(thankYouText, "translationY", startY, endY);
        translationYAnimator.setDuration(2000);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(thankYouText, "alpha", 0f, 1f);
        alphaAnimator.setDuration(2000);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(thankYouText, "scaleX", 0.8f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(thankYouText, "scaleY", 0.8f, 1f);
        scaleXAnimator.setDuration(2000);
        scaleYAnimator.setDuration(2000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationYAnimator, alphaAnimator, scaleXAnimator, scaleYAnimator);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                thankYouText.setVisibility(View.VISIBLE); // Make the TextView visible
            }
        });
        animatorSet.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Drawable drawable = done.getDrawable();

                if (drawable instanceof AnimatedVectorDrawableCompat) {
                    AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) drawable;
                    avd.start();
                } else if (drawable instanceof AnimatedVectorDrawable) {
                    AnimatedVectorDrawable avd2 = (AnimatedVectorDrawable) drawable;
                    avd2.start();
                }
            }
        }, 2000);

        ProgressBar progressBar = findViewById(R.id.progressbar);
        TextView textView = findViewById(R.id.textView);

        progressBar.setMax(1000);

        int currentProgress = 1000;

        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", currentProgress);
        animation.setDuration(2000);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                int percentage = (progress * 100) / progressBar.getMax();
                textView.setText(percentage + "%");
            }
        });
        animation.start();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(SigncheckActivity.this, MainActivity.class);
                        intent.putExtra("USER_EMAIL", useremail);
                        intent.putExtra("USER_USERNAME", userusername);
                        intent.putExtra("USER_EMAIL", userEmail);
                        startActivity(intent);
                        finish();
                    }
                }, 500); // Delay of 500 milliseconds (1 second)
            }
        });
    }
}