# Sistema de Recomendación de Productos 


## Descripción
Este proyecto implementa un sistema de recomendación de productos utilizando grafos. Los nodos representan usuarios y productos, mientras que las aristas representan interacciones como compras, visualizaciones y valoraciones. El sistema es capaz de manejar grandes cantidades de datos y ofrece recomendaciones personalizadas para los usuarios.

## Objetivo
Explorar y comprender las diversas aplicaciones prácticas de los grafos en situaciones reales, destacando la importancia y utilidad que estos tienen, en la resolución de problemas complejos y en la optimización de sistemas.

## Representacion Nodos & Aristas
Los nodos se representan usuarios y productos, mientras que las aristas representan interacciones en este caso, valoraciones. 

**I) Sobre los nodos**: 

- Se identifican con el un parametro _"id"_ y se reprensentan con un _"type"_ que indica si el nodo representa un usuario o un producto

**II) Sobre las aristas**: 

- Relaciones compuestas por dos nodos _"source & target"_ y un peso _"weight"_. El source representa donde empieza la arista (es decir un  nodo usuario), y el target representa donde termina la arista (es decir un producto). El weight (valor entero) cumple el rol de representar la valoracion que da el usuario al producto

## Prerequisitos

Para cargar datos en el sistema, asegurate de tener un archivo JSON con la estructura adecuada. 

*Ejemplo de estructura JSON:*

 ![image](https://github.com/user-attachments/assets/1bcc35f4-ebbe-4d8c-8e7f-741fcc7d90de)

## Ejecucion de Algoritmos

Al iniciar el sistema, se carga automaticamente el grafo desde el archivo json especificado 

*1. Recomendación de productos:*

- Seleccione la opción 1 del menú principal.

- Ingrese el ID del usuario para obtener una lista de productos recomendados.

**- Ejemplo de entrada:** user1

**- Ejemplo de salida:**

  ![image](https://github.com/user-attachments/assets/cc2a4129-21ea-4f90-98bd-e9fb3ea57543)

*2. Mostrar grafo:*

- Seleccione la opción 2 del menú principal para ver una representación textual del grafo.

**- Ejemplo de salida:**

  ![image](https://github.com/user-attachments/assets/1544156f-df51-4e56-9aeb-aa316f9a0198)

*3. Mostrar ponderados de productos:*

- Seleccione la opción 3 del menú principal para ver los productos ponderados por sus calificaciones de mayor a menor.

**- Ejemplo de salida:**

  ![image](https://github.com/user-attachments/assets/cd7bd1bc-633d-401f-b979-7e1fad9ab9df)

 *4. Añadir nuevo usuario y calificación:*

- Seleccione la opción 4 del menú principal.

- Ingrese el ID del nuevo usuario, el ID del producto y la calificación (1-5).

**- Ejemplo de entrada:**

  ![image](https://github.com/user-attachments/assets/25da568d-5b19-4f76-acff-429e7127a09e)

 *5. Borrar nodo:*

- Seleccione la opción 5 del menú principal.

- Ingrese el ID del nodo a borrar.

**- Ejemplo de entrada:**

  ![image](https://github.com/user-attachments/assets/ed10a883-2732-4d96-b8e1-f69a2a23594b)

## Descripción de Funciones

- *addUserRating(String userId, String productId, int rating):*

 Añade una interacción entre un usuario y un producto con una calificación. Si el usuario y/o producto no existen, se crean.

- *recommendProducts(Node user):* 

Recomienda productos no comprados/valorados por un usuario, priorizando aquellos relacionados con usuarios similares o productos populares. Verifica si el usuario existe en el grafo antes de generar recomendaciones.

- *deleteNode(String nodeId):* 

Elimina un nodo y todas sus conexiones del grafo.

- *saveGraph(String filePath):* 

Guarda el estado actual del grafo en un archivo JSON.

- *loadGraph(String filePath):* 

Carga el grafo desde un archivo JSON.

- *displayGraph():* 

Muestra una representación textual del grafo en la consola.

- *getProductWeights():* 

Devuelve una lista de productos y sus ponderaciones basadas en las calificaciones obtenidas.


## Instalación
1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/monxvoll/RecommendationSystem.git
   



