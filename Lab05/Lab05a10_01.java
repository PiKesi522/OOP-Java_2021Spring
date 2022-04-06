class Supp
{
    int i = 10;
    int f()
    {
        return i;
    }
}

class Subb extends Supp
{
    int f()
    {
        return i;
    }
}

public class Lab05a10_01 {
    public static void main(String[] args) {
        Subb s = new Subb();
        System.out.println(s.f());
    }
}
