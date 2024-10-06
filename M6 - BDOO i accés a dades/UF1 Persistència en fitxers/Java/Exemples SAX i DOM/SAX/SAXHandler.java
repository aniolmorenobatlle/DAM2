package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    
    boolean titol = false;
    boolean autor = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("titol")) {
            titol = true;
        } else if (qName.equalsIgnoreCase("autor")) {
            autor = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (titol) {
            System.out.println("Títol: " + new String(ch, start, length));
            titol = false;
        }

        if (autor) {
            System.out.println("Autor: " + new String(ch, start, length));
            autor = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Pots afegir accions en acabar un element si cal
    }
}
