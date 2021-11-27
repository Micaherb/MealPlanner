/**
 * A DirectedConstraintGraph whose vertices contain Recipes, and are distinguished from one another by Integers.
 */
public class MealPlanGraph extends DirectedConstraintGraph<Recipe, Integer> {

    /**
     * Edge satisfied if the two vertices have non-equal values
     */
    public class NotEqualEdge extends Edge {
        /**
         * Initialize with start and endVertex
         * @param startVertex start vertex
         * @param endVertex end vertex
         */
        public NotEqualEdge(Vertex startVertex, Vertex endVertex) {
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

        public IsVegetarianEdge(Vertex startVertex, Vertex endVertex) {
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
    public boolean setVertexValue(Vertex vertex, Recipe value) {
        Vertex v = getVertex(vertex);
        v.setVal(value);
        // Update possible values for all vertices connected to the set vertex
        for (Edge edge : getOutgoingEdges(v)) {
            updatePossibleValues(edge.getEndVertex());
        }
        return true;
    }


}
