import java.util.ArrayList;

/**
 * Forward checking is baked in via each Vertex having a list of possible values
 */
public abstract class DirectedConstraintGraph {
    /** The list of all vertices */
    ArrayList<Vertex<Recipe,?>> vertexList;
    /** The list of all edges */
    ArrayList<Edge> edgeList;

    /**
     * Abstract class for a Vertex
     * @param <T> Type of value that Vertex stores. Must be comparable
     * @param <U> Type of id by which Vertex can be identified. Must be comparable
     */
    public abstract class Vertex<T extends Comparable<T>, U extends Comparable<U>> {
        T val;
        ArrayList<T> possibleValues;
        U id;
        public Vertex(U id, T val, ArrayList<T> possibleValues) {
            this.id = id;
            this.val = val;
            this.possibleValues = possibleValues;
        }

        @Override
        public boolean equals(Object o) {
            try {
                if (this.getClass() != o.getClass())
                    return false;
                Vertex<Recipe,?> v = (Vertex<Recipe,?>) o;
                return this.id.equals(v.id);
            } catch (Exception e) {
                return false;
            }
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }
    }

    /**
     * Abstract class for an Edge.
     * Contains abstract method for evaluating edge constraint
     */
    public abstract class Edge {
        Vertex<Recipe,?> startVertex;
        Vertex<Recipe,?> endVertex;
        public Edge(Vertex<Recipe,?> startVertex, Vertex<Recipe,?> endVertex) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
        }

        public Vertex<Recipe,?> getStartVertex() {
            return startVertex;
        }
        public Vertex<Recipe,?> getEndVertex() {
            return endVertex;
        }

        /**
         * See if edge constraint is satisfied for given vertices
         * @return ture if constraint satisfied, false otherwise.
         */
        public abstract boolean satisfiesConstraint();

        /**
         * Return if this edge's vertices have assigned values or not
         * @return true if vertices have values, false otherwise
         */
        public boolean verticesHaveAssignedValues() {
            return startVertex.val != null && endVertex.val != null;
        }
    }

    /**
     * Get all outgoing edges for a given Vertex
     * @param givenVertex the given Vertex
     * @return ArrayList of outgoing edges from the Vertices
     */
    public ArrayList<Edge> getOutgoingEdges(Vertex<Recipe,?> givenVertex) {
        ArrayList<Edge> returnList = new ArrayList<Edge>();
        for (Edge e : edgeList) {
            if (e.getStartVertex().equals(givenVertex))
                returnList.add(e);
        }
        return returnList;
    }

    /**
     * Add a Vertex to the graph.
     * @param vertex Vertex to add to the graph
     * @return the Vertex added
     */
    public Vertex<Recipe,?> addVertex(Vertex<Recipe,?> vertex) {
        vertexList.add(vertex);
        return vertex;
    }

    /**
     * Add an Edge to the graph.
     * @param edge Edge to add to the graph
     * @return the Edge added
     */
    public Edge addEdge(Edge edge) {
        edgeList.add(edge);
        return edge;
    }

    /**
     * Get the list of all Vertices
     * @return list of all vertices
     */
    public ArrayList<Vertex<Recipe,?>> getVertices() {
        return vertexList;
    }

    /**
     * Get the list of all Edges
     * @return list of all edges
     */
    public ArrayList<Edge> getEdges() {
        return edgeList;
    }

    /**
     * Get an empty vertex from the collection of vertices
     * @return Vertex with empty value. Returns null if no empty vertex found
     */
    public Vertex<Recipe,?> getEmptyVertex() {
        for (Vertex<Recipe,?> vertex : vertexList) {
            if (vertex.val == null)
                return vertex;
        }
        return null;
    }

    /**
     * Find if any of the graph's constraint edges have conflicts
     * @return true if valid (no conflicts), false otherwise
     */
    public boolean isValid() {
        for (Edge edge : getEdges()) {
            // Unassigned values for vertices don't stop a graph from being valid -- it just means it's not complete
            if (edge.verticesHaveAssignedValues()) {
                // If edge does not satisfy constraints, graph is not valid
                if (!edge.satisfiesConstraint())
                    return false;
            }
        }
        return true;
    }

    /**
     * See if the DirectedConstraintGraph is solved
     * @return if DirectedConstraintGraph is solved
     */
    public boolean isSolved() {
        // Make sure that each Vertex has an assigned value
        for (Vertex<Recipe,?> vertex : vertexList) {
            // In a solved graph, you can't have a Vertex with no assigned value
            if (vertex.val == null)
                return false;
        }
        // Make sure that all values for filled boxes are valid
        return isValid();
    }

    public Vertex<Recipe,?> getVertex(Vertex<Recipe,?> vertex) {
        for (Vertex<Recipe,?> v : getVertices()) {
            if (v.equals(vertex))
                return v;
        }
        return null;
    }

    /**
     * Will remove all impossible values from possible values list for given Vertex.
     * Will also need to add back possible values for backtracking purposes.
     * @param vertex given Vertex
     */
    public void updatePossibleValues(Vertex<Recipe,?> vertex) {
        // TODO: Implement
    }
}
