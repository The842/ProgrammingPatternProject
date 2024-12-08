package org.example.model;

import java.util.Stack;

public class PatientHistory {

    private final Stack<PatientMemento> mementoStack = new Stack<>();

    /**
     * This method save the state in the stack
     * @param memento is the memento of patient
     */
    public void saveState(PatientMemento memento) {
        mementoStack.push(memento);
    }

    /**
     * This method restore the state of the
     * @return null is the memento stack is not empty
     */
    public PatientMemento restoreState() {
        if (!mementoStack.isEmpty()) {
            return mementoStack.pop();
        }
        return null;
    }
}
