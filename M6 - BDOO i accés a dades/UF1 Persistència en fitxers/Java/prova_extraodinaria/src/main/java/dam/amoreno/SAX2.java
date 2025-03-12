package dam.amoreno;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAX2 {
  static Scanner sc = new Scanner(System.in);
  
  public static void main(String[] args) {
    System.out.println();

    File fitxer = new File("xmls/tasques.xml");

    if (!fitxer.exists()) {
      System.out.println("El fitxer no existeix.");
      return;
    }

    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();
      SAXHandler2 handler = new SAXHandler2();
      parser.parse(fitxer, handler);
    } catch (Exception e) {
      System.out.println("Error en llegir el fitxer.");
    }
  }
}
  