package lassie.lassie;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MijnOverzicht extends Fragment {

    View fragmentview;
    ToggleButton berichten;
    ToggleButton vermissingen;

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
                berichten.toggle();
                checkElements();
            }
        });

        // Klik event berichten button
        berichten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vermissingen = (ToggleButton) fragmentview.findViewById(R.id.button_vermissingen);
                vermissingen.toggle();
                checkElements();
            }
        });

        RoundImage roundedImage;
        // Rond profiel aanmaken Zoef
        ImageView imageview_profiel = (ImageView) fragmentview.findViewById(R.id.imageview_zoef);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hond_voorbeeld);
        roundedImage = new RoundImage(bm);
        imageview_profiel.setImageDrawable(roundedImage);

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
}

