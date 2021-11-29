public class ForwardChecking implements CSPInference {
    @Override
    public boolean fitsInference(MealPlanGraph graph) {
        for (MealPlanGraph.Vertex v : graph.getVertices()) {
            // If an emptyVertex has no possible values, then the graph is invalid
            if (v.getVal() == null && v.getPossibleValues().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
