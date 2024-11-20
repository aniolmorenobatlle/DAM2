import matplotlib.pyplot as plt


# Llegir dades guardades en el fitxer
n_values = []
times_1p = []
times_3p = []


with open("numbers.txt", "r") as f:
    for line in f:
        n, t1, t3 = map(float, line.strip().split("\t"))
        n_values.append(n)
        times_1p.append(t1)
        times_3p.append(t3)


# Crear grafic
plt.figure(figsize=(10, 6))
width = 0.35


# Posicions de les barres
index = range(len(n_values))



plt.bar(index, times_1p, width, label="1 process", color="blue")
plt.bar([i + width for i in index], times_3p, width, label="3 process", color="green")


# Afegir les etiquetes
plt.xlabel("Valor de Bubble Sort (n)")
plt.ylabel("Temps d'execució (segons)")
plt.title("Comparativa entre Bubble Sort 1 procés i 3 processos")
plt.xticks([i + width / 2 for i in index], n_values)  # Posar els valors de n com a etiquetes de l'eix X
plt.legend()


# Mostrar el gràfic
plt.tight_layout()
plt.show()
