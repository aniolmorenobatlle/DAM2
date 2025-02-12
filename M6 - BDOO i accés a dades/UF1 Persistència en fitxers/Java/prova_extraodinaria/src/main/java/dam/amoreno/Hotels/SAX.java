package dam.amoreno.Hotels;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAX {
    public static void main(String[] args) {
        String ruta = "prova_extraodinaria/xmls/hotels.xml";
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SAXHandlerHotels handler = new SAXHandlerHotels();

            File fitxer = new File(ruta);
            parser.parse(fitxer, handler);

            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
