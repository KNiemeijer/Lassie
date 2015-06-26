package lassie.lassie;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;


public class Home extends Activity {
    public final static String gebruikersnaammessage = Home.gebruikersnaammessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //display the logo during 5 secondes,
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                //set the new Content of your activity)
                setContentView(R.layout.activity_home);
                setTitle("Lassie");
            }
        }.start();
        Database db = new Database(this);
    }

    public void Continue(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText gebruikersnaam = (EditText) findViewById(R.id.edittext_gebruikersnaam);
        String gebruikersnaamgegevens = gebruikersnaam.getText().toString();
        intent.putExtra(gebruikersnaammessage, gebruikersnaamgegevens);
        startActivity(intent);
    }
}
