package lassie.lassie;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class PlaatsBerichtGevonden extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    View fragmentview;
    Database db;
    Dier dier;
    Bitmap BitimageBitmap;
    View.OnClickListener GaDoor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fragment = new BerichtGevonden();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack("BerichtGevonden").replace(R.id.frame_container, fragment).commit();
            Bundle extras = new Bundle();

            EditText edittextNaam = (EditText) fragmentview.findViewById(R.id.edittext_naam);
            EditText edittextDiersoort = (EditText) fragmentview.findViewById(R.id.edittext_diersoort);
            EditText edittextRassoort = (EditText) fragmentview.findViewById(R.id.edittext_rassoort);
            EditText edittextGeslacht = (EditText) fragmentview.findViewById(R.id.edittext_geslacht);
            EditText edittextKleur = (EditText) fragmentview.findViewById(R.id.edittext_kleur);
            EditText edittextPostcode = (EditText) fragmentview.findViewById(R.id.edittext_postcode);
            EditText edittextEigenschappen = (EditText) fragmentview.findViewById(R.id.edittext_eigenschappen);

            String naam = edittextNaam.getText().toString();
            String diersoort = edittextDiersoort.getText().toString();
            String rassoort = edittextRassoort.getText().toString();
            String geslacht = edittextGeslacht.getText().toString();
            String kleur = edittextKleur.getText().toString();
            String postcode = edittextPostcode.getText().toString();
            String eigenschappen = edittextEigenschappen.getText().toString();

            int dierID = db.getAllDieren().size() + 1;
            extras.putInt("dierID", dierID);
            extras.putParcelable("bm", BitimageBitmap);

            dier = new Dier(dierID, naam, diersoort, rassoort, geslacht, kleur, "gevonden", postcode, eigenschappen, ((MainActivity) getActivity()).gebruikersID);
            db.addDier(dier);

            fragment.setArguments(extras);

        }
    };
    View.OnClickListener maakFoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dispatchTakePictureIntent();
        }
    };
    String mCurrentPhotoPath;

    public PlaatsBerichtGevonden() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentview = inflater.inflate(R.layout.fragment_plaats_bericht_gevonden, container, false);
        Button button = (Button) fragmentview.findViewById(R.id.button_GaNaarBevestiging);
        button.setOnClickListener(GaDoor);

        ImageView foto = (ImageView) fragmentview.findViewById(R.id.imageview_upload);
        foto.setOnClickListener(maakFoto);

        db = new Database(getActivity());

        return fragmentview;

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == MainActivity.RESULT_OK) {
            Bundle extras = data.getExtras();
            BitimageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView) fragmentview.findViewById(R.id.imageview_upload);
            mImageView.setImageBitmap(BitimageBitmap);
            mImageView.setRotation(270);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "dier" + db.getAllDieren().size() + 1;
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}