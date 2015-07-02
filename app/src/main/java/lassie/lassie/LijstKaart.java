package lassie.lassie;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

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

        Database db = new Database(getActivity());
        int gebruikersID = ((MainActivity) getActivity()).getGebruikersID();
        List<Dier> dieren = db.getAllDieren();
        int t = 1;
        for (final Dier dier : dieren) {


            // LinearLayout voor plaatje maken
            LinearLayout layout = new LinearLayout(getActivity());
            LinearLayout.LayoutParams lp = (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lp.setMargins(0, 0, 5, 0);
            layout.setPadding(3, 3, 3, 3);

            String imageviewString = "dier";
            imageviewString += t;
            int resourceID = getResources().getIdentifier(imageviewString,
                    "drawable", "lassie.lassie");
            final Bitmap bm = BitmapFactory.decodeResource(getResources(), resourceID);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new DetailView();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack("DetailView").replace(R.id.frame_container, fragment).commit();
                    Bundle extras = new Bundle();
                    extras.putParcelable("imageview_profiel", bm);
                    extras.putInt("dier_ID", dier.getDier_ID());
                    fragment.setArguments(extras);
                }
            });
            layout.setLayoutParams(lp);

            // Plaatje invullen
            ImageView imageview = new ImageView(getActivity());
            imageview.setMinimumWidth(50);
            imageview.setMinimumHeight(50);
            imageview.setMaxHeight(50);
            imageview.setMaxWidth(50);
            imageview.setImageDrawable(tekenen(t, dier));
            layout.addView(imageview);

            // Naam van dier invullen
            TextView textview_naam = new TextView(getActivity());
            textview_naam.setText(dier.getNaam());
            RelativeLayout.LayoutParams params = (new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.addRule(RelativeLayout.RIGHT_OF, layout.getId());
            params.addRule(RelativeLayout.ALIGN_TOP, layout.getId());
            textview_naam.setLayoutParams(params);
            textview_naam.setTextSize(15);
            textview_naam.setTypeface(Typeface.SANS_SERIF);
            textview_naam.setTextColor(343434);

            // Beschrijving van dier invullen
            TextView textview_beschrijving = new TextView(getActivity());
            textview_beschrijving.setText(dier.getEigenschappen());
            RelativeLayout.LayoutParams params_eigenschappen = (new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params_eigenschappen.addRule(RelativeLayout.ALIGN_BOTTOM, layout.getId());
            params_eigenschappen.addRule(RelativeLayout.END_OF, layout.getId());
            params_eigenschappen.addRule(RelativeLayout.BELOW, textview_naam.getId());
            params_eigenschappen.setMargins(0, 1, 0, 0);
            textview_beschrijving.setLayoutParams(params_eigenschappen);
            textview_beschrijving.setTextSize(10);
            textview_beschrijving.setTextColor(343434);

            // Status dier invullen
            TextView textview_status = new TextView(getActivity());
            textview_status.setText(dier.getStatus());
            RelativeLayout.LayoutParams params_status = (new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.ALIGN_TOP, textview_naam.getId());
            params_status.setMargins(0, 5, 0, 0);
            textview_status.setGravity(Gravity.RIGHT);
            textview_status.setLayoutParams(params);
            textview_status.setTextSize(10);
            if (dier.getStatus().equals("Gevonden")) {
                textview_status.setTextColor(getResources().getColor(R.color.green));
            } else {
                textview_status.setTextColor(getResources().getColor(R.color.red));
            }

            t++;
        }

        return fragmentview;
    }


    public Drawable tekenen(int t, Dier dier) {
        RoundImage roundedImage;
        String imageviewString = "dier";
        imageviewString += t;
        int resourceID = getResources().getIdentifier(imageviewString,
                "drawable", "lassie.lassie");
        Bitmap bm = BitmapFactory.decodeResource(getResources(), resourceID);
        TextView status = new TextView(getActivity());
        status.setText(dier.getStatus());
        roundedImage = new RoundImage(bm);
        return roundedImage;

    }


}