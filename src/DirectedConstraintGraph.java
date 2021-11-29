import java.util.ArrayList;

/**
 *
 * @param <T> Type of thing that each Vertex holds
 * @param <U> The id by which each vertex is compared / identified.
 */
public abstract class DirectedConstraintGraph<T extends Comparable<T>,U extends Comparable<U>> {
    /** The list of all vertices */
    ArrayList<Vertex> vertexList;
    /** The list of all edges */
    ArrayList<Edge> edgeList;

    ArrayList<T> ALL_POSSIBLE_VALUES;

    public DirectedConstraintGraph(ArrayList<T> ALL_POSSIBLE_VALUES) {
        this.ALL_POSSIBLE_VALUES = ALL_POSSIBLE_VALUES;
    }

    public void copyVertexValues(DirectedConstraintGraph<T,U> copyGraph) {
        // If not the same amount of vertices, don't iterate to copy
        if (this.getVertices().size() != copyGraph.getVertices().size()) {
            return;
        }
        for (int i = 0; i < this.getVertices().size(); i++) {
            // Set this graph's vertex value equal to the other graph's vertex val
            this.getVertices().get(i).setVal(copyGraph.getVertices().get(i).getVal());
        }

    }

    /**
     * Abstract class for a Vertex
     */
    public abstract class Vertex {
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
                @SuppressWarnings("unchecked")
                Vertex v = (Vertex) o;
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

        public U getID() { return id; }

        public void setPossibleValues(ArrayList<T> possibleValues) {
            this.possibleValues = possibleValues;
        }

        public ArrayList<T> getPossibleValues() {
            return possibleValues;
        }
    }

    /**
     * Abstract class for an Edge.
     * Contains abstract method for evaluating edge constraint
     */
    public abstract class Edge {
        Vertex startVertex;
        Vertex endVertex;
        public Edge(Vertex startVertex, Vertex endVertex) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
        }

        public Vertex getStartVertex() {
            return startVertex;
        }
        public Vertex getEndVertex() {
            return endVertex;
        }

        /**
         * See if edge constraint is satisfied for given vertices
         * @return true if constraint satisfied, false otherwise.
         */
        public abstract boolean violatesConstraint();

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
    public ArrayList<Edge> getOutgoingEdges(Vertex givenVertex) {
        ArrayList<Edge> returnList = new ArrayList<Edge>();
        for (Edge e : edgeList) {
            if (e.getStartVertex().equals(givenVertex))
                returnList.add(e);
        }
        return returnList;
    }

    /**
     * Get all incoming edges for a given Vertex
     * @param givenVertex given Vertex
     * @return list of all incoming edges
     */
    public ArrayList<Edge> getIncomingEdges(Vertex givenVertex) {
        ArrayList<Edge> returnList = new ArrayList<Edge>();
        for (Edge e : edgeList) {
            if (e.getEndVertex().equals(givenVertex))
                returnList.add(e);
        }
        return returnList;
    }
    /**
     * Add a Vertex to the graph.
     * @param vertex Vertex to add to the graph
     * @return the Vertex added
     */
    public Vertex addVertex(Vertex vertex) {
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
    public ArrayList<Vertex> getVertices() {
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
    public Vertex getEmptyVertex() {
        for (Vertex vertex : vertexList) {
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
                // If edge violates constraints, graph is not valid
                if (edge.violatesConstraint())
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
        for (Vertex vertex : vertexList) {
            // In a solved graph, you can't have a Vertex with no assigned value
            if (vertex.val == null)
                return false;
        }
        // Make sure that all values for filled boxes are valid
        return isValid();
    }

    public Vertex getVertex(Vertex vertex) {
        for (Vertex v : getVertices()) {
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
    public void updatePossibleValues(Vertex vertex) {
        // Refresh all the possible values of the given vertex
        vertex.setPossibleValues(ALL_POSSIBLE_VALUES);

        // Incoming edges should affect change in a given vertex, so iterate and check through all incoming edges
        for (Edge e : getIncomingEdges(vertex)) {
            // Need only set possibleValues for unassigned vertices. Connected vertices that effect possibleValues of
            // this vertex should have an assigned value though -- otherwise how can they make an impact?
            if (vertex.val == null & e.getStartVertex() != null) {
                for (int i = 0; i < vertex.getPossibleValues().size(); i++) {
                    // Set vertex value to possible value.
                    vertex.setVal(vertex.getPossibleValues().get(i));
                    // If possible value violates constraint edge, remove possible value
                    if (e.violatesConstraint()) {
                        // Remove possible value
                        vertex.getPossibleValues().remove(i);
                        i--;
                    }
                }
            }
        }
    }

    /**
     * Set a vertex's value, and update possible values for connected vertices
     * @param vertex given vertex
     * @param value given value
     */
    public void setVertexValue(Vertex vertex, T value) {
        Vertex v = getVertex(vertex);
        v.setVal(value);
        // Update possible values for all vertices connected to the set vertex (outgoing)
        for (Edge edge : getOutgoingEdges(v)) {
            updatePossibleValues(edge.getEndVertex());
        }
    }

    /**
     * Return a list of all vertex values
     * @return list of all vertex values
     */
    public ArrayList<T> toList() {
        ArrayList<T> returnList = new ArrayList<T>();
        for (Vertex v : vertexList) {
            returnList.add(v.getVal());
        }
        return returnList;
    }
}
