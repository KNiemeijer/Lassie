package lassie.lassie;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

//Wordt nog niet gebruikt maar moet uitkomst bieden bij de lijst-weergave
public class CustomArrayAdapter extends ArrayAdapter<Menu> {

    Context context;

    public CustomArrayAdapter(Context context, int textViewResourceId, List<Menu> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewLijst holder = null;
        Menu rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_lijst_kaart, null);
            holder = new ViewLijst();
            //       holder.txtLijstNaam = (TextView) convertView.findViewById(R.id.lijst_naam);holder.txtBeschrijving = (TextView) convertView.findViewById(R.id.beschrijving);
            //       holder.txtStatus = (TextView) convertView.findViewById(R.id.dierstatus);
            //       holder.imageView = (ImageView) convertView.findViewById(R.id.lijst_plaatje);
            convertView.setTag(holder);
        } else
            holder = (ViewLijst) convertView.getTag();

        // TODO Gegevens uit lijst laten ophalen
        //holder.txtLijstNaam.setText(rowItem.getDescription());
        //holder.txtBeschrijving.setText(rowItem.getName());
        //holder.txtStatus.setText(String.valueOf(rowItem.getPrice()) + " TL");
        //holder.imageView.setImageResource(rowItem.getImageId());

        return convertView;
    }

    private class ViewLijst {
        ImageView imageView;
        TextView txtLijstNaam;
        TextView txtBeschrijving;
        TextView txtStatus;
    }

}