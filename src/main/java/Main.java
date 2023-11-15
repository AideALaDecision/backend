public class Main {
    public static void main(String[] args){
        DataGenerator dg= new DataGenerator();
        dg.perfectCase(7);
        for (int i = 0; i < 10; i++) {
            Student tst = new Student();
            System.out.println(tst.getId());
        }
    }
}
