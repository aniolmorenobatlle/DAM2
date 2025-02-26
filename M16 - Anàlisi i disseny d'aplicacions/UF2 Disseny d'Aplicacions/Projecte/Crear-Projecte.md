# Front-end

## Crear projecte amb Expo

```
npx create-expo-app@latest nom-del-projecte --template blank
```

## Instal·lar dependències

```
cd nom-del-projecte
npm install @react-navigation/native
npm install @react-navigation/stack
npm install react-native-screens react-native-safe-area-context react-native-gesture-handler react-native-reanimated react-native-vector-icons
```

## Instal·lar Axio (peticions Backend)

```
npm install axios
```

## Instal·lar AsyncStorage (guardar dades localment)

```
npm install @react-native-async-storage/async-storage
```


# Back-end

## Crear carpeta backend

```
mkdir backend
cd backend
npm init -y
```

## Instal·lar dependències

```
npm install express pg cors dotenv
```

## Configurar .env

### Crear arxiu .env

```
touch .env
```

### Afegir variables d'entorn

```
PORT=5000
DATABASE_URL=postgres://usuari:contrasenya@localhost:5432/nom_base_dades
```

## Crear el servidor (server.js)

```
require("dotenv").config();
const express = require("express");
const cors = require("cors");
const { Pool } = require("pg");

const app = express();
app.use(cors());
app.use(express.json());

// Configurar PostgreSQL
const pool = new Pool({
  connectionString: process.env.DATABASE_URL,
});

// Ruta per obtenir un text de la base de dades
app.get("/text", async (req, res) => {
  try {
    const result = await pool.query("SELECT text FROM prova LIMIT 1");
    res.json({ message: result.rows[0].text });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: "Error de servidor" });
  }
});

// Iniciar el servidor
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`Servidor en marxa al port ${PORT}`));
```

## Crear taules al Postgress

```
CREATE TABLE prova (
  id SERIAL PRIMARY KEY,
  text TEXT NOT NULL
);

INSERT INTO prova (text) VALUES ('Hola des de PostgreSQL!');
```

## Iniciar el servidor

```
node server.js
```

## Provar el servidor

```
http://localhost:5000/text
```

# Connectar Front-end amb Back-end

## Modificar App.js

```
import React, { useEffect, useState } from "react";
import { View, Text, ActivityIndicator, StyleSheet } from "react-native";
import axios from "axios";

const API_URL = "http://localhost:5000/text"; // Si fas servir un emulador, potser hauràs de posar la IP del teu ordinador

const App = () => {
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchText = async () => {
      try {
        const response = await axios.get(API_URL);
        setMessage(response.data.message);
      } catch (error) {
        console.error("Error carregant el text:", error);
        setMessage("Error carregant el text.");
      } finally {
        setLoading(false);
      }
    };

    fetchText();
  }, []);

  return (
    <View style={styles.container}>
      {loading ? <ActivityIndicator size="large" color="#0000ff" /> : <Text style={styles.text}>{message}</Text>}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#f5f5f5",
  },
  text: {
    fontSize: 20,
    fontWeight: "bold",
  },
});

export default App;
```

## Executar l'aplicació

```
npm start
```