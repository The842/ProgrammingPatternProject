package org.example.model;

import java.util.Stack;

public class PatientHistory {

    private final Stack<PatientMemento> mementoStack = new Stack<>();

    public void saveState(PatientMemento memento) {
        mementoStack.push(memento);
    }

    public PatientMemento restoreState() {
        if (!mementoStack.isEmpty()) {
            return mementoStack.pop();
        }
        return null;
    }
}
