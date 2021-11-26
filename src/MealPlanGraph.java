import Graph.DirectedConstraintGraph;

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
        public NotEqualEdge(Vertex<?, ?> startVertex, Vertex<?, ?> endVertex) {
            super(startVertex, endVertex);
        }

        @Override
        public boolean satisfiesConstraint() {
            return !(getStartVertex().equals(getEndVertex()));
        }
    }

    /**
     * Edge satisfied if the two vertices have recipes that are vegetarian
     */
    public class IsVegetarianEdge extends Edge {

        public IsVegetarianEdge(Vertex<Recipe, ?> startVertex, Vertex<Recipe, ?> endVertex) {
            super(startVertex, endVertex);
        }

        @Override
        public boolean satisfiesConstraint() {

            return true;
        }
    }


}
