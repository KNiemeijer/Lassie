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

public class LijstKaart extends Fragment {

 /*   public LijstKaart() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lijst_kaart, container, false);

    }
}*/

    View fragmentview;
    public LijstKaart() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentview = inflater.inflate(R.layout.fragment_lijst_kaart, container, false);

        return fragmentview;
    }
}