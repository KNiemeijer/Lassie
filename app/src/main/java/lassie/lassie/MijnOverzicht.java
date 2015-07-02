package lassie.lassie;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

public class MijnOverzicht extends Fragment {

    View fragmentview;
    ToggleButton berichten;
    ToggleButton vermissingen;
    Bitmap bm;

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

        Database db = new Database(getActivity());
        int gebruikersID = ((MainActivity) getActivity()).getGebruikersID();
        List<Dier> dieren = db.getAllDieren();
        for (Dier dierUitLijst : dieren) {
            if (dierUitLijst.getGebruiker_ID() == gebruikersID) {
                // TODO: plaatjes_id aan dieren toevoegen
                TableLayout tablelayout = (TableLayout) fragmentview.findViewById(R.id.layout_vermissingen);
                TableRow row = new TableRow(fragmentview.getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, 4f);
                row.setLayoutParams(lp);

                // Goeie image toevoegen. Nu nog even 1 image
                ImageView imageview = new ImageView(fragmentview.getContext());
                imageview.setLayoutParams(new TableRow.LayoutParams(0, RadioGroup.LayoutParams.WRAP_CONTENT, 1f));
                bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hond_voorbeeld);
                RoundImage roundedImage;
                roundedImage = new RoundImage(bm);
                imageview.setLayoutParams(new TableRow.LayoutParams(100, 100));
                imageview.setImageResource(dierUitLijst.getImage_ID());
                imageviewProfielListener(imageview, dierUitLijst.getDier_ID(), bm);
                row.addView(imageview);

                // TextView naam
                TextView textviewNaam = new TextView(fragmentview.getContext());
                textviewNaam.setLayoutParams(new TableRow.LayoutParams(0, RadioGroup.LayoutParams.WRAP_CONTENT, 1f));
                textviewNaam.setText(dierUitLijst.getNaam());
                row.addView(textviewNaam);

                // TextView status
                TextView textviewStatus = new TextView(fragmentview.getContext());
                textviewStatus.setLayoutParams(new TableRow.LayoutParams(0, RadioGroup.LayoutParams.WRAP_CONTENT, 1f));
                textviewStatus.setText(dierUitLijst.getStatus());
                // Kleur tekst TextView status
                if (textviewStatus.getText().equals("Vermist") || textviewStatus.getText().equals("vermist")) {
                    textviewStatus.setTextColor(getResources().getColor(R.color.red));
                }
                if (textviewStatus.getText().equals("Gevonden") || textviewStatus.getText().equals("gevonden")) {
                    textviewStatus.setTextColor(getResources().getColor(R.color.green));
                }
                row.addView(textviewStatus);

                // Button Details
                Button button = new Button(fragmentview.getContext());
                button.setText("Details");
                button.setBackgroundColor(Color.parseColor("#E0411D"));
                button.setPaddingRelative(-10, 0, -10, 0);
                button.setLayoutParams(new TableRow.LayoutParams(0, RadioGroup.LayoutParams.WRAP_CONTENT, 1f));
                buttonClickListener(button, dierUitLijst.getDier_ID());
                row.addView(button);

                tablelayout.addView(row);

            }
        }
        List<Bericht> berichten = db.getAllBerichten();
        for (Bericht berichtUitLijst : berichten) {
            if (berichtUitLijst.getBerichtGebruikerID() == gebruikersID) {
                TextView dynamicTextView = new TextView(fragmentview.getContext());
                dynamicTextView.setText(berichtUitLijst.getBericht());
                LinearLayout layout = (LinearLayout) fragmentview.findViewById(R.id.layout_berichten);
                layout.addView(dynamicTextView);
                Button button = new Button(fragmentview.getContext());
                button.setText("Details");
                button.setBackgroundColor(Color.parseColor("#E0411D"));
                button.setLayoutParams(new LinearLayout.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                layout.addView(button);
            }
        }


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

    private void buttonClickListener(Button button, final int dier_ID) {
        button.setOnClickListener(new View.OnClickListener() {
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

}

