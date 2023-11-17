package org.m2.backend.models;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

public class DataGenerator {
    //TODO : write here methods to generate data
    //TODO : perfect case --> Every institution desires the student who has selected that institution as their first choice.
    //TODO : normal case
    //TODO : worst case --> Each institution does not desire the student who selected that institution as their first choice.

    private List<Student> students;
    private List<Establishment> establishments;
    private int n;

    public List<Student> getStudents() {
        return students;
    }

    public List<Establishment> getEstablishments() {
        return establishments;
    }

    public DataGenerator(int n){
        this.students=new ArrayList<>();
        this.establishments=new ArrayList<>();
        this.n=n;

        for (int i=0;i<n;i++){
            this.students.add(new Student());
            this.establishments.add(new Establishment());
        }
    }

    public void setNormalCase(){

        for (Student student : this.students) {
            List<Integer> preferences = this.establishments.stream()
                    .map(Establishment::getId)
                    .collect(Collectors.toList());
            Collections.shuffle(preferences);
            student.setPreferences(preferences);
        }

        for (Establishment establishment : this.establishments) {
            List<Integer> preferences = this.students.stream()
                    .map(Student::getId)
                    .collect(Collectors.toList());
            Collections.shuffle(preferences);
            establishment.setPriorities(preferences);
        }
    }

    public void setWorstCase(){
        for (Student student : this.students) {
            List<Integer> preferences = this.establishments.stream()
                    .map(Establishment::getId)
                    .collect(Collectors.toList());
            Collections.shuffle(preferences);
            student.setPreferences(preferences);
        }

        for (Establishment establishment : this.establishments) {
            List<Integer> preferences = this.students.stream()
                    .map(Student::getId)
                    .collect(Collectors.toList());
            Collections.shuffle(preferences);
            Integer firstChoice=preferences.get(0);
            if(this.students.get(firstChoice-1).getId()==(establishment.getId())){
                Collections.shuffle(preferences);
                firstChoice=preferences.get(0);
            }
            establishment.setPriorities(preferences);
        }
    }

public void setPerfectCase() {

        for (int i = 0; i < n; i++) {
                Student student = students.get(i);
                Establishment preferredEstab = establishments.get(i);
                List<Integer> preferences = this.establishments.stream()
                    .map(Establishment::getId)
                    .collect(Collectors.toList());

                preferences.remove(i);
                Collections.shuffle(preferences);
                preferences.add(0, preferredEstab.getId());

                student.setPreferences(preferences);
        }

        for (int i = 0; i < n; i++) {
                Establishment establishment = establishments.get(i);
                Student preferredStudent = students.get(i);
                List<Integer> preferences = this.students.stream()
                    .map(Student::getId)
                    .collect(Collectors.toList());
                preferences.remove(i);
                Collections.shuffle(preferences);
                preferences.add(0, preferredStudent.getId());

                establishment.setPriorities(preferences);
        }


}
}
