package lassie.lassie;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Home extends Activity {
    public final static String gebruikersnaammessage = Home.gebruikersnaammessage;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                setContentView(R.layout.activity_home);
                setTitle("Lassie");

        getApplicationContext().deleteDatabase("LassieDB");
        db = new Database(this);


    }

    public void Continue(View view) {
        // Controleer of wachtwoorden overeenkomen
        EditText wachtwoord = (EditText) findViewById(R.id.edittext_wachtwoord);
        EditText herhaal_wachtwoord = (EditText) findViewById(R.id.edittext_herhaal_wachtwoord);
        if (wachtwoord.getText().toString().equals(herhaal_wachtwoord.getText().toString())) {
            int gebruikerID = db.getAllGebruikers().size() + 1;
            voegToeVoorPrototype(gebruikerID);

            Gebruiker gebruiker = new Gebruiker(gebruikerID, ((EditText) findViewById(R.id.edittext_gebruikersnaam)).getText().toString(),
                    ((EditText) findViewById(R.id.edittext_wachtwoord)).getText().toString(),
                    ((EditText) findViewById(R.id.edittext_voornaam)).getText().toString(),
                    ((EditText) findViewById(R.id.edittext_achternaam)).getText().toString(),
                    ((EditText) findViewById(R.id.edittext_stad)).getText().toString(),
                    ((EditText) findViewById(R.id.edittext_email)).getText().toString(),
                    ((EditText) findViewById(R.id.edittext_telefoonnummer)).getText().toString(),
                    ((EditText) findViewById(R.id.edittext_postcode)).getText().toString());
            db.addGebruiker(gebruiker);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("gebruikerID", gebruikerID);
            startActivity(intent);
        } else {
            // Als ze niet overeenkomen, maak het veld leeg en laat zien dat ze niet overeenkomen
            herhaal_wachtwoord.setText("");
            herhaal_wachtwoord.setHint("Wachtwoorden komen niet overeen");
            herhaal_wachtwoord.setHintTextColor(getResources().getColor(R.color.red));

        }

    }

    private void voegToeVoorPrototype(int gebruikerID) {

        Dier dier = new Dier(1, "Zoef", "Hond", "Beagle", "Man", "Wit met bruin", "Vermist", "3571ZZ", "Is schuw maar wel lief", 1, R.drawable.ic_hond_voorbeeld);
        db.addDier(dier);
        dier = new Dier(2, "Lassie", "Hond", "Collie", "Man", "Bruin met wit", "Vermist", "3552VC", "Is erg gesteld op kinderen", 0, R.drawable.ic_lassie);
        db.addDier(dier);
        dier = new Dier(3, "Frank", "Kat", "Lapjeskat", "Vrouw", "Wit met grijs", "Gevonden", "3607CA", "Verstopt zich graag onder auto's", 0, R.drawable.ic_kat_voorbeeld);
        db.addDier(dier);

        Bericht bericht = new Bericht(1, gebruikerID, 3, "27-06-2015", "1202AE", "Aan DierenLiefhebber1:\nHallo ik geloof dat ik" +
                "jouw kat heb gezien op de Kruisstraat. Kan dit?");
        db.addBericht(bericht);
    }
}
