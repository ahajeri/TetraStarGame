package com.design.pattern.tetrastar.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class TetraRoverCareTaker {

    // LIFO Dequeue
    private Deque<TetraRoverMemento> mementosList = null;

    public TetraRoverCareTaker() {
        this.mementosList = new ArrayDeque<>();
    }

    public void addMemento(TetraRoverMemento memento) {
        mementosList.add(memento);
    }

    public TetraRoverMemento getMemento() {
        return mementosList.removeLast();
    }

}
