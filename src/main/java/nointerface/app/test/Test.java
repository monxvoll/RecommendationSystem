package nointerface.app.test;

import nointerface.app.model.GraphManager;
import nointerface.app.model.Node;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        GraphManager graphManager = new GraphManager();
        Random random = new Random();
        int nodeCount = 25000;
        int edgeCount = 500000;

        // Generar nodos
        System.out.println("Generando nodos...");
        for (int i = 1; i <= nodeCount; i++) {
            String userId = "user" + i;
            String productId = "product" + i;
            graphManager.addUserRatingForTest(userId, productId, random.nextInt(5)+1); //asigna peso aleatorio (1-5)
        }


        System.out.println("Generando aristas...");
        for (int i = 1; i <= edgeCount - nodeCount; i++) { //Se resta ya que arriba ya se añadieron varias aristas.
            String userId = "user" + (random.nextInt(nodeCount) + 1); //Asigna id aleatorias
            String productId = "product" + (random.nextInt(nodeCount) + 1);
            graphManager.addUserRatingForTest(userId, productId, random.nextInt(5) + 1);
        }


        // Medir el rendimiento de operaciones
        System.out.println("Midiendo el rendimiento...");
        long startTime = System.currentTimeMillis();
        /*Ya con el grafo lleno , se crea un nodo para probar cuanto tarda el algoritmo de recomendacion en ejecutarse con todos los productos */
        graphManager.recommendProducts(new Node("user1", "user"));
        long endTime = System.currentTimeMillis();

        System.out.println("Tiempo para recomendar productos: " + (endTime - startTime) + " ms");

        System.out.println("Número de nodos: " + graphManager.getGraph().getNodes().size());
        System.out.println("Número de aristas: " + graphManager.getGraph().getEdges().size());
    }
}
