package app;

import model.GraphManager;
import model.Node;

public class Test {
    public static void main(String[] args) {
        GraphManager graphManager = new GraphManager();

        int nodeCount = 25000;
        int edgeCount = 500000;

        // Generar nodos
        System.out.println("Generando nodos...");
        for (int i = 1; i <= nodeCount; i++) {
            String userId = "user" + i;
            String productId = "product" + i;
            graphManager.addUserRatingForTest(userId, productId, (int) (Math.random() * 5) + 1);
        }

        // Generar aristas adicionales
        System.out.println("Generando aristas...");
        for (int i = 1; i <= edgeCount - nodeCount; i++) {
            String userId = "user" + ((int) (Math.random() * nodeCount) + 1);
            String productId = "product" + ((int) (Math.random() * nodeCount) + 1);
            graphManager.addUserRatingForTest(userId, productId, (int) (Math.random() * 5) + 1);
        }

        // Medir el rendimiento de operaciones clave
        System.out.println("Midiendo el rendimiento...");
        long startTime = System.currentTimeMillis();
        graphManager.recommendProducts(new Node("user1", "user"));
        long endTime = System.currentTimeMillis();

        System.out.println("Tiempo para recomendar productos: " + (endTime - startTime) + " ms");

        // Validar integridad del grafo
        System.out.println("Número de nodos: " + graphManager.getGraph().getNodes().size());
        System.out.println("Número de aristas: " + graphManager.getGraph().getEdges().size());
    }
}
