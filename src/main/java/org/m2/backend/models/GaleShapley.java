package org.m2.backend.models;

import java.util.*;

public class GaleShapley {

    private DataGenerator data;
    private int n;

    public GaleShapley(DataGenerator data){
        this.data=data;
        this.n=data.getStudents().size();
    }

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
