# Reducción del Problema del Viajante TSP a SAT

Proyecto desarrollado en Java que implementa la reducción polinómica del Problema del Viajante al problema de Satisfactibilidad Booleana (SAT), utilizando la librería *Sat4j* para la resolución de instancias CNF en formato DIMACS.

## Estructura del Proyecto

El proyecto está organizado en tres clases principales:
* **`GrafoTSP.java`**: Modela el grafo de entrada con las ciudades, la matriz de adyacencia de costos y el límite máximo permitido ($K$).
* **`ReduccionCNF.java`**: Contiene la lógica de reducción que genera las cláusulas en forma normal conjuntiva (CNF) asegurando:
  1. Que cada ciudad sea visitada exactamente en un paso.
  2. Que en cada paso del recorrido haya exactamente una ciudad.
  3. Que no se superen los costos permitidos ($K$).
* **`TSP.java` (Clase Principal)**: Orquesta la ejecución, genera el archivo DIMACS (`instancia_tsp.cnf`) y ejecuta el solver Sat4j para determinar si la instancia es **SAT** o **UNSAT**.

