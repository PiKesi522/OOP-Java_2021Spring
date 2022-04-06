package Lab02;
class Mytype05 {
    int X = 1;
    double logx;
    double increase_logx;

    double x;
    double increase_x;

    double xlogx;
    double increase_xlogx;

    double x2;
    double increase_x2;

    double x3;
    double increase_x3;

    void flogx(int X)
    {
        logx = Math.log(X);
        increase_logx = 1 / X;
    }

    void fx(int X)
    {
        x = X;
        increase_x = 1;
    }

    void fxlogx(int X,double logx)
    {
        xlogx = X * logx;
        increase_xlogx = logx + 1;
    }

    void fx2(int X)
    {
        x2 = Math.pow(X, 2);
        increase_x2 = 2 * X;
    }

    void fx3(int X,double x2)
    {
        x3 = Math.pow(X, 3);
        increase_x3 = 3 * x2;
    }

    public static void main(String[] args){

        Mytype05 m = null;
        for(int i = 1;i <= 2048; i *= 2)
        {
            m = new Mytype05();
            m.X = i;
            m.flogx(i);
            m.fx(i);
            m.fxlogx(i,m.logx);
            m.fx2(i);
            m.fx3(i,m.x2);
            System.out.println("-------------");
            System.out.print("logx = ");
            System.out.println(m.logx);
            System.out.print("x = ");
            System.out.println(m.x);
            System.out.print("xlogx = ");
            System.out.println(m.xlogx);
            System.out.print("x2 = ");
            System.out.println(m.x2);
            System.out.print("x3 = ");
            System.out.println(m.x3);
            System.out.print("in logx = ");
            System.out.println(m.increase_logx);
            System.out.print("in x = ");
            System.out.println(m.increase_x);
            System.out.print("in xlogx = ");
            System.out.println(m.increase_xlogx);
            System.out.print("in x2 = ");
            System.out.println(m.increase_x2);
            System.out.print("in x3 = ");
            System.out.println(m.increase_x3);
        }
    }
}
