package lassie.lassie;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class HomeActivity extends Fragment {

    View fragmentview;
    String status;
    String naam;
    String ras;
    String kleur;
    String eigenschappen;

    public HomeActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageView imageview_profiel;
        RoundImage roundedImage;
        fragmentview = inflater.inflate(R.layout.fragment_home_activity, container, false);

        // Rond profiel aanmaken Zoef
        imageview_profiel = (ImageView) fragmentview.findViewById(R.id.imageview_profiel);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hond_voorbeeld);
        roundedImage = new RoundImage(bm);
        imageview_profiel.setImageDrawable(roundedImage);
        status = "Vermist";
        naam = "Zoef";
        ras = "beagle";
        kleur = "Wit met bruin";
        eigenschappen = "Is schuw maar wel lief";
        imageviewProfielListener(imageview_profiel, bm, status, naam, ras, kleur, eigenschappen);

        // Rond profiel aanmaken Zoef
        imageview_profiel = (ImageView) fragmentview.findViewById(R.id.imageview_lassie);
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lassie);
        roundedImage = new RoundImage(bm);
        imageview_profiel.setImageDrawable(roundedImage);
        status = "Vermist";
        naam = "Lassie";
        ras = "Collie";
        kleur = "Bruin met wit";
        eigenschappen = "Is goed met kinderen";
        imageviewProfielListener(imageview_profiel, bm, status, naam, ras, kleur, eigenschappen);

        // Rond profiel aanmaken Frank
        imageview_profiel = (ImageView) fragmentview.findViewById(R.id.imageview_frank);
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_kat_voorbeeld);
        roundedImage = new RoundImage(bm);
        imageview_profiel.setImageDrawable(roundedImage);
        status = "Gevonden";
        naam = "Frank";
        ras = "Lapjeskat";
        kleur = "Wit met grijs";
        eigenschappen = "Reageert op zijn naam";
        imageviewProfielListener(imageview_profiel, bm, status, naam, ras, kleur, eigenschappen);

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

        //  tekenen();
        return fragmentview;
    }

    private void imageviewProfielListener(final ImageView profiel, final Bitmap bm, final String status, final String naam, final String ras,
                                          final String kleur, final String eigenschappen) {
        profiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DetailView();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack("detailView").replace(R.id.frame_container, fragment).commit();
                Bundle extras = new Bundle();
                extras.putParcelable("imageview_profiel", bm);
                extras.putString("status", status);
                extras.putString("naam", naam);
                extras.putString("ras", ras);
                extras.putString("kleur", kleur);
                extras.putString("eigenschappen", eigenschappen);
                fragment.setArguments(extras);
            }
        });
    }

    // Methode voor meerdere plaatjes naast elkaar plaatsen (reeks {1,2,3...n} ).
  /*      public void tekenen () {
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
            String i = "R.drawable.";
            i = i + t;
            int resID = getResources().getIdentifier(i,
                    "id", "lassie.lassie");
            return (ImageView) fragmentview.findViewById(resID);
        }
*/

}

