package nointerface.app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

// Clase Edge para representar relaciones entre nodos
public class Edge implements Serializable {
    @SerializedName("source")
    private Node source; // (Origen) nodo donde empieza la arista, es decir que representa el punto de partida donde empieza una interacción
    @SerializedName("target")
    private Node target; // (Destino) donde termina la arista, como tal es el punto final de una interacción
    @SerializedName("weight")
    private int weight; // En este caso por ejemplo, una valoración

    // Constructor
    public Edge(Node source, Node target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    // Getters
    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source.getId() + " -> " + target.getId() + " [" + weight + "]";
    }
}
