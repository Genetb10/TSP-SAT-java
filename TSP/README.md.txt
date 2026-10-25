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

## Requisitos y Dependencias
* **Java Development Kit (JDK)** (versión 8 o superior).
* **Apache NetBeans IDE** (o cualquier entorno Java).
* **Sat4j Core (v2.3.5)**: Incluido en las librerías del proyecto (`org.sat4j.core-2.3.5.jar`).

## Instrucciones de Ejecución
1. Clona o descarga este repositorio en tu computadora.
2. Abre el proyecto en **NetBeans** como un proyecto de Java tradicional (Java with Ant).
3. Asegúrate de que el archivo `org.sat4j.core-2.3.5.jar` esté añadido en la carpeta **Libraries**.
4. Ejecuta la clase **`TSP.java`** para verificar la generación de cláusulas y la resolución del SAT-Solver en la consola.
