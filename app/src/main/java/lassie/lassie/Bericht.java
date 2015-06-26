package lassie.lassie;

public class Bericht {
    // Bericht table name
    private static final String TABLE_BERICHT = "BERICHT";
    // Bericht tabel kolom namen
    private static final String BERICHT_BERICHT_ID = "bericht_ID";
    private static final String BERICHT_DIER_ID = "dier_ID";
    private static final String BERICHT_GEBRUIKER_ID = "gebruiker_ID";
    private static final String BERICHT_DATUM = "datum";
    private static final String BERICHT_POSTCODE = "postcode";
    private static final String BERICHT_BERICHT = "bericht";
    private static final String[] BerichtKolom = {BERICHT_BERICHT_ID, BERICHT_DIER_ID, BERICHT_GEBRUIKER_ID, BERICHT_DATUM, BERICHT_POSTCODE, BERICHT_BERICHT};
    private int bericht_ID;
    private int dier_ID;
    private int gebruiker_ID;
    private String datum;
    private String postcode;
    private String bericht;

    public Bericht() {
    }

    public Bericht(int bericht_ID, int gebruiker_ID, int dier_ID, String datum, String postcode, String bericht) {
        super();
        this.bericht_ID = bericht_ID;
        this.gebruiker_ID = gebruiker_ID;
        this.dier_ID = dier_ID;
        this.datum = datum;
        this.postcode = postcode;
        this.bericht = bericht;
    }

    //getters & setters

    public int getBericht_ID() {
        return bericht_ID;
    }

    public void setBericht_ID(int bericht_ID) {
        this.bericht_ID = bericht_ID;
    }

    public int getBerichtGebruikerID() {
        return gebruiker_ID;
    }

    public void setBerichtGebruikerID(int gebruiker_ID) {
        this.gebruiker_ID = gebruiker_ID;
    }

    public int getBerichtDierID() {
        return dier_ID;
    }

    public void setBerichtDierID(int dier_ID) {
        this.dier_ID = dier_ID;
    }

    public String getBerichtPostcode() {
        return postcode;
    }

    public void setBerichtPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getBerichtDatum() {
        return datum;
    }

    public void setBerichtDatum(String datum) {
        this.datum = datum;
    }

    public String getBericht() {
        return bericht;
    }

    public void setBericht(String bericht) {
        this.bericht = bericht;
    }

    @Override
    public String toString() {
        return "BERICHT [bericht_ID = " + bericht_ID + ", dier_ID = " + dier_ID + ", gebruiker_ID = " + gebruiker_ID + ", datum = "
                + datum + ", postcode = " + postcode + ", bericht = " + bericht + "]";
    }
}
