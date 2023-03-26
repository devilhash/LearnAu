package com.app.learnau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button register;
    private LinearLayout linear;
//    private ImageView applogo;
    private ImageView applogo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        applogo = findViewById(R.id.applogo);
        applogo1 = findViewById(R.id.applogo1);
        linear = findViewById(R.id.linear);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        linear.animate().alpha(0f).setDuration(0);
        TranslateAnimation animation = new TranslateAnimation(0,0,0,0);
        animation.setDuration(3000);
        animation.setFillAfter(false);
        animation.setAnimationListener(new MyAnimationListener());
        applogo1.setAnimation(animation);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LogInActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

private class MyAnimationListener implements Animation.AnimationListener{

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        applogo1.clearAnimation();
        applogo1.setVisibility(View.INVISIBLE);
        linear.animate().alpha(1f).setDuration(1000);

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,BodyActivity.class));
            finish();
        }
    }
}