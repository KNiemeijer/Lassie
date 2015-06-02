package lassie.lassie;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Sander on 31-5-2015.
 */
public /*static*/ class Fragment extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    private ListView listView;
    private ArrayList<Menu> menuItems;
    private CustomArrayAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment,
                container, false);
        listView = (ListView) rootView.findViewById(android.R.id.list);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int num = getArguments().getInt(ARG_SECTION_NUMBER);
        // GlobalList is a class that holds global variables, arrays etc
        // getMenuCategories returns global arraylist which is initialized in GlobalList class

        //menuItems = GlobalList.getMenuCategories().get(num).getMenu();
        mAdapter = new CustomArrayAdapter(getActivity(), android.R.id.list, menuItems);
        listView.setAdapter(mAdapter);
    }
}