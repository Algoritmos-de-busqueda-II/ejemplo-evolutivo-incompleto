import java.util.Arrays;

public class Solucion implements Comparable<Solucion>{

    private int[] valores;
    private int fitness;

    public Solucion(int n) {
        this.valores = new int[n];
    }

    // Constructor copia
    public Solucion(Solucion solucion) {
        this.fitness = solucion.fitness;
        this.valores = new int[solucion.valores.length];
        for (int i = 0; i < solucion.valores.length; i++) {
            this.valores[i] = solucion.valores[i];
        }
    }

    public void put(int i, int valor) {
        valores[i] = valor;
    }

    @Override
    public String toString() {
        return "Fitness: " + fitness +
                " - Genotipo = " + Arrays.toString(valores);
    }

    public int get(int i) {
        return valores[i];
    }

    public void setFitness(int valorFitness) {
        fitness = valorFitness;
    }

    @Override
    public int compareTo(Solucion o) {
        // Maximizando para ordenar de mayor a menor valor de fitness
        if (this.fitness < o.fitness) {
            return 1;
        }
        if (this.fitness > o.fitness) {
            return -1;
        }
        return 0;
    }

}
