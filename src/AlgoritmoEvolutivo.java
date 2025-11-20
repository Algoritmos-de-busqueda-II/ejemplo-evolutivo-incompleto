import java.util.ArrayList;
import java.util.List;

public class AlgoritmoEvolutivo {

    final Instancia instancia;
    final int tamanioPoblacion;
    final boolean debug;
    private final long maxEvaluaciones;

    public AlgoritmoEvolutivo(Instancia instancia, int tamanioPoblacion, long maxEvaluaciones, boolean debug) {
        this.instancia = instancia;
        this.tamanioPoblacion = tamanioPoblacion;
        this.maxEvaluaciones = maxEvaluaciones;
        this.debug = debug;
    }


    public Solucion run() {
        List<Solucion> poblacion = generarPoblacionInicial();
        Solucion best = evaluarPoblacion(poblacion);
        imprimePoblacion("Población inicial:",poblacion,debug);

        // Cálculo de número de generaciones: se quita la evaluación de la población inicial.
        long generaciones = (maxEvaluaciones / tamanioPoblacion) - 1;
        if (generaciones <= 0) {
            System.out.println("Número de generaciones incorrecto: revisa el ratio maxEvaluaciones/tamanioPoblacion");
            return best;
        }

        for (int i = 0; i < generaciones; i++) {
            // TODO: completar algoritmo

            // Selección de progenitores

            // Operadores de variación
            // -- Cruce de progenitores produce descendientes (offspring)
            // -- Mutación en descendientes

            // Evaluación de descendientes

            // Actualización de best

            // Reemplazo de población

        }


        return best;
    }

    private void imprimePoblacion(String msg, List<Solucion> poblacion, boolean debug) {
        if (debug) {
            System.out.println(msg);
            for (Solucion s : poblacion) {
                System.out.println(s);
            }
        }
    }

    private Solucion evaluarPoblacion(List<Solucion> poblacion) {
        Solucion best = null;
        int bestScore = Integer.MIN_VALUE;
        for (Solucion s : poblacion) {
            int fitness = instancia.evaluar(s);
            if (fitness > bestScore) {
                best = s;
                bestScore = fitness;
            }
            s.setFitness(fitness);
        }
        return best;
    }

    private List<Solucion> generarPoblacionInicial() {
        List<Solucion> poblacion = new ArrayList<>();
        for (int i = 0; i < tamanioPoblacion; i++) {
            poblacion.add(instancia.generarSolucionAleatoria());
        }
        return poblacion;
    }
}
