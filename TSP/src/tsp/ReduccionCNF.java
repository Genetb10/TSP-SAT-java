/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tsp;

/**
 *
 * @author genes
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReduccionCNF {

    private GrafoTSP grafo;
    private List<String> clausulas;

    public ReduccionCNF(GrafoTSP grafo) {
        this.grafo = grafo;
        this.clausulas = new ArrayList<>();
    }

    // Mapea la variable X(i, t) a un número entero único para DIMACS
    private int var(int i, int t, int n) {
        return i * n + t + 1;
    }

    public void generarArchivoCNF(String nombreArchivo) throws IOException {
        int n = grafo.getNumNodos();
        int[][] matriz = grafo.getMatrizDistancias();
        int k = grafo.getK();

        clausulas.clear();

        // REGLA 1: Cada ciudad debe ser visitada exactamente en un paso
        for (int i = 0; i < n; i++) {
            StringBuilder clausula1 = new StringBuilder();
            for (int t = 0; t < n; t++) {
                clausula1.append(var(i, t, n)).append(" ");
            }
            clausula1.append("0");
            clausulas.add(clausula1.toString());

            // Mutuamente excluyentes (no puede visitarse dos veces en pasos distintos)
            for (int t1 = 0; t1 < n; t1++) {
                for (int t2 = t1 + 1; t2 < n; t2++) {
                    clausulas.add("-" + var(i, t1, n) + " -" + var(i, t2, n) + " 0");
                }
            }
        }

        // REGLA 2: En cada paso del recorrido debe haber exactamente una ciudad
        for (int t = 0; t < n; t++) {
            StringBuilder clausula2 = new StringBuilder();
            for (int i = 0; i < n; i++) {
                clausula2.append(var(i, t, n)).append(" ");
            }
            clausula2.append("0");
            clausulas.add(clausula2.toString());

            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = i1 + 1; i2 < n; i2++) {
                    clausulas.add("-" + var(i1, t, n) + " -" + var(i2, t, n) + " 0");
                }
            }
        }

        // REGLA 3: Restricción de Costo (Si la distancia supera K, se prohíbe la transición)
        for (int t = 0; t < n; t++) {
            int siguienteT = (t + 1) % n; // El paso siguiente (regresa al origen al final)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && matriz[i][j] > k) {
                        clausulas.add("-" + var(i, t, n) + " -" + var(j, siguienteT, n) + " 0");
                    }
                }
            }
        }

        int numVariables = n * n;

        // Escribir el archivo en formato DIMACS CNF
        BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
        writer.write("p cnf " + numVariables + " " + clausulas.size() + "\n");
        for (String c : clausulas) {
            writer.write(c + "\n");
        }
        writer.close();
        
        System.out.println("Reduccion completada. Clausulas generadas: " + clausulas.size());
    }
}
