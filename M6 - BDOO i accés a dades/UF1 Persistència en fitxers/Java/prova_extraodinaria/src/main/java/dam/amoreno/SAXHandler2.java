package dam.amoreno;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler2 extends DefaultHandler{
  boolean nom, data, prioritat, categoria = false;

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("nom")) {
      nom = true;
    } else if (qName.equalsIgnoreCase("data")) {
      data = true;
    } else if (qName.equalsIgnoreCase("prioritat")) {
      prioritat = true;
    } else if (qName.equalsIgnoreCase("categoria")) {
      categoria = true;
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (nom) {
      System.out.println("Nom: " + new String(ch, start, length));
      nom = false;
    }

    if (data) {
      System.out.println("Data: " + new String(ch, start, length));
      data = false;
    }

    if (prioritat) {
      System.out.println("Prioritat: " + new String(ch, start, length));
      prioritat = false;
    }

    if (categoria) {
      System.out.println("Categoria: " + new String(ch, start, length));
      categoria = false;

      System.out.println("-----------------------------");
    }
  }
}
