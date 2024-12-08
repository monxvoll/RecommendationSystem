package withinterface.GraphicView.weighted;

import nointerface.app.controller.GraphController;
import nointerface.app.model.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class WeightedView {

    private GraphController graphController;

    public WeightedView(GraphController graphController) {
        this.graphController = graphController;
        crearInterfaz();
    }

    public void crearInterfaz() {
        // Crear el frame principal
        JFrame frame = new JFrame("Ponderados de Productos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Cambiar a DISPOSE_ON_CLOSE
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);  // Centra la ventana

        // Fondo de la ventana
        frame.getContentPane().setBackground(new Color(240, 248, 255));  // Fondo suave azul claro

        // Cargar el grafo desde un archivo JSON
        String filePath = "resources/data.json";  // Cambia esta ruta según el archivo JSON
        graphController.loadGraph(filePath);

        // Obtener los productos ponderados
        List<Map.Entry<Node, Integer>> productWeights = graphController.getProductWeights();

        // Títulos de las columnas
        String[] columnNames = {"Marca", "Ponderado"};

        // Preparar los datos para la tabla
        Object[][] data = new Object[productWeights.size()][2];
        for (int i = 0; i < productWeights.size(); i++) {
            Map.Entry<Node, Integer> entry = productWeights.get(i);
            data[i][0] = entry.getKey().getId();  // Marca del producto
            data[i][1] = entry.getValue();        // Ponderado
        }

        // Crear la tabla con el modelo de datos
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);

        // Centrar los datos en las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Alinear al centro

        // Aplicar el renderizador a todas las columnas
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Estilo para hacer más legible la tabla
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // Personalizar bordes y colores alternos de las filas
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));  // Espaciado entre celdas
        table.setSelectionBackground(new Color(135, 206, 250));  // Fondo azul suave para selección
        table.setSelectionForeground(Color.BLACK);

        // Alternar colores en las filas para mayor legibilidad
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    comp.setBackground(new Color(245, 245, 245));  // Color gris suave para las filas pares
                } else {
                    comp.setBackground(Color.WHITE);  // Blanco para las filas impares
                }
                return comp;
            }
        });

        // Crear un panel de scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Crear un panel para el botón "Salir"
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(new Color(240, 248, 255));

        // Crear el botón "Salir"
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnSalir.setBackground(new Color(64, 158, 255));  // Azul claro
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.setPreferredSize(new Dimension(150, 40));
        btnSalir.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));  // Borde azul
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalir.setBackground(new Color(0, 123, 255));  // Efecto hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalir.setBackground(new Color(64, 158, 255));  // Restaurar color al salir
            }
        });
        btnSalir.addActionListener(e -> frame.dispose()); // Cambiar System.exit(0) a frame.dispose()
        panel.add(btnSalir);

        // Añadir el panel con el botón al frame
        frame.add(panel, BorderLayout.SOUTH);

        // Mostrar la ventana
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphController graphController = new GraphController();
            new WeightedView(graphController);
        });
    }
}
