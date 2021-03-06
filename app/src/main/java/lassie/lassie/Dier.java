package lassie.lassie;

public class Dier {
    // Dier table name
    private static final String TABLE_DIER = "DIER";
    // Dier tabel kolom namen
    private static final String DIER_NAAM = "naam";
    private static final String DIER_DIERSOORT = "diersoort";
    private static final String DIER_RAS = "ras";
    private static final String DIER_GESLACHT = "geslacht";
    private static final String DIER_KLEUR = "kleur";
    private static final String DIER_STATUS = "status";
    private static final String DIER_POSTCODE = "postcode";
    private static final String DIER_EIGENSCHAPPEN = "eigenschappen";
    private static final String DIER_GEBRUIKER_ID = "gebruiker_ID";
    private static final String DIER_IMAGE_ID = "image_ID";
    private static final String[] DierKolom = {DIER_NAAM, DIER_DIERSOORT, DIER_RAS, DIER_GESLACHT, DIER_KLEUR, DIER_STATUS, DIER_POSTCODE, DIER_EIGENSCHAPPEN, DIER_GEBRUIKER_ID};
    private int dier_ID;
    private String naam;
    private String diersoort;
    private String ras;
    private String geslacht;
    private String kleur;
    private String status;
    private String postcode;
    private String eigenschappen;
    private int gebruiker_ID;
    private int image_ID;

    public Dier() {
    }

    public Dier(int dier_ID, String naam, String diersoort, String ras, String geslacht, String kleur, String status, String postcode, String eigenschappen, int gebruiker_ID, int image_ID) {
        super();
        this.dier_ID = dier_ID;
        this.diersoort = diersoort;
        this.naam = naam;
        this.ras = ras;
        this.geslacht = geslacht;
        this.kleur = kleur;
        this.status = status;
        this.postcode = postcode;
        this.eigenschappen = eigenschappen;
        this.gebruiker_ID = gebruiker_ID;
        this.image_ID = image_ID;
    }

    public Dier(int dier_ID, String naam, String diersoort, String ras, String geslacht, String kleur, String status, String postcode, String eigenschappen, int gebruiker_ID) {
        super();
        this.dier_ID = dier_ID;
        this.diersoort = diersoort;
        this.naam = naam;
        this.ras = ras;
        this.geslacht = geslacht;
        this.kleur = kleur;
        this.status = status;
        this.postcode = postcode;
        this.eigenschappen = eigenschappen;
        this.gebruiker_ID = gebruiker_ID;
    }

    //getters & setters

    public int getDier_ID() {
        return dier_ID;
    }

    public void setDier_ID(int dier_ID) {
        this.dier_ID = dier_ID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getDiersoort() {
        return diersoort;
    }

    public void setDiersoort(String diersoort) {
        this.diersoort = diersoort;
    }

    public String getRas() {
        return ras;
    }

    public void setRas(String ras) {
        this.ras = ras;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEigenschappen() {
        return eigenschappen;
    }

    public void setEigenschappen(String eigenschappen) {
        this.eigenschappen = eigenschappen;
    }

    public int getGebruiker_ID() {
        return gebruiker_ID;
    }

    public void setGebruiker_ID(int gebruiker_ID) {
        this.gebruiker_ID = gebruiker_ID;
    }

    public int getImage_ID() {
        return image_ID;
    }

    public void setImage_ID(int image_ID) {
        this.image_ID = image_ID;
    }

    @Override
    public String toString() {
        return "DIER [Dier_ID = " + dier_ID + ", naam = " + naam + ", diersoort = " + diersoort + ", ras = "
                + ras + ", geslacht = " + geslacht + ", kleur = " + kleur + ", status = " + status
                + ", postcode = " + postcode + ", eigenschappen = " + eigenschappen + ", gebruiker_ID = " + gebruiker_ID
                + ", image_ID = " + image_ID + "]";
    }
}
