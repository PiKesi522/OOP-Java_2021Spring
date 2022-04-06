public class Lab_04_b_07 {
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
    
    public static double judge(double a, double b, double c)
    {
        double delta = b * b - 4 * a * c;
        return delta;
    }
    public static void main(String[] args)
    {
        double[] nums = todouble(args);
        // method:
        //         -b + sqrt(b^2 - 4ac)            -b - sqrt(b^2 - 4ac)
        // x1 =  -----------------------    x2 = ------------------------
        //                 2a                               2a
        //
        //      delta = b^2 - 4ac
        // if(delta < 0), the result is complex number, we will use ComplexCart
        // if(delta == 0), the result is the same number
        // if(delta > 0), we can calculate it with simple function
        double a = nums[0];
        double b = nums[1];
        double c = nums[2];
        double delta = judge(a, b, c);

        if(delta == 0)
        {
            double x1 = -b / (2 * a);
            double x2 = -b / (2 * a); 
            System.out.println("x1 = " + x1 + "; x2 = " + x2);
            return;
        }
        else if(delta > 0)
        {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("x1 = " + x1 + "; x2 = " + x2);
            return;
        }
        else if(delta < 0)
        {
            ComplexCart x1 = new ComplexCart();
            ComplexCart x2 = new ComplexCart();
            x1.real = -b / (2 * a);
            x2.real = -b / (2 * a);
            delta = -delta;
            x1.imag = Math.sqrt(delta);
            x2.imag = -Math.sqrt(delta);
            System.out.println("x1 = " + x1.tostring() + "; x2 = " + x2.tostring());
        }
        else        // testing bug point
        {
            System.out.println("Bug in test delta");
            return;
        }
    }
}
