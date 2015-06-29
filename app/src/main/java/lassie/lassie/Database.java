package lassie.lassie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LassieDB";
    private static final String TABLE_GEBRUIKER = "GEBRUIKER";
    private static final String TABLE_DIER = "DIER";
    private static final String TABLE_BERICHT = "BERICHT";
    // kolom namen GEBRUIKER
    private static final String GEBRUIKER_GEBRUIKER_ID = "gebruiker_ID";
    private static final String GEBRUIKER_GEBRUIKERSNAAM = "gebruikersnaam";
    private static final String GEBRUIKER_WACHTWOORD = "wachtwoord";
    private static final String GEBRUIKER_VOORNAAM = "voornaam";
    private static final String GEBRUIKER_TUSSENVOEGSEL = "tussenvoegsel";
    private static final String GEBRUIKER_ACHTERNAAM = "achternaam";
    private static final String GEBRUIKER_STAD = "stad";
    private static final String GEBRUIKER_EMAIL = "email";
    private static final String GEBRUIKER_TELEFOONNUMMER = "telefoonnummer";

    // Kolom namen DIER
    private static final String DIER_ID = "dier_ID";
    private static final String DIER_NAAM = "naam";
    private static final String DIER_DIERSOORT = "diersoort";
    private static final String DIER_RAS = "ras";
    private static final String DIER_GESLACHT = "geslacht";
    private static final String DIER_KLEUR = "kleur";
    private static final String DIER_STATUS = "status";
    private static final String DIER_GEBRUIKER_ID = "gebruiker_ID";

    // Kolom namen BERICHT
    private static final String BERICHT_BERICHT_ID = "bericht_ID";
    private static final String BERICHT_DIER_ID = "dier_ID";
    private static final String BERICHT_GEBRUIKER_ID = "gebruiker_ID";
    private static final String BERICHT_DATUM = "datum";
    private static final String BERICHT_POSTCODE = "postcode";
    private static final String BERICHT_BERICHT = "bericht";
    private static final String[] GebruikersKolom = {GEBRUIKER_GEBRUIKER_ID, GEBRUIKER_GEBRUIKERSNAAM, GEBRUIKER_WACHTWOORD, GEBRUIKER_VOORNAAM, GEBRUIKER_TUSSENVOEGSEL, GEBRUIKER_ACHTERNAAM,
            GEBRUIKER_STAD, GEBRUIKER_EMAIL, GEBRUIKER_TELEFOONNUMMER};
    //    private static final String[] GebruikersPassKolom = {GEBRUIKER_PASS_EMAIL, GEBRUIKER_PASS_WACHTWOORD};
    private static final String[] DierKolom = {DIER_ID, DIER_NAAM, DIER_DIERSOORT, DIER_RAS, DIER_GESLACHT, DIER_KLEUR, DIER_STATUS, DIER_GEBRUIKER_ID};
    private static final String[] BerichtKolom = {BERICHT_BERICHT_ID, BERICHT_DIER_ID, BERICHT_GEBRUIKER_ID, BERICHT_DATUM, BERICHT_POSTCODE, BERICHT_BERICHT};

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES_GEBRUIKER =
                "CREATE TABLE GEBRUIKER (" +
                        "gebruiker_ID INTEGER NOT NULL," +
                        "gebruikersnaam VARCHAR(20) NOT NULL, " +
                        "wachtwoord VARCHAR (30) NOT NULL, " +
                        "voornaam VARCHAR(20)," +
                        "tussenvoegsel VARCHAR (10)," +
                        "achternaam VARCHAR(30)," +
                        "stad VARCHAR (30)," +
                        "email VARCHAR(70)," +
                        "telefoonnummer INTEGER," +
                        "PRIMARY KEY (gebruiker_ID)" +
                        ");";
        String SQL_CREATE_ENTRIES_DIER =
                "CREATE TABLE DIER (" +
                        "dier_ID INTEGER NOT NULL," +
                        "naam VARCHAR (30)," +
                        "diersoort VARCHAR (30) NOT NULL," +
                        "ras VARCHAR (30)," +
                        "geslacht VARCHAR (1)," +
                        "kleur VARCHAR(15) NOT NULL," +
                        "status VARCHAR(15) NOT NULL," +
                        "gebruiker_ID INTEGER NOT NULL," +
                        "PRIMARY KEY (dier_ID)" +
                        " FOREIGN KEY (gebruiker_ID) REFERENCES GEBRUIKER (gebruiker_ID)" +
                        ");";
        String SQL_CREATE_ENTRIES_BERICHT =
                "CREATE TABLE BERICHT (" +
                        "bericht_ID INTEGER," +
                        "dier_ID INTEGER," +
                        "gebruiker_ID INTEGER," +
                        "datum DATE," +
                        "postcode VARCHAR(7)," +
                        "bericht VARCHAR(200)," +
                        "PRIMARY KEY(bericht_ID)" +
                        "FOREIGN KEY (dier_ID) REFERENCES DIER (dier_ID)," +
                        "FOREIGN KEY (gebruiker_ID) REFERENCES GEBRUIKER (gebruikers_ID)" +
                        ");";
        db.execSQL(SQL_CREATE_ENTRIES_GEBRUIKER);
        db.execSQL(SQL_CREATE_ENTRIES_DIER);
        db.execSQL(SQL_CREATE_ENTRIES_BERICHT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS GEBRUIKER");
        db.execSQL("DROP TABLE IF EXISTS GEBRUIKER_PASS");
        db.execSQL("DROP TABLE IF EXISTS DIER");
        db.execSQL("DROP TABLE IF EXISTS BERICHT");
        this.onCreate(db);
    }

    public void addGebruiker(Gebruiker gebruiker) {
        Log.d("addGebruiker", gebruiker.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GEBRUIKER_GEBRUIKER_ID, gebruiker.getGebruiker_ID());
        values.put(GEBRUIKER_GEBRUIKERSNAAM, gebruiker.getGebruikersnaam());
        values.put(GEBRUIKER_WACHTWOORD, gebruiker.getWachtwoord());
        values.put(GEBRUIKER_VOORNAAM, gebruiker.getVoornaam());
        values.put(GEBRUIKER_TUSSENVOEGSEL, gebruiker.getTussenvoegsel());
        values.put(GEBRUIKER_ACHTERNAAM, gebruiker.getAchternaam());
        values.put(GEBRUIKER_STAD, gebruiker.getStad());
        values.put(GEBRUIKER_EMAIL, gebruiker.getEmail());
        values.put(GEBRUIKER_TELEFOONNUMMER, gebruiker.getTelefoonnummer());


        db.insert(TABLE_GEBRUIKER,
                "",
                values);
        db.close();
    }

    public Gebruiker getGebruiker(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_GEBRUIKER, // table naam
                        GebruikersKolom, // table kolom
                        " gebruiker_ID = ?", // selections
                        new String[]{String.valueOf(id)}, // selections args
                        null, // group by
                        null, // having
                        null, // order by
                        null); // limit

        // Als er resultaten zijn, pak dan de eerste
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build gebruiker object
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setGebruiker_ID(Integer.parseInt(cursor.getString(0)));
        gebruiker.setGebruikersnaam(cursor.getString(1));
        gebruiker.setWachtwoord(cursor.getString(2));
        gebruiker.setVoornaam(cursor.getString(3));
        gebruiker.setTussenvoegsel(cursor.getString(4));
        gebruiker.setAchternaam(cursor.getString(5));
        gebruiker.setStad(cursor.getString(6));
        gebruiker.setEmail(cursor.getString(7));
        gebruiker.setTelefoonnummer(cursor.getString(8));

        Log.d("getGebruiker(" + id + ")", gebruiker.toString());
        return gebruiker;
    }

    public List<Gebruiker> getAllGebruikers() {
        List<Gebruiker> gebruikers = new LinkedList<Gebruiker>();

        String query = "SELECT  * FROM " + TABLE_GEBRUIKER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Gebruiker gebruiker = null;
        if (cursor.moveToFirst()) {
            do {
                gebruiker = new Gebruiker();
                gebruiker.setGebruiker_ID(Integer.parseInt(cursor.getString(0)));
                gebruiker.setGebruikersnaam(cursor.getString(1));
                gebruiker.setWachtwoord(cursor.getString(2));
                gebruiker.setVoornaam(cursor.getString(3));
                gebruiker.setTussenvoegsel(cursor.getString(4));
                gebruiker.setAchternaam(cursor.getString(5));
                gebruiker.setStad(cursor.getString(6));
                gebruiker.setEmail(cursor.getString(7));
                gebruiker.setTelefoonnummer(cursor.getString(8));

                gebruikers.add(gebruiker);
            } while (cursor.moveToNext());
        }

        Log.d("getAllGebruikers()", gebruikers.toString());

        return gebruikers;
    }

    public int updateGebruiker(Gebruiker gebruiker) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(GEBRUIKER_GEBRUIKER_ID, gebruiker.getGebruiker_ID());
        values.put(GEBRUIKER_GEBRUIKERSNAAM, gebruiker.getGebruikersnaam());
        values.put(GEBRUIKER_WACHTWOORD, gebruiker.getWachtwoord());
        values.put(GEBRUIKER_VOORNAAM, gebruiker.getVoornaam());
        values.put(GEBRUIKER_TUSSENVOEGSEL, gebruiker.getTussenvoegsel());
        values.put(GEBRUIKER_ACHTERNAAM, gebruiker.getAchternaam());
        values.put(GEBRUIKER_STAD, gebruiker.getStad());
        values.put(GEBRUIKER_EMAIL, gebruiker.getEmail());
        values.put(GEBRUIKER_TELEFOONNUMMER, gebruiker.getTelefoonnummer());

        int i = db.update(TABLE_GEBRUIKER,
                values,
                GEBRUIKER_GEBRUIKER_ID + " = ?",
                new String[]{String.valueOf(gebruiker.getGebruiker_ID())}); //selection args

        db.close();
        return i;

    }

    public void deleteGebruiker(Gebruiker gebruiker) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_GEBRUIKER,
                GEBRUIKER_GEBRUIKER_ID + " = ?",
                new String[]{String.valueOf(gebruiker.getGebruiker_ID())});

        // 3. close
        db.close();

        Log.d("deleteGebruiker", gebruiker.toString());

    }

    public void addDier(Dier dier) {
        Log.d("addDier", dier.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DIER_ID, dier.getDier_ID());
        values.put(DIER_NAAM, dier.getNaam());
        values.put(DIER_DIERSOORT, dier.getDiersoort());
        values.put(DIER_RAS, dier.getRas());
        values.put(DIER_GESLACHT, dier.getGeslacht());
        values.put(DIER_KLEUR, dier.getKleur());
        values.put(DIER_STATUS, dier.getStatus());
        values.put(DIER_GEBRUIKER_ID, dier.getGebruiker_ID());


        db.insert(TABLE_DIER,
                null,
                values);
        db.close();
    }

    public Dier getDier(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_DIER, // table naam
                        DierKolom, // table kolom
                        " dier_ID = ?", // selections
                        new String[]{String.valueOf(id)}, // selections args
                        null, // group by
                        null, // having
                        null, // order by
                        null); // limit

        // Als er resultaten zijn, pak dan de eerste
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build dier object
        Dier dier = new Dier();
        dier.setDier_ID(Integer.parseInt(cursor.getString(0)));
        dier.setNaam(cursor.getString(1));
        dier.setDiersoort(cursor.getString(2));
        dier.setRas(cursor.getString(3));
        dier.setGeslacht(cursor.getString(4));
        dier.setKleur(cursor.getString(5));
        dier.setStatus(cursor.getString(6));
        dier.setGebruiker_ID(Integer.parseInt(cursor.getString(7)));

        Log.d("getDier(" + id + ")", dier.toString());
        return dier;
    }

    public List<Dier> getAllDieren() {
        List<Dier> dieren = new LinkedList<Dier>();

        String query = "SELECT  * FROM " + TABLE_DIER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Dier dier = null;
        if (cursor.moveToFirst()) {
            do {
                dier = new Dier();
                dier.setDier_ID(Integer.parseInt(cursor.getString(0)));
                dier.setNaam(cursor.getString(1));
                dier.setDiersoort(cursor.getString(2));
                dier.setRas(cursor.getString(3));
                dier.setGeslacht(cursor.getString(4));
                dier.setKleur(cursor.getString(5));
                dier.setStatus(cursor.getString(6));
                dier.setGebruiker_ID(Integer.parseInt(cursor.getString(7)));

                dieren.add(dier);
            } while (cursor.moveToNext());
        }

        Log.d("getAllDiers()", dieren.toString());

        return dieren;
    }

    public int updateDier(Dier dier) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(DIER_ID, dier.getDier_ID());
        values.put(DIER_NAAM, dier.getNaam());
        values.put(DIER_DIERSOORT, dier.getDiersoort());
        values.put(DIER_RAS, dier.getRas());
        values.put(DIER_GESLACHT, dier.getGeslacht());
        values.put(DIER_KLEUR, dier.getKleur());
        values.put(DIER_STATUS, dier.getStatus());
        values.put(DIER_GEBRUIKER_ID, dier.getGebruiker_ID());

        int i = db.update(TABLE_DIER,
                values,
                DIER_ID + " = ?",
                new String[]{String.valueOf(dier.getDier_ID())}); //selection args

        db.close();
        return i;

    }

    public void deleteDier(Dier dier) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_DIER,
                DIER_ID + " = ?",
                new String[]{String.valueOf(dier.getDier_ID())});

        // 3. close
        db.close();

        Log.d("deleteGebruiker", dier.toString());

    }

    public void addBericht(Bericht bericht) {
        Log.d("addBericht", bericht.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BERICHT_BERICHT_ID, bericht.getBericht_ID());
        values.put(BERICHT_DIER_ID, bericht.getBerichtDierID());
        values.put(BERICHT_GEBRUIKER_ID, bericht.getBerichtGebruikerID());
        values.put(BERICHT_DATUM, bericht.getBerichtDatum());
        values.put(BERICHT_POSTCODE, bericht.getBerichtPostcode());
        values.put(BERICHT_BERICHT, bericht.getBericht());

        db.insert(TABLE_BERICHT,
                null,
                values);
        db.close();
    }

    public Bericht getBericht(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_BERICHT, // table naam
                        BerichtKolom, // table kolom
                        " bericht_ID = ?", // selections
                        new String[]{String.valueOf(id)}, // selections args
                        null, // group by
                        null, // having
                        null, // order by
                        null); // limit

        // Als er resultaten zijn, pak dan de eerste
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build bericht object
        Bericht bericht = new Bericht();
        bericht.setBericht_ID(Integer.parseInt(cursor.getString(0)));
        bericht.setBerichtDierID(Integer.parseInt(cursor.getString(1)));
        bericht.setBerichtGebruikerID(Integer.parseInt(cursor.getString(2)));
        bericht.setBerichtDatum(cursor.getString(3));
        bericht.setBerichtPostcode(cursor.getString(4));
        bericht.setBericht(cursor.getString(5));

        Log.d("getBericht(" + id + ")", bericht.toString());
        return bericht;
    }

    public List<Bericht> getAllBerichten() {
        List<Bericht> berichten = new LinkedList<Bericht>();

        String query = "SELECT  * FROM " + TABLE_BERICHT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Bericht bericht = null;
        if (cursor.moveToFirst()) {
            do {
                bericht = new Bericht();
                bericht.setBericht_ID(Integer.parseInt(cursor.getString(0)));
                bericht.setBerichtDierID(Integer.parseInt(cursor.getString(1)));
                bericht.setBerichtGebruikerID(Integer.parseInt(cursor.getString(2)));
                bericht.setBerichtDatum(cursor.getString(3));
                bericht.setBerichtPostcode(cursor.getString(4));
                bericht.setBericht(cursor.getString(5));

                berichten.add(bericht);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBerichts()", berichten.toString());

        return berichten;
    }

    public int updateBericht(Bericht bericht) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(BERICHT_BERICHT_ID, bericht.getBericht_ID());
        values.put(BERICHT_DIER_ID, bericht.getBerichtDierID());
        values.put(BERICHT_GEBRUIKER_ID, bericht.getBerichtGebruikerID());
        values.put(BERICHT_DATUM, bericht.getBerichtDatum());
        values.put(BERICHT_POSTCODE, bericht.getBerichtPostcode());
        values.put(BERICHT_BERICHT, bericht.getBericht());

        int i = db.update(TABLE_BERICHT,
                values,
                BERICHT_BERICHT_ID + " = ?",
                new String[]{String.valueOf(bericht.getBericht_ID())}); //selection args

        db.close();
        return i;

    }

    public void deleteBericht(Bericht bericht) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BERICHT,
                BERICHT_BERICHT_ID + " = ?",
                new String[]{String.valueOf(bericht.getBericht_ID())});

        // 3. close
        db.close();

        Log.d("deleteGebruiker", bericht.toString());

    }

}