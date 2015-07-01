package lassie.lassie;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        TextView naam = (TextView) fragmentview.findViewById(R.id.dier_naam);
        TextView diersoort = (TextView) fragmentview.findViewById(R.id.dier_diersoort);
        TextView ras = (TextView) fragmentview.findViewById(R.id.dier_ras);
        TextView geslacht = (TextView) fragmentview.findViewById(R.id.dier_geslacht);
        TextView kleur = (TextView) fragmentview.findViewById(R.id.dier_kleur);
        TextView status = (TextView) fragmentview.findViewById(R.id.dier_status);
        TextView postcode = (TextView) fragmentview.findViewById(R.id.dier_postcode);
        TextView eigenschappen = (TextView) fragmentview.findViewById(R.id.dier_eigenschappen);

        naam.setText(dier.getNaam());
        diersoort.setText(dier.getDiersoort());
        ras.setText(dier.getRas());
        geslacht.setText(dier.getGeslacht());
        kleur.setText(dier.getKleur());
        status.setText(dier.getStatus());
        postcode.setText(dier.getPostcode());
        eigenschappen.setText(dier.getEigenschappen());

        Button button = (Button) fragmentview.findViewById(R.id.button_registreer);
        if (dier.getGebruiker_ID() == ((MainActivity) getActivity()).getGebruikersID()) {
            button.setText("Bewerk");
        } else {
            button.setText("Reageer");
        }

        return fragmentview;

    }
}