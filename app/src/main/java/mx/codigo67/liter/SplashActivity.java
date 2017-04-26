package mx.codigo67.liter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.ParseUser;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i;

                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser != null) {
                    Log.d("*a*",currentUser.getUsername());
                    i = new Intent(SplashActivity.this, mx.codigo67.liter.MainActivity.class);
                } else {
                    i = new Intent(SplashActivity.this, mx.codigo67.liter.StartActivity.class);
                }

                startActivity(i);
                finish();


            }
        }, SPLASH_TIME_OUT);
    }
}
