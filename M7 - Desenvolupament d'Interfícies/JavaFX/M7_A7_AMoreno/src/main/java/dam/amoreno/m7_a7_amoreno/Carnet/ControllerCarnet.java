package dam.amoreno.m7_a7_amoreno.Carnet;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerCarnet {

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageViewPersona;

    @FXML
    private Pane pane1;

    @FXML
    private StackPane stackPane1;

    @FXML
    private StackPane stackPane2;

    @FXML
    private Text textDNI;

    @FXML
    private Text textNom;

    @FXML
    private Text textResultat;

    private String rutaFitxerPreguntes = "preguntes.txt";
    private String rutaFitxerRespostes = "respostes.txt";
    private String rutaFitxerUsuari = "usuari.txt";
    private String rutaUsuari = "usuari/";

    @FXML
    private void initialize() {
        String tipusUsuari = obtenirTipusUsuari();
        ResultatExamen resultat = haSuperatExamen(tipusUsuari);

        if (resultat.aprovat) {
            imatgePlantilla();
            llegirInfoUsuari();
            textResultat.setText("Has encertat " + resultat.encerts + "/" + resultat.total
                    + ". Enhorabona pel carnet de " + tipusUsuari);
        } else {
            mostrarImatgeUsuari();
            textResultat.setText("Has encertat " + resultat.encerts + "/" + resultat.total +
                    ". No has aprovat el carnet de " + tipusUsuari);
        }
    }

    private void imatgePlantilla() {
        File imageViewPlantilla = new File("dniplantilla.jpg");
        Image imagePlantilla = new Image(imageViewPlantilla.toURI().toString());
        imageView1.setImage(imagePlantilla);
    }

    private void mostrarImatgeUsuari() {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFitxerUsuari))) {
            String imatge = "";

            String linia;
            while ((linia = br.readLine()) != null) {
                String[] parts = linia.split(": ");
                if (parts[0].equalsIgnoreCase("imatge")) {
                    imatge = parts[1].trim();
                    break;
                }
            }

            String imatgeUsuari = rutaUsuari + imatge;
            File imageFile = new File(imatgeUsuari);
            Image image = new Image(imageFile.toURI().toString());
            imageViewPersona.setImage(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void llegirInfoUsuari() {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFitxerUsuari))) {
            String tipus = "";
            String dni = "";
            String nom = "";
            String imatge = "";

            String linia;

            while ((linia = br.readLine()) != null) {
                String[] parts = linia.split(": ");

                String clau = parts[0].toLowerCase();
                String valor = parts[1];

                if (clau.equals("tipus")) {
                    tipus = valor;
                } else if (clau.equals("dni")) {
                    dni = valor;
                } else if (clau.equals("nom")) {
                    nom = valor;
                } else if (clau.equals("imatge")) {
                    imatge = valor;
                }
            }

            String imatgeUsuari = rutaUsuari + imatge;
            File imageViewUsuari = new File(imatgeUsuari);
            Image imageUsuari = new Image(imageViewUsuari.toURI().toString());
            imageViewPersona.setImage(imageUsuari);

            textDNI.setText(dni);
            textNom.setText(nom);
            textResultat.setText("Enhorabona pel carnet de " + tipus);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResultatExamen haSuperatExamen(String tipusUsuari) {
        ResultatExamen resultat = new ResultatExamen();
        try (BufferedReader brPreguntes = new BufferedReader(new FileReader(rutaFitxerPreguntes));
                BufferedReader brRespostes = new BufferedReader(new FileReader(rutaFitxerRespostes))) {

            List<String> respostesUsuari = new ArrayList<>();
            String liniaResposta;
            while ((liniaResposta = brRespostes.readLine()) != null) {
                String[] parts = liniaResposta.split(": ");
                if (parts.length == 2) {
                    respostesUsuari.add(parts[1].trim());
                }
            }

            String linia;
            List<String> respostesCorrectes = new ArrayList<>();
            boolean esDelTipus = false;

            while ((linia = brPreguntes.readLine()) != null) {
                if (linia.equals("---")) {
                    esDelTipus = false;
                    continue;
                }

                if (linia.startsWith("tipus: ")) {
                    String tipus = linia.substring(7).trim();
                    esDelTipus = tipus.equalsIgnoreCase(tipusUsuari);
                }

                if (esDelTipus && linia.contains("- correcta")) {
                    String[] parts = linia.split(": ");
                    if (parts.length == 2) {
                        String respostaCorrecta = parts[1].replace(" - correcta", "").trim();
                        respostesCorrectes.add(respostaCorrecta);
                    }
                }
            }

            int correctes = 0;
            for (int i = 0; i < respostesCorrectes.size() && i < respostesUsuari.size(); i++) {
                if (respostesUsuari.get(i).equalsIgnoreCase(respostesCorrectes.get(i))) {
                    correctes++;
                }
            }

            resultat.encerts = correctes;
            resultat.total = respostesCorrectes.size();
            resultat.aprovat = correctes >= 5;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    private String obtenirTipusUsuari() {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFitxerUsuari))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] parts = linia.split(": ");
                if (parts[0].equalsIgnoreCase("tipus")) {
                    return parts[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static class ResultatExamen {
        boolean aprovat;
        int encerts;
        int total;
    }

}
