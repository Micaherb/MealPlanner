/*
import java.util.ArrayList;

*/
/**
 * Backtracking algorithm to solve BackTrackingAlgorithm problem
 * @author Obi
 *
 *//*


public class BackTrackingAlgorithm {

    DirectedConstraintGraph<Recipe,Integer> graph;
    CSPInference inference;
    public BackTrackingAlgorithm(DirectedConstraintGraph<Recipe,Integer> graph, CSPInference inference) {
        this.graph = graph;
        this.inference = inference;
    }

    */
/**
     * Run the backtracking algorithm, and return all solutions to the Futoshiki game as their string equivalents
     * @return
     *//*

    ArrayList<ArrayList<Recipe>> run() {
        // List of all solutions found for the graph (their string equivalents)
        ArrayList<ArrayList<Recipe>> solutionLists = new ArrayList<ArrayList<Recipe>>();
        // Make mutable graph
        MealPlanGraph mutableGraph = new MealPlanGraph(graph.ALL_POSSIBLE_VALUES);
        // Add vertex to mutableGraph for all vertices in graph
        for (int i = 0; i < graph.getVertices().size(); i++) {
            mutableGraph.addVertex()
        }
        // Most recent solution found from RCBS
        MealPlanGraph recentSolution = RCBS(mutableGraph, solutionLists);
        // Continue finding unique solutions until no more solutions can be found
        while (recentSolution != null) {
            // Reset mutable graph to original graph
            mutableGraph = new MealPlanGraph(graph);
            // Update solutionStrings with most recent solution found
            solutionLists.add(recentSolution.toList());
            // Search for another solution
            recentSolution = RCBS(mutableGraph, solutionLists);
        }
        return solutionLists;
    }
    */
/**
     * Get a solution for a given FutoshikiGraph using recursive backtracking. Found solution cannot already be present in solutionLists
     * @param graph given FutoshikiGraph
     * @param solutionLists list of solutions already found for graph
     * @return solution graph, null if no solution found
     *//*

    MealPlanGraph RCBS(MealPlanGraph graph, ArrayList<ArrayList<Recipe>> solutionLists) {
        //	System.out.println("For (0,1): " + graph.getVertex(0, 1).possibleValuesToString());
        //	System.out.print(graph.toString());
        // See if graph is a unique solution
        if (graph.isSolved() && !solutionLists.contains(graph.toList()))
            return graph;
        // Graph is solved, but not unique
        if (graph.isSolved() && solutionLists.contains(graph.toList()))
            return null;

        // Get a vertex without a value
        MealPlanGraph.Vertex emptyVertex = graph.getEmptyVertex();
        // Get a list of all possible values to check for this vertex
        ArrayList<Recipe> possibleValuesToCheck = new ArrayList<Recipe>(emptyVertex.getPossibleValues());
        for (Recipe possibleRecipe : possibleValuesToCheck) {
            //		System.out.println("For (0,1): " + graph.getVertex(0, 1).possibleValuesToString() + "\n");
            // Set emptyVertex to test value
            graph.setVertexValue(emptyVertex, possibleRecipe);
            // See if graph is valid given chosen value and fits inference
            //	if (graph.toString().equals("5 4 3 2 1 \n4 3 1 5 2 \n2 1 4 3 5 \n3 5 2 1 4 \n1 2 5 4 3 \n")) {
            //		System.out.println("Da Solution");
            //	}
            if (graph.isValid() && inference.fitsInference(graph)) {
                // Get result of search for chosen emptyVertex value
                //		System.out.println("For (0,1): " + graph.getVertex(0, 1).possibleValuesToString() + "\n");
                MealPlanGraph result = RCBS(graph, solutionLists);
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
*/
