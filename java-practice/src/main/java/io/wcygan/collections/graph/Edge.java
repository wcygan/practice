package io.wcygan.collections.graph;

public record Edge<T>(Vertex<T> source, Vertex<T> target, Double weight) {}
