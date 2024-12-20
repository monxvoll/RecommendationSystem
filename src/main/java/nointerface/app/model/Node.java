package nointerface.app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

// Clase Node  para representar usuarios y productos
public class Node implements Serializable {
    @SerializedName("id") // Util para decirle a gson que ese campo que esta en el json corresponde a la variable id
    private String id; // Para identificar el nodo "Es decir el nombre que le damos"
    @SerializedName("type")
    private String type; // El tipo que representará un "user" or "product".

    // Constructor
    public Node(String id, String type) {
        this.id = id;
        this.type = type;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + " (" + type + ")";
    }
}
