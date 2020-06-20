package com.rishi.common;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void addChild(T child) {
        Node childNode = new Node(child);
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(childNode);
    }

    public List<Node<T>> getChildren() {
        return children;
    }
}
