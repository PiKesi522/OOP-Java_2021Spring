public class Turtle {
    double x = 0.0;     // present location in X axis
    double y = 0.0;     // present location in Y axis
    double angle = 0.0; // present degree
    String move = new String();
    void initialize()
    {
        this.x = 0.0;
        this.y = 0.0;
        this.angle = 0.0;
    }
    void turnLeft(double delta)
    {
        this.angle += delta;
    }
    void turnRight(double delta)
    {
        this.angle-= delta;
    }
    void goForward(double step)
    {
    /*                      
                        |    L : y = kx
                        |   /
                      +-+--O <-(x1,y1)              step = x ^ 2 + y ^ 2 = |p1 - p0|
                      | | /
                      | |/ delta = tan(y / x)       x1 - x0 = step * cos(delta) 
            ----------+-+------------             ->x1 = x0 + step * cos(delta) 
                      |/|
            (x0,y0)-> O |                           y1 - y0 = step * sin(delta)
                     /  |                         ->y1 = y0 + step * sin(delta)
                    /   |
    */
        double x1 = this.x + step * Math.cos(angle * (Math.PI / 180));
        double y1 = this.y + step * Math.sin(angle * (Math.PI / 180));
        StdDraw.line(this.x, this.y, x1, y1);
        this.x = x1;
        this.y = y1;
    }
    void NEdgeFigure(int N)
    {               
        if(N <= 2)
        {
            System.out.println("the figure have at least three edges");
            return;
        }
        this.x = 0;     // Initialize the location as normalize circle
        this.y = -1;    
        this.angle = 90 - ((180 - (360 / (double)N)) / 2);       
        int AllDegree = 180 * (N - 2);  // In any figure, the sum of degree of angles
                                        // are 180*(N-2)"
        double normal_length = 2 * Math.cos(((180 - (360 / (double)N)) / 2) * (Math.PI / 180));     
                // the length of each step is relative to the numbers of Edge
        for(int i = 0; i < N; i++)
        {
            this.goForward(normal_length);
            this.turnLeft(180 - (AllDegree / N));
        }
        return;
    }
    void Spiral(int N, int T, double D)
    {
        if(N <= 2)
        {
            System.out.println("the figure have at least three edges");
            return;
        }
        this.x = 0;     // Initialize the location as normalize circle
        this.y = -1;    
        this.angle = 90 - ((180 - (360 / (double)N)) / 2);       
        int AllDegree = 180 * (N - 2);  // In any figure, the sum of degree of angles
                                        // are 180*(N-2)"
        double normal_length = 2 * Math.cos(((180 - (360 / (double)N)) / 2) * (Math.PI / 180));     
                // the length of each step is relative to the numbers of Edge
        for(int i = 0; i < T; i++)
        {
            for(int j = 0; j < N; j++)
            {
                this.goForward(normal_length);
                normal_length /= D;
                this.turnLeft(180 - (AllDegree / N));
            }
        }
        return;
        
    }
    void BrownianMotion(int T, double S)
    {
        this.initialize();
        for(int i = 0; i < T; i++)
        {
            this.goForward(S);
            this.turnLeft(Math.random() * 360);
        }
    } 
    void KochCurve(int N)
    {
        if(N == 1)
            this.goForward(1);
        else
        {
            KochCurve(N-1);
            this.turnLeft(60);
            KochCurve(N-1);
            this.turnRight(120);
            KochCurve(N-1);
            this.turnLeft(60);
            KochCurve(N-1);
        }
    }
    void REKochCurve(int N)
    {
        if(N == 1)
        this.goForward(1);
    else
    {
        KochCurve(N-1);
        this.turnRight(60);
        KochCurve(N-1);
        this.turnLeft(120);
        KochCurve(N-1);
        this.turnRight(60);
        KochCurve(N-1);
    }
    }
    void Kochsnowflake(int N)
    {
        this.initialize();
        KochCurve(N);
        this.turnRight(120);
        KochCurve(N);
        this.turnRight(120);
        KochCurve(N);
    }
    void REKochsnowflake(int N)
    {
        this.initialize();
        REKochCurve(N);
        this.turnRight(120);
        REKochCurve(N);
        this.turnRight(120);
        REKochCurve(N);
    }
    void DragonCurve(int N)
    {                               //   The DragonCurve is a figure that 
        if(N==1)                    // from the beginning of the figure,we  
        {                           // move to the destination in certain steps.
            this.goForward(1);      //   Then, in the destination, turn left 90"
            move += "G";            // and go back as the steps it comes
        }                           //   In general, it looks like two figures
        else                        // which turn right 90" as a entirety
        {
            DragonCurve(N-1);
            interprete();
        }
    }  
    void interprete()
    {                               //    In this function, the line will read
        this.turnLeft(90);          // the trail of previous line, and go back
        move += "L";                // as it comes
        if(move.length() != 0)
        {
            for(int i = move.length() - 2; i >= 0 ; i--)
            {
                switch(move.charAt(i))
                {
                    case 'G':
                        this.goForward(1);
                        move += "G";
                        break;
                    case 'L':
                        this.turnRight(90);
                        move += "R";
                        break;
                    case 'R':
                        this.turnLeft(90);
                        move += "L";
                        break;
                    default:
                        break;
                }
            }
        }
    }
    void MinkowskiCurve(int N,double length)
    {
        if(N == 1)              // the basic figure of Minkowski is a rectangle
        {
            this.goForward(length / 3);     // length is used to self-adjust to
            this.turnLeft(90);              // the size of the picture
            this.goForward(length / 3);
            this.turnRight(90);
            this.goForward(length / 3);
        }
        else
        {
            MinkowskiCurve(N-1,length / 3); // Draw a part of the figure
            if(N % 2 == 0)                  //   Judge the direction that
                this.turnRight(90);         // the line will head for
            else
                this.turnLeft(90);
            MinkowskiCurve(N-1,length / 3);
            if(N % 2 == 0)
                this.turnLeft(90);
            else
                this.turnRight(90);
            MinkowskiCurve(N-1,length / 3);
        }
    }
    void Minkowski(int N,double length)
    {
        MinkowskiCurve(N,length);   // Draw a partial figure and turn left
        this.turnLeft(90);          // repeat it for four times
        MinkowskiCurve(N,length);
        this.turnLeft(90);
        MinkowskiCurve(N,length);
        this.turnLeft(90);
        MinkowskiCurve(N,length);
    }
    void GosperCurve(int N,double length)     // Similar to MinkowskiCurve
    {
        if(N == 1)
        {
            this.goForward(length / 2.4);
            this.turnRight(60);
            this.goForward(length / 2.4);
            this.turnLeft(60);
            this.goForward(length / 2.4);
        }
        else
        {
            GosperCurve(N-1,length / 2.4);
            if(N % 2 == 0)
                this.turnRight(75);
            else
                this.turnLeft(75);
            GosperCurve(N-1,length / 2.4);
            if(N % 2 == 0)
                this.turnLeft(75);
            else
                this.turnRight(75);
            GosperCurve(N-1,length / 2.4);
        }
    }
    void Gosper(int N,double length)
    {
        GosperCurve(N,length);
        this.turnLeft(60);
        GosperCurve(N,length);
        this.turnLeft(60);
        GosperCurve(N,length);
        this.turnLeft(60);
        GosperCurve(N,length);
        this.turnLeft(60);
        GosperCurve(N,length);
        this.turnLeft(60);
        GosperCurve(N,length);
    }
    void wbsecyCurve(int N, double length)    // Design in casual
    {
        if(N == 1)
        {
            this.goForward(length);
            this.turnRight(60);
            this.goForward(length * 1.2);
            this.turnLeft(90);
            this.goForward(length * 1.2);
        }
        else
        {
            this.wbsecyCurve(N-1,length/1.8);
            if(N % 2 == 0)
                this.turnLeft(60);
            else
                this.turnRight(90);
            this.wbsecyCurve(N-1,length/1.8);
            if(N % 2 == 0)
                this.turnRight(90);
            else
                this.turnLeft(60);
            this.wbsecyCurve(N-1,length/1.8);
        }
    }
    void wbsecy(int N,double length)
    {
        this.wbsecyCurve(N,length);
        this.turnLeft(90);
        this.wbsecyCurve(N,length);
        this.turnLeft(90);
        this.wbsecyCurve(N,length);
        this.turnLeft(90);
        this.wbsecyCurve(N,length);
        this.turnLeft(90);
        this.wbsecyCurve(N,length);
    }
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-10,10);
        StdDraw.setYscale(-10,10);
        double length = 5;
        //pen.NEdgeFigure(5);
        //pen.Spiral(5, 10, 1.1);
        //pen.BrownianMotion(1000, 0.1);
        //pen.DragonCurve(5);
        //pen.Minkowski(5, length);
        //pen.Gosper(5, length);
        pen.wbsecy(5,length);
    }
}
