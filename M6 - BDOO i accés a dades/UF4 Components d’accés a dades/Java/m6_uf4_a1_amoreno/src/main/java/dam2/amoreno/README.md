# Gestor de Connexions a Bases de Dades

Establir una conexió a una base de dades utiltizant el component `Conector`.

## Com utilitzar el component

### 1. Configurar les credencials de connexió

Al moment d'executar l'aplicació, demanarà les següents dades per consola:

- **Gestor de base de dades**: El tipus de base de dades (per exemple:`mysql` o `postgresql`).
- **IP**: L'adreça IP de la base de dades.
- **Port**: El seu respectiu port.
- **Nom d'usuari**: El nom de l'usuari per autenticar-se a la base de dades.
- **Contrasenya**: La contrasenya de l'usuari.
- **Nom de la base de dades**: El nom de la base de dades a utiltizar.

### 2. Exemple d'ús

Per utilitzar la classe `Conector`, segueix aquests passos:

```
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Conector conn = new Conector();

        System.out.println();

        if (conn.obrirConnexio()) {
            conn.connexioActiva();


            // Aqui dins realitzar les operacions que es volen fer amb la base de dades.


            conn.tancarConnexio();
        }
    }
}
```

### 3. Explicació del codi

- **Conector**: La classe principal per gestionar la connexió amb la base de dades. Utilitza JDBC per establir la connexió.
- **Mètode `obrirConnexio()`**: Aquest mètode intenta establir una connexió amb la base de dades i retorna `true` si la connexió es realitza correctament, o `false` en cas d'error.
- **Mètode `tancarConnexio()`**: Tanca la connexió amb la base de dades.
- Dins del `if (conn.obrirConnexio())` es poden realitzar les operacions que es vulguin fer amb la base de dades, sempre entre `conn.connexioActiva()` i `conn.tancarConnexio()`.
