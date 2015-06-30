package lassie.lassie;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Gebruikerspagina extends Fragment {

    Database db;
    View fragmentview;
    public Gebruikerspagina() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentview = inflater.inflate(R.layout.fragment_gebruikerspagina, container, false);

        int gebruikersID = ((MainActivity) getActivity()).getGebruikersID();
        Database db = new Database(getActivity());
        Gebruiker gebruiker = db.getGebruiker(gebruikersID);

        // TextViews ophalen Gebruiker
        TextView textview_naam = (TextView) fragmentview.findViewById(R.id.voornaam);
        TextView textview_achternaam = (TextView) fragmentview.findViewById(R.id.achternaam);
        TextView textview_gebruikersnaam = (TextView) fragmentview.findViewById(R.id.gebruikersnaam);
        TextView textview_email = (TextView) fragmentview.findViewById(R.id.email);
        TextView textview_stad = (TextView) fragmentview.findViewById(R.id.stad);
        TextView textview_telefoonnummer = (TextView) fragmentview.findViewById(R.id.telefoonnummer);
        TextView textview_postcode = (TextView) fragmentview.findViewById(R.id.postcode);

        // Waardes invullen Gebruiker
        textview_naam.setText(gebruiker.getVoornaam());
        textview_achternaam.setText(gebruiker.getAchternaam());
        textview_gebruikersnaam.setText(gebruiker.getGebruikersnaam());
        textview_email.setText(gebruiker.getEmail());
        textview_stad.setText(gebruiker.getStad());
        textview_telefoonnummer.setText(gebruiker.getTelefoonnummer());
        textview_postcode.setText(gebruiker.getPostcode());

        // TextViews ophalen Dier
        TextView naam = (TextView) fragmentview.findViewById(R.id.dier_naam);
        TextView diersoort = (TextView) fragmentview.findViewById(R.id.dier_diersoort);
        TextView ras = (TextView) fragmentview.findViewById(R.id.dier_ras);
        TextView geslacht = (TextView) fragmentview.findViewById(R.id.dier_geslacht);
        TextView kleur = (TextView) fragmentview.findViewById(R.id.dier_kleur);
        TextView status = (TextView) fragmentview.findViewById(R.id.dier_status);
        TextView postcode = (TextView) fragmentview.findViewById(R.id.dier_postcode);
        TextView eigenschappen = (TextView) fragmentview.findViewById(R.id.dier_eigenschappen);

        // Waardes invullen Dier
        Boolean waarde = false;
        List<Dier> dieren = db.getAllDieren();
        for (Dier dierUitLijst : dieren) {
            if (dierUitLijst.getGebruiker_ID() == gebruikersID) {
                naam.setText(dierUitLijst.getNaam());
                diersoort.setText(dierUitLijst.getDiersoort());
                ras.setText(dierUitLijst.getRas());
                geslacht.setText(dierUitLijst.getGeslacht());
                kleur.setText(dierUitLijst.getKleur());
                status.setText(dierUitLijst.getStatus());
                postcode.setText(dierUitLijst.getPostcode());
                eigenschappen.setText(dierUitLijst.getEigenschappen());
                waarde = true;
                break;
            }
        }

        if (!waarde) {
            naam.setText("Geen dier geregistreerd");
        }


        return fragmentview;

    }
}