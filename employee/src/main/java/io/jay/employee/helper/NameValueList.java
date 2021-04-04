package io.jay.employee.helper;

import java.util.ArrayList;
import java.util.List;

public class NameValueList {

    private List<NameValue> nameValues;

    public NameValueList() {
        this.nameValues = new ArrayList<>();
    }

    public void add(NameValue nameValue) {
        this.nameValues.add(nameValue);
    }

    public List<NameValue> getNameValues() {
        return this.nameValues;
    }

    public static NameValueList sample() {
        NameValueList nameValues = new NameValueList();
        nameValues.add(new NameValue("key", "value"));
        return nameValues;
    }
}
