interface Function{
    double eval(double x); 
}

interface DifferentiableFunction extends Function{
    double diff(double x);
}

class Linear implements DifferentiableFunction{     
    private final double k;
    private final double b;
    // F(x) = kx + b

    public Linear(double k, double b){
        this.k = k;
        this.b = b;
    }
    @Override
    public double eval(double x){
        return (this.k * x + this.b);
    }
    @Override
    public double diff(double x){
        return this.k;
    }
    public String Tostring(){
        return (this.k + "x + " + this.b);
    }
}

class Quadartic implements DifferentiableFunction{  
    private final double a;
    private final double b;
    private final double c;
    // F(x) = ax^2 + bx + c

    public Quadartic(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double eval(double x){
        return (this.a * x * x + this.b * x + this.c);
    }
    @Override
    public double diff(double x){
        return (this.a * x + this.b);
    }
    public String Tostring(){
        return (this.a + "x^2 + " + this.b + "x + " + this.c);
    }
}

class Sin implements DifferentiableFunction{
    private final double w;
    private final double p;
    // F(x) = sin(wx + p);

    public Sin(double w, double p){
        this.w = w;
        this.p = Math.toRadians(p); // 30' --> 6 / pi;
    }
    @Override
    public double eval(double x){   // Data in degrees, not Radians
        x = Math.toRadians(x); 
        return Math.sin(this.w * x + this.p);
    }
    @Override
    public double diff(double x){   // Data in degrees, not Radians
        x = Math.toRadians(x);
        return (this.w * Math.cos(this.w * x + this.p));
    }
    public String Tostring(){
        return ("sin(" + this.w + "x + " + this.p + ")");
    }
}

class NormalPDF implements DifferentiableFunction{
    private final double u;
    private final double o;
    // F(x) = exp{-(x-u)^2 / 2o^2};
    public NormalPDF(double u, double o){
        this.u = u;
        this.o = o;
    }
    @Override
    public double eval(double x){
        return Math.exp(-0.5 * Math.pow((x - u) / o, 2));
    }
    @Override
    public double diff(double x){
        return (this.eval(x) * (-x + u) / Math.pow(o, 2));
    }
    public String Tostring(){
        return ("exp{-(x - " + this.u + ")^2 / 2*(" + this.o + "^2)}");
    }
}

class NewtonRoot{
    public static double findRoot(DifferentiableFunction f){
        double mistake = 1e-15;
        double x = 1.0;
        while(Math.abs(f.eval(x)) > mistake){
            // 切线 y = kx + b; k 为 导数
            double kk = f.diff(x);
            double bb = f.eval(x) - kk * x;
            // 当y为0的时候，求此时的x值为下一次迭代的x值
            x = - bb / kk;
        }
        return x;
    }
}

class NewtonCatos{
    public static double Trapozoidal(Function f, double a, double b){
        double fa = f.eval(a);
        double fb = f.eval(b);
        return ((b - a) * (fa + fb) / 2);
    }
    public static double Simpson(Function f, double a, double b){
        double fa = f.eval(a);
        double fb = f.eval(b);
        double fm = f.eval((a + b) / 2);
        return ((b - a) * (fa + 4 * fm + fb) / 6);
    }
}

public class Functions{
    public static void main(String[] args) {
        Linear l = new Linear(2, 3);
        Quadartic q = new Quadartic(2, 15, -8);
        Sin s = new Sin(1, 0);
        NormalPDF n = new NormalPDF(0, 1);

        System.out.println("The Root of " + l.Tostring() + " is : x = " + NewtonRoot.findRoot(l));
        System.out.println("One Root of " + q.Tostring() + " is : x = " + NewtonRoot.findRoot(q));
        System.out.println("One Root of " + s.Tostring() + " is : x = " + NewtonRoot.findRoot(s));
        System.out.println("The Root of " + n.Tostring() + " is : x = " + NewtonRoot.findRoot(n));

        System.out.println("---------------------------------------------------");

        System.out.println("The Integrat of " + l.Tostring() + " in Method:Trapozodia is : " 
                                                + NewtonCatos.Trapozoidal(l, 3, 5));                                                    
        System.out.println("The Integrat of " + q.Tostring() + " in Method:Trapozodia is : " 
                                                + NewtonCatos.Trapozoidal(q, 3, 5));                                                    
        System.out.println("The Integrat of " + s.Tostring() + " in Method:Trapozodia is : " 
                                                + NewtonCatos.Trapozoidal(s, 3, 5));                                                    
        System.out.println("The Integrat of " + n.Tostring() + " in Method:Trapozodia is : " 
                                                + NewtonCatos.Trapozoidal(n, 3, 5));           

        System.out.println("-------------------------------------------------------------------");  
        
        System.out.println("The Integrat of " + l.Tostring() + " in Method:Simpson is : " 
                                                + NewtonCatos.Simpson(l, 3, 5));                                                    
        System.out.println("The Integrat of " + q.Tostring() + " in Method:Simpson is : " 
                                                + NewtonCatos.Simpson(q, 3, 5));                                                    
        System.out.println("The Integrat of " + s.Tostring() + " in Method:Simpson is : " 
                                                + NewtonCatos.Simpson(s, 3, 5));                                                    
        System.out.println("The Integrat of " + n.Tostring() + " in Method:Simpson is : " 
                                                + NewtonCatos.Simpson(n, 3, 5));      
        
    }
}