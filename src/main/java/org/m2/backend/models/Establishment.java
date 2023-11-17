package org.m2.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Establishment {
    private static int count = 0;
    int id;
    List<Integer> priorities = new ArrayList<>();


    public Establishment() {
        setId(count++);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getPriorities() {
        return priorities;
    }


    public void setPriorities(List<Integer> preferences) {

        this.priorities.clear();
        this.priorities.addAll(preferences);
    }

    @Override
    public String toString() {
        return "id{" + id +'}';
    }
}
