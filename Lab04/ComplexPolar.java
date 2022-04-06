public class ComplexPolar {
    double r = 0.0;     // z = r(cos + isin)
    double delta = 0.0;

    ComplexPolar(double r, double delta)// constructor function
    {
        this.r = r;
        this.delta = delta;
    }
    ComplexPolar()                      // Default constructor function
    {
        this.r = 0;
        this.delta = 0;
    }
    ComplexPolar(ComplexPolar x)    // shallow copy
    {
        this.r = x.r;
        this.delta = x.delta;
    }
    
    ComplexPolar add(ComplexPolar x)
    {   
        ComplexPolar ans_ploar = new ComplexPolar();        // use Cartesian to calculate

        ComplexPolar this_polar = new ComplexPolar(this);   // use shallow copy in order
        ComplexPolar x_polar = new ComplexPolar(x);         // not to change the origin
        ComplexCart this_cart = this_polar.toCartesian();
        ComplexCart x_cart = x_polar.toCartesian();

        ans_ploar = this_cart.add(x_cart).toPolar();
        return ans_ploar;
    }
    ComplexPolar subtract(ComplexPolar x)
    {   
        ComplexPolar ans_ploar = new ComplexPolar();        // use Cartesian to calculate

        ComplexPolar this_polar = new ComplexPolar(this);   // use shallow copy in order
        ComplexPolar x_polar = new ComplexPolar(x);         // not to change the origin
        ComplexCart this_cart = this_polar.toCartesian();
        ComplexCart x_cart = x_polar.toCartesian();

        ans_ploar = this_cart.subtract(x_cart).toPolar();
        return ans_ploar;
    }
    ComplexPolar multiply(ComplexPolar x)   // z1 * z2  = r1*r2 * [cos(x1+x2) + isin(x1+x2)]
    {
        ComplexPolar ans = new ComplexPolar();
        ans.r = this.r * x.r;               // r = r1 * r2
        ans.delta = this.delta + x.delta;   // delta = delta1 + delta2
        return ans;
    }
    ComplexPolar scale(double a)            // Widen the range
    {
        ComplexPolar ans = new ComplexPolar();
        ans.delta = this.delta;
        ans.r = this.r * a;
        return ans;
    }
    ComplexPolar divide(ComplexPolar x)     // z1 / z2  = r1/r2 * [cos(x1-x2) + isin(x1-x2)]
    {
        ComplexPolar ans = new ComplexPolar();  
        ans.r = this.r / x.r;               // r = r1 / r2;
        ans.delta = this.delta - x.delta;   // delta = delta1 - delta2
        return ans;
    }
    ComplexPolar reciprocal()       // given that z = r(cosx + isinx) = a + bi
    {                               // we can find that the reciprocal of z is a Complex
                                    // which multiply the oringal one equals 1, that is, 
                                    // reciprocal with original one are orthogonal
                                    // z = a + bi; z' = c + di; z' = 1/z
                                    // z * z' = 1
                                    // it is hard to implement in Cartesian,however, in 
                                    // Polar Complex, we can come with an alogrithm
                                    // delta' = -delta;
                                    // r'     = 1 / r;
        ComplexPolar ans = new ComplexPolar();
        ans.r = 1 / this.r;
        ans.delta = -this.delta;
        return ans;
    }
    ComplexPolar conjugate()        // 1 / z = z' / |z^2| = z' / r^2
    {                               // z' = r^2 * (1 / z) 
        ComplexPolar ans = new ComplexPolar();
        ans = this.reciprocal();
        ans.r *= this.r * this.r;
        return ans;
    }
    ComplexPolar exp()      // e^z = e^(r*cosx) * (cos(rsinx) + isin(rsinx))
    {
        ComplexPolar ans = new ComplexPolar();
        ans.r = Math.exp(this.r * Math.cos(this.delta * Math.PI / 180));
            // r = e^(r * cosx)
        ans.delta = this.r * Math.sin(this.delta * Math.PI / 180);
            // delta = r * sinx
        return ans;
    }
    ComplexPolar log()
    {
        ComplexCart ans_cart = new ComplexCart();   // lnz = lnr + xi 
            //We use ComplexCart to calculate
        ans_cart.real = Math.log(this.r);   // RealPart = ln(r)
        ans_cart.imag = this.delta;         // ImaginaryPart = x
        ComplexPolar ans = ans_cart.toPolar();      // transform it to ComplexPolar
        return ans;
    }
    ComplexPolar pow(double a)      // z^a = e^(alnz) = exp(a*log(z));
    {
        ComplexPolar ans = (this.log().scale(a).exp());
        return ans;
    }
    ComplexCart toCartesian()
    {
        ComplexCart ans = new ComplexCart();
        ans.real = this.r * Math.cos(this.delta * Math.PI / 180);   // Re = r * cosx
        ans.imag = this.r * Math.sin(this.delta * Math.PI / 180);   // Im = r * sinx
        return ans;
    }
    double abs()                    // the abs of ComplexPolar is its range
    {
        return this.r;
    }
    double getRealPart()            // RealPart = r * cosx
    {
        return this.r * Math.cos(this.delta * Math.PI / 180);
    }
    double getImaginaryPart()       // ImaginaryPart = r * sinx
    {
        return this.r * Math.sin(this.delta * Math.PI / 180);
    }
    boolean equals(ComplexPolar x)  // Rewrite function:
    {                               // If the subject is ComplexPolar, we can compare it easily    
        if(this.r == x.r && this.delta == x.delta)
            return true;
        else
            return false;
    }
    boolean equals(ComplexCart x)   // If the subject is ComplexCart, we should transform it
    {
        if(Math.abs(this.r * Math.cos(this.delta * Math.PI / 180) - x.real) < 1e-6 && 
                Math.abs(this.r * Math.sin(this.delta * Math.PI / 180) - x.imag) < 1e-6) 
            return true;    // In order to prevent the loss of accuracy, I don't compare it with "=="
        else                // I calculate the differnce between the numbers 
            return false;   // if they are very close, I consider it as the same
    }
    String tostring()
    {
        String ans = new String();
        if(this.r == 0)
        {
            ans = "0";
            return ans;
        }
        if(this.r < 0)              // The range of the ComplexPolar is positive
        {
            this.r = -this.r;       // if r < 0, give opposite r, and the degree is oppositr,too
            this.delta += 180.0;
        }
        while(this.delta >= 180.0)  // The domin of the ComplexPolar is [-180,180)
        {
            this.delta -= 360.0;
        }
        while(this.delta < -180.0)
        {
            this.delta += 360.0;
        }
        
        ans += this.r + "[cos(" + this.delta + ") + isin(" + this.delta + ")]" ;
        return ans;
    }
    
    public static double[] todouble(String[] nums)  // String to Double function
    {
        int size = nums.length;
        double[] ans = new double[size];
        for(int i = 0; i < size; i++)
        {
            boolean negetive = false;
            if(nums[i].charAt(0) == '-')            // deal with negetive numbers
            {
                negetive = true;                    // take the negetive signal
                nums[i] = nums[i].substring(1);     // -4214 -> 4214
            }
            int point = nums[i].indexOf('.');       // 36.25 -> 36 . 25 : sperately deal with it
            if(point == -1)                         // give a Integer number, we will return double
            {                                       //   36  -> 36.0
                int num_size = nums[i].length();
                double real_num = 0.0;
                for(int j = 0; j < num_size; j++)
                {
                    real_num *= 10;
                    real_num += nums[i].charAt(j) - '0';
                }
                if(negetive)
                    real_num = -real_num;
                ans[i] = real_num;
                continue;
            }
            String szs = nums[i].substring(0,point);// zs : (36).25
            String sxs = nums[i].substring(point+1);// xs : 36.(25)
            int xs_weishu = sxs.length();           // count the numbers, and divide corresponding 
                                                    // numbers to imitate the decimal
            double zs = 0.0;
            double xs = 0.0;
            for(int j = 0; j < szs.length(); j++)   // zs : 36
            {
                zs *= 10;
                zs += szs.charAt(j) - '0';
            }
            for(int j = 0; j < sxs.length(); j++)   // xs : 25
            {
                xs *= 10;
                xs += sxs.charAt(j) - '0';
            }
            while(xs_weishu-- != 0)     // xs : 25      xs_weishu = 2
            {                           // xs : 2.5     xs_weishu = 1
                xs /= 10;               // xs : 0.25    xs_weishu = 0 >>> over
            }

            ans[i] = zs + xs;           // ans = 36 + 0.25 = 36.25
            if(negetive)
                ans[i] = -ans[i];
        }                       
        return ans;
    }
    public static void main(String[] args)
    {
        //String[] arg = {"-12.34","80","0.45","-89"};
        int size = args.length;                 // Since data are given as String, and as Type:double
        double[] numbers = new double[size];    // We cannot simply use Integer.parseInt() to transform it
        numbers = todouble(args);               // So we have to use String methods to deal with the data
        
        ComplexPolar a = new ComplexPolar(numbers[0],numbers[1]);
        ComplexPolar b = new ComplexPolar(numbers[2],numbers[3]);
        ComplexPolar c = new ComplexPolar();
        String ans = new String();

        c = a.add(b);
        ans = c.tostring();
        System.out.println("(" + a.tostring() + ") + (" + b.tostring() + ") = " + ans);

        c = a.subtract(b);
        ans = c.tostring();
        System.out.println("(" + a.tostring() + ") - (" + b.tostring() + ") = " + ans);

        c = a.multiply(b);
        ans = c.tostring();
        System.out.println("(" + a.tostring() + ") * (" + b.tostring() + ") = " + ans);

        c = a.divide(b);
        ans = c.tostring();
        System.out.println("(" + a.tostring() + ") / (" + b.tostring() + ") = " + ans);

        c = a.reciprocal();
        ans = c.tostring();
        System.out.println(a.tostring() + "'s reciprocal is " + ans);

        c = a.conjugate();
        ans = c.tostring();
        System.out.println(a.tostring() + "'s conjugate is " + ans);
    }
}
