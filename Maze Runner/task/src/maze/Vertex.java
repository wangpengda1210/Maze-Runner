package maze;

import java.util.ArrayList;

public class Vertex {

    private boolean wall;
    private boolean searched;
    private boolean path;
    private final ArrayList<Vertex> neighbours;
    private final int x;
    private final int y;

    public Vertex(int x, int y) {
        this(x, y, true);
    }

    public Vertex(int x, int y, boolean wall) {
        this.wall = wall;
        this.x = x;
        this.y = y;
        searched = false;
        path = false;
        neighbours = new ArrayList<>();
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(" + x + ", " + y + ")");
        sb.append("Neighbours: ");
        for (Vertex vertex : neighbours) {
            sb.append("(");
            sb.append(vertex.getX());
            sb.append(", ");
            sb.append(vertex.getY());
            sb.append(")");
        }
        sb.append(";");
        return sb.toString();
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    public ArrayList<Vertex> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Vertex neighbour) {
        neighbours.add(neighbour);
    }

    public void clearNeighbours() {
        neighbours.clear();
    }

    public boolean isPath() {
        return path;
    }

    public void setPath(boolean path) {
        this.path = path;
    }

}
