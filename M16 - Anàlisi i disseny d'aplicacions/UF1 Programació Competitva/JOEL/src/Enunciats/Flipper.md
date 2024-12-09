T’han fet fora de classe per estar resolent problemes del JOEL quan caldria estar fent una altra tasca, no es la primera vegada que passa i aquest cop el professor t’ha enviat a veure la cap d’estudis. Asseguda al sofà davant del seu despatx observes que al final del passadís hi ha un quadre que conté una graella amb quatre números:

<table style="border-collapse: collapse; margin: auto;"><tbody><tr><td style="border: 1px solid; text-align: center; width: 400px;"><img alt="quadre" src="https://www.dropbox.com/scl/fi/v2w4naeok5w3tfnxqf4p4/Flipp.png?rlkey=khzgh8st4dco3c7xmvhyrtu6i&amp;dl=1" style="float: right; vertical-align: top;"></td></tr></tbody></table>

El temps passa, el quadre et te flipat i encara que saps que això pot comportar una altra sanció, treus el mòbil per fer-te una selfie.

Quan et mires la foto que has fet observes que l’efecte mirall del mòbil fa que el quadre es vegi diferent i et preguntes com es veuria movent-lo horitzontalment o verticalment.

Concretament, fent un gir "horitzontal" (a través de la línia central horitzontal) faria que es mostrés de la següent manera:

<table style="border-collapse: collapse; margin: auto;"><tbody><tr><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">3</td><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">4</td></tr><tr><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">1</td><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">2</td></tr></tbody></table>

Un gir "vertical" (a través de la línia central vertical) faria que es mostrés de la següent manera:

<table style="border-collapse: collapse; margin: auto;"><tbody><tr><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">2</td><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">1</td></tr><tr><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">4</td><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">3</td></tr></tbody></table>

Determina l'orientació final dels números del quadre després d'una seqüència de girs horitzontals i verticals.

La posició inicial del quadre sempre serà:

<table style="border-collapse: collapse; margin: auto;"><tbody><tr><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">1</td><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">2</td></tr><tr><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">3</td><td style="border: 1px solid black; padding: 20px; text-align: center; width: 20px; height: 20px; font-size: 24px;">4</td></tr></tbody></table>

#### Entrada

L'entrada consta d'una línia, composta per una seqüència d'almenys un i com a màxim **1.000.000** caràcters. Cada caràcter és o `H`, que representa un gir horitzontal, o `V`, que representa un gir vertical.

#### Sortida

Mostra la orientació final dels quatre nombres. Concretament, cadascuna de les dues línies de sortida ha de contenir dos nombres enters, separats per un espai.

#### Exemple d'Entrada 1

Copy

```
HV
```

#### Exemple de Sortida 1

Copy

```
4 3
2 1
```

#### Exemple d'Entrada 2

Copy

```
VVHH
```

#### Exemple de Sortida 2

Copy

```
1 2
3 4
```