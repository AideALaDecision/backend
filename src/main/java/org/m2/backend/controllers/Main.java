package org.m2.backend.controllers;

import org.m2.backend.models.DataGenerator;
import org.m2.backend.models.Establishment;
import org.m2.backend.models.GaleShapley;
import org.m2.backend.models.Student;

import java.util.Map;

public class Main {
    public static void main(String[] args){

        DataGenerator dg = new DataGenerator(8);
        dg.setNormalCase();
        GaleShapley gp= new GaleShapley(dg);

        for(Student st : dg.getStudents()){
            System.out.println("Préférences de l'étudiant "+st.getId());
            System.out.println(st.getPreferences());
        }
        for(Establishment est : dg.getEstablishments()){
            System.out.println("Prioritées des établissement "+est.getId());
            System.out.println(est.getPriorities());
        }

        Map<Student,Establishment> result=gp.findStableMatch(dg.getStudents(),dg.getEstablishments());

        System.out.println(result);
    }
}
