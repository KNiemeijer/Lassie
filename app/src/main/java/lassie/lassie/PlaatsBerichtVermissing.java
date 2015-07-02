package lassie.lassie;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class PlaatsBerichtVermissing extends Fragment {

    private static int RESULT_LOAD_IMAGE = 1;
    View fragmentview;
    Database db;
    Dier dier;
    Bitmap bm;
    int image_ID;
    View.OnClickListener GaDoor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fragment = new BerichtVermissing();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack("BerichtVermissing").replace(R.id.frame_container, fragment).commit();
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
            extras.putParcelable("bm", bm);

            dier = new Dier(dierID, naam, diersoort, rassoort, geslacht, kleur, "vermist", postcode, eigenschappen, ((MainActivity) getActivity()).gebruikersID);
            db.addDier(dier);

            fragment.setArguments(extras);


        }
    };
    View.OnClickListener maakFoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selecteerFoto();
        }
    };

    public PlaatsBerichtVermissing() {

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

    private void selecteerFoto() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);


            try {
                draaien(cursor, columnIndex, picturePath);
                cursor.close();
            } catch (IOException E) {
                ImageView imageView = (ImageView) fragmentview.findViewById(R.id.imageview_upload);
                imageView.setImageBitmap((BitmapFactory.decodeFile(picturePath)));
                cursor.close();
            }


        }
    }

    private void draaien(Cursor cursor, int columnIndex, String picturePath) throws IOException {
        ExifInterface ei = new ExifInterface(cursor.getColumnName(columnIndex));
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        ImageView imageView = (ImageView) fragmentview.findViewById(R.id.imageview_upload);
        imageView.setImageBitmap((BitmapFactory.decodeFile(picturePath)));

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                imageView.setRotation(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                imageView.setRotation(180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                imageView.setRotation(270);
                break;
        }
    }

}