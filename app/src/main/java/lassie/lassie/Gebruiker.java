package lassie.lassie;

public class Gebruiker {
    // Gebruikers table name
    private static final String TABLE_GEBRUIKER = "GEBRUIKER";
    // Gebruikers Table Columns names
    private static final String GEBRUIKER_GEBRUIKER_ID = "gebruiker_ID";
    private static final String GEBRUIKER_GEBRUIKERSNAAM = "gebruikersnaam";
    private static final String GEBRUIKER_WACHTWOORD = "wachtwoord";
    private static final String GEBRUIKER_VOORNAAM = "voornaam";
    private static final String GEBRUIKER_TUSSENVOEGSEL = "tussenvoegsel";
    private static final String GEBRUIKER_ACHTERNAAM = "achternaam";
    private static final String GEBRUIKER_STAD = "stad";
    private static final String GEBRUIKER_EMAIL = "email";
    private static final String GEBRUIKER_TELEFOONNUMMER = "telefoonnummer";
    private static final String GEBRUIKER_POSTCODE = "postcode";
    private static final String[] GebruikersKolom = {GEBRUIKER_GEBRUIKER_ID, GEBRUIKER_GEBRUIKERSNAAM, GEBRUIKER_WACHTWOORD, GEBRUIKER_VOORNAAM, GEBRUIKER_TUSSENVOEGSEL, GEBRUIKER_ACHTERNAAM,
            GEBRUIKER_STAD, GEBRUIKER_EMAIL, GEBRUIKER_TELEFOONNUMMER, GEBRUIKER_POSTCODE};
    private int gebruiker_ID;
    private String gebruikersnaam;
    private String wachtwoord;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String stad;
    private String email;
    private String telefoonnummer;
    private String postcode;

    public Gebruiker() {
    }

    public Gebruiker(int gebruiker_ID, String gebruikersnaam, String wachtwoord, String email) {
        this.gebruiker_ID = gebruiker_ID;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.email = email;
    }

    public Gebruiker(int gebruiker_ID, String gebruikersnaam, String wachtwoord, String voornaam, String achternaam, String stad, String email, String telefoonnummer) {
        super();
        this.gebruiker_ID = gebruiker_ID;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.stad = stad;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
    }

    public Gebruiker(int gebruiker_ID, String gebruikersnaam, String wachtwoord, String voornaam, String achternaam, String stad, String email, String telefoonnummer, String postcode) {
        super();
        this.gebruiker_ID = gebruiker_ID;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.stad = stad;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
        this.postcode = postcode;
    }

    //getters & setters

    public int getGebruiker_ID() {
        return gebruiker_ID;
    }

    public void setGebruiker_ID(int gebruiker_ID) {
        this.gebruiker_ID = gebruiker_ID;
    }


    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "GEBRUIKER [Gebruiker_ID = " + gebruiker_ID + ", voornaam = " + voornaam + ", tussenvoegsel = " + tussenvoegsel + ", achternaam = "
                + achternaam + ", stad = " + stad + ", email = " + email + ", telefoonnummer = " + telefoonnummer + ", postcode = " + postcode
                + "]";
    }
}
