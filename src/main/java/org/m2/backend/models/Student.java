package org.m2.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;
    List<Integer> preferences = new ArrayList<>();

    public Student(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public List<Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Integer> preferences) {
        this.preferences.clear();
        this.preferences.addAll(preferences);
    }

    @Override
    public String toString() {
        return "Etudiant{"+ id +'}';
    }
}