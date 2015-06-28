package lassie.lassie;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;


public class Home extends Activity {
    public final static String gebruikersnaammessage = Home.gebruikersnaammessage;
    Database db;

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
        getApplicationContext().deleteDatabase("LassieDB");
        db = new Database(this);


    }

    public void Continue(View view) {
        int gebruikerID = 1;
        Gebruiker gebruiker = new Gebruiker(gebruikerID, ((EditText) findViewById(R.id.edittext_gebruikersnaam)).getText().toString(),
                ((EditText) findViewById(R.id.edittext_wachtwoord)).getText().toString(),
                ((EditText) findViewById(R.id.edittext_email)).getText().toString());
        db.addGebruiker(gebruiker);

        Dier dier = new Dier(1, "Zoef", "Hond", "Beagle", "Man", "Wit met bruin", "Vermist", 0);
        db.addDier(dier);
        dier = new Dier(2, "Lassie", "Hond", "Collie", "Man", "Bruin met wit", "Vermist", 0);
        db.addDier(dier);
        dier = new Dier(3, "Frank", "Kat", "Lapjeskat", "Vrouw", "Wit met grijs", "Gevonden", 0);
        db.addDier(dier);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("gebruikerID", gebruikerID);
        startActivity(intent);
    }
}
