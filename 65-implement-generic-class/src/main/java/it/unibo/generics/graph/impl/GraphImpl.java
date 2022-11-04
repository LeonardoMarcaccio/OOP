package it.unibo.generics.graph.impl;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> edges = new LinkedHashMap<>();

    public GraphImpl(boolean mode) {
    }

    @Override
    public void addNode(final N node) {
        edges.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void addEdge(final N source, final N target) {
        if (edges.containsKey(source)) {
            edges.get(source).add(target);
        }
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(edges.keySet());
    }

    @Override
    public Set<N> linkedNodes(final N node) {
        return edges.get(node);
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        final Queue<N> near = new LinkedList<>();
        
        near.add(source);
        
        final Set<N> alreadyVisited = new HashSet<N>();
        
        while (!near.isEmpty() && alreadyVisited.size() < edges.size()) {
            final List<N> path = new LinkedList<>(); 
            N currentNode = near.poll();
            if (currentNode.equals(target)) {
                path.add(currentNode);
                return path;
            } else if (!alreadyVisited.contains(currentNode)) {
                alreadyVisited.add(currentNode);
                path.add(currentNode);
                for (N n : edges.get(currentNode)) {
                    near.add(n);
                }
                currentNode = near.poll();
                path.addAll(getPath(currentNode, target));
                return new LinkedList<>(path);
            }
        }
        return Collections.emptyList();
    }

    
    
}
