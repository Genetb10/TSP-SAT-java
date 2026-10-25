/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tsp;

/**
 *
 * @author genes
 */

import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.specs.ISolver;

public class TSP {
    public static void main(String[] args) {
        int numNodos = 3;
        int k = 10; // Costo máximo permitido
        
        int[][] matrizDistancias = {
            {0, 2, 5},
            {2, 0, 3},
            {5, 3, 0}
        };

        GrafoTSP grafo = new GrafoTSP(numNodos, matrizDistancias, k);
        ReduccionCNF reduccion = new ReduccionCNF(grafo);
        String archivoCnf = "instancia_tsp.cnf";

        try {
            // Fase 1: Generar reducción a CNF
            reduccion.generarArchivoCNF(archivoCnf);
            
            // Fase 2: Integración y ejecución con Sat4j
            ISolver solver = SolverFactory.newDefault();
            solver.setTimeout(3600); // 1 hora de timeout máximo
            DimacsReader reader = new DimacsReader(solver);
            
            org.sat4j.specs.IProblem problem = reader.parseInstance(archivoCnf);
            
            System.out.println("Ejecutando SAT-Solver...");
            if (problem.isSatisfiable()) {
                // Fase 3: Si es SAT, se encontró una ruta válida
                System.out.println("SATISFIABLE (SAT)! Si existe una ruta con costo menor o igual a K.");
                int[] model = problem.model();
                System.out.print("Modelo asignado por el solver: ");
                for (int var : model) {
                    if (var > 0) System.out.print("X" + var + " ");
                }
                System.out.println();
            } else {
                // Fase 3: Si es UNSAT
                System.out.println("NO existe ninguna ruta con costo menor o igual a K (UNSAT).");
            }
            
        } catch (Exception e) {
            System.err.println("Error durante la ejecucion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}