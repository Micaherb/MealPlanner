
import java.util.ArrayList;


/**
 * Backtracking algorithm to solve BackTrackingAlgorithm problem
 * @author Obi
 *
 */


public class  BackTrackingAlgorithm {

    MealPlanGraph graph;
    CSPInference inference;
    public BackTrackingAlgorithm(MealPlanGraph graph, CSPInference inference) {
        this.graph = graph;
        this.inference = inference;
    }


/**
     * Run the backtracking algorithm, and return all solutions to the MealPlan as ArrayList of recipe
     * @return ArrayList of ArrayList of recipes
     */

    ArrayList<ArrayList<Recipe>> run() {
        // List of all solutions found for the graph (their string equivalents)
        ArrayList<ArrayList<Recipe>> solutionLists = new ArrayList<ArrayList<Recipe>>();
        // Most recent solution found from RCBS
        MealPlanGraph recentSolution = RCBS(graph);
        // Add singular solution to list
        if (recentSolution != null) {
            solutionLists.add(recentSolution.toList());
        }
        return solutionLists;
    }

/**
     * Get a solution for a given FutoshikiGraph using recursive backtracking. Found solution cannot already be present in solutionLists
     * @param graph given FutoshikiGraph
     * @return solution graph, null if no solution found
     */
    MealPlanGraph RCBS(MealPlanGraph graph) {
        // See if graph is a unique solution
        if (graph.isSolved()) {
            System.out.println("Solution Found");
            return graph;
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
            // Set emptyVertex to test value
            graph.setVertexValue(emptyVertex, possibleRecipe);
            // See if graph is valid given chosen value and fits inference
            if (graph.isValid() && inference.fitsInference(graph)) {
                // Get result of search for chosen emptyVertex value
                MealPlanGraph result = RCBS(graph);
                // If result is not null, it is a solution graph
                if (result != null)
                    return result;
            }
            // Set emptyVertex back to empty value
            graph.setVertexValue(emptyVertex, null);
        }
        return null;
    }

}

