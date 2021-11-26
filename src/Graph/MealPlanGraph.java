package Graph;

public class MealPlanGraph extends DirectedConstraintGraph {

    // Eventually: Set up a comparator class for a generic comparison Edge.
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
}
