Un artista emergent ho està petant perque té una manera única de pintar. La seva técnica és utilitzar un llenç de **_M_**\-per-**_N_** que inicialment és completament negre. Aleshores, l'artista tria repetidament una fila o columna i passa el seu pinzell màgic al llarg de la fila o columna. El pinzell canvia el color de cada cel·la de la fila o columna de negre a daurat o de daurat a negre. Tenint en compte els moviments de l'artista, la teva tasca és determinar quantes cel·les daurades apareixeran finalment en el llenç.

#### Entrada

La primera línia d'entrada es un nombre enter positiu **_M_**.

La segona línia d'entrada es un nombre enter positiu **_N_**.

La tercera línia d'entrada es un nombre enter positiu **_K_**.

La resta **_K_** línies descriuen les eleccions fetes per l'artista. Cadascuna d'aquestes línies serà `R`(de row en anglès) seguida d'un únic espai i després un nombre enter que és un número de fila, o `C` seguida d'un únic espai i després un nombre enter que és un número de columna. Les files es numeren de dalt a baix des de **1** fins a **_M_**. Les columnes estan numerades d'esquerra a dreta des de **1** fins a **_N_**.

**_M N ≤ 5.000.000_** , **_K ≤ 1.000.000_**

#### Sortida

Mostra el nombre de cel·les daurades que hi ha en el llenç despres de totes les passades de pinzell.

#### Exemple d'Entrada 1

Copy

```
3
3
2
R 1
C 1
```

#### Exemple de Sortida 1

Copy

```
4
```

##### Explicació de l'exemple 1

Després de passar el pinzell per la primera fila, el llenç té aquest aspecte (D daurat,N negre):

Copy

```
DDD
NNN
NNN
```

Després de passar el pinzell per la segona fila, el llenç té aquest aspecte, corresponent al resultat final:

Copy

```
NDD
DNN
DNN
```

#### Exemple d'Entrada 2

Copy

```
4
5
7
R 3
C 1
C 2
R 2
R 2
C 1
R 4
```

#### Exemple de Sortida 2

Copy

```
10
```

##### Explicació de l'exemple 2

Després de totes les passades de pinzell, el llenç te 10 cel·les de color negre(D daurat,N negre).

Copy

```
NDNNN
NDNNN
DNDDD
DNDDD
```