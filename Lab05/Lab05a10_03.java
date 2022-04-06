class A{
    int num = 10;
    public void get()
    {
        System.out.println("In A");
    }
}

class B extends A{
    int num = 100;
    public void get()
    {
        System.out.println("In B");
    }
}

class Sup{
    A f() 
    {
        A a = new A();
        return a; 
    }
}

class Sub extends Sup{
    B f()
    {
        B b = new B();
        return b;
    }
}

public class Lab05a10_03 {
    public static void main(String[] args) {
        Sub s = new Sub();
        B b = s.f();
        b.get();
        System.out.println(b.num);
    }
}
