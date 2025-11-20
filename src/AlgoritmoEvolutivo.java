import java.util.ArrayList;
import java.util.Collections;
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

            // Selección de progenitores
            List<Solucion> progenitores = seleccionProgenitores(poblacion);

            // Operadores de variación
            // -- Cruce de progenitores produce descendientes (offspring)
            List<Solucion> offspring = cruce(progenitores);
            // -- Mutación en descendientes
            mutacion(offspring);

            // Evaluación de descendientes
            Solucion bestOffspring = evaluarPoblacion(offspring);

            // Actualización de best
            if (bestOffspring.compareTo(best) < 0) {
                best = bestOffspring;
            }

            // Reemplazo de población
            poblacion = reemplazo(poblacion,offspring);
        }

        return best;
    }

    private List<Solucion> seleccionProgenitores(List<Solucion> poblacion) {
        // TODO: implementar método de selección
        return poblacion;
    }

    private List<Solucion> cruce(List<Solucion> progenitores) {
        List<Solucion> offspring = new ArrayList<>();

        // TODO: implementar método de cruce
        for  (int i = 0; i < progenitores.size(); i++) {
            offspring.add(new Solucion(progenitores.get(i)));
        }

        return offspring;
    }

    private void mutacion(List<Solucion> offspring) {
        // TODO: implementar mutación
    }

    private List<Solucion> reemplazo(List<Solucion> poblacion, List<Solucion> offspring) {
        // TODO: implementar reemplazo
        return offspring;
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
