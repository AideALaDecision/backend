import java.util.List;
import java.util.stream.IntStream;

public class DataGenerator {
    //TODO : write here methods to generate data



    // this method generate the perfect case, where there is no conflicts each student prefers an establishment that chose them
     void perfectCase(int n){
         // n the number of establishements/students
         List<Integer> rangeStudent = IntStream.rangeClosed(0, n-1)
                 .boxed().toList();
         List<Integer> rangeEstab = IntStream.rangeClosed(0, n-1)
                 .boxed().toList();
         System.out.println(rangeEstab);
         System.out.println(rangeStudent);


     }
}
