public class Teacher extends Person{
    public Teacher()
    {
        super();
    }
    public Teacher(int i, String s, Name n) // When we use constrcution function
    {                                       // we should first use .super() to 
        super(i,s,n);                       // inherit the function in Superclass
                                            // and use the constuction function 
                                            // to create a subclass instance.
    }
    public void talk()
    {
        System.out.println("Hi, how is your paper going?");
    }    
    public static void main(String[] args) {
        Name n = new Name();
        Teacher tec = new Teacher(10,"Male",n);
        tec.talk();
        Student std = new Student(10,"Male",n);
        std.talk();
        Person p = new Person(10,"Male",n);
        p.talk();
    }
}
