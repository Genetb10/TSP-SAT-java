/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tsp;

/**
 *
 * @author genes
 */
public class GrafoTSP {
    private int numNodos;      // N: Número de ciudades
    private int[][] matrizDistancias; // Matriz de adyacencia con los costos
    private int k;             // Límite de costo máximo

    // Constructor para inicializar los datos del grafo
    public GrafoTSP(int numNodos, int[][] matrizDistancias, int k) {
        this.numNodos = numNodos;
        this.matrizDistancias = matrizDistancias;
        this.k = k;
    }

    public int getNumNodos() {
        return numNodos;
    }

    public int[][] getMatrizDistancias() {
        return matrizDistancias;
    }

    public int getK() {
        return k;
    }
}
