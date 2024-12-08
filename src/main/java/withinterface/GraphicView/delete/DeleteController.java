package withinterface.GraphicView.delete;

import nointerface.app.controller.GraphController;

public class DeleteController {
    private GraphController graphController;

    public DeleteController(GraphController graphController) {
        this.graphController = graphController;
    }

    public boolean deleteNode(String nodeId) {
        if (graphController.deleteNode(nodeId)) {
            graphController.saveGraph("resources/data.json");
            graphController.loadGraph("resources/data.json");
            return true;
        }
        return false;
    }
}
