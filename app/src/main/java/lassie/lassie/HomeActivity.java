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

public class HomeActivity extends Fragment {

    View fragmentview;

    public HomeActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageView imageview_profiel;
        RoundImage roundedImage;

        fragmentview = inflater.inflate(R.layout.fragment_home_activity, container, false);

        // Rond profiel aanmaken
        imageview_profiel = (ImageView) fragmentview.findViewById(R.id.imageview_profiel);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hond_voorbeeld);
        roundedImage = new RoundImage(bm);
        imageview_profiel.setImageDrawable(roundedImage);

        ToggleButton vermist = (ToggleButton) fragmentview.findViewById(R.id.button_vermist);
        vermist.setChecked(true);

        return fragmentview;
    }

    public void Switch(View view) {
        ToggleButton vermist = (ToggleButton) fragmentview.findViewById(R.id.button_vermist);
        ToggleButton gevonden = (ToggleButton) fragmentview.findViewById(R.id.button_gevonden);
        if (view == gevonden) {
            vermist.toggle();
        }
        if (view == vermist) {
            gevonden.toggle();
        }
    }
}

