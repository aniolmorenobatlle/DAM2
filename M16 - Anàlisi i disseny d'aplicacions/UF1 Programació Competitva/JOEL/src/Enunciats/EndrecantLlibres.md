La Valentina vol que els llibres d'un prestatge estiguin disposats d'una manera concreta. Cada vegada que veu un prestatge de llibres, reordena els llibres de manera que apareixen tots els llibres grans a l'esquerra, seguits de tots els llibres de mida mitjana, i després tots els llibres petits a la dreta. Ho fa triant repetidament dos llibres i intercanviant-ne la ubicació. L'intercanvi de les ubicacions de dos llibres s'anomena permuta.

Ajuda a la Valentina a determinar el menor nombre de permutes necessaries per organitzar un prestatge de llibres tal com ella vol.

#### Entrada

L'entrada constarà exactament d'una línia que contindrà almenys un caràcter, els caràcters que apareixeran seran `G` per representar llibres grans, `M` corresponent a llibres mitjans o `P` per als llibres petits.

#### Sortida

Mostra un sol enter que correspongui al nombre mínim possible de permutes necessaries per organitzar els llibres de manera que totes les aparicions de `G` vinguin primer, seguides de totes les de `M` i després totes les `P`.

#### Exemple d'Entrada 1

Copy

```
GMMMP
```

#### Exemple de Sortida 1

Copy

```
0
```

##### Explicació de l'exemple 1

Els llibres ja estan endreçats segons les normes de la Valentina.

#### Exemple d'Entrada 2

Copy

```
GGPGM
```

#### Exemple de Sortida 2

Copy

```
2
```

##### Explicació de l'exemple 2

Podem començar permutant la `P` i la `M`, obtenim `GGMGP`. A continuació, es pot permutar la `M` amb la `G` que hi ha a la seva dreta. D'aquesta manera fent dos permutes podem organitzar els llibres segons els desitjos de la Valentina. No és possible utilitzar menys permutes perquè tant `P` com `M` s'han de moure, però l'ús d'una sola permuta entre aquests dos no deixa els llibres disposats com la Valentina vol que estiguin.