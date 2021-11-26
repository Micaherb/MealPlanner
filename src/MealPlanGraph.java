public class MealPlanGraph extends DirectedConstraintGraph {

    /**
     * Edge satisfied if the two vertices have non-equal values
     */
    public class NotEqualEdge extends Edge {
        /**
         * Initialize with start and endVertex
         * @param startVertex start vertex
         * @param endVertex end vertex
         */
        public NotEqualEdge(Vertex<Recipe,?> startVertex, Vertex<Recipe,?> endVertex) {
            super(startVertex, endVertex);
        }

        @Override
        public boolean satisfiesConstraint() {
            return !(getStartVertex().equals(getEndVertex()));
        }
    }

    /**
     * Edge satisfied if the startVertex recipe is vegetarian
     */
    public class IsVegetarianEdge extends Edge {

        public IsVegetarianEdge(Vertex<Recipe, ?> startVertex, Vertex<Recipe, ?> endVertex) {
            super(startVertex, endVertex);
        }

        @Override
        public boolean satisfiesConstraint() {
            // Get Recipes for Start and End vertices
            Recipe startVertexRecipe = getStartVertex().getVal();
            // TODO: Add vegetarian code check here? Or in recipe itself? Implement via for loop, checking that "isVegetarian" is present on each ingredient.
            return true;
        }
    }

    /**
     * Set a vertex's value, and update possible values for connected vertices
     * @param vertex given vertex
     * @param value given value
     * @return whether set was successful or not
     */
    public boolean setVertexValue(Vertex<Recipe, ?> vertex, Recipe value) {
        Vertex<Recipe,?> v = getVertex(vertex);
        v.setVal(value);
        // Update possible values for all vertices connected to the set vertex
        for (Edge edge : getOutgoingEdges(v)) {
            updatePossibleValues(edge.getEndVertex());
        }
        return true;
    }


}
