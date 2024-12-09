La Bàrbara planta **N** gira-sols diferents, cadascun amb una alçada única, ordenats de més petit a més gran. Cada dia, tots els girasols creixen i es fan més alts que el dia anterior.

La Bàrbara registra les alçades dels girasols durant **N** dies consecutius en una taula, amb una fila per a cada planta, amb la primera fila registrant el creixement del gira-sol més baix i l'última fila el creixement del gira-sol més alt.

La columna més a l'esquerra és la primera mesura per a cada gira-sol, i la columna més a la dreta és l'última mesura per a cada gira-sol.

Si un gira-sol era més petit que un altre quan es va plantar inicialment, continua sent més petit cada cop que es mesura.

Malauradament, els fills de la Bàrbara son molt entremaliats i poden haver alterat la seva taula fent-la girar un múltiple de 90 graus.

La teva feina és ajudar a la Bàrbara a determinar les seves dades originals.

#### Entrada

La primera línia d'entrada conté el número **N**. Les següents **N** línies contenen cadascuna **N** enters positius, cadascun dels quals és com a màxim **10<sup>9</sup>**. Es garanteix que la graella d'entrada representa una versió girada de la graella de la Bàrbara.

**_2 ≤ N ≤ 100_**

#### Sortida

Mostra les dades originals de Bàrbara, que consisteixen en **N** línies, cadascuna de les quals conté **N** nombres enters positius.

#### Exemple d'Entrada 1

Copy

```
2
1 3
2 9
```

#### Exemple de Sortida 1

Copy

```
1 3
2 9
```

##### Explicació de l'exemple 1

Les dades s'han girat un múltiple de 360 graus, el que significa que la disposició d'entrada és la disposició original.

#### Exemple d'Entrada 2

Copy

```
3
4 3 1
6 5 2
9 7 3
```

#### Exemple de Sortida 2

Copy

```
1 2 3
3 5 7
4 6 9
```

##### Explicació de l'exemple 2

Les dades originals es van girar 90 graus cap a la dreta en el sentit de les agulles del rellotge.

#### Exemple d'Entrada 3

Copy

```
3
3 7 9
2 5 6
1 3 4
```

#### Exemple de Sortida 3

Copy

```
1 2 3
3 5 7
4 6 9
```

##### Explicació de l'exemple 3

Les dades originals es van girar 90 graus cap a l'esquerra en sentit contrari a les agulles del rellotge.