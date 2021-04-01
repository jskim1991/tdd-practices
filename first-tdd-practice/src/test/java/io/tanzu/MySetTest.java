package io.tanzu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MySetTest {

    SimpleSet simpleSet;

    @Before
    public void setup() {
        simpleSet = new SimpleSet();
    }

    /**
     * given: an empty set
     * when:
     * then: isEmpty() should return true
     */
    @Test
    public void testIsEmptyOnEmptySet() {
        assertEquals(simpleSet.isEmpty(), true);
    }

    /**
     * given: an empty
     * when: add an element
     * then: isEmpty() should return false
     */
    @Test
    public void testIsEmptyOnNonEmptySet() {
        simpleSet.add(new Object());
        assertEquals(simpleSet.isEmpty(), false);
    }

    /**
     * given: an empty set
     * when: add an element
     * then: exists() on the same element should return true
     */
    @Test
    public void testExistsOnEmptySet() {
        Object sameObject = new Object();
        simpleSet.add(sameObject);
        assertEquals(simpleSet.exists(sameObject), true);
    }

    /**
     * given: an empty set
     * when: add an element
     * then: exists() on a different element should return false
     */
    @Test
    public void testExistsOnNonEmptySet() {
        Object firstObject = new Object();
        Object secondObject = new Object();
        simpleSet.add(firstObject);
        assertEquals(simpleSet.exists(secondObject), false);
    }

    /**
     * given: an empty set
     * when: add a same element twice
     * then: size should be 1
     */
    @Test
    public void testSizeAfterAddingSameElementTwice() {
        Object o = new Object();
        simpleSet.add(o);
        simpleSet.add(o);
        assertEquals(simpleSet.getSize(), 1);
    }

    /**
     * given: an empty set
     * when: two different elements are added
     * then: size should be 2
     */
    @Test
    public void testSizeAfterAddingDifferentElementTwice() {
        simpleSet.add(new Object());
        simpleSet.add(new Object());
        assertEquals(simpleSet.getSize(), 2);
    }

    /**
     * given: an empty set
     * when: remove something
     * then: size should be 0
     */
    @Test
    public void testRemoveOnEmptySet() {
        simpleSet.remove(new Object());
        assertEquals(simpleSet.getSize(), 0);
    }

    /**
     * given: an empty set
     * when: add an element and remove it
     * then: size should be 0
     */
    @Test
    public void testRemoveOnNonEmptySet() {
        Object o = new Object();
        simpleSet.add(o);
        simpleSet.remove(o);
        assertEquals(simpleSet.getSize(), 0);
    }

}
