import matplotlib.pyplot as plt


# Llegir dades guardades en el fitxer
n_values = []
times_1p = []
times_3p = []
times_pool = []


# Obrir el fitxer i llegir les dades
with open("times_pool.txt", "r") as f:

    next(f)  # Ignorar la primera línia (encapçalament)

    for line in f:
        n, t1, t3, tPool = map(float, line.strip().split("\t"))
        n_values.append(int(n))  # Convertir n a enter per a l'eix X
        times_1p.append(t1)
        times_3p.append(t3)
        times_pool.append(tPool)


# Crear gràfic
plt.figure(figsize=(10, 6))
width = 0.35  # Amplada de les barres


# Posicions de les barres
index = range(len(n_values))


# Dibuixar les barres
plt.bar(index, times_1p, width, label="1 process", color="blue")
plt.bar([i + width for i in index], times_3p, width, label="3 processes", color="red")
plt.bar([i + 2 * width for i in index], times_pool, width, label = "Pool", color = "green")


# Afegir etiquetes i títols
plt.xlabel("Nombre de Números (n)")
plt.ylabel("Temps d'Execució (segons)")
plt.title("Comparativa entre Bubble Sort 1 Procés i 3 Processos")
plt.xticks([i + width / 2 for i in index], n_values, rotation=45)  # Afegir etiquetes a l'eix X
plt.legend()


# Ajustar el disseny i mostrar el gràfic
plt.tight_layout()
plt.show()
