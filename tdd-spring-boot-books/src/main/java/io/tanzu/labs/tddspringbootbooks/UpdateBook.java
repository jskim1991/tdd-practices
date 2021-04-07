package io.tanzu.labs.tddspringbootbooks;

public class UpdateBook {

    private String newName;

    public UpdateBook(String newName) {
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }
}
