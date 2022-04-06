public class PersonFactory {
    public static Person next()
    {
        if(Math.random() > 0.5)
        {
            Teacher t = new Teacher();
            return t;
        }
        else
        {
            Student s = new Student();
            return s;
        }
    }
}