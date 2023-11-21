package org.m2.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Establishment {
    int id;
    List<Integer> priorities = new ArrayList<>();


    public Establishment(int id) {
        this.id = id;
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
        return "Etablissement{" + id +'}';
    }
}
