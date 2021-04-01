package io.tanzu;


import java.util.ArrayList;
import java.util.List;

/**
 */
public class SimpleSet {

    private List<Object> elements;

    public SimpleSet() {
        this.elements = new ArrayList<>();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void add(Object o) {
        if (!exists(o)) {
            elements.add(o);
        }
    }

    public boolean exists(Object sameObject) {
        for (Object element : elements) {
            if (element == sameObject) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return elements.size();
    }

    public void remove(Object o) {
        elements.remove(o);
    }
}
