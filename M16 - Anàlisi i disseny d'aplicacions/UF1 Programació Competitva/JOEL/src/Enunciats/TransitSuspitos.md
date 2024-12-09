Si observem que una IP d'un cert prefix de xarxa interactua consecutivament amb una altra IP amb un prefix de xarxa diferent, pot ser indicatiu d'un **atac lateral**. Això succeeix quan un atacant aconsegueix accés a una màquina d'una xarxa i després intenta moure's lateralment cap a altres xarxes dins d'una organització. Aquesta anàlisi pot ajudar a identificar possibles **forats de seguretat**.

Així doncs volem dissenyar un algoritme que analitzi tot el transit extern que rebem.

#### Entrada

Primera linia, llistat d’adreçes IP separedes per una coma.

Segona linia, prefixes a cercar separats per espai

#### Sortida

IPs analitzades i quantitat de vegades que els dos prefixos de xarxa donats es troben en dues IPs consecutives tal que la primera té el primer prefix i la segona té el segon prefix.

#### Exemple d'entrada 1

Copy

```
203.250.72.120,156.67.135.234,104.26.10.78,8.8.8.8,13.35.15.1,122.160.35.84,135.239.28.102,122.206.91.65,135.0.101.195,157.5.138.54
122 135
```

#### Exemple de sortida 1

Copy

```
10 2
```

#### Exemple d'entrada 2

Copy

```
23.45.67.89,45.67.89.101,123.45.67.89,156.78.90.101,198.51.100.1,203.0.113.5,157.240.221.35,185.199.108.153,91.198.174.192,93.184.216.34,192.168.0.34,172.16.20.47,172.217.10.78,204.79.197.200,13.227.3.68,142.250.180.206,151.101.129.69,104.244.42.1,203.250.72.123,157.67.135.234,104.26.10.78,8.8.8.8,13.35.15.1,35.160.177.68,185.60.216.35,52.85.151.103,99.84.127.53,203.239.28.102,157.206.91.65,203.0.113.195,157.159.138.54,203.216.112.82
```

#### Exemple de sortida 2

Copy

```
30 4
```