import java.util.List;

public class Main {
    public static void main(String[] args){
        DataGenerator dg= new DataGenerator(5);
        dg.setPerfectCase();
        List<Student> st=dg.getStudents();
        List<Establishment> est=dg.getEstablishments();


        for (int i = 0; i < est.size(); i++) {
            List<Integer> preferences = est.get(i).getPreferences();
            System.out.println("Preferences for establishment "+est.get(i).getId()+":");
            System.out.println(preferences);
        }

        System.out.println("=======================");

        for (int i = 0; i < st.size(); i++) {
            List<Integer> preferences = st.get(i).getPreferences();
            System.out.println("Preferences for student "+st.get(i).getId()+":");
            System.out.println(preferences);
        }

    }
}
