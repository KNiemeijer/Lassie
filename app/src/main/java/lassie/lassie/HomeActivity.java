package lassie.lassie;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeActivity extends Fragment {

    public HomeActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_activity, container, false);
//        ToggleButton vermist = (ToggleButton) getView().findViewById(R.id.button_vermist);
        //      vermist.setChecked(true);
    }

    /* public void Switch(View view) {
        ToggleButton vermist = (ToggleButton) getView().findViewById(R.id.button_vermist);
        ToggleButton gevonden = (ToggleButton) getView().findViewById(R.id.button_gevonden);
        if (view == gevonden) {
            vermist.toggle();
        }
        if (view == vermist) {
            gevonden.toggle();
        }
    } */
}