# Lassie
Dit is de officiële lassie app die mensen helpt met het zoeken naar hun huisdieren. Hieronder volgt een korte handleiding
waar staat wat wat is en hoe de app in elkaar zit. Ook worden er een paar standaarden beschreven die ten alle tijden
gevolgd moeten worden. Dit is in verband met consistentie van de lay-out.

## Inhoudsopgave
* Waar begin ik?
* De belangrijkste bestanden
* Enkele guidlines conform design
* De opbouw
* Code voor de database
* FAQ

## Waar begin ik?
Je begint met het downloaden van Android Studio (https://developer.android.com/sdk/index.html), waarna je meteen Java SE
Development Kit kan downloaden (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
Kloon (lees: download) de repo en importeer hem in Android Studio (File -> New -> Import project). Als het goed is, is het
project nu geopend.

## De belangrijkste bestanden
De belangrijkste bestanden voor de lassie app zijn de volgende:
  * Alle .Java bestanden voor de activiteiten (b.v. Home.Java). Deze worden automatisch verzameld in Lassie/app/src/main/java/lassie.lassie
  * De bijbehorden .xml bestanden van de activiteiten (b.v. menu_home.xml). Deze worden automatisch verzameld in Lassie/app/src/main/res/
  Hierbij kan gedacht worden aan menu_home.xml of activity_home.xml
  * AndroidManifest.xml. Dit bestand beschrijft de grote lijnen van de app, zoals het thema, de titel en het splashscreen
  * Strings.xml. Hierin worden ALLE strings opgeslagen die voorkomen in de app. Gebruik dus geen hardcoded tekst, maar
  een verwijzing naar strings.xml door middel van "@string/voorbeeld"
  * Drawawbles. Drawables zijn plaatjes die in de app gebruikt worden. Deze zijn te vinden in Lassie/app/src/res/.
  
## Enkele guidelines conform design
 * DE BELANGRIJKSTE: CONTROLEER ALTIJD JE CODE VOORDAT JE IETS COMMIT. Als je dit niet doet, gaat iemand anders dus verder met 
 jouw gepruts.
 * Gebruik verschillende formaten voor de drawables (mdpi, hdpi, xhdpi en xxhdpi vereist). xxxhdpi is handig maar niet noodzakelik.
 Plaatjes kunnen eenvoudig naar drawables worden geconverteerd (incl alle formaten en de juiste namen) met Android asset studio:
 https://romannurik.github.io/AndroidAssetStudio/index.html
 * Sla strings altijd op in Strings.xml. Hierdoor kan tekst makkelijk bewerkt worden en hoeft er dus niet helemaal gezocht
  te worden tussen alle regels code.
 * Kijk naar de panelen voordat je iets maakt. Hierdoor voorkom je dat dat je iets maakt waar niet over is overlegd. Het is de bedoeling dat alles wat gemaakt wordt is besproken en dus niet ter plekke uit je duim gezogen is.
 * Kies goede namen voor id's en strings. In activity_home.xml is dit bijvoorbeeld "@id/edittext_email" voor de editText van de email. Houdt het dus leesbaar!

## De opbouw
Elk paneel is min of meer opgebouwd uit 2 bestanden:
* Een .java bestand
* Een corresponderend .XML bestand

In het .java bestand staat beschreven wat het paneel moet doen, terwijl in het .XML bestand staat hoe het paneel is opgebouwd. De hele app bestaat natuurlijk niet uit een hoopje losse panelen, hier zit samenhang tussen. Deze is als volgt opgebouwd:

Home.java en activity_home.XML zijn het inlogscherm. Hier komt de gebruiker als eerste langs (of niet, hangt ervan af of deze als is ingelogd). Omdat dit tot nu toe een horizontaal prototype is, verwijst de "Registreer" knop rechtstreeks door naar MainActivity.java en zijn bijbehorende activity_main.XML. MainActivity.java is de basis voor de hele app. In MainActivity.java zit namelijk het menu zelf ingebouwd, en niet in elk .java bestand zoals je misschien zou denken. Het principe is hetzelfde als bij website en iFrames in html. Het menu bevindt zich op één plek (MainActivity.java) en verandert dus niet wanneer je in het menu op een ander scherm klikt. De inhoud echter (alles onder de ActionBar), verandert wel. 

Dit stuk dat wel verandert, heet een Fragment. Vandaar dat je in bijvoorbeeld HomeActivity of MijnOverzicht ook ziet staan:
``` java
  public class HomeActivity extends Fragment
```
Dit wil zeggen dat elk paneel dat in het menu staat een uitbreiding is van een standaard Fragment. Door dit principe hoef je niet steeds het menu steeds opnieuw te maken. Ook verandert de titel automatisch mee.

Dan nog even een korte uitleg van alle .java classes en methodes zoals wij die tot nu toe hebben:
* Home: Dit is het registratiescherm en ook het eerste scherm wat de gebruiker ziet. In deze klasse zitten de volgende methodes:
 * onCreate: De methode die wordt aangeroepen wanneer de activity wordt gemaakt. Hierin wordt het splash screen getoond.
 * Continue: Komt in actie wanneer de gebruiker op de "Registreren" knop drukt en stuurt hem door naar het volgende scherm via een intent.
* MainActivity: Dit is de grootste klasse in Lassie. Hierin wordt het menu en de actionbar gemaakt en bepaald welk fragment aan de beurt is om te laten zien. Omdat een hamburgermenu maken best lastig is, hebben wij gebruik gemaakt van de tutorial van Android Developer gebruik gemaakt.
 * onCreate: Wordt aangeroepen wanneer MainActivity aangeroepen wordt. In onCreate zitten nog enkele methodes die helpen met het menu (de 'drawer') en de fragments.
 * onCreateOptions: Een standaard methode die wordt aangeroepen wanneer de opties in de action bar worden aangemaakt (zoals de settings in overflow).
 * onOptionsSelected: Wordt aangeroepen wanneer de gebruiker op een item uit de action bar klikt en zorgt ervoor dat er dan iets mee gedaan wordt.
 * actionSettings: Wanneer er op settings wordt gedrukt in de action bar, verwijs dan door naar de fragment 'Instellingen'.
 * onPrepareOptions: Zorgt ervoor dat als het menu geopend is, er geen items op de action bar te zien zijn.
 * onPostCreate: Synchroniseert de status van de 'hamburger' nadat onRestoreInstanceState heeft plaatsgevonden
 * onConfigurationChanged: Wordt aangeroepen wanneer er iets in de configuratie van de app veranderd is. Dat wil zeggen, er wordt op de menu knop geklikt.
 * selecteerFragment: Deze methode krijgt van DrawerItemClickListener binnen op welke fragment er is geklikt (1-7). SelecteerFragment zorgt er dan voor dat de inhoud van het scherm (standaard 1 = HomeActivity) wordt vervangen door de fragment waarop geklikt is in het menu.
 * setTitle: Past de titel in de action bar aan wanneer er van fragment gewisseld wordt.
 * DrawerItemClickListener: 'Luistert' naar wanneer er op een menu geklikt wordt en geeft dit door aan selectFragment
* HomeActivity: Deze klasse is het eerste wat de gebruiker ziet nadat hij is ingelogd. Hierin zijn enkele vermiste en gevonden dieren te zien. Vanuit hier kan de gebruiker ook meteen een bericht plaatsen.
 * De contructor HomeActivity. Deze is leeg.
 * onCreateView: Wordt aangeroepen wanneer de fragment gecreëerd word. Hierin worden onder andere de ronde plaatjes voor de dieren gecreëerd.
 * imageviewProfielListener: 'Luistert' wanneer een gebruiker op een plaatje van het dier klikt en stuurt hem door naar de detailview van het betreffende dier.
* DetailView
 * onCreateView: Wordt aangeroepen bij het creëeren van de fragment. Het enige wat hier gebeurt, is het ophalen van de gegevens die DetailView doorgespeeld krijgt van bijvoorbeeld HomeActivity. De gegevens worden uitgepakt en doorgestuurd naar DetailView.xml, waarin het uiterlijk van de fragment wordt bepaald. 
* MijnOverzicht: Dit is het overzicht van de activiteiten van de gebruiker. MijnOverzicht bevat zowel 'Mijn vermissingen' als 'Mijn berichten'. Beiden zitten in dezelfde klasse en het enige wat ze opdeeld is de stand van de togglebuttons bovenaan in de pagina.
 * onCreateView: Wordt aangeroepen bij het creëeren van de fragment. Hierin wordt wordt gezorgd dat maar één 'togglebutton' tegelijk aan kan staan en wordt het ronde plaatje van 'Mijn vermissingen' aangemaakt.
 * checkElements: Hierin staat het principe van MijnOverzicht. Als de togglebutton 'Mijn vermissingen' actief is, verberg dan alle onderdelen van 'Mijn berichten' en laat de andere onderdelen zien. Als de togglebutton 'Mijn berichten' actief is, doe dan het tegenovergestelde. Het feit dat er maar één togglebutton tegelijk actief kan zijn, wordt geregeld in de onCreateView methode.
 * imageviewProfielListener: 'Luistert' of de gebruiker op een plaatje van een dier klikt en stuurt de gebruiker door naar de detailview van het dier.
 * buttonClickListener: 'Luistert' of de gebruiker op de 'Details' knop van een dier klikt en stuurt de gebruiker door naar de detailview van het dier. De gebruiker heeft zo twee mogelijkheden om naar de detailview te gaan.
* Gebruikerspagina
* Instellingen
* LijstKaart
* Notificaties
* PlaatsBerichtGevonden
* PlaatsBerichtVermissing
* Fragment
* GlobalList
* CustomArrayAdapter
* RoundImage: Een klassie die bitmaps omzet naar ronde drawables. Geschreven door githubgebruiker vinc3m1. Zie ook: https://github.com/vinc3m1/RoundedImageView

TL;DR: Het menu hoeft niet meer aan de panelen toegevoegd te worden.
 
## Code voor de database
Het idee voor de database (in ieder geval voor de lijst), is dat elk plaatje een unieke identifier heeft. Stel we hebben en verzameling plaatjes met de ID's {1,2,3,4,5...n}, dan halen we die als volgt op in de app

``` java
   int t = 1;
   try {
      while (true) {
        plaatjesLijst.add(afGaan(t));
        t++;
        }
      }
   catch (Exception e) { }
   
   public drawable afGaan (int t) {
      string i = "R.drawable.";
      i += t;
      final Field field = R.drawable.getField(i);
      int id = field.getInt(null);
      Drawable drawable = getResources().getDrawable(id);
   }
```

De database is opgebouwd uit 4 klassen:
* Database
* Gebruiker
* Dier
* Bericht

In de klasse Database wordt de database zelf gemaakt. Als het goed is, hoeft hier niks of bijna niks aan veranderd te worden.
De klassen Gebruiker, Dier en Bericht zijn objecten. Elk van deze objecten is voorzien van de nodige methoden. Voor Gebruiker is dit:
* addGebruiker(Gebruiker gebruiker)
* getGebruiker(int id)
* getAllGebruikers()
* updateGebruiker(Gebruiker gebruiker)
* deleteGebruiker(Gebruiker gebruiker)

Dezelfde methodes bestaan voor Dier en Bericht, maar dan met een andere naam. Om een van de methodes te gebruiken, moet je in de meeste gevallen eerst een object (bijvoorbeeld een Gebruiker) aanmaken. Dit kun je doen door te zeggen
``` java
Gebruiker geberuiker = new Gebruiker();
```
Dan is nog de vraag wat je als parameters mee moet geven. Het is eigenlijk heel simpel: als ie in een foutmelding geeft dat je de verkeerde parameters gebruikt hebt, ga je naar de klasse toe en maak je een constructor die wél de goede parameters heeft. 

Dan nog een voorbeeldje van hoe je bijvoorbeeld een nieuwe gebruiker aan kan maken:
``` java

public class ...
Database db;

    // Constructor

   // Nog wat andere methodes

   private void mijnMethode() {
        Gebruiker gebruiker = new Gebruiker(((EditText) findViewById(R.id.edittext_gebruikersnaam)).getText().toString(),
                ((EditText) findViewById(R.id.edittext_wachtwoord)).getText().toString(),
                ((EditText) findViewById(R.id.edittext_email)).getText().toString());
        db.addGebruiker(gebruiker);
   }
```

## FAQ
### Hoe test ik de app?
 Om de app te testen, moet je hem afspelen op een zogenaamde emulator. De computer simuleert een telefoon, waardoor de app
 afgespeeld kan worden. Laad het project en druk op shift + f10. Met het creëeren van de app is de Nexus 5 met API 22
 gebruikt, maar je bent vrij om een ander apparaat te gebruiken. Zorg er wel voor dat je voldoende RAM geheugen beschikbaar
 hebt (maar dat geeft hij vanzelf aan als dast niet zo is).
 
### Wat moet ik doen?
 Dit wordt afgesproken in de taakverdeling. Als dit niet duidelijk is -> vraag Sander.
 
### Hoe koppel ik Android Studio aan de Github repo?
 Allereerst, download de github tools: https://git-scm.com/download/win. Klik vervolgens op VCS -> Enable version Control Integration -> Git. Om de files te updaten, klik op VCS -> Update project. Om te 
 comitten, klik op VCS -> Commit changes.
