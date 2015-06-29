package lassie.lassie;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

public class HomeActivity extends Fragment {

    View fragmentview;

    public HomeActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentview = inflater.inflate(R.layout.fragment_home_activity, container, false);

        Database db = new Database(getActivity());
        List<Dier> dieren = db.getAllDieren();

        String i;
        for (Dier dier : dieren) {
            int t = 1;
            while (t < 4) {
                Dier diertemp = dieren.get(t - 1);
                i = "textview_dier";
                i += t;
                int resID = getResources().getIdentifier(i, "id", "lassie.lassie");
                TextView textview = (TextView) fragmentview.findViewById(resID);
                textview.setText(diertemp.getNaam() + "\n" + diertemp.getRas() + "\n" + diertemp.getKleur());
                t++;
            }
        }

        for (Dier dier : dieren) {
            int t = 1;
            while (t < 4) {
                Dier diertemp = dieren.get(t - 1);
                tekenen(t, diertemp.getDier_ID());
                t++;
            }
        }

        final ToggleButton vermist = (ToggleButton) fragmentview.findViewById(R.id.button_vermist);

        // Klik event vermist button
        final ToggleButton gevonden = (ToggleButton) fragmentview.findViewById(R.id.button_gevonden);
        vermist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton gevonden = (ToggleButton) fragmentview.findViewById(R.id.button_gevonden);
                gevonden.toggle();
            }
        });

        // Klik event gevonden button
        gevonden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton vermist = (ToggleButton) fragmentview.findViewById(R.id.button_vermist);
                vermist.toggle();
            }
        });

        final Button plaatsBericht = (Button) fragmentview.findViewById(R.id.button_plaats_bericht);
        // Klik event plaats bericht button
        plaatsBericht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new PlaatsBerichtGevonden();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack("PlaatsBerichtGevonden").replace(R.id.frame_container, fragment).commit();

            }
        });
        return fragmentview;
    }

    private void imageviewProfielListener(final ImageView profiel, final int dier_ID, final Bitmap bm) {
        profiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DetailView();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack("detailView").replace(R.id.frame_container, fragment).commit();
                Bundle extras = new Bundle();
                extras.putParcelable("imageview_profiel", bm);
                extras.putInt("dier_ID", dier_ID);
                fragment.setArguments(extras);
            }
        });
    }

    public void tekenen(int t, int dier_ID) {
        RoundImage roundedImage;
        String imageviewString = "dier";
        String imageview_profiel = "imageview_dier";
        imageviewString += t;
        imageview_profiel += t;
        int resourceID = getResources().getIdentifier(imageviewString,
                "drawable", "lassie.lassie");
        int resID = getResources().getIdentifier(imageview_profiel,
                "id", "lassie.lassie");
        ImageView imageview = (ImageView) fragmentview.findViewById(resID);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), resourceID);
        roundedImage = new RoundImage(bm);
        imageview.setImageDrawable(roundedImage);
        imageviewProfielListener(imageview, dier_ID, bm);
    }

    // Methode voor meerdere plaatjes naast elkaar plaatsen (reeks {1,2,3...n} ).
 /*       public void tekenen () {
        int t = 1;
        int margin_left = 120;
        try {
            while (true) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(margin_left, 0, 0, 80);
                afGaan(t).setLayoutParams(lp);
                margin_left += 300;
                t++;
            }
        } catch (Exception e) { }
    }

        public ImageView afGaan (int t) {
            String i = "R.drawable.dier";
            i = i + t;
            int resID = getResources().getIdentifier(i,
                    "id", "lassie.lassie");
            return (ImageView) fragmentview.findViewById(resID);
        } */


}

