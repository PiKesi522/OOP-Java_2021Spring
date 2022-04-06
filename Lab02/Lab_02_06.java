package Lab02;
import java.util.Scanner;

class Mytype06 {
    double x;
    double a;
    double fx(double x)
    {
        x = (x + a / x) / 2; 
        return x;
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in); 
        int a = input.nextInt();
        double ans = 4;
        Mytype06 m = new Mytype06();
        m.a = a;
        while(Math.abs(a - ans * ans) > 1e-9)
        {
            ans = m.fx(ans);
        }
        System.out.println(ans);
        input.close();
    }
}
