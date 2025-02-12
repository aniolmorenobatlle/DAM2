package dam.amoreno.Hotels;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandlerHotels extends DefaultHandler {
    private boolean nom = false;
    private boolean adreca = false;
    private boolean numero = false;
    private boolean tipus = false;
    private boolean preu = false;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("nom")) {
            nom = true;
        } else if (qName.equalsIgnoreCase("adreca")) {
            adreca = true;
        } else if (qName.equalsIgnoreCase("numero")) {
            numero = true;
        } else if (qName.equalsIgnoreCase("tipus")) {
            tipus = true;
        } else if (qName.equalsIgnoreCase("preu")) {
            preu = true;
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (nom) {
            System.out.println("Nom de l'hotel: " + new String(ch, start, length));
            nom = false;
        }

        if (adreca) {
            System.out.println("Adreça: " + new String(ch, start, length));
            adreca = false;
        }

        if (numero) {
            System.out.println("\tNúmero d'habitació: " + new String(ch, start, length));
            numero = false;
        }

        if (tipus) {
            System.out.println("\tTipus d'habitació: " + new String(ch, start, length));
            tipus = false;
        }

        if (preu) {
            System.out.println("\tPreu: " + new String(ch, start, length) + "€");
            preu = false;
            System.out.println("---------------");
        }
    }
}

