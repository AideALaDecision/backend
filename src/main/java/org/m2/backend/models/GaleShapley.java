package org.m2.backend.models;

import java.util.*;

public class GaleShapley {

    private DataGenerator data;
    private int n;

    public GaleShapley(DataGenerator data){
        this.data=data;
        this.n=data.getStudents().size();
    }

    /*public Map<Establishment,Student> applyAlgorithm(){
        //The choice of every student to consider when applying the algorithm
        Map<Student,Integer> studentsFirstChoice=new HashMap<>();
        //Students who have not yet been accepted into an institution
        //When this list is empty the algorithm stops
        List<Student> freeStudents=this.data.getStudents();
        Map<Student,Integer> studentChoice=new HashMap<>();
        Map<Establishment,Student> result=new HashMap<>();

        for(Student student : this.data.getStudents()){
            studentChoice.put(student,0);
        }


        for(Student student : this.data.getStudents()){
            studentsFirstChoice.put(student,student.getPreferences().get(studentChoice.get(student)));
            studentChoice.compute(student,(key,value)->value+1);
        }


        while(!freeStudents.isEmpty()){
//            for(Student st : )
//            System.out.println(freeStudents);
//            System.out.println(result);
            for (Map.Entry<Student,Integer> entry : studentsFirstChoice.entrySet()){

                Student st=entry.getKey();
                Establishment est=data.getEstablishmentById(entry.getValue());
                if(result.containsKey(est)){
                    Student st2=result.get(est);
                    if(est.getPriorities().indexOf(st.getId())<
                            est.getPriorities().indexOf(st2.getId())){
                        result.replace(est,st2,st);
                        freeStudents.add(st2);
                        freeStudents.remove(st);
                        studentChoice.compute(st2,(key,value)->value+1);
//                        System.out.println(studentChoice);
                        studentsFirstChoice.replace(st2,est.getId(),st2.getPreferences().get(studentChoice.get(st2)));
                    }
                    else{
                        studentChoice.compute(st,(key,value)->value+1);
                        studentsFirstChoice.replace(st,est.getId(),st.getPreferences().get(studentChoice.get(st)));
                    }
                }
                else {
                    result.put(est, st);
                    freeStudents.remove(st);
                }
            }
        }
        return result;
    }*/

    public Map<Student, Establishment> findStableMatch(List<Student> students, List<Establishment> establishments) {
        Map<Student, Establishment> matched = new HashMap<>();
        Queue<Student> freeStudents = new LinkedList<>(students);
        Map<Integer, List<Integer>> establishmentPreferences = new HashMap<>();

        for (Establishment est : establishments) {
            establishmentPreferences.put(est.getId(), est.getPriorities());
        }

        while (!freeStudents.isEmpty()) {
            Student currentStudent = freeStudents.poll();
            List<Integer> preferences = currentStudent.getPreferences();

            for (Integer establishmentId : preferences) {
                Establishment preferredEstablishment = establishments.stream()
                        .filter(e -> e.getId() == establishmentId)
                        .findFirst()
                        .orElse(null);

                if (preferredEstablishment == null) continue;

                if (!matched.containsValue(preferredEstablishment)) {
                    matched.put(currentStudent, preferredEstablishment);
                    break;
                } else {
                    Student otherStudent = matched.entrySet().stream()
                            .filter(entry -> entry.getValue().equals(preferredEstablishment))
                            .map(Map.Entry::getKey)
                            .findFirst()
                            .orElse(null);

                    if (otherStudent != null) {
                        List<Integer> establishmentPref = establishmentPreferences.get(establishmentId);

                        if (establishmentPref.indexOf(currentStudent.getId()) < establishmentPref.indexOf(otherStudent.getId())) {
                            matched.put(currentStudent, preferredEstablishment);
                            matched.remove(otherStudent);
                            freeStudents.add(otherStudent);
                            break;
                        }
                    }
                }
            }
        }

        return matched;
    }
}
