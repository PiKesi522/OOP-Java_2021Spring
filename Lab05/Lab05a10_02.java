class Suppp
{
    int[] i = {1,2,3,4,5};
    int[] f()
    {
        return i;
    }
}

class Subbb extends Suppp
{
    int subi = 10;
    /*int f()
    {
        return subi;
    }*/
}

public class Lab05a10_02 {
    public static void main(String[] args) {
        Subbb s = new Subbb();
        System.out.println(s.f());
    }
}
