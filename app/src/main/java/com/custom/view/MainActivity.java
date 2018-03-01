package com.custom.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RoundTextView roundText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roundText = (RoundTextView) findViewById(R.id.round_text);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.round_text:
                ValueAnimator animator = ObjectAnimator.ofInt(roundText, "roundColor", 0xffffffff, 0xff3f51b5, 0xffffffff);
                animator.setDuration(1500);
                animator.setEvaluator(new ArgbEvaluator());
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatCount(4);
                animator.start();
                break;
            default:
                break;
        }
    }
}
