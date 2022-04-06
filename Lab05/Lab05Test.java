public class Lab05Test {
    public static void test(Person p)      
    {
        p.talk();
    }
    public static void main(String[] args) {
        Teacher t = new Teacher();
        Student s = new Student();
        Person  p = new Person();
        Lab05Test.test(t);          // Upcasting 
        Lab05Test.test(s);          // Upcasting
        Lab05Test.test(p);
    } 
}