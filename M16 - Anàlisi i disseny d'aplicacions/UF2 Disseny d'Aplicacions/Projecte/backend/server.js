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
app.get("/text", async (_, res) => {
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
