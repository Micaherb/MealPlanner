import java.util.ArrayList;

/**
 * A DirectedConstraintGraph whose vertices contain Recipes, and are distinguished from one another by Integers.
 */
public class MealPlanGraph extends DirectedConstraintGraph<Recipe, Integer> {
    /**
     * Initialize with the list of all possible recipe values a given vertex can become
     * @param ALL_POSSIBLE_VALUES list of all possible values
     */
    public MealPlanGraph(ArrayList<Recipe> ALL_POSSIBLE_VALUES) {
        super(ALL_POSSIBLE_VALUES);
    }

    /**
     * Vertex of the MealPlanGraph
     */
    public class MealVertex extends Vertex {
        public MealVertex(Integer id, Recipe val, ArrayList<Recipe> possibleValues) {
            super(id, val, possibleValues);
        }
    }


    /**
     * Add a Vertex to the graph.
     * @return the Vertex added
     */
    public Vertex addVertex(Integer id, Recipe val, ArrayList<Recipe> possibleValues) {
        MealVertex vertex = new MealVertex(id, val, possibleValues);
        vertexList.add(vertex);
        return vertex;
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

    public void createNotEqualEdge(int i1, int i2) {
        Edge e = new NotEqualEdge(vertexList.get(i1), vertexList.get(i2));
        addEdge(e);
    }

    /**
     * Edge that checks that both start and end vertex are of a given recipe type.
     */
    public class BothGivenRecipeTypeEdge extends Edge {
        Recipe.RecipeType recipeType;
        public BothGivenRecipeTypeEdge(DirectedConstraintGraph<Recipe, Integer>.Vertex startVertex, DirectedConstraintGraph<Recipe, Integer>.Vertex endVertex, Recipe.RecipeType recipeType) {
            super(startVertex, endVertex);
            this.recipeType = recipeType;
        }

        @Override
        public boolean violatesConstraint() {
            boolean startIsRecipeType = (startVertex.val.getRecipeType() == recipeType);
            boolean endIsRecipeType = (endVertex.val.getRecipeType() == recipeType);
            return !(startIsRecipeType && endIsRecipeType);
        }
    }

    public void createBothGivenRecipeTypeEdge(int i1, int i2, Recipe.RecipeType type) {
        Edge e = new BothGivenRecipeTypeEdge(vertexList.get(i1), vertexList.get(i2), type);
        addEdge(e);
    }

    /**
     * Get all the Recipe values for the vertices in one string
     * @return the one string of all the recipe values
     */
    public String getVertexRecipesAsString() {
        StringBuilder returnStringBuilder = new StringBuilder();
        for (Vertex v : getVertices()) {
            returnStringBuilder.append(v.getVal() + ", ");
        }
        return returnStringBuilder.toString();
    }
}
