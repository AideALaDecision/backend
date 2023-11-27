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
            this.students.add(new Student(i+1));
            this.establishments.add(new Establishment(i+1));
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
        this.setPerfectCase();
//        for (Student student : this.students) {
//            List<Integer> preferences = this.establishments.stream()
//                    .map(Establishment::getId)
//                    .collect(Collectors.toList());
//            Collections.shuffle(preferences);
//            student.setPreferences(preferences);
//        }

        for (Establishment establishment : this.establishments) {
            List<Integer> priorities=establishment.getPriorities();
//            System.out.println(priorities);
            Collections.reverse(priorities);
//            System.out.println(priorities);
//            establishment.setPriorities(priorities);
//            List<Integer> preferences = this.students.stream()
//                    .map(Student::getId)
//                    .collect(Collectors.toList());
//            Collections.shuffle(preferences);
//            Integer firstChoice=preferences.get(0);
//            if(this.students.get(firstChoice-1).getId()==(establishment.getId())){
//                Collections.shuffle(preferences);
//                firstChoice=preferences.get(0);
//            }
//            establishment.setPriorities(preferences);
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

    public void setExempleCase(){
        this.students.get(0).setPreferences(Arrays.asList(2,1,3));
        this.students.get(1).setPreferences(Arrays.asList(1,2,3));
        this.students.get(2).setPreferences(Arrays.asList(1,2,3));

        this.establishments.get(0).setPriorities(Arrays.asList(1,3,2));
        this.establishments.get(1).setPriorities(Arrays.asList(2,1,3));
        this.establishments.get(2).setPriorities(Arrays.asList(2,1,3));
    }

    public Establishment getEstablishmentById(int id){
        return establishments.get(id-1);
    }
}
