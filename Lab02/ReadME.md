# Project 2 

## 基本类型和控制语句



#### 1.以下表达式是否能够通过编译? 如果能够通过, 表达式的类型是什么? 表达式的值是什么? 请尝试解释原因.

##### 1）int类型 与 boolean类型

~~~java
public class Lab_02_01 {
    public static void main(String[] args)
    {
        System.out.println(1 == True);
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:
        True cannot be resolved to a variable
~~~

```java
public class Lab_02_01 {
    public static void main(String[] args)
    {
        System.out.println(1 == true);
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:
        The operator == is undefined for the argument type(s) int, boolean
```

```java
public class Lab_02_01 {
    public static void main(String[] args)
    {
        System.out.println(0 == false);
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:
        The operator == is undefined for the argument type(s) int, boolean
```

##### 2）int/float类型 与 char/String类型

~~~java
public class Lab_02_01 {
    public static void main(String[] args)
    {
        System.out.println(2 + "ab");
        System.out.println(2.3 + "ab");
        System.out.println(2.3 + 'a');
    }
}

>>>2ab		字符数组类型：将‘2’视作字符进行拼接
>>>2.3ab	字符数组类型：将‘2.3’视作三个字符拼接
>>>99.3		浮点型：将‘a’的Ascii码‘97’进行转换
>>>99		整型：将‘a’的Ascii码‘97’进行转换
~~~

```java
public class Lab_02_01 {
    public static void main(String[] args)
    {
        System.out.println(2* "ab");
    }
}
Exception in thread "main" java.lang.Error: Unresolved compilation problem:
        The operator * is undefined for the argument type(s) int, String
```

```java
public class Lab_02_01 {
    public static void main(String[] args)
    {
        System.out.println(2* 'a');
    }
}

>>> 194		整型：将‘a’的Ascii码‘97’进行转换
```

##### 3）int/float 之间

```java
public class Lab_02_01 {
    public static void main(String[] args)
    {
        System.out.println(1 + 1.0);
        System.out.println(1 / 3);
        System.out.println(1.0 / 3);
    }
}

>>>2.0		浮点型：整型朝浮点型转化
>>>0		整型：整型除法，使用去尾，保留整数
>>>0.3333...双精度浮点型：浮点数除法，除不尽转为double
```



#### 2.假设`int a = 2147483647;` (即, `Integer.MAX_VALUE`). 请问以下语句的输出值是什么? 并解释原因.

```java
public class Lab_02_02 {
    public static void main(String[] args){
        int a = 2147483647;
        System.out.println(a);
        System.out.println(a + 1);
        System.out.println(2 - a);
        System.out.println(-2 - a);
        System.out.println(2 * a);
        System.out.println(4 * a);
    }
}

>>>2147483647	:01111111 11111111 11111111 11111111
>>>-2147483648	:10000000 00000000 00000000 00000000
    
>>>-2147483645	: 2	- a = 10000000 00000000 00000000 00000001
    			        + 00000000 00000000 00000000 00000010
    					----------------------------------------
    			 		  10000000 00000000 00000000 00000011
    
>>>2147483647	: -2 - a = 10000000 00000000 00000000 00000001
    			         + 11111111 11111111 11111111 11111110
    					-----------------------------------------
    					1| 01111111 11111111 11111111 11111111				
    
>>>-2			:  a + a = 01111111 11111111 11111111 11111111
    			     	 + 01111111 11111111 11111111 11111111
    					-----------------------------------------
    				  溢位| 11111111 11111111 11111111 11111110
    
>>>-4			:  2a + 2a = 11111111 11111111 11111111 11111110
    					   + 11111111 11111111 11111111 11111110
    					------------------------------------------
    				    溢位| 11111111 11111111 11111111 11111100
    
```



#### 3.函数`Math.sqrt(double t)` 计算一个数的平方根. 表达式`Math.sqrt(2)*Math.sqrt(2) == 2`的值是什么?

```java
public class Lab_02_03 {
    public static void main(String[] args){
        System.out.println(Math.sqrt(2)*Math.sqrt(2) == 2);
    }
}

>>>false 	: Math.sqrt(double t)返回double类型的数值，double类型相乘得到double			   类型，而‘2’是int类型，故不相等
```



#### 4.给定命令行参数 x1, y1, x2, y2. 计算平面上的点 (x1, y1) 和 (x2, y2) 的距离.

~~~java
public class Lab_02_04 {
    public static void main(String[] args){
        int X1, Y1, X2, Y2;
        X1 = Integer.parseInt(args[0]);
        Y1 = Integer.parseInt(args[1]);
        X2 = Integer.parseInt(args[2]);
        Y2 = Integer.parseInt(args[3]);

        double ans = Math.sqrt(Math.pow(X1 - X2, 2) + Math.pow(Y1 - Y2, 2)); 
        System.out.println(ans);
    }
}
~~~

~~~cmd
D:\Java_for_vscode>javac Lab_02_04.java

D:\Java_for_vscode>java Lab_02_04 1 2 3 4
2.8284271247461903
~~~



#### 5.计算函数 logx, x, xlogx, x2, x3在 x=1, 2, 4, 8, 16, ..., 2048 处的值. 并比较它们的增长速度.

~~~java
class Mytype05 {
    int X = 1;
    double logx; // fx = logx
    double increase_logx; // f'x = 1 / x

    double x; // fx = x 
    double increase_x; // f'x = 1
 
    double xlogx; // fx = xlogx
    double increase_xlogx; // f'x = logx + 1

    double x2; // fx = x^2
    double increase_x2; // f'x = 2x

    double x3; // fx = X^3
    double increase_x3; // f'x = 3x^2

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
        for(int i = 1;i <= 2048; i *= 2) // 所需要的范围
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

-------------
logx = 0.0
x = 1.0
xlogx = 0.0
x2 = 1.0
x3 = 1.0
in logx = 1.0
in x = 1.0
in xlogx = 1.0
in x2 = 2.0
in x3 = 3.0
-------------
logx = 0.6931471805599453
x = 2.0
xlogx = 1.3862943611198906
x2 = 4.0
x3 = 8.0
in logx = 0.0
in x = 1.0
in xlogx = 1.6931471805599454
in x2 = 4.0
in x3 = 12.0
-------------
logx = 1.3862943611198906
x = 4.0
xlogx = 5.545177444479562
x2 = 16.0
x3 = 64.0
in logx = 0.0
in x = 1.0
in xlogx = 2.386294361119891
in x2 = 8.0
in x3 = 48.0
-------------
logx = 2.0794415416798357
x = 8.0
xlogx = 16.635532333438686
x2 = 64.0
x3 = 512.0
in logx = 0.0
in x = 1.0
in xlogx = 3.0794415416798357
in x2 = 16.0
in x3 = 192.0
-------------
logx = 2.772588722239781
x = 16.0
xlogx = 44.3614195558365
x2 = 256.0
x3 = 4096.0
in logx = 0.0
in x = 1.0
in xlogx = 3.772588722239781
in x2 = 32.0
in x3 = 768.0
-------------
logx = 3.4657359027997265
x = 32.0
xlogx = 110.90354888959125
x2 = 1024.0
x3 = 32768.0
in logx = 0.0
in x = 1.0
in xlogx = 4.465735902799727
in x2 = 64.0
in x3 = 3072.0
-------------
logx = 4.1588830833596715
x = 64.0
xlogx = 266.168517335019
x2 = 4096.0
x3 = 262144.0
in logx = 0.0
in x = 1.0
in xlogx = 5.1588830833596715
in x2 = 128.0
in x3 = 12288.0
-------------
logx = 4.852030263919617
x = 128.0
xlogx = 621.059873781711
x2 = 16384.0
x3 = 2097152.0
in logx = 0.0
in x = 1.0
in xlogx = 5.852030263919617
in x2 = 256.0
in x3 = 49152.0
-------------
logx = 5.545177444479562
x = 256.0
xlogx = 1419.565425786768
x2 = 65536.0
x3 = 1.6777216E7
in logx = 0.0
in x = 1.0
in xlogx = 6.545177444479562
in x2 = 512.0
in x3 = 196608.0
-------------
logx = 6.238324625039508
x = 512.0
xlogx = 3194.022208020228
x2 = 262144.0
x3 = 1.34217728E8
in logx = 0.0
in x = 1.0
in xlogx = 7.238324625039508
in x2 = 1024.0
in x3 = 786432.0
-------------
logx = 6.931471805599453
x = 1024.0
xlogx = 7097.82712893384
x2 = 1048576.0
x3 = 1.073741824E9
in logx = 0.0
in x = 1.0
in xlogx = 7.931471805599453
in x2 = 2048.0
in x3 = 3145728.0
-------------
logx = 7.6246189861593985
x = 2048.0
xlogx = 15615.219683654448
x2 = 4194304.0
x3 = 8.589934592E9
in logx = 0.0
in x = 1.0
in xlogx = 8.624618986159398
in x2 = 4096.0
in x3 = 1.2582912E7
~~~



#### 6.使用[牛顿法](https://en.wikipedia.org/wiki/Newton's_method)计算平方根. 

~~~java
import java.util.Scanner;

class Mytype06 {
    double x; // x是作为迭代的对象
    double a; // a是所求的对象
    double fx(double x)
    {
        x = (x + a / x) / 2; //牛顿法
        return x;
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in); 
        int a = input.nextInt();
        double ans = 4; // 初始从 x = 4 开始
        Mytype06 m = new Mytype06();
        m.a = a;
        while(Math.abs(a - ans * ans) > 1e-9) // 判断收敛范围1e-9
        {
            ans = m.fx(ans);
        }
        System.out.println(ans);
    }
}


<<<5
>>>2.23606797749979
~~~



#### 7.给定5个整数 (通过命令行参数), 找出它们的中位数 (即第3大的数).

~~~java
import java.util.Arrays;

public class Lab_02_07 {
    public static void main(String[] args)
    {
        int[] ls = new int[5]; // 创建新数组
        for(int i = 0 ;i < 5; i++) 
        {
            ls[i] = Integer.parseInt(args[i]); // 循环输入数组
        }

        Arrays.sort(ls); // 排序数组

        System.out.println(ls[2]); // 输出第三项
    }
}
~~~

~~~cmd
D:\Java_for_vscode>javac Lab_02_07.java

D:\Java_for_vscode>java Lab_02_07 1 9 2 3 8
3
~~~



## 数组



#### 8.以下程序的运行结果是什么?

```java
int[] a = { 1, 2, 3 };
int[] b = { 1, 2, 3 };
System.out.println(a == b);
```

~~~java
public class Lab_02_08 {
    public static void main(String[] args)
    {
        int[] a = { 1, 2, 3 };
        int[] b = { 1, 2, 3 };
        System.out.println(a == b);
    }
}

>>>false
~~~



#### 9.Hadamard 矩阵 H(N)为 2^(N−1) × 2^(N−1)的方阵. 矩阵中的元素为 0 或者 1.给定 N, 请输出 H(N).

~~~java
public class Lab_02_09 {
    public static void main(String[] args)
    {
        int N = 5; // 假设输入的值为5

        int max = (int) Math.pow(2, N);  // 结果需要的大小为 2^5 的方阵 
        int[][] Had = new int[max][max]; // 声明并且创建一个新的整型二位数组
        Had[0][0] = 1; // 初始化的首元素为 1

        for(int i = 1;i <= N;i++)
        {
            int size = (int)Math.pow(2, i); // 在循环中所需要改变的大小为size

            for(int j = size / 2; j < size ;j++)
            {
                for(int k = 0; k < size / 2 ;k++)
                {
                    Had[j][k] = Had[j - (size / 2)][k]; // 左下方方阵复制左上方
                    Had[k][j] = Had[k][j - (size / 2)]; // 右上方方阵复制左上方
                }                
            }

            for(int j = size / 2; j < size ;j++)
            {
                for(int k = size / 2; k < size ;k++) // 右下方方阵和左上方方阵相反
                {
                    if(Had[k - (size / 2)][j - (size / 2)] == 1)
                        Had[k][j] = 0;
                    else
                        Had[k][j] = 1;
                }                
            }
        }

        for(int i = 0; i < max;i++)
        {   
            for(int j = 0;j < max;j++)
            {
                System.out.print(Had[i][j]); // 输出循环
                System.out.print(' '); // 便于观察
            }
            System.out.println();
        }
    }
}

1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 
1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 
1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 
1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 
1 0 1 0 0 1 0 1 1 0 1 0 0 1 0 1 1 0 1 0 0 1 0 1 1 0 1 0 0 1 0 1 
1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 
1 0 0 1 0 1 1 0 1 0 0 1 0 1 1 0 1 0 0 1 0 1 1 0 1 0 0 1 0 1 1 0 
1 1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 
1 0 1 0 1 0 1 0 0 1 0 1 0 1 0 1 1 0 1 0 1 0 1 0 0 1 0 1 0 1 0 1 
1 1 0 0 1 1 0 0 0 0 1 1 0 0 1 1 1 1 0 0 1 1 0 0 0 0 1 1 0 0 1 1 
1 0 0 1 1 0 0 1 0 1 1 0 0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0 0 1 1 0 
1 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 
1 0 1 0 0 1 0 1 0 1 0 1 1 0 1 0 1 0 1 0 0 1 0 1 0 1 0 1 1 0 1 0 
1 1 0 0 0 0 1 1 0 0 1 1 1 1 0 0 1 1 0 0 0 0 1 1 0 0 1 1 1 1 0 0 
1 0 0 1 0 1 1 0 0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0 0 1 1 0 1 0 0 1 
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1
1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1
1 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0
1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1
1 0 1 0 0 1 0 1 1 0 1 0 0 1 0 1 0 1 0 1 1 0 1 0 0 1 0 1 1 0 1 0
1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 0 0 1 1 1 1 0 0 0 0 1 1 1 1 0 0
1 0 0 1 0 1 1 0 1 0 0 1 0 1 1 0 0 1 1 0 1 0 0 1 0 1 1 0 1 0 0 1
1 1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1
1 0 1 0 1 0 1 0 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 1 0 1 0 1 0 1 0
1 1 0 0 1 1 0 0 0 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 1 1 0 0 1 1 0 0
1 0 0 1 1 0 0 1 0 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 1 0 0 1 1 0 0 1
1 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0
1 0 1 0 0 1 0 1 0 1 0 1 1 0 1 0 0 1 0 1 1 0 1 0 1 0 1 0 0 1 0 1
1 1 0 0 0 0 1 1 0 0 1 1 1 1 0 0 0 0 1 1 1 1 0 0 1 1 0 0 0 0 1 1
1 0 0 1 0 1 1 0 0 1 1 0 1 0 0 1 0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
~~~



​	Alice 去参加一个聚会. Bob 也在这个聚会中. 看到 Alice 之后, Bob 把一个和 Alice 有关的八卦谣言告诉了他的一个同伴. 随后谣言开始在聚会中传播. 假设人们都按照以下方式传递谣言: 如果他第一次听到, 则从其他的人中随机选择一个 (除了告诉他的那个人和Alice), 将谣言传递出去. 如果他已经知道了这个谣言, 那么他停止传播. 

#### 10.请通过模拟来估计在谣言传播停止前, 所有人(除了 Alice)都知道这个谣言的概率. 同时, 请估计听到谣言人数的期望值.

​	在本题中，我假设了接收到信息的人不会再将这个信息转达给之前告诉他的人以及自己，即 A -> B，则 B 不会告诉 A 和 B。

~~~java
    public class Lab_02_10 {
        public static void main(String[] args)
        {   
            double people;
            for(people = 3; people <= 13; people++) // Ranging from 3 to 13 people 
            {
                double keyi = 0; // Counting the times that all people are acknowledged 
                double times = 0; // Testing for 10 times
                for(times = 0; times < 100000;times++) // Statistics
                {
                    int[] man = new int[(int)people]; // All the people forming a Array 
                    for(int i = 0;i < people; i++)
                        man[i] = 0; // All the people haven't heard about the information
                    man[0] = 1; // Bob has aleady known this information
                    man[1] = 1; // Bob told one of his friend
                    
                    while(true) 
                    {
                        boolean yes = true;
                        int who = 1; // In case that people will tell himself
                        int heard = 0; // The information is heard from who?
                        int next = (int)(Math.random() * people); // Generate a random number
                        while(next == who || next == heard) 
                        {
                            next = (int)(Math.random() * people); // Generate a random number
                        }
                        if(man[next] == 1) // When a person who has already known the information was told again
                        {            // According to the context, this information will not be spread again
                            for(int i = 0; i < people;i++) // whether there are somebody who aren't acknowledged
                            {
                                if(man[i] == 0)
                                {
                                    yes = false;
                                    break;
                                }
                            }
                            
                            if(yes)
                                keyi++; // All of them were told about the information
                            
                            break; // end of this times test
                        }
                        else // if the people be told haven't been told yet, he will tell it to somebody else;
                        {
                            man[next] = 1;
                            heard = who;
                            who = next;
                        }
                    }    
                }
                
                double EV = 1.0;
                double unknown;
                for(unknown = people - 2; unknown >= 2; unknown--)
                {
                    EV *= unknown / people;
                }
                System.out.print("人数为: ");
                System.out.println((int)people);
                System.out.print("所有人都能知晓的概率为: ");
                System.out.println(keyi / times);
                System.out.print("所有人都能知晓的期望值为: ");
                System.out.println(EV);
                //System.out.printf("%.6lf",prob);
                //System.out.printf("The possiblity within %d people is %lf",people,prob);
                //System.out.println();
            }
        }
    }

人数为: 3
所有人都能知晓的概率为: 1.0
所有人都能知晓的期望值为: 1.0
人数为: 4
所有人都能知晓的概率为: 0.50184
所有人都能知晓的期望值为: 0.5
人数为: 5
所有人都能知晓的概率为: 0.22402
所有人都能知晓的期望值为: 0.24
人数为: 6
所有人都能知晓的概率为: 0.09387
所有人都能知晓的期望值为: 0.1111111111111111
人数为: 7
所有人都能知晓的概率为: 0.03849
所有人都能知晓的期望值为: 0.049979175343606824
人数为: 8
所有人都能知晓的概率为: 0.01541
所有人都能知晓的期望值为: 0.02197265625
人数为: 9
所有人都能知晓的概率为: 0.00613
所有人都能知晓的期望值为: 0.00948364917272096
人数为: 10
所有人都能知晓的概率为: 0.00218
所有人都能知晓的期望值为: 0.004032
人数为: 11
所有人都能知晓的概率为: 9.6E-4
所有人都能知晓的期望值为: 0.0016928619813050805
人数为: 12
所有人都能知晓的概率为: 4.1E-4
所有人都能知晓的期望值为: 7.032857510288065E-4
人数为: 13
所有人都能知晓的概率为: 1.6E-4
所有人都能知晓的期望值为: 2.8954908373524004E-4
~~~



#### 11.给定整数 N , 输出 1 到 N 的所有排列. 利用本题测试第7题中寻找中位数的算法是否正确.

~~~java
import java.util.Arrays;

public class Lab_02_11 {
    
    public static void perm(int[] array,int start,int end) 
    {
        if(start == end) 
        {
            System.out.println(Arrays.toString(array));
        } 
        else 
        {
            for (int i = start; i <= end; i++) 
            {
                swap(array,start,i);
                perm(array,start+1,end);
                swap(array,start,i);
            }
        }
    }

    public static void swap(int[] array,int i,int j)  // exchange the element in the Array
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) 
    {
        int[] table = {1,2,3,4};
        perm(table,0,3); // the Array itself ans its begin , end
    }
}

[1, 2, 3, 4]
[1, 2, 4, 3]
[1, 3, 2, 4]
[1, 3, 4, 2]
[1, 4, 3, 2]
[1, 4, 2, 3]
[2, 1, 3, 4]
[2, 1, 4, 3]
[2, 3, 1, 4]
[2, 3, 4, 1]
[2, 4, 3, 1]
[2, 4, 1, 3]
[3, 2, 1, 4]
[3, 2, 4, 1]
[3, 1, 2, 4]
[3, 1, 4, 2]
[3, 4, 1, 2]
[3, 4, 2, 1]
[4, 2, 3, 1]
[4, 2, 1, 3]
[4, 3, 2, 1]
[4, 3, 1, 2]
[4, 1, 3, 2]
[4, 1, 2, 3]
~~~



#### 12 定义衡等置换e=(1,2,..N)(即, e(i)=i, ∀i). 定义置换 σ的逆 σ−1满足 σ∘σ−1=σ−1∘σ=e 给定一个置换, 输出它的逆. 要求除了存储置换的数组外不能使用其他的数组.

~~~java
public class Lab_02_12 {
    public static void main(String[] args)
    {
        int[] delta = {2,1,4,5,3}; // given that the ONLY Array is {2, 1, 4, 5, 3}
                                   // According to the describe of the context, the answer
                                   // should be {2, 1, 5, 3, 4}.
                                   // Each number in the Array is related to the location
                                   // of the corresponding element of the primer Arrays
                                   // that is, the Answer Array stores the location of the 
                                   // sequence of the dictionary-order, the procedure is 
                                   // shown as follow:
                                   //  {2, 1, 4, 5, 3}
                                   //1.Find the location of element '1' -- 2
                                   //2.Find the location of element '2' -- 1
                                   //3.Find the location of element '3' -- 5
                                   //4.Find the location of element '4' -- 3
                                   //5.Find the location of element '5' -- 4
                                   //                                  -->{2, 1, 5, 4, 3} 
                                   
        for(int i = 1; i <= delta.length; i++) // Find the sequence in order
        {
            for(int j = 0; j < delta.length; j++) // Ergodic all the array to find corresponding loc
            {
                if(delta[j] == i)
                {
                    System.out.print(j + 1);
                }
            }
        }
        System.out.println();
    }
}

21534
~~~



#### 13.给定排列, 输出其代表的棋盘是否是一个“安全”的棋盘, 要求除了存储排列的数组外, 不能使用其他的数组. 国际象棋规则中, 一个皇后可以 “吃掉” 任何和它处于同一直线上的棋子(即同列, 同行, 同对角线, 同反对角线). 

```java
public class Lab_02_13 {
    public static void main(String[] args)
    {
        int[] loc = {5,2,3,1,4}; // Any Array other than this given array is unpremittable

        /*for(int i = 0;i < 5; i++) // This fake Array is established to show the present situation
        {                           // in the table
            for(int j = 0; j < 5;j++)
            {
                if(j == loc[i] - 1)
                {
                    table[j][i] = 'Q';
                }
                else
                {
                    table[j][i] = '*';
                }
            }
        }*/

        // According to the data given
        // The queen only apeared in each line for only once
        // At the same time, the queen will only appeared in each colomn for once
        // So we only have to check the diagols' and anti-diagols fesibility

        // for example: 5,2,3,1,4
        // we will have a chess table like that
        // [1][5], [2][2], [3][3], [4][1], [5][4](Of all the element need to minus one)
        // The diagols' of [0][4](Or [1][5] as given) is [1][3], [2][2], [3][1], [4][0]
        // We can find that 0 + 4 == 1 + 3 == 2 + 2 == 3 + 1 == 4 + 0
        // The anti-dignols' of [1][1](Or [2][2] as given) is [0][0]. [1][1], [3][3], [4][4]
        // We can also find that 2 - 2 == 0 - 0 == 1 - 1 == 3 - 3 == 4 - 4
        // So we can test whether there are any situation happened in the array given

        boolean ans = true; // A signal the represent whether the table is "SAFE"

        for(int i = 0; i < 4; i++)
        {
            int temp = (loc[i] - 1) + i;   // (i) and (loc[i] - 1) seperately stands for the colomn
            int retemp = (loc[i - 1]) - i; // and the line of the specfic element, so we can calculate
                                           // the diagols and anti-diagols
                                           // Since we can't use other Array, so we can only use
                                           // temporary signal to repeat the exam
            for(int j = i + 1; j < 5;j++)
            {
                int pre = loc[j] - 1 + i;
                int repre = (loc[j] - 1) - i;
                if(temp == pre || retemp == repre)
                {
                    ans = false;
                    break;
                }
            }    

            if(ans)
            {
                break;
            }
        }

        if(ans)
        {
            System.out.println("皇后均处于安全位置");
        }
        else
        {
            System.out.println("皇后不处于安全位置");
        }
    }
}

```



#### 14给定N, 输出N阶蛇型矩阵S(N).

```JAVA
public class Lab_02_14 {
    public static void main(String[] args)
    {
        int N = 9; // The size of the Martix is N*N, given that the N is 9
        int[][] table = new int[N][N]; // Make a table that the answer reserved

        int num = 1; // We use joint number to fill the table
        int max = N * N; // The break situation
        table[0][0] = 1; // The orignal situation

        int hor = 0,ver = 0;
        while(true)
        {
            hor++;
            while(table[ver][hor] == 0) // GO RIGHT
            {
                table[ver][hor] = ++num;
                hor++;
                if(hor >= N)
                    break;
            }
            hor--;
            
            ver++;
            while(table[ver][hor] == 0) // GO DOWN
            {
                table[ver][hor] = ++num;
                ver++;
                if(ver >= N)
                    break;
            }
            ver--;

            hor--;
            while(table[ver][hor] == 0) // GO LEFT
            {
                table[ver][hor] = ++num;
                hor--;
                if(hor < 0)
                    break;
            }
            hor++;

            ver--;
            while(table[ver][hor] == 0) // GO UP
            {
                table[ver][hor] = ++num;
                ver--;
                if(ver < 0)
                    break;
            }
            ver++;
           	
            if(num == max) // the number will end here
                break;
        }



        for(int i = 0; i < N; i++) // Print the table
        {
            for(int j = 0 ; j < N; j++)
            {
                if(table[i][j] < 10)
                    System.out.print('0'); // Make it more readable
                System.out.print(table[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}

01 02 03 04 05 06 07 08 09
32 33 34 35 36 37 38 39 10
31 56 57 58 59 60 61 40 11
30 55 72 73 74 75 62 41 12
29 54 71 80 81 76 63 42 13
28 53 70 79 78 77 64 43 14
27 52 69 68 67 66 65 44 15
26 51 50 49 48 47 46 45 16
25 24 23 22 21 20 19 18 17
```



## 库文件

#### 编写库文件 Statistic.java, 包含如下静态方法:

| 方法                         | 说明                                   |
| :--------------------------- | :------------------------------------- |
| double max(double a[])       | 返回数组a中的最大值                    |
| double min(double a[])       | 返回数组a中的最小值                    |
| double mean(double a[])      | 返回数组a的均值                        |
| double variance(double a[])  | 返回数组a的方差                        |
| double select(double a[], k) | 返回数组a中第k大的数                   |
| int[] histogram(double a[])  | 返回数组b, b[i]表示a[i]在a中出现的次数 |

~~~java
import java.util.Arrays;

public class Statistic {
    public static double max(double a[])
    {
        double temp = 0;
        int size = a.length;
        for(int i = 0; i < size;i++)
        {
            if(temp < a[i])
                temp = a[i];
        }
        return temp;
    }

    public static double min(double a[])
    {
        double temp = 1;
        int size = a.length;
        for(int i = 0; i < size;i++)
        {
            if(temp > a[i])
                temp = a[i];
        }
        return temp;
    }

    public static double mean(double a[]) 
    {
        double temp = 0;
        int size = a.length;
        for(int i = 0;i < size;i++)
        {
            temp += a[i];
        }

        return (temp / size);
    }

    public static double variance(double a[])
    {
        double mean = mean(a);
        int size = a.length;
        double temp = 0;
        for(int i = 0;i < size; i++)
        {
            temp += Math.pow((a[i] - mean), 2);
        }

        temp = Math.sqrt(temp / size);

        return temp;
    }

    public static double select(double a[], int k)
    {
        int size = a.length;
        double[] table = new double[size];
        for(int i = 0 ; i < size; i++)
        {
            table[i] = a[i];
        }
        Arrays.sort(table);

        return table[size-k]; 
    }

    public static int[] histogram(double a[])
    {
        int size = a.length; // a = [2,1,3,1,4,1,2];
        //double[] table = a.clone();
        //Arrays.sort(table);
        //int[] b = new int[size];
        double[][] table = new double[size][2];
        for(int i = 0;i < size; i++)
        {
            table[i][0] = a[i];
            table[i][1] = 0; // [2][0],[1][0],[3][0],[1][0],[4][0],[1][0],[2][0];
        }

        int[] b = new int[size];
        for(int i = 0;i < size;i++)
        {
            double temp = a[i];
            for(int j = 0; j < size;j++)
            {
                if(table[j][0] == temp)
                {
                    table[i][1]++;
                }
            }
            b[i] = (int)table[i][1];
        }
        
        return b;
    }
}

~~~



#### 15.假定数据文件的格式为每行一个[0, 1]间的数字. 编写程序Compute.java, 从命令行获得数据文件的名称, 输出该文件中数字的最大值, 均值, 方差, 中位数. 同时输出出现次数最多的数, 最靠近均值的数, 与均值的距离小于1倍方差, 2倍方差, 3倍方差的数.

~~~java
import javax.xml.validation.Validator;

public class Lab_02_15 {
    public static void main(String[] args)
    {
        double[] table = new double[10];
        for(int i = 0;i < 10;i++)
        {
            double temp = Math.random();
            int zs = (int)(temp * 10);
            table[i] = zs / 10.0;
        }
        int size = table.length;

        System.out.println("--------------------------"); // Follow codes are used to test the right of Statisti.java
        System.out.print("The table is:\n");
        for(int i = 0;i < 10; i++)
        {
            System.out.print(table[i]);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println("--------------------------");

        /*System.out.println("--------------------------");
        System.out.print("The max is: ");
        System.out.println(Statistic.max(table));
        System.out.println("--------------------------");
        System.out.print("The min is: ");
        System.out.println(Statistic.min(table));
        System.out.println("--------------------------");
        System.out.print("The mean is: ");
        System.out.println(Statistic.mean(table));
        System.out.println("--------------------------");
        System.out.print("The variance is: ");
        System.out.println(Statistic.variance(table));
        System.out.println("--------------------------");
        System.out.print("The third biggest number is: ");
        System.out.println(Statistic.select(table,3));
        System.out.println("--------------------------");
        int[] temp = Statistic.histogram(table);
        for(int i = 0;i < 10;i++)
        {
            System.out.print(table[i]);
            System.out.print(" appears for ");
            System.out.print(temp[i]);
            System.out.println(" times.");
        }
        */

        System.out.print("The max is: ");
        System.out.println(Statistic.max(table));
        System.out.println("--------------------------");

        System.out.print("The mean is: ");
        System.out.println(Statistic.mean(table));
        System.out.println("--------------------------");

        System.out.print("The variance is: ");
        System.out.println(Statistic.variance(table));
        System.out.println("--------------------------");

        System.out.print("The middle number is: ");
        if(table.length % 2 == 1) // If the size of table is odd, we can use the middle number 
        {                         // Since that it is a Integer.
            int loc = table.length / 2;
            System.out.println(Statistic.select(table,loc));
        }
        else
        {
            int left = (table.length / 2) - 1; // If the size of table is odd, we need to calculate the 
            int right = table.length / 2;      // sum of the left and right and divide with two. 
            double ans = (Statistic.select(table,left)+Statistic.select(table,right)) / 2;
            System.out.println(ans);
        }
        System.out.println("--------------------------");

        System.out.print("The number appears most is: "); 
        int[] freq = Statistic.histogram(table); 

        double[] frans = new double[size]; 

        for(int i = 0; i < size;i++)
            frans[i] = 0;

        int loc = 0;

        int Maxfreq = 0;
        for(int i = 0; i < size;i++)
        {
            if(freq[i] > Maxfreq) // We need to find out the frequency that is the largest.
                Maxfreq = freq[i];
        }

        boolean appear = false;
        for(int i = 0; i < size;i++)
        {
            if(freq[i] == Maxfreq)
            {
                for(int j = 0; j < loc; j++) // Whether it has appered in the arrays
                    if(table[i] == frans[j])
                        appear = true;
                
                if(!appear)
                    frans[loc++] = table[i];
                
                appear = false;
            }
        }

        for(int i = 0; i < loc ; i++)
        {
            System.out.print(frans[i]);
            System.out.print(' ');
        }
        System.out.println();


        System.out.println("--------------------------");
        double mean = Statistic.mean(table);

        double temp = 1.0;
        loc = 0;
        for(int i = 0; i < size; i++)
        {
            if(temp > Math.abs(table[i] - mean)) // We need to calculate of all the elements
            {                                    // to find the number closest to the mean number
                temp = Math.abs(table[i] - mean);
                loc = i;
            }
        }
        System.out.print("The number that is closest to mean number is: ");
        System.out.println(table[loc]);


        System.out.println("--------------------------");
        double variance_once = Statistic.variance(table);
        double variance_twice = Statistic.variance(table) * 2;
        double variance_third = Statistic.variance(table) * 3;

        double[] varans = new double[size];
        for(int i = 0; i < size; i++)
        {
            varans[i] = 0;
        }
        loc = 0;
        appear = false;
        

        for(int i = 0; i < size; i++)
        {
            if(variance_once > Math.abs(table[i] - mean))
            {
                for(int j = 0; j < loc; j++)
                    if(varans[j] == table[i])
                        appear = true;

                if(!appear)
                    varans[loc++] = table[i];
                
                appear = false;
            }
        }
        System.out.print("The number far from the mean less than once variance is: ");
        for(int i = 0; i < loc; i++)
        {
            System.out.print(varans[i]);
            System.out.print(' ');
        }
        System.out.println();



        System.out.println("--------------------------");
        for(int i = 0; i < size; i++)
        {
            if(variance_twice > Math.abs(table[i] - mean)) // We inherit the arrays because
            {                                              // If a number minus mean number is
                for(int j = 0; j < loc; j++)               // less than once variance, it must
                    if(varans[j] == table[i])              // be less than twice times variance  
                        appear = true;                     // and third times variance 

                if(!appear)
                    varans[loc++] = table[i];
                
                appear = false;
            }
        }
        System.out.print("The number far from the mean less than twice variance is: ");
        for(int i = 0; i < loc; i++)
        {
            System.out.print(varans[i]);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println("--------------------------");




        for(int i = 0; i < size; i++)
        {
            if(variance_third > Math.abs(table[i] - mean))
            {
                for(int j = 0; j < loc; j++)
                    if(varans[j] == table[i])
                        appear = true;

                if(!appear)
                    varans[loc++] = table[i];
                
                appear = false;
            }
        }
        System.out.print("The number far from the mean less than third variance is: ");
        for(int i = 0; i < loc; i++)
        {
            System.out.print(varans[i]);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println("--------------------------");


    }
}
~~~





# 熟悉数组，字符串以及FileIO	

## 文件IO

#### 学会调用 **FileIO.java** 中的函数。

- 调用4次writeStringToFile()，逐行写入以下内容到test.txt中(总共四行)

  ```
  《老人与海》这本小说是根据真人真事写的。第一次世界大战结束后，海明威移居古巴，认识了老渔民格雷戈里奥·富恩特斯。
  1930年，海明威乘的船在暴风雨中沉没，富恩特斯搭救了海明威。从此，海明威与富恩特斯结下了深厚的友谊，并经常一起出海捕鱼。
  The novel The Old Man and the Sea is based on a real story. After the end of World War I, Hemingway moved to Cuba, where he met an old fisherman, Gregorio Fuentes.
  In 1930, Hemingway was rescued by Fuentes when his boat sank in a storm. From then on, Hemingway and Fuentes formed a deep friendship, and often went fishing together.
  ```

- 针对test.txt文件，调用getCharFromFile()，返回第5个字符，并打印出来。

- 针对test.txt文件，调用getLineFromFile(),返回第3行，并打印出来。

- 针对test.txt文件，调用getAllLinesFromFile()，返回所有行，并打印出来。

  ~~~java
  import java.io.File;
  
  public class Lab_02_16 {
      public static void main(String[] args)
      {
          String[] s = {"《老人与海》这本小说是根据真人真事写的。第一次世界大战结束后，海明威移居古巴，认识了老渔民格雷戈里奥·富恩特斯。\n",
                      "1930年，海明威乘的船在暴风雨中沉没，富恩特斯搭救了海明威。从此，海明威与富恩特斯结下了深厚的友谊，并经常一起出海捕鱼。\n",
                      "The novel The Old Man and the Sea is based on a real story. After the end of World War I, Hemingway moved to Cuba, where he met an old fisherman, Gregorio Fuentes.\n",
                      "In 1930, Hemingway was rescued by Fuentes when his boat sank in a storm. From then on, Hemingway and Fuentes formed a deep friendship, and often went fishing together.\n"};
  
          for(int i = 0; i < 4; i++)
          {
              FileIO.writeStringToFile(s[i], "test.txt");
          }
  
          System.out.println("--------------------------");
          char ch = FileIO.getCharFromFile(5 - 1, "test.txt");
          System.out.println(ch);
  
          System.out.println("--------------------------");
          String line = FileIO.getLineFromFile(3 - 1, "test.txt");
          System.out.println(line);
  
          System.out.println("--------------------------");
          String[] text = FileIO.getAllLinesFromFile("test.txt");
          for(int i = 0;i < 4; i++)
          {
              System.out.println(text[i]);
          }
      }
  }
  
  
  --------------------------
  海
  --------------------------
  The novel The Old Man and the Sea is based on a real story. After the end of World War I, Hemingway moved to Cuba, where he met an old fisherman, Gregorio Fuentes.
  --------------------------
  《老人与海》这本小说是根据真人真事写的。第一次世界大战结束后，海明威移居古巴，认识了老渔民格雷戈里奥·富恩特斯。
  1930年，海明威乘的船在暴风雨中沉没，富恩特斯搭救了海明威。从此，海明威与富恩特斯结下了深厚的友谊，并经常一起出海捕鱼。
  The novel The Old Man and the Sea is based on a real story. After the end of World War I, Hemingway moved to Cuba, where he met an old fisherman, Gregorio Fuentes.
  In 1930, Hemingway was rescued by Fuentes when his boat sank in a storm. From then on, Hemingway and Fuentes formed a deep friendship, and often went fishing together.
  ~~~

  

  ​	

  
