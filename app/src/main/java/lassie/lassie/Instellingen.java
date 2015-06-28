package lassie.lassie;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

public class Instellingen extends Fragment {

    View fragmentview;
    Switch taalNederlands;
    Switch taalEngels;

    public Instellingen() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentview = inflater.inflate(R.layout.fragment_instellingen, container, false);
 /*       // Klik listeners voor Nederlands en Engels
        taalNederlands = (Switch) fragmentview.findViewById(R.id.switch_nederlands);
        taalEngels = (Switch) fragmentview.findViewById(R.id.switch_engels);

        taalNederlands.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    taalEngels.toggle();
            }
        });

        taalEngels.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    taalNederlands.toggle();
        }}); */

        EditText gebruikersnaam = (EditText) fragmentview.findViewById(R.id.edittext_gebruikersnaam);
        gebruikersnaam.setText(new Database(getActivity()).getGebruiker(((MainActivity) getActivity()).getGebruikersID()).getGebruikersnaam());
        EditText email = (EditText) fragmentview.findViewById(R.id.edittext_email);
        email.setText(new Database(getActivity()).getGebruiker(((MainActivity) getActivity()).getGebruikersID()).getEmail());
        EditText wachtwoord = (EditText) fragmentview.findViewById(R.id.edittext_wachtwoord);
        wachtwoord.setText(new Database(getActivity()).getGebruiker(((MainActivity) getActivity()).getGebruikersID()).getWachtwoord());
        return fragmentview;

    }
}