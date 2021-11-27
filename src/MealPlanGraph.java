import java.util.ArrayList;

/**
 * A DirectedConstraintGraph whose vertices contain Recipes, and are distinguished from one another by Integers.
 */
public class MealPlanGraph extends DirectedConstraintGraph<Recipe, Integer> {

    public MealPlanGraph(ArrayList<Recipe> ALL_POSSIBLE_VALUES) {
        super(ALL_POSSIBLE_VALUES);
    }

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
        public boolean violatesConstraint() {
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
        public boolean violatesConstraint() {
            // Get Recipes for Start and End vertices
            Recipe startVertexRecipe = getStartVertex().getVal();
            // TODO: Add vegetarian code check here? Or in recipe itself? Implement via for loop, checking that "isVegetarian" is present on each ingredient.
            return true;
        }
    }


}
