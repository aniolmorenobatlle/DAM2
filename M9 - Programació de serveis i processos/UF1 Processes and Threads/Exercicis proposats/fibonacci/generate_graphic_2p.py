import matplotlib.pyplot as plt


# Llegir dades guardades en el fitxer
n_values = []
times_1p = []
times_2p = []


with open("times_2p.txt", 'r') as f:
    for line in f:
        n, t1, t2 = map(float, line.strip().split(','))
        n_values.append(n)
        times_1p.append(t1)
        times_2p.append(t2)


# Crear grafic
plt.figure(figsize=(10, 6))
width = 0.35


# Posicions de les barres
index = range(len(n_values))


plt.bar(index, times_1p, width, label = "1 process", color = "blue")
plt.bar([i + width for i in index], times_2p, width, label = "2 process", color = "red")


# Afegir les etiquetes
plt.xlabel('Valor de Fibonacci (n)')
plt.ylabel('Temps d\'execució (segons)')
plt.title('Comparativa entre Fibonacci 1 procés i 2 processos')
plt.xticks([i + width / 2 for i in index], n_values)  # Posar els valors de n com a etiquetes de l'eix X
plt.legend()


# Mostrar el gràfic
plt.tight_layout()
plt.show()