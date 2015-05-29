package lassie.lassie;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity { // Ja, deze is deprecated, maar we moeten hem toch gebruiken

    private String[] menuItems;
    private DrawerLayout menuLayout;
    private ListView menuLijst;
    private ActionBarDrawerToggle menuToggle;
    private CharSequence menuTitel;
    private CharSequence titel;

    // onCreate wordt als eerste aangeroepen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gegevens van het inloggen
        setTitle("Lassie");
        Intent intent = getIntent();
        String emailMessage = intent.getStringExtra(Home.gebruikersnaammessage);
        TextView email = new TextView(this);
        email.setTextSize(40);
        email.setText(emailMessage);

        // Ophalen menu namen uit de array menu_items in strings.xml
        menuItems = getResources().getStringArray(R.array.menu_items);

        // menuLayout is de overlappende layout waar de frames in geplaatst worden
        menuLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Haalt de eigenschappen voor het menu zelf op uit activity_main.xml
        menuLijst = (ListView) findViewById(R.id.menu_links);

        // Geen idee wat dit is
        menuLijst.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, menuItems));

        // Maakt een nieuwe Click Listener aan. Deze 'luistert' of je op een item klikt
        menuLijst.setOnItemClickListener(new DrawerItemClickListener());

        // Haalt de huidige titel van het menu op
        titel = menuTitel = getTitle();

        // Creëert de 'hamburger'
        menuToggle = new ActionBarDrawerToggle(this, menuLayout, R.string.menu_open, R.string.menu_gesloten) {

            // Vertelt wat er moet gebeuren als het menu gesloten is
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (menuTitel != null) {
                    getSupportActionBar().setTitle(titel); // Produced geen NullPointerException
                }
                invalidateOptionsMenu(); // Roept onPrepareOptionsMenu() aan
            }

            // Vertelt wat er moet gebeuren als het menu open is
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (menuTitel != null) {
                    getSupportActionBar().setTitle(menuTitel); // Produced geen NullPointerException
                }
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Koppelt de DrawerListener aan de 'hamburger'
        menuLayout.setDrawerListener(menuToggle);

        // Laat het pijltje en de 'hamburger' zien
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Produced geen NullPointerException
            getSupportActionBar().setHomeButtonEnabled(true);
        } catch (Exception E) {
        } // Beveiliging tegen NullPointerException

        // Als er nog geen frame is (bij opstarten), gebruik dan HomeActivity
        if (savedInstanceState == null) {
            selecteerFragment(0);
        }
    }

    // Maakt de menu opties (standaard methode)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Reageert op action bar acties
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        if (menuToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Als het menu geopend is, laat dan geen action bar icons zien
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = menuLayout.isDrawerOpen(menuLijst);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    // Synchroniseert de status van de 'hamburger' nadat onRestoreInstanceState heeft plaatsgevonden
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        menuToggle.syncState();
    }

    // Geen idee
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        menuToggle.onConfigurationChanged(newConfig);
    }

    // Verwisselt de fragments
    private void selecteerFragment(int position) {
        // Creëer de fragment
        Fragment fragment = null;

        // Vervangt de nieuwe fragment (van de case) met de huidige

        switch (position) {
            case 0:
                fragment = new HomeActivity();
                break;
            case 1:
                fragment = new MijnOverzicht();
                break;
            case 2:
                fragment = new PlaatsBerichtVermissing();
                break;
            case 3:
                fragment = new PlaatsBerichtGevonden();
                break;
            case 4:
                fragment = new LijstKaart();
                break;
            case 5:
                fragment = new Gebruikerspagina();
                break;
            case 6:
                fragment = new Notificaties();
                break;
            case 7:
                fragment = new Instellingen();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            menuLijst.setItemChecked(position, true);
            menuLijst.setSelection(position);
            setTitle(menuItems[position]);
            menuLayout.closeDrawer(menuLijst);
        } else {
            // Vangt de fout op als de fragment niet bestaat
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    // Past de titel aan
    @Override
    public void setTitle(CharSequence title) {
        titel = title;
        if (titel != null) {
            getSupportActionBar().setTitle(titel); // Produced geen NullPointerException
        }
    }

    // 'Luister' naar wanneer er op een menu geklikt wordt en geeft dit door aan selectFragment
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selecteerFragment(position);
        }
    }

}



