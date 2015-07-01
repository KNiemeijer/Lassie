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
    ToggleButton gevonden;
    ToggleButton vermist;

    public HomeActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentview = inflater.inflate(R.layout.fragment_home_activity, container, false);


        vermist = (ToggleButton) fragmentview.findViewById(R.id.button_vermist);

        // Klik event vermist button
        gevonden = (ToggleButton) fragmentview.findViewById(R.id.button_gevonden);
        vermist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton gevonden = (ToggleButton) fragmentview.findViewById(R.id.button_gevonden);
                gevonden.toggle();
                checkDieren();
            }
        });

        // Klik event gevonden button
        gevonden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton vermist = (ToggleButton) fragmentview.findViewById(R.id.button_vermist);
                vermist.toggle();
                checkDieren();
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

        checkDieren();

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
        ImageView dier1 = (ImageView) fragmentview.findViewById(R.id.imageview_dier1);
        ImageView dier2 = (ImageView) fragmentview.findViewById(R.id.imageview_dier2);
        ImageView dier3 = (ImageView) fragmentview.findViewById(R.id.imageview_dier3);
        dier1.setImageResource(0);
        dier2.setImageResource(0);
        dier3.setImageResource(0);
        String imageviewString = "dier";
        String imageview_profiel = "imageview_dier";
        String textview_status = "textview_status_dier";
        imageviewString += t;
        imageview_profiel += t;
        textview_status += t;
        int resourceID = getResources().getIdentifier(imageviewString,
                "drawable", "lassie.lassie");
        Bitmap bm = BitmapFactory.decodeResource(getResources(), resourceID);
        resourceID = getResources().getIdentifier(imageview_profiel,
                "id", "lassie.lassie");
        ImageView imageview = (ImageView) fragmentview.findViewById(resourceID);
        resourceID = getResources().getIdentifier(textview_status,
                "id", "lassie.lassie");
        TextView status = (TextView) fragmentview.findViewById(resourceID);
        roundedImage = new RoundImage(bm);
        imageview.setImageDrawable(roundedImage);
        imageviewProfielListener(imageview, dier_ID, bm);
        Database db = new Database(getActivity());
        status.setText(db.getDier(dier_ID).getStatus());
        if (db.getDier(dier_ID).getStatus().equals("Gevonden")) {
            status.setTextColor(getResources().getColor(R.color.black));
            status.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            status.setTextColor(getResources().getColor(R.color.black));
            status.setBackgroundColor(getResources().getColor(R.color.red));
        }

    }

    public void checkDieren() {
        Database db = new Database(getActivity());
        List<Dier> vermisteDieren = db.getAllVermisteDieren();
        List<Dier> gevondenDieren = db.getAllGevondenDieren();

        String i;

        if (gevonden.isChecked()) {
            for (Dier dier : gevondenDieren) {
                {
                    int t = 1;
                    while (t < 4) {
                        try {
                            Dier diertemp = gevondenDieren.get(t - 1);
                            i = "textview_dier";
                            i += t;
                            int resID = getResources().getIdentifier(i, "id", "lassie.lassie");
                            TextView textview = (TextView) fragmentview.findViewById(resID);
                            textview.setText(diertemp.getNaam() + "\n" + diertemp.getRas() + "\n" + diertemp.getKleur());
                            t++;
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }
        }
        if (vermist.isChecked()) {
            for (Dier dier : vermisteDieren) {
                {
                    int t = 1;
                    while (t < 4) {
                        try {
                            Dier diertemp = vermisteDieren.get(t - 1);
                            i = "textview_dier";
                            i += t;
                            int resID = getResources().getIdentifier(i, "id", "lassie.lassie");
                            TextView textview = (TextView) fragmentview.findViewById(resID);
                            textview.setText(diertemp.getNaam() + "\n" + diertemp.getRas() + "\n" + diertemp.getKleur());
                            t++;
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }
        }
        if (vermist.isChecked()) {
            for (Dier dier : vermisteDieren) {
                int t = 1;
                while (t < 4) {
                    try {
                        Dier diertemp = vermisteDieren.get(t - 1);
                        tekenen(t, diertemp.getDier_ID());
                        t++;
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        }
        if (gevonden.isChecked()) {
            for (Dier dier : gevondenDieren) {
                int t = 1;
                while (t < 4) {
                    try {
                        Dier diertemp = gevondenDieren.get(t - 1);
                        tekenen(t, diertemp.getDier_ID());
                        t++;
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        }
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

