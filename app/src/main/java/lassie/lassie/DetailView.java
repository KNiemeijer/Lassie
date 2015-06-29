package lassie.lassie;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailView extends Fragment {

    View fragmentview;

    public DetailView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentview = inflater.inflate(R.layout.fragment_detailview, container, false);

        // Profiel plaatje ophalen en invoegen
        RoundImage roundedImage;
        Bundle extras = this.getArguments();
        Bitmap profiel = extras.getParcelable("imageview_profiel");
        ImageView imageview_profiel = (ImageView) fragmentview.findViewById(R.id.imageview_profiel);
        roundedImage = new RoundImage(profiel);
        imageview_profiel.setImageDrawable(roundedImage);

        Database db = new Database(getActivity());
        int dier_ID = extras.getInt("dier_ID");
        Dier dier = db.getDier(dier_ID);

        TextView textview_naam = (TextView) fragmentview.findViewById(R.id.textview_naam_content);
        TextView textview_ras = (TextView) fragmentview.findViewById(R.id.textview_ras_content);
        TextView textview_kleur = (TextView) fragmentview.findViewById(R.id.textview_kleur_content);
        TextView textview_eigenschappen = (TextView) fragmentview.findViewById(R.id.textview_eigenschappen_content);

        textview_naam.setText(dier.getNaam());
        textview_ras.setText(dier.getRas());
        textview_kleur.setText(dier.getKleur());
        textview_eigenschappen.setText(dier.getDiersoort() + dier.getGeslacht());
        getActivity().setTitle(dier.getNaam());


 /*       // Status ophalen en invoegen
        String status = extras.getString("status");
        TextView statusTekst = (TextView) fragmentview.findViewById(R.id.textview_status);
        statusTekst.setText(status);
        if (status.equals("Vermist") || status.equals("vermist")) {
            statusTekst.setBackgroundColor(getResources().getColor(R.color.red));
        }
        if (status.equals("Gevonden") || status.equals("gevonden")){
            statusTekst.setBackgroundColor(getResources().getColor(R.color.green));
        }

        else {
            statusTekst.setBackgroundColor(getResources().getColor(R.color.black));
        }

        // Naam, ras, kleur, eigenschappen ophalen en invoegen
        TextView textview_naam = (TextView) fragmentview.findViewById(R.id.textview_naam_content);
        TextView textview_ras = (TextView) fragmentview.findViewById(R.id.textview_ras_content);
        TextView textview_kleur = (TextView) fragmentview.findViewById(R.id.textview_kleur_content);
        TextView textview_eigenschappen = (TextView) fragmentview.findViewById(R.id.textview_eigenschappen_content);

        String naam = extras.getString("naam");
        String ras = extras.getString("ras");
        String kleur = extras.getString("kleur");
        String eigenschappen = extras.getString("eigenschappen");

        textview_naam.setText(naam);
        textview_ras.setText(ras);
        textview_kleur.setText(kleur);
        textview_eigenschappen.setText(eigenschappen);

        getActivity().setTitle(naam); */
        return fragmentview;

    }
}