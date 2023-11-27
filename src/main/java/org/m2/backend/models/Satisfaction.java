package org.m2.backend.models;

import java.util.List;

public class Satisfaction {

    private List<Affectation> affectationList;
    private Double satisfactionStudents=0.0; // Statisfaction des Etudiants
    private Double satisfactionEst=0.0; // Statisfaction des Etablissments
    private Double satisfaction=0.0; // Statisfaction general Etudiants et Etablissements

    public List<Affectation> getAffectationList() {
        return affectationList;
    }

    public Double getSatisfactionStudents() {
        return satisfactionStudents;
    }

    public Double getSatisfactionEst() {
        return satisfactionEst;
    }

    public Double getSatisfaction() {
        return satisfaction;
    }

    public Satisfaction(List<Affectation> affectationList){
        this.affectationList=affectationList;
    }

    public  void satisfactionStudents(List<Student> students)
    {
        double satisfaction=0.0;
        for (Affectation a : affectationList){
            Student st=students.get(a.getIdEtudiant()-1);
            satisfaction= satisfaction + 1.0 - ((double) st.getPreferences().indexOf(a.getIdEstablishment())/(st.getPreferences().size()-1));
        }
        this.satisfactionStudents= (satisfaction/students.size()) * 100;
    }

    public void satisfactionEstablishments(List<Establishment> establishments)
    {
        Double satisfaction=0.;
        for (Affectation a : affectationList){
            Establishment est=establishments.get(a.getIdEstablishment()-1);
            satisfaction= satisfaction + 1.0 - ((double) est.getPriorities().indexOf(a.getIdEtudiant())/(est.getPriorities().size()-1));
        }
        this.satisfactionEst=(satisfaction/establishments.size())*100;
    }

    public  void satisfaction()
    {
        this.satisfaction= ( this.satisfactionEst + this.satisfactionStudents) / 2;
    }
}
