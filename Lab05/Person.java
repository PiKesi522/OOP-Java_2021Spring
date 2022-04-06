public class Person{    // Combination of Class:Name
    public Name name;
    public final int age;
    private String gender;

    public Person()
    {   
        this.name = new Name();
        this.age = 0;
        this.gender = "Male";
    }
    public Person(int a, String g, Name n)
    {
        this.name = n;
        this.age = a;
        this.gender = g;
    }
    public String getGender()
    {
        return this.gender;
    } 
    public void setGender(String g)
    {
        this.gender = g;
    }
    public void talk()
    {
        System.out.println("Hi, how is it going");
    }
    public void talk(String s)
    {
        System.out.println(s);
    }
    public void chatWith(Person p, String s)
    {
        System.out.println(this.name.ToString() + " to " + p.name.ToString() + ": \"" + s + "\"");
    }

    public static void main(String[] args)
    {
        Name n = new Name("yb","wu");
        Person p = new Person(30,"male",n);
        System.out.println(p.name.ToString());
        p.talk();
    }
}
