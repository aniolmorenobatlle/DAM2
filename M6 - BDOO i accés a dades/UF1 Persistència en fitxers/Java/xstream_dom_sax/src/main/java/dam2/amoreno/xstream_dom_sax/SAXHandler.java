package dam2.amoreno.xstream_dom_sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    
    boolean name = false;
    boolean surname = false;
    boolean mail = false;
    boolean number = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("nom")) {
            name = true;
        } else if (qName.equalsIgnoreCase("cognom")) {
            surname = true;
		} else if (qName.equalsIgnoreCase("mail")) {
			mail = true;
		} else if (qName.equalsIgnoreCase("telefon")) {
			number = true;
		}
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (name) {
        	System.out.println();
        	
            System.out.println("Nom: " + new String(ch, start, length));
            name = false;
        }

        if (surname) {
            System.out.println("Cognom: " + new String(ch, start, length));
            surname = false;
        }
        
		if (mail) {
			System.out.println("Correu: " + new String(ch, start, length));
			mail = false;
		}
		
		if (number) {
			System.out.println("Telefon: " + new String(ch, start, length));
			number = false;
		}
    }
}
