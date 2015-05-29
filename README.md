# Lassie
Dit is de officiële lassie app die mensen helpt met het zoeken naar hun huisdieren. Hieronder volgt een korte handleiding
waar staat wat wat is en hoe de app in elkaar zit. Ook worden er een paar standaarden beschreven die ten alle tijden
gevolgd moeten worden. Dit is in verband met consistentie van de lay-out.

## Inhoudsopgave
* Waar begin ik?
* De belangrijkste bestanden
* Enkele guidlines conform design
* De opbouw
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
* Een corresponderen .XML bestand

In het .java bestand staat beschreven wat het paneel moet doen, terwijl in het .XML bestand staat hoe het paneel is opgebouwd. De hele app bestaat natuurlijk niet uit een hoopje losse panelen, hier zit samenhang tussen. Deze is als volgt opgebouwd:

Home.java en activity_home.XML zijn het inlogscherm. Hier komt de gebruiker als eerste langs (of niet, hangt ervan af of deze als is ingelogd). Omdat dit tot nu toe een horizontaal prototype is, verwijst de "Registreer" knop rechtstreeks door naar MainActivity.java en zijn bijbehorende activity_main.XML. MainActivity.java is de basis voor de hele app. In MainActivity.java zit namelijk het menu zelf ingebouwd, en niet in elk .java bestand zoals je misschien zou denken. Het principe is hetzelfde als bij website en iFrames. Het menu bevindt zich op één plek (MainActivity.java) en verandert dus niet wanneer je in het menu op een ander scherm klikt. De inhoud echter (alles onder de ActionBar), verandert wel. 

Dit stuk dat wel verandert, heet een Fragment. Vandaar dat je in bijvoorbeeld HomeActivity of MijnOverzicht ook ziet staan:
```
  public class HomeActivity extends Fragment
```
Dit wil zeggen dat elk paneel dat in het menu staat een uitbreiding is van een standaard Fragment. Door dit principe hoef je niet steeds het menu steeds opnieuw te maken. Ook verandert de titel automatisch mee.

TL;DR: Het menu hoeft niet meer aan de panelen toegevoegd te worden.
 

## FAQ
### Hoe test ik de app?
 Om de app te testen, moet je hem afspelen op een zogenaamde emulator. De computer simuleert een telefoon, waardoor de app
 afgespeeld kan worden. laadt het project en druk op shift + f10. Met het creëeren van de app is de Nexus 5 met API 22
 gebruikt, maar je bent vrij om een ander apparaat te gebruiken. Zorg er wel voor dat je voldoende RAM geheugen beschikbaar
 hebt (maar dat geeft hij vanzelf aan als dast niet zo is).
 
### Wat moet ik doen?
 Dit wordt afgesproken in de taakverdeling. Als dit niet duidelijk is -> vraag Sander.
 
### Hoe koppel ik Android Studio aan de Github repo?
 Allereerst, download de github tools: https://git-scm.com/download/win. Klik vervolgens op VCS -> Enable version Control Integration -> Git. Om de files te updaten, klik op VCS -> Update project. Om te 
 comitten, klik op VCS -> Commit changes.
