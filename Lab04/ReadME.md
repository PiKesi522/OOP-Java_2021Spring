# Project 4 创建Java类

## Project 4.a 长整数

#### 1.实现长整数类BigInt, 支持任意精度的整数及其运算. 包含以下 API :

| 方法                      | 说明                                      |
| :------------------------ | :---------------------------------------- |
| BigInt()                  | 默认构造函数, 初始化为0                   |
| BigInt(String s)          | 使用字符串构造长整数                      |
| BigInt add(BigInt x)      | 返回与 x 的和                             |
| BigInt subtract(BigInt x) | 返回与 x 的差                             |
| BigInt multiply(BigInt x) | 返回与 x 的积                             |
| BigInt divide(BigInt x)   | 返回除以 x 后的商                         |
| BigInt mod(BigInt x)      | 返回除以 x 后的余数                       |
| int compare(BigInt x)     | 返回1 如果大于 x, -1如果小于 x, 0如果相等 |
| boolean equals(BigInt x)  | 是否等于x                                 |
| String toString()         | 返回字符串表示                            |

~~~java
    public class BigInt {
        int[] num = new int[10000];

        int len = 0;
        boolean zero = false;       // whether this number is 0?
        boolean negetive = false;   // whether this number is below 0?
                                    // if negetive, we will take the signal 
                                    // and keep the number as positive

        BigInt()                    // default constructor function
        {
            this.zero = true;
        }
        BigInt(String s)            // constructor function
        {
            this.len = s.length();
            int i = 0;
            if(s.charAt(0) == '-')      // for example: -12345
            {
                i = 1;                  // we only use: 12345
                this.negetive = true;   // signal negetive is sperately processed
            }
            else if(s.charAt(0) == '0')
            {
                this.zero = true;
                return;
            }

            for(; i < len; i++)
            {
                this.num[(len - 1) - i] = s.charAt(i) - '0';
            }

            if(negetive)
                this.len--;             // the negetive signal is useless
        }
        BigInt(int i)               // self-made constructor function
        {
            if(i == 0)
            {
                this.zero = true;
                return;
            }

            if(i < 0)
            {
                this.negetive = true;
                i = -i;             // take the signal and deal with it as positve number
            }

            int[] ans_num = new int[10000];
            while(i != 0)
            {                               
                int temp = i % 10;          // int i = 12345
                i /= 10;
                this.num[this.len++] = temp;// num[] = [5][4][3][2][1]
            }
        }
        BigInt(BigInt big)          // Shallow copy
        {
            this.len = big.len;
            this.negetive = big.negetive;
            this.zero = big.zero;
            for(int i = 0; i < this.len; i++)
            {
                this.num[i] = big.num[i];
            }
        }
        BigInt add(BigInt x)        // To make it easy to calculate, only two positive number is availble
        {                           // so we have to deal with the data before calculation
            BigInt ans = new BigInt();
            ans.zero = false;
            if(this.zero)
                return x;
            if(x.zero)
                return this;
            if(this.negetive && x.negetive)         // (-1) + (-2) = -(1 + 2)
            {
                ans.negetive = true;
            }
            else if(this.negetive && !x.negetive)   // (-1) + 2 = 2 - 1
            {
                BigInt tempthis = new BigInt(this);
                tempthis.negetive = false;
                ans = x.subtract(tempthis);
                return ans;
            }
            else if(!this.negetive && x.negetive)   // 1 + (-2) = 1 - 2
            {
                BigInt tempx = new BigInt(x);
                tempx.negetive = false;
                ans = this.subtract(tempx);
                return ans;
            }
            else if(!this.negetive && !x.negetive)  // 1 + 2 = 1 + 2
            {
                // default situation 
            }
            
            BigInt longer = new BigInt();
            longer.zero = false;
            BigInt shorter = new BigInt();
            shorter.zero = false;
            if(len > x.len)  // We find the longer number to determine the calculation
            {
                longer = this;
                shorter = x;
            }
            else
            {
                longer = x;
                shorter = this;
            }

            boolean jin_wei = false;
            int temp = 0;
            for(int i = 0; i < longer.len; i++) 
            {
                if(i < shorter.len)     // if both number is availble, we will add both of them
                    temp = shorter.num[i] + longer.num[i];
                else                    // else we only inherit the longer number
                    temp = longer.num[i];
                if(jin_wei)             // if jin_wei is needed, the present number + 1
                {
                    jin_wei = false;
                    temp++;
                }

                if(temp >= 10)          // if present number over 10, it should be changed
                {
                    temp -= 10;
                    jin_wei = true;     // and give 1 to next number
                }
                else
                    jin_wei = false;

                ans.num[ans.len++] = temp;
            }

            if(jin_wei)
            {
                jin_wei = false;
                ans.num[ans.len++] = 1;
            }
            return ans;
        }
        BigInt multiply(BigInt x)
        {
            BigInt ans = new BigInt();
            ans.zero = false;
            if(zero || x.zero)      // One of the number is zero 
            {
                ans.zero = true;
                return ans;
            }

            if(this.negetive == x.negetive)     // if both of the number has same signal
                ans.negetive = false;           // the ans is positive
            else        
                ans.negetive = true;            // otherwise ans is negetive

            for(int i = 0; i < x.len; i++)      // traversal x and choose all the number to multiply self-number
            {
                for(int j = 0; j < len; j++)    // traversal self-number and multiply with x to calculate the answer
                {
                    ans.num[j + i] += x.num[i] * num[j];
                    if(ans.num[j + i] >= 10)                // if the temproray answer is above 10
                    {
                        int jin_wei = ans.num[j + i] / 10;  // The part we don't need it and give it to next number
                        ans.num[j + i] %= 10;               // Now we have temproray correct answer
                        ans.num[j + i + 1] += jin_wei;      // The next number have all the jin_wei
                    }
                }
            }
            int big = ans.num[len + x.len - 1];
            if(big == 0)
            {
                ans.len = len + x.len - 1;
                return ans;
            }
            else
            {
                ans.len = len + x.len;
                return ans;
            }
        }
        BigInt subtract(BigInt x)   // to make it easy to calculate, only two positive number is availble
        {                           // so we have to deal with the data before calculation
                                    // Furthermore, we can make it bigger one subtract smaller one
            BigInt ans = new BigInt();
            ans.zero = false;
            if(this.compare(x) == 0)// a - a = 0
            {
                ans.zero = true;
                return ans;
            }
            if(this.zero)           // 0 - (-5) =  5
            {                       // 0 -  5   = -5
                x.negetive = !x.negetive;
                return x;
            }
            if(x.zero)              // (-5) - 0 = -5
                return this;        //  5   - 0 =  5
            if(this.negetive && x.negetive)         // (-1) - (-2) = -(1 - 2) =  1
            {                                       // (-2) - (-1) = -(2 - 1) = -1
                BigInt tempthis = new BigInt(this); // Compared to BigInt.add:
                BigInt tempx = new BigInt(x);       // subtract may lead to negetive number.
                tempthis.negetive = false;          // So we have to give two copy of these number
                tempx.negetive = false;             // both of them is postive 
                ans = tempthis.subtract(tempx);     // And finally, we change the signal
                ans.negetive = !ans.negetive;
                return ans;
            }
            else if(this.negetive && !x.negetive)   // (-1) - 2 = -(2 + 1)
            {
                BigInt tempthis = new BigInt(this);
                tempthis.negetive = false;
                ans = tempthis.add(x);
                ans.negetive = true;
                return ans;
            }
            else if(!this.negetive && x.negetive)   // 1 - (-2) = 1 + 2
            {
                BigInt tempx = new BigInt(x);
                tempx.negetive = false;
                ans = this.add(tempx);
                return ans;
            }
            else if(!this.negetive && !x.negetive)  // 1 - 2 = 1 - 2
            {
                // default situation
            }

            if(this.compare(x) == -1)   // To make it simple, we only make it bigger num subtract smaller one
            {                           // And give the result a negetive signal
                BigInt tempthis = new BigInt(this); // 3 - 5 = -(5 - 3) < 0
                ans = x.subtract(tempthis);
                ans.negetive = true;
                return ans;
            }

            boolean tui_wei = false;
            for(int i = 0; i < this.len; i++)
            {
                int temp = 0;
                if(i < x.len)
                    temp = this.num[i] - x.num[i];                
                else
                    temp = this.num[i] - 0;
                
                if(tui_wei)
                {
                    temp--;
                    tui_wei = false;
                }

                if(temp < 0)
                {
                    temp += 10;
                    tui_wei = true;
                }
                else   
                    tui_wei = false;

                ans.num[ans.len++] = temp;
            }
            for(int j = ans.len - 1; j >= 0; j--)
            {
                if(ans.num[j] == 0)
                    ans.len--;
                else
                    break;
            }
            return ans;
        }
        BigInt divide(BigInt x)     // To make it easy to calculate, only two positive number is availble
        {                           // so we have to deal with the data before calculation
            BigInt ans = new BigInt();
            ans.zero = false;
            if(x.zero)      // (? / 0) is undefined
            {
                System.out.println("the dividend cannot be zero!");
                return ans;
            }
            if(this.zero)   // (0 / ?) is zero
            {
                ans.zero = true;
                return ans;
            }

            if(this.negetive == x.negetive)     // judge the ans is postive or not
                ans.negetive = false;           // and there is no need to handle the signal again
            else if(this.negetive != x.negetive)
                ans.negetive = true;

            BigInt posthis = new BigInt(this);  // we create two positive number to simplify the caculation
            BigInt posx = new BigInt(x);
            posthis.negetive = false;   
            posx.negetive = false;

            if(posthis.compare(posx) == 0)          // 5 / 5 == 1
            {
                ans.len = 1;
                ans.num[0] = 1;
                return ans;
            }
            else if(posthis.compare(posx) == -1)    // 3 / 5 == 0
            {
                ans.zero = true;
                return ans;
            }

            int differ_len = this.len - x.len;
            if(differ_len > 0)
            {
                for(int i = posx.len - 1; i >= 0; i--)
                    posx.num[i + differ_len] = posx.num[i];
                for(int i = 0; i < differ_len; i++)
                    posx.num[i] = 0;
                posx.len += differ_len;
            }

            int[] ans_num = new int[10000];
            boolean start = false;
            do                                      // test: 12345 / 23
            {                                       // 1.    12345 < 23000 not start
                if(posthis.compare(posx) == 0)      // 2.    12345 / 2300  >>> 5 (start here)
                {                                   // 3.    845   / 230   >>> 3
                    ans_num[ans.len++] = 1;         // 4.    155   / 23    >>> 6
                    break;                          // 5.    posx: 23 == x: 23
                }                                   //         break;
                else if(posthis.compare(posx) == -1)//       answer: 536
                {
                    if(start)
                        ans_num[ans.len++] = 0;
                }
                else
                {
                    int times = 0;
                    while(posthis.compare(posx) != -1)
                    {
                        posthis = posthis.subtract(posx);
                        times++;
                    }
                    start = true;
                    ans_num[ans.len++] = times;
                }

                posx.len--;                         // 23000 -> 2300
                for(int i = 1; i <= posx.len; i++)  // 2 3 0 0 0 
                {                                   //  \ \ \ \
                    posx.num[i - 1] = posx.num[i];  // 2|2 3 0 0|
                }                                   //  |  used |
                    posx.num[posx.len] = 0;         // 0|2 3 0 0|

            }while(posx.len >= x.len);              // 230...0 == 23 ?
            for(int i = 0; i < ans.len; i++)
                ans.num[ans.len - 1 - i] = ans_num[i]; 
            
            return ans;
        }
        BigInt mod(BigInt x)
        {
            BigInt ans = new BigInt();
            ans.zero = false;
            if(x.zero)      // (? % 0) is undefined
            {
                System.out.println("the modend cannot be zero!");
                return ans;
            }
            ans = this.subtract(this.divide(x).multiply(x));    // a % b = r 
            return ans;                                         // r = a - (a/b)*b
        }
        int compare(BigInt x)       // To makt it easy to calculate, only two positive number is availble
        {                           // other situation will be change to two positive number or compare signal
            if(!this.negetive && x.negetive)        // 1 > (-2)
            {
                return 1;
            }
            else if(this.negetive && !x.negetive)   // (-1) < 2
            {
                return -1;
            }
            else if(this.zero)      // number is zero
            {
                if(x.zero)          // 0 == 0
                    return 0;
                else if(x.negetive) // 0 > (-2)
                    return 1;
                else if(!x.negetive)// 0 < 2
                    return -1;
                else
                {
                    System.out.println("Bug in compare 'if:this.zero'");
                    return 0;
                }
            }
            else if(x.zero)         // the other number is zero
            {
                if(this.negetive)       // (-1) < 0
                    return -1;
                else if(!this.negetive) // 1 > 0
                    return 1;
                else
                {
                    System.out.println("Bug in compare 'if:x.zero'");
                    return 0;
                }
            }
            else if(this.negetive && x.negetive)    // (-1) > (-2)
            {                                       //   2  >   1
                BigInt tempthis = new BigInt(this);
                BigInt tempx = new BigInt(x);
                tempx.negetive = false;
                tempthis.negetive = false;  
                int ans = tempx.compare(tempthis);      
                return ans;
            }
            
            if(this.len > x.len)    // general situation: two positive numbers
                return 1;           // check the bits that each of them have
            else if(this.len < x.len)
                return -1;
            else
            {
                for(int i = 0; i < this.len; i++)
                {
                    if(this.num[this.len - 1 - i] > x.num[x.len - 1 - i])
                        return 1;
                    else if(this.num[this.len - 1 - i] < x.num[x.len - 1 - i])
                        return -1;
                }
                return 0;
            }
        }
        boolean equals(BigInt x)    // Use BigInt.compare, if consequence is zero, represent that 
        {                           // they are the same
            if(this.compare(x) == 0)
                return true;
            return false;
        }
        String tostring()           // Rename the function since it is the same to another function in Java
        {
            String ans = new String();
            if(this.zero)
                return "0";
            if(this.negetive)
                ans += "-";
            for(int i = 0; i < this.len; i++)
            {
                ans += this.num[this.len - 1 - i];
            }
            return ans;
        }
        public static void main(String[] args){
            BigInt a = new BigInt(args[0]);
            BigInt b = new BigInt(args[1]);
            
            BigInt c = new BigInt();
            String ans = new String();

            c = a.add(b);
            ans = c.tostring();
            System.out.println(args[0] + " + " + args[1] + " = " + ans);

            c = a.subtract(b);
            ans = c.tostring();
            System.out.println(args[0] + " - " + args[1] + " = " + ans);

            c = a.multiply(b);
            ans = c.tostring();
            System.out.println(args[0] + " * " + args[1] + " = " + ans);

            c = a.divide(b);
            ans = c.tostring();
            System.out.println(args[0] + " / " + args[1] + " = " + ans);

            c = a.mod(b);
            ans = c.tostring();
            System.out.println(args[0] + " % " + args[1] + " = " + ans);
        }
    }
~~~

~~~cmd
PS C:\Java_for_vscode> javac BigInt.java
PS C:\Java_for_vscode> java BigInt 12321521 -123140
12321521 + -123140 = 12198381
12321521 - -123140 = 12444661
12321521 * -123140 = -1517272095940
12321521 / -123140 = -100
12321521 % -123140 = 7521
~~~



#### 2.使用 BigInt 计算阶乘函数 n! 及指数函数 2^n.

~~~java
public class Lab_04_a_02 {
    public static void main(String[] args)
    {
        BigInt N1 = new BigInt(args[0]);
        //BigInt N1 = new BigInt("14");
        BigInt one = new BigInt(1);         // Using my self-made consturctor function within Integer
        BigInt two = new BigInt(2);         // Using my self-made consturctor function within Integer

        BigInt i = new BigInt(1);
        BigInt fac_N = new BigInt(1);
        BigInt two_fac = new BigInt(1);         
        for(; i.compare(N1) != 1; i = i.add(one))  //   1 * 2 * 3 ...*(N-1)*N  => N!       
        {                                          //   | +1  +1 ..... +1  +1 |
            fac_N = fac_N.multiply(i);             // start                  end
            two_fac = two_fac.multiply(two);       //   |                     |
        }                                          //   2 *2 *2 ...... *2 *2 *2=> 2^N
        System.out.println(args[0] + "! = " + fac_N.tostring());
        System.out.println("-------------------------------------------");
        System.out.println("2^(" + args[0] + ") = " + two_fac.tostring());
    }
}
~~~

~~~cmd
PS C:\Java_for_vscode> javac Lab_04_a_02.java
PS C:\Java_for_vscode> java Lab_04_a_02 12   
12! = 479001600
-------------------------------------------
2^(12) = 4096
PS C:\Java_for_vscode> java Lab_04_a_02 123
123! = 12146304367025329675766243241881295855454217088483382315328918161829235892362167668831156960612640202170735835221294047782591091570411651472186029519906261646730733907419814952960000000000000000000000000000
-------------------------------------------
2^(123) = 10633823966279326983230456482242756608
~~~



#### 3.比较 BigInt 与 int 的性能差异 (例如做多次运算时间上的区别). 并尝试分析原因.

~~~txt
Int直接通过数字电路和模拟信号通过二进制进行操作计算，而BigInt每一个数字都是通过十进制进行计算，而且还有冗余的数组移位操作
~~~

#### 4.比较 BigInt 与 Java 自中 BigInteger 的性能差异, 并尝试分析原因.

~~~txt
可不敢乱比，BigInt有四分之一BigInteger的性能就厉害了，别被人告收12亿刀就remake了
~~~

#### 5.除了已经使用的方法外, 你能否想到其他方法来实现 BigInt ?

~~~txt
或许通过C++里的STL等容器来进行数组操作可以减少数组内部运算时间
~~~



## Project 4.b 复数

#### 1.定义 ComplexCart 类, 它使用笛卡尔坐标系来代表复数, 包含以下 API 且为不可变的 (immutable).

| 方法                                  | 说明        |
| :------------------------------------ | :---------- |
| ComplexCart(double real, double imag) | 构造函数    |
| ComplexCart add(ComplexCart x)        | 返回与x的和 |
| ComplexCart subtract(ComplexCart x)   | 返回与x的差 |
| ComplexCart multiply(ComplexCart x)   | 返回与x的积 |
| ComplexCart divide(ComplexCart x)     | 返回与x的商 |
| ComplexCart reciprocal()              | 返回倒数    |
| ComplexCart conjugate()               | 返回共轭    |
| double abs()                          | 返回模长    |
| double getRealPart()                  | 返回实部    |
| double getImaginaryPart()             | 返回虚部    |
| boolean equals(ComplexCart x)         | 是否等于x   |
| String toString()                     | 字符串表示  |

~~~java
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
            int point = nums[i].indexOf('.');       
            					// 36.25 -> 36 . 25 : sperately deal with it
            if(point == -1)     // give a Integer number, we will return double
            {                   //   36  -> 36.0
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
            int xs_weishu = sxs.length();           
            					// count the numbers, and divide corresponding 
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
~~~

~~~cmd
PS C:\Java_for_vscode> javac ComplexCart.java
PS C:\Java_for_vscode> java ComplexCart -12.34 0 0.45 -2.03
(-12.34) + (0.45-2.03i) = -11.89-2.03i
(-12.34) - (0.45-2.03i) = -12.79+2.03i
(-12.34) * (0.45-2.03i) = -5.553+19.497199999999996i
(-12.34) / (0.45-2.03i) = -1.2844057917379843-5.794097238284684i
-12.34's reciprocal is -0.08103727714748785
-12.34's conjugate is -12.34
PS C:\Java_for_vscode> java ComplexCart -12.4 0.99 124.3 -2.03   
(-12.4+0.99i) + (124.3-2.03i) = 111.89999999999999-1.0399999999999998i
(-12.4+0.99i) - (124.3-2.03i) = -136.7+3.0199999999999996i
(-12.4+0.99i) * (124.3-2.03i) = -1539.3102999999999-1516.148i
(-12.4+0.99i) / (124.3-2.03i) = -0.09986208711343228+0.006333708472725121i
-12.4+0.99i's reciprocal is -0.0801343672390027-0.006397824481178441i     
-12.4+0.99i's conjugate is -12.4-0.99i
~~~

#### 2.思考并回答以下问题:

- 在 API 中加入方法 `ComplexCart add(ComplexCart x, ComplexCart y)` (返回三个复数的和)是否合适?

  ~~~java
  ComplexCart add(ComplexCart x, ComplexCart y)              
  {
      ComplexCart ans = new ComplexCart();         //   a    +    b   i
      ans.real = this.real + x.real + y.real;      //   c    +    d   i
      ans.imag = this.imag + x.imag + y.imag;      //   e    +    f   i
      return ans;                                  //(a+c+e) + (b+d+f)i
  }
  ~~~

  ~~~java
  可以节约繁杂操作，可行
  ~~~

- 在 API 中加入方法 `ComplexCart scale(double f)` (返回与一个实数的乘积) 是否合适?

  ~~~java
  ComplexCart scale(double f)
  {
      ComplexCart ans = new ComplexCart();
      ans.real = this.real * f;
      ans.imag = this.imag * f;
      return ans;
  }
  ~~~

  ~~~txt
  可以通过.multiply乘上一个虚数部分为零的复数来操作，额外写.scale也提高了工作效率
  ~~~

  在 API 中加入方法 `boolean isReal()` (返回是否是实数) 是否合适?

  ~~~java
  boolean isReal()
  {
      if(this.imag == 0)
          return true;
      else    
          return false;
  }
  ~~~

- 在 API 中加入方法 `void setRealPart(double a)` (将实部修改为a) 是否合适?

  ~~~java
  不可以，题目中要求本类为不可改变类型
  ~~~

- 在 API 中去掉方法 `double abs()` 是否合适? (由定义, 模长可以通过乘法计算得到)

  ~~~txt
  可以删去，但是会使得其他函数实现变得复杂，可读性较差。
  ~~~

- 在 API 中去掉除法 `ComplexCart divide(ComplexCart x)` 是否合适? (由定义, 除法可以由倒数和乘法得到)

  ~~~txt
  可以删去，但是若是删去，并不如除法更为直观和可读性强
  ~~~

- 在 API 中去掉方法 `double getRealPart()` 是否合适? (直接访问实部)

  ~~~txt
  本题中可行，但是若是内部属性变为protected，则无法访问
  ~~~

- 以下两种使用方法哪一种更好? 为什么?

  ```
  ComplexCard a = new ComplexCart(1, 2);
  ComplexCard b = new ComplexCart(3, 4);
  
  double real = a.getRealPart() + b.getRealPart();
  double imag = a.getImaginaryPart() + b.getImaginaryPart();
  ComplexCard c = new ComplexCart(real, imag);
  ```

  ```txt
  ComplexCard a = new ComplexCart(1, 2);
  ComplexCard b = new ComplexCart(3, 4);
  ComplexCard c = a.add(b);
  ```

  ~~~txt
  第二种更好，调用了API，能够简化代码，可读性强
  ~~~

  

#### 3.使用极坐标实现复数类 ComplexPolar , 包含相同的 API .

~~~java
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
        ComplexPolar ans_ploar = new ComplexPolar(); // use Cartesian to calculate

        ComplexPolar this_polar = new ComplexPolar(this);  	// use shallow copy
        ComplexPolar x_polar = new ComplexPolar(x);  // not to change the origin
        ComplexCart this_cart = this_polar.toCartesian();
        ComplexCart x_cart = x_polar.toCartesian();

        ans_ploar = this_cart.add(x_cart).toPolar();
        return ans_ploar;
    }
    ComplexPolar subtract(ComplexPolar x)
    {   
        ComplexPolar ans_ploar = new ComplexPolar(); // use Cartesian to calculate

        ComplexPolar this_polar = new ComplexPolar(this); // use shallow copy
        ComplexPolar x_polar = new ComplexPolar(x);  // not to change the origin
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
    ComplexPolar reciprocal()  // given that z = r(cosx + isinx) = a + bi
    {                          // we can find that the reciprocal of z is a Complex
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
        ComplexPolar ans = ans_cart.toPolar(); // transform it to ComplexPolar
        return ans;
    }
    ComplexPolar pow(double a)      // z^a = e^(alnz) = exp(a*log(z));
    {
        ComplexPolar ans = (this.log().scale(a).exp());
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
    {               // If the subject is ComplexPolar, we can compare it easily    
        if(this.r == x.r && this.delta == x.delta)
            return true;
        else
            return false;
    }
    boolean equals(ComplexCart x)   
    {        		// If the subject is ComplexCart, we should transform it
        if(Math.abs(this.r * Math.cos(this.delta * Math.PI / 180) - x.real) < 1e-6 && 
                Math.abs(this.r * Math.sin(this.delta * Math.PI / 180) - x.imag) < 1e-6) 
            return true;    
        	// In order to prevent the loss of accuracy, I don't compare it with "=="
        else                // I calculate the differnce between the numbers 
            return false;   // if they are very close, I consider it as the same
    }
    ComplexCart toCartesian()
    {
        ComplexCart ans = new ComplexCart();
        ans.real = this.r * Math.cos(this.delta * Math.PI / 180);// Re = r * cosx
        ans.imag = this.r * Math.sin(this.delta * Math.PI / 180);// Im = r * sinx
        return ans;
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
            this.r = -this.r;       // if r < 0, give opposite r
            this.delta += 180.0;	// and the degree is opposite,too
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
            int point = nums[i].indexOf('.');       
            					// 36.25 -> 36 . 25 : sperately deal with it
            if(point == -1)     // give a Integer number, we will return double
            {                   //   36  -> 36.0
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
            int xs_weishu = sxs.length();           
            					// count the numbers, and divide corresponding 
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
~~~

~~~java
PS C:\Java_for_vscode> javac ComplexPolar.java     
PS C:\Java_for_vscode> java ComplexPolar 4 30 6 30 
(4.0[cos(30.0) + isin(30.0)]) + (6.0[cos(30.0) + isin(30.0)]) = 10.0[cos(29.999999999999993) + isin(29.999999999999993)]
(4.0[cos(30.0) + isin(30.0)]) - (6.0[cos(30.0) + isin(30.0)]) = 1.9999999999999998[cos(150.00000000000003) + isin(150.00000000000003)]    
(4.0[cos(30.0) + isin(30.0)]) * (6.0[cos(30.0) + isin(30.0)]) = 24.0[cos(60.0) + isin(60.0)]
(4.0[cos(30.0) + isin(30.0)]) / (6.0[cos(30.0) + isin(30.0)]) = 0.6666666666666666[cos(0.0) + isin(0.0)]
4.0[cos(30.0) + isin(30.0)]'s reciprocal is 0.25[cos(-30.0) + isin(-30.0)]
4.0[cos(30.0) + isin(30.0)]'s conjugate is 4.0[cos(-30.0) + isin(-30.0)]
PS C:\Java_for_vscode> java ComplexPolar 4 30 6 60 
(4.0[cos(30.0) + isin(30.0)]) + (6.0[cos(60.0) + isin(60.0)]) = 9.673118389725882[cos(48.067537291975036) + isin(48.067537291975036)]
(4.0[cos(30.0) + isin(30.0)]) - (6.0[cos(60.0) + isin(60.0)]) = 3.229671905681279[cos(81.73803380029015) + isin(81.73803380029015)]
(4.0[cos(30.0) + isin(30.0)]) * (6.0[cos(60.0) + isin(60.0)]) = 24.0[cos(90.0) + isin(90.0)]
(4.0[cos(30.0) + isin(30.0)]) / (6.0[cos(60.0) + isin(60.0)]) = 0.6666666666666666[cos(-30.0) + isin(-30.0)]
4.0[cos(30.0) + isin(30.0)]'s reciprocal is 0.25[cos(-30.0) + isin(-30.0)]
4.0[cos(30.0) + isin(30.0)]'s conjugate is 4.0[cos(-30.0) + isin(-30.0)]
~~~



#### 4.为ComplexCart类增加方法 `ComplexPolar toPolar()` 返回它的极坐标表示.                                                同样, 为 ComplexPolar  增加方法  `ComplexCart toCartesian()` 返回它的笛卡尔坐标表示.

~~~java
ComplexPolar toPolar()
{
    ComplexPolar ans = new ComplexPolar();  
    ans.r = Math.sqrt(this.real * this.real + this.imag * this.imag);
    //   r   = sqrt(x * x + y * y)
    ans.delta = 180 * Math.acos(this.real / ans.r) / Math.PI;
    // delta = arccos(Real / r) 
    return ans;
}

ComplexCart toCartesian()
{
    ComplexCart ans = new ComplexCart();
    ans.real = this.r * Math.cos(this.delta * Math.PI / 180);   // Re = r * cosx
    ans.imag = this.r * Math.sin(this.delta * Math.PI / 180);   // Im = r * sinx
    return ans;
}
~~~



#### 5.为 ComplexPolar 类增加下列 API.

| 方法                       | 说明             |
| :------------------------- | :--------------- |
| ComplexPolar exp()         | 返回 e^z         |
| ComplexPolar log()         | 返回 lnz (取k=0) |
| ComplexPolar pow(double a) | 返回 z^a (取k=0) |

~~~java
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
~~~



#### 6.为 ComplexCart 类增加以上 API.

~~~java
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
~~~



#### 7.给定二元一次方程, ax^2+bx+c=0, 输出它的根.

~~~java
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
            int point = nums[i].indexOf('.');       
            					// 36.25 -> 36 . 25 : sperately deal with it
            if(point == -1)     // give a Integer number, we will return double
            {                   //   36  -> 36.0
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
            int xs_weishu = sxs.length();           
            					// count the numbers, and divide corresponding 
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
        // delta = b^2 - 4ac
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
~~~

~~~cmd
PS C:\Java_for_vscode> javac Lab_04_b_07.java
PS C:\Java_for_vscode> java Lab_04_b_07 1 2 1
x1 = -1.0; x2 = -1.0
PS C:\Java_for_vscode> java Lab_04_b_07 4 5 6
x1 = -0.625+8.426149773176359i; x2 = -0.625-8.426149773176359i
PS C:\Java_for_vscode> java Lab_04_b_07 3 -1 5
x1 = 0.16666666666666666+7.681145747868608i; x2 = 0.16666666666666666-7.681145747868608i
PS C:\Java_for_vscode> java Lab_04_b_07 1 3 -4
x1 = 1.0; x2 = -4.0
~~~



## Project 4.c 分形

| 方法                                                     | 说明                                                         |
| :------------------------------------------------------- | :----------------------------------------------------------- |
| StdDraw.line(double x1, double y1, double x2, double y2) | 画一条从 (x1, y1) 到 (x2, y2) 的直线                         |
| StdDraw.setXscale(double minx, double maxx)              | 画纸 X 轴最左边坐标为 minx, 最右边坐标为 maxx, 默认情况分别为0和1 |
| StdDraw.setYscale(double miny, double maxy)              | 画纸 Y 轴最下边坐标为 miny, 最上边坐标为 maxy, 默认情况分别为0和1 |

#### 1.利用 StdDraw 设计一个类 Turtle，并使用 Turtle 类画出以下三类图片

Turtle类包含数据成员(x, y)表示当前所在的坐标点, 数据成员angle表示当前画笔前进的角度, 

方法turnLeft(delta)表示把当前画笔角度左转delta度, goForward(step)表示画笔从当前位置沿当前角度画一条长度为step的线

~~~java
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
                      +-+--O <-(x1,y1)          step = x ^ 2 + y ^ 2 = |p1 - p0|
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
        int AllDegree = 180 * (N - 2);  // In any figure, the sum of degree of
                                        // angles are 180*(N-2)"
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
    }
}
~~~

<img src="C:\Java_for_vscode\NEdge.png" alt="NEdge" style="zoom:33%;" />  <img src="C:\Java_for_vscode\Spiral.png" alt="Spiral" style="zoom:33%;" />  <img src="C:\Java_for_vscode\Brownian.png" alt="Brownian" style="zoom:33%;" /> 



#### 2.绘制 Koch 雪花. 接收命令行参数 N, 表示图形的阶数 (以下实验中, 参数均通过命令行给出.)

~~~java
//---------- In Turtle.java ----------//
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
void Kochsnowflake(int N)
{
    this.initialize();
    KochCurve(N);
    this.turnRight(120);
    KochCurve(N);
    this.turnRight(120);
    KochCurve(N);
}
//---------- In KochSnowFlake.java ----------//
public class KochSnowFlake {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-50,150);
        StdDraw.setYscale(-100,100);
        //pen.KochCurve(5);
        //pen.Kochsnowflake(5);
        pen.Kochsnowflake(Integer.parseInt(args[0]));
    }
}
~~~

<img src="C:\Java_for_vscode\KochSnow.png" alt="KochSnow" style="zoom:50%;" /> 

#### 3.在绘制 Koch 曲线时, 将其中的顺时针替换成逆时针, 逆时针替换成顺时针. 用修改后的 Koch 曲线绘制 Koch 雪花.

~~~java
//---------- In Turtle.java ----------//
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
void REKochsnowflake(int N)
{
    this.initialize();
    REKochCurve(N);
    this.turnRight(120);
    REKochCurve(N);
    this.turnRight(120);
    REKochCurve(N);
}
//---------- In KochSnowFlakeRe.java ----------//
public class KochSnowFlakeRe {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-100,100);
        StdDraw.setYscale(-100,100);
        //pen.REKochCurve(5);
        //pen.REKochsnowflake(5)
        pen.REKochsnowflake(Integer.parseInt(args[0]));
    }
}
~~~

<img src="C:\Java_for_vscode\KochSnowRe.png" alt="KochSnowRe" style="zoom:50%;" /> 

#### 4.绘制以下分形(任选三个, 提示：使用 Turtle 类).

- ##### Dragon curve

~~~java
//---------- In Turtle.java ----------//
void DragonCurve(int N)
{                               //   The DragonCurve is a figure that 
    if(N==1)                    // from the beginning of the figure,we  
    {                           // move to the destination in certain steps.
        this.goForward(0.5);    //   Then, in the destination, turn left 90"
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
//---------- In DragonCurve.java ----------//
public class DragonCurve{
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-200,200);
        StdDraw.setYscale(-200,200);
        //pen.DragonCurve(15);
        pen.DragonCurve(Integer.parseInt(args[0]));
    }
}
~~~

<img src="C:\Java_for_vscode\DargonCurve.png" alt="DargonCurve" style="zoom:50%;" /> 



- ##### Minkowski Curve

~~~java
//---------- In Turtle.java ----------//
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
//---------- In Minkowski.java ----------//
public class MinkowskiCurve {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-1,2);
        StdDraw.setYscale(-1,2);
        pen.Minkowski(Integer.parseInt(args[0]),5);
    }
}
~~~

<img src="C:\Java_for_vscode\Minkowski.png" alt="Minkowski" style="zoom:50%;" /> 

- ##### Gosper island

~~~java
//---------- In Turtle.java ----------//
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
//---------- In GosperIsland.java ----------//
public class GosperIsland {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-3,7);
        StdDraw.setYscale(-4,6);
        pen.Gosper(Integer.parseInt(args[0]),2);
    }
}
~~~

<img src="C:\Java_for_vscode\GosperIsland.png" alt="GosperIsland" style="zoom:50%;" /> 



#### 5.你能否设计出自己的分形?	

~~~java
//---------- In Turtle.java ----------//
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

//---------- In wbsecy.java ----------//
public class wbsecy {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-10,10);
        StdDraw.setYscale(-12,8);
        pen.wbsecy(Integer.parseInt(args[0]),3);
    }
}
~~~

<img src="C:\Java_for_vscode\wbsecy.png" alt="wbsecy" style="zoom:40%;" /> 
