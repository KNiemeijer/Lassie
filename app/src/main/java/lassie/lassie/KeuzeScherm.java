package lassie.lassie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class KeuzeScherm extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keuzescherm);
    }

    public void registreren(View v) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
