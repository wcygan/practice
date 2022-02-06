package io.wcygan.collections.graph;

public class Vertex<T> implements Comparable<Vertex<T>> {
    private final T data;
    private final String name;
    private Color color;
    private double cost = 1.0;
    private Vertex<T> pred = null;
    private boolean visited = false;

    public Vertex(T data, String name) {
        this.data = data;
        this.name = name;
        this.color = Color.WHITE;
    }

    public T getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Vertex<T> getPred() {
        return pred;
    }

    public void setPred(Vertex<T> pred) {
        this.pred = pred;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public int compareTo(Vertex<T> o) {
        return Double.compare(this.cost, o.getCost());
    }
}
