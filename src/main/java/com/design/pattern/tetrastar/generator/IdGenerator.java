package com.design.pattern.tetrastar.generator;

/**
 *  Singleton class for generating all character's integer id numbers using nextId() function
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public class IdGenerator {

    private static IdGenerator INSTANCE = new IdGenerator(0);

    private int currentId;

    /**
     *  Singleton Pattern
     * @param currentId
     */
    private IdGenerator(int currentId) {
        this.currentId = currentId;
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public int nextId() {
        return ++currentId;
    }

    public int currentId() {
        return currentId;
    }

}
