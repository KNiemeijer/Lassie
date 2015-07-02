package lassie.lassie;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;


public class Splash extends Activity {
    public final static String gebruikersnaammessage = Splash.gebruikersnaammessage;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final Intent intent = new Intent(this, KeuzeScherm.class);
        //display the logo during 5 secondes,
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                //set the new Content of your activity)
                setTitle("Lassie");
                startActivity(intent);
            }
        }.start();

    }
}