public interface CSPInference {
    /**
     * Check to see if graph is valid graph according to CSP inference
     * @param graph given graph
     * @return boolean whether graph is valid or not
     */
    boolean fitsInference(MealPlanGraph graph);
}
