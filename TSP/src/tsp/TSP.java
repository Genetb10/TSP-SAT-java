/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tsp;

/**
 *
 * @author genes
 */
import java.util.Scanner;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.Reader;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;

public class TSP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== RESOLVEDOR DE TSP MEDIANTE SAT ===");
        
        // 1. Leer el número de nodos
        System.out.print("Ingrese el numero de nodos (N): ");
        int numNodos = scanner.nextInt();

        // 2. Leer el umbral de costo K
        System.out.print("Ingrese el umbral de costo maximo (K): ");
        int k = scanner.nextInt();

        // 3. Leer la matriz de adyacencia
        int[][] matrizDistancias = new int[numNodos][numNodos];
        System.out.println("Ingrese la matriz de distancias (" + numNodos + "x" + numNodos + "):");
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                System.out.print("Distancia de nodo " + i + " a nodo " + j + ": ");
                matrizDistancias[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        try {
            // 4. Instanciar el grafo y generar la reducción a CNF
            GrafoTSP grafo = new GrafoTSP(numNodos, matrizDistancias, k);
            ReduccionCNF reduccion = new ReduccionCNF(grafo);
            
            String archivoCNF = "instancia_tsp.cnf";
            reduccion.generarArchivoCNF(archivoCNF);
            System.out.println("Archivo CNF generado con exito: " + archivoCNF);

            // 5. Ejecutar el SAT-Solver (Sat4j) para evaluar el archivo generado
            System.out.println("Ejecutando Sat4j Solver...");
            ISolver solver = SolverFactory.newDefault();
            solver.setTimeout(3600); // 1 hora de timeout máximo
            Reader reader = new DimacsReader(solver);
            
            IProblem problem = reader.parseInstance(archivoCNF);
            
            if (problem.isSatisfiable()) {
                System.out.println(" RESULTADO: SATISFIABLE (SAT)");
                System.out.println("Si existe una ruta valida con un costo menor o igual a K!");
                // Opcional: mostrar el modelo asignado si lo deseas
                // int[] model = problem.model();
                // reader.printModel(model);
            } else {
                System.out.println("RESULTADO: UNSATISFIABLE (UNSAT)");
                System.out.println("NO existe ninguna ruta que cumpla con el costo maximo K.");
            }

        } catch (Exception e) {
            System.err.println("Error durante la ejecucion del solver: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
