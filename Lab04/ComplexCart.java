public class ComplexCart {
    double real = 0;
    double imag = 0;
    
    ComplexCart(double real,double imag) // Constructr function
    {
        this.real = real;
        this.imag = imag;
    }
    ComplexCart()                        // Default Constructr function
    {
        this.real = 0.0;
        this.imag = 0.0;
    }
    
    ComplexCart add(ComplexCart x)              
    {
        ComplexCart ans = new ComplexCart();//   a   +   b  i
        ans.real = this.real + x.real;      //   c   +   d  i
        ans.imag = this.imag + x.imag;      // (a+c) + (b+d)i
        return ans;
    }
    ComplexCart add(ComplexCart x, ComplexCart y)              
    {
        ComplexCart ans = new ComplexCart();         //   a    +    b   i
        ans.real = this.real + x.real + y.real;      //   c    +    d   i
        ans.imag = this.imag + x.imag + y.imag;      //   e    +    f   i
        return ans;                                  //(a+c+e) + (b+d+f)i
    }
    ComplexCart subtract(ComplexCart x)
    {
        ComplexCart ans = new ComplexCart();//   a   -   b  i
        ans.real = this.real - x.real;      //   c   -   d  i
        ans.imag = this.imag - x.imag;      // (a-c) - (b-d)i
        return ans;
    }
    ComplexCart multiply(ComplexCart x) // z1*z2 = (x1*x2 - y1*y2) + (x1*y2 + x2y1)i
    {
        ComplexCart ans = new ComplexCart();
        ans.real = (this.real * x.real) - (this.imag * x.imag);
        ans.imag = (this.real * x.imag) + (x.real * this.real);
        return ans;
    }
    ComplexCart scale(double f)
    {
        ComplexCart ans = new ComplexCart();
        ans.real = this.real * f;
        ans.imag = this.imag * f;
        return ans;
    }
    ComplexCart divide(ComplexCart x)   // z1/z2 = [(r1*r2 + i1*i2) + (-r1*i2 + r2*i1)i] / r2*r2 + i2*i2
    {
        ComplexCart ans = new ComplexCart();
        if(x.real == 0 && x.imag == 0)  // (a + bi) / 0 is not defined
        {
            System.out.println("the dividen cannot be zero");
            return ans;
        }
        ans.real = (this.real * x.real + this.imag * x.imag) / (x.real * x.real + x.imag * x.imag);
        ans.imag = (-this.real * x.imag + x.real * this.imag) / (x.real * x.real + x.imag * x.imag);
        // ans = this.multiply(x.reciprocal());     // I abolish this method because it use other
        // z1/z2 = z1 * (1/z2)                      // function. In order to limit the influence of 
                                                    // change other function and easy to debug
        return ans;
    }
    ComplexCart reciprocal()
    {
        ComplexCart ans = this.conjugate(); // z' = a - bi
        ans.real /= this.abs() * this.abs();// a  / |z^2|
        ans.imag /= this.abs() * this.abs();// b  / |z^2|
        return ans;
    }
    ComplexCart conjugate()                 // a + bi -> a + (-b)i
    {
        ComplexCart ans = new ComplexCart();
        ans.imag = -this.imag;
        ans.real = this.real;
        return ans;
    }
    ComplexCart exp()           // Since it's easy to calculate in ComplexPolar
    {                           // We calculate it in ComplexPolar
        ComplexPolar ans_polar = this.toPolar();
        ComplexCart ans = ans_polar.exp().toCartesian();
        return ans;
    }
    ComplexCart log()
    {
        ComplexPolar ans_polar = this.toPolar();
        ComplexCart ans = new ComplexCart();
        ans.real = Math.log(ans_polar.r);       // RealPart = ln(r)
        ans.imag = ans_polar.delta;             // ImaginaryPart = x
        return ans;
    }
    ComplexCart pow(double a)   // Since it's easy to calculate in ComplexPolar
    {                           // We calculate it in ComplexPolar
        ComplexPolar ans_polar = this.toPolar();
        ComplexCart ans = ans_polar.pow(a).toCartesian();
        return ans;
    }
    ComplexPolar toPolar()
    {
        ComplexPolar ans = new ComplexPolar();  
        ans.r = Math.sqrt(this.real * this.real + this.imag * this.imag);
                                            //   r   = sqrt(x * x + y * y)
        ans.delta = 180 * Math.acos(this.real / ans.r) / Math.PI;
                                            // delta = arccos(Real / r) 
        return ans;
    }
    double abs()                            // |z| = sqrt(x * x + y * y)
    {
        double ans = Math.sqrt(this.real * this.real + this.imag * this.imag);
        return ans;
    }
    double getRealPart()
    {
        return this.real;
    }
    double getImaginaryPart()
    {
        return this.imag;
    }
    boolean equals(ComplexCart x)           
    {
        if(this.real == x.real && this.imag == x.imag)
            return true;                    // z1 = a + bi; z2 = c + di;
        else                                // if a == c && b == d
            return false;                   // Same!!!
    }
    boolean isReal()
    {
        if(this.imag == 0)
            return true;
        else    
            return false;
    }
    String tostring()               // keep less than three decimal
    {
        String ans = new String();
        if(this.real == 0 && this.imag == 0)    // if RealNumber is zero, we don't have to print it
        {
            ans = "0";
        }
        else if(this.real == 0)     // 0 +- bi = (-)bi;
        {                           // Positive signal don't have to judge
            ans += this.imag;
            ans += "i";
        }
        else if(this.imag == 0)     // (-)a + 0i = (-)a 
        {
            ans += this.real;
        }
        else
        {
            ans += this.real;

            if(this.imag > 0)       // (-)a + (-)bi 
                ans += "+";         // if ImaginaryNumber is positive, a signal is necessary
            ans += this.imag;
            ans += "i";
        }
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
        
        ComplexCart a = new ComplexCart(numbers[0],numbers[1]);
        ComplexCart b = new ComplexCart(numbers[2],numbers[3]);
        ComplexCart c = new ComplexCart();
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
