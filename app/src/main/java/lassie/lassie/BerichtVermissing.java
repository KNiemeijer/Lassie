package lassie.lassie;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BerichtVermissing extends Fragment {

    View fragmentview;

    public BerichtVermissing() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentview = inflater.inflate(R.layout.fragment_berichtgevonden, container, false);

        Bundle extras = this.getArguments();
        int dierID = extras.getInt("dierID");
        Bitmap bm = extras.getParcelable("bm");

        Database db = new Database(getActivity());
        Dier dier = db.getDier(dierID);

        TextView textviewNaam = (TextView) fragmentview.findViewById(R.id.edittext_textview_naam);
        TextView textviewDiersoort = (TextView) fragmentview.findViewById(R.id.edittext_textview_diersoort);
        TextView textviewRassoort = (TextView) fragmentview.findViewById(R.id.edittext_textview_rassoort);
        TextView textviewGeslacht = (TextView) fragmentview.findViewById(R.id.edittext_textview_geslacht);
        TextView textviewKleur = (TextView) fragmentview.findViewById(R.id.edittext_textview_kleur);
        TextView textviewPostcode = (TextView) fragmentview.findViewById(R.id.edittext_textview_postcode);
        TextView textviewEigenschappen = (TextView) fragmentview.findViewById(R.id.edittext_textview_eigenschappen);

        textviewNaam.setText(dier.getNaam());
        textviewDiersoort.setText(dier.getDiersoort());
        textviewRassoort.setText(dier.getRas());
        textviewGeslacht.setText(dier.getGeslacht());
        textviewKleur.setText(dier.getKleur());
        textviewPostcode.setText(dier.getPostcode());
        textviewEigenschappen.setText(dier.getEigenschappen());

        ImageView imageview = (ImageView) fragmentview.findViewById(R.id.imageview_upload);
        imageview.setImageBitmap(bm);

        return fragmentview;

    }
}
