package lassie.lassie;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

public class LijstKaart extends Fragment {
    View fragmentview;

    public LijstKaart() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentview = inflater.inflate(R.layout.fragment_lijst_kaart, container, false);
        final ToggleButton lijst = (ToggleButton) fragmentview.findViewById(R.id.button_lijst);

        // Klik event lijst button (geleend van vermist/gevonden uit HomeActivity.java)
        final ToggleButton kaart = (ToggleButton) fragmentview.findViewById(R.id.button_kaart);
        lijst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton kaart = (ToggleButton) fragmentview.findViewById(R.id.button_kaart);
                kaart.toggle();
            }
        });

        // Klik event kaart button (idem)
        kaart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton lijst = (ToggleButton) fragmentview.findViewById(R.id.button_lijst);
                lijst.toggle();
            }
        });

        //  tekenen();
        return fragmentview;
    }


}