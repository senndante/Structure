package Lesson_7;

public class Vertex {

    private final String label;
    private boolean wasVisited = false;
    private Vertex previousvertex;

    public void setPreviousvertex(Vertex previousvertex) {
        this.previousvertex = previousvertex;
    }

    public Vertex getPreviousvertex() {

        return previousvertex;
    }

    public Vertex(String label) {
        this.previousvertex = null;
        this.label = label;
    }

    public String getLabel() {

        return label;
    }

    @Override
    public String toString() {

        return label;
    }

    public boolean isWasVisited() {

        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {

        this.wasVisited = wasVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return label != null ? label.equals(vertex.label) : vertex.label == null;
    }
}
