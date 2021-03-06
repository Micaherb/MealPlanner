
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Backtracking algorithm to solve BackTrackingAlgorithm problem
 * @author Obi
 *
 */


public class  BackTrackingAlgorithm {

    MealPlanGraph graph;
    CSPInference inference;
    IngredientCollection ingredients;
    public BackTrackingAlgorithm(MealPlanGraph graph, CSPInference inference, HashMap<String, Ingredient> totalIngredients) {
        this.graph = graph;
        this.inference = inference;
        this.ingredients = new IngredientCollection(totalIngredients);
    }


/**
     * Run the backtracking algorithm, and return all solutions to the MealPlan as ArrayList of recipe
     * @return ArrayList of ArrayList of recipes
     */

    ArrayList<String> run() {
        // List of all solutions found for the graph (their string equivalents)
        return RCBS(graph);
    }

/**
     * Get a solution for a given FutoshikiGraph using recursive backtracking. Found solution cannot already be present in solutionLists
     * @param graph given FutoshikiGraph
     * @return solution graph, null if no solution found
     */
    ArrayList<String> RCBS(MealPlanGraph graph) {
        ArrayList<String> solutionStrings = new ArrayList<String>();
        // See if graph is a unique solution
        if (graph.isSolved()) {
            solutionStrings.add(graph.getVertexRecipesAsString());
            return solutionStrings;
        }
        // Get a vertex without a value
        MealPlanGraph.Vertex emptyVertex = graph.getEmptyVertex();

        // If no empty vertices left, return null.
        if (emptyVertex == null) {
            return null;
        }
        // Get a list of all possible values to check for this vertex
        ArrayList<Recipe> possibleValuesToCheck = new ArrayList<Recipe>(emptyVertex.getPossibleValues());
        for (Recipe possibleRecipe : possibleValuesToCheck) {
            //Check if ingredients are available
            if(ingredients.hasIngredients(possibleRecipe)) {
                // Set emptyVertex to test value
                graph.setVertexValue(emptyVertex, possibleRecipe);
                // See if graph is valid given chosen value and fits inference
                if (graph.isValid() && inference.fitsInference(graph)) {
                    ingredients.removeIngredients(possibleRecipe);
                    // Get result of search for chosen emptyVertex value
                    ArrayList<String> result = RCBS(graph);
                    // If result is not null, it is a solution graph
                    if (result != null) {
                        solutionStrings.addAll(result);
                    }
                    ingredients.addIngredients(possibleRecipe);
                }
                // Set emptyVertex back to empty value
                graph.setVertexValue(emptyVertex, null);
            }
        }
        return solutionStrings;
    }

}

