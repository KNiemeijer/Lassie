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
import android.widget.ToggleButton;

public class MijnOverzicht extends Fragment {

    View fragmentview;
    ToggleButton berichten;
    ToggleButton vermissingen;
    String status;
    String naam;
    String ras;
    String kleur;
    String eigenschappen;
    ImageView imageview_profiel;
    Bitmap bm;
    Button button;

    public MijnOverzicht() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentview = inflater.inflate(R.layout.fragment_mijn_overzicht, container, false);

        vermissingen = (ToggleButton) fragmentview.findViewById(R.id.button_vermissingen);

        // Klik event vermissingen button
        berichten = (ToggleButton) fragmentview.findViewById(R.id.button_berichten);
        vermissingen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                berichten = (ToggleButton) fragmentview.findViewById(R.id.button_berichten);
                if (vermissingen.isChecked()) {
                    berichten.toggle();
                    checkElements();
                } else {
                    vermissingen.toggle();
                }
            }
        });

        // Klik event berichten button
        berichten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vermissingen = (ToggleButton) fragmentview.findViewById(R.id.button_vermissingen);
                if (berichten.isChecked()) {
                    vermissingen.toggle();
                    checkElements();
                } else {
                    berichten.toggle();
                }
            }
        });

        RoundImage roundedImage;
        // Rond profiel aanmaken Zoef
        imageview_profiel = (ImageView) fragmentview.findViewById(R.id.imageview_zoef);
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hond_voorbeeld);
        roundedImage = new RoundImage(bm);
        imageview_profiel.setImageDrawable(roundedImage);
        status = "Vermist";
        naam = "Zoef";
        ras = "beagle";
        kleur = "Wit met bruin";
        eigenschappen = "Is schuw maar wel lief";
        imageviewProfielListener(imageview_profiel, bm, status, naam, ras, kleur, eigenschappen);

        button = (Button) fragmentview.findViewById(R.id.button_overzicht_details);
        buttonClickListener(button);
        return fragmentview;
    }


    public void checkElements() {
        if (berichten.isChecked()) {
            fragmentview.findViewById(R.id.layout_berichten).setVisibility(View.VISIBLE);
            fragmentview.findViewById(R.id.layout_vermissingen).setVisibility(View.GONE);
        }

        if (vermissingen.isChecked()) {
            fragmentview.findViewById(R.id.layout_berichten).setVisibility(View.GONE);
            fragmentview.findViewById(R.id.layout_vermissingen).setVisibility(View.VISIBLE);
        }
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

    private void buttonClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "Vermist";
                naam = "Zoef";
                ras = "beagle";
                kleur = "Wit met bruin";
                eigenschappen = "Is schuw maar wel lief";
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
}

