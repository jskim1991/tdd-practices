package io.tanzu.firstspringboottdd.domain;

public class Todo {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private boolean isComplete;


}
