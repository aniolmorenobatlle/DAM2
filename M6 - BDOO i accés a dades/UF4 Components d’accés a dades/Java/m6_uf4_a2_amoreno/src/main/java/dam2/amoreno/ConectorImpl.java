package dam2.amoreno;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConectorImpl implements ConectorListener {

    private void escriureLog(String message) {
        
        try (FileWriter writer = new FileWriter("events.log", true)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHora = LocalDateTime.now().format(formatter);
            writer.write(dataHora + " - " + message + "\n");
            
        } catch (Exception e) {
            System.out.println("Error en escriure en el fixter de log!!");
        }
    }

    @Override
    public void onConnexioEstablerta() {
        String message = "Esdeveniment: Connexió establerta!!";
        System.out.println(message);
        escriureLog(message);
    }

    @Override
    public void onErrorConnexio(String missatge) {
        String message = "Esdeveniment: Error en establir la connexio. Missatge d'error: " + missatge;
        System.out.println(message);
        escriureLog(message);
    }

    @Override
    public void onConnexioTancada() {
        String message = "Esdeveniment: Connexió tancada!!";
        System.out.println(message);
        escriureLog(message);
    }

    @Override
    public void onConnexioCaiguda() {
        String message = "Esdeveniment: Connexió caiguda!!";
        System.out.println(message);
        escriureLog(message);
    }
}
