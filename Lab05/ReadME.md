# Project 5 继承与组合

## Project 5.a 继承与组合基础

#### 1.定义 Name 类包含以下成员, 其中数据成员是私有的, 方法是公开的

| 数据成员  | 说明 |
| --------- | ---- |
| firstName | 名   |
| lastName  | 姓   |

| 方法            | 说明                       |
| --------------- | -------------------------- |
| Name()          | 构造函数                   |
| Name(f, l)      | 构造函数, 其中f为名, l为姓 |
| getFirstName()  | 返回名                     |
| setFirstName(f) | 设置名                     |
| getLastName()   | 返回姓                     |
| setLastName(f)  | 设置姓                     |
| toString()      | 转化为String               |

~~~java
import Fileio.FileIO;
public class Name{
    public String firstName;
    public String lastName;

    Name()
    {
        this.firstName = FileIO.getLineFromFile((int)(Math.random() * 162), "NAMETABLE");
        this.lastName = FileIO.getLineFromFile((int)(Math.random() * 162), "NAMETABLE");
        // Using nametable to create a random name since the name is not given
    }
    Name(String f, String l)
    {
        this.firstName = f;
        this.lastName = l;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public void setFirstName(String f)
    {
        this.firstName = f;
    }
    public void setLastName(String l)
    {
        this.lastName = l;
    }
    public String ToString()
    {
        String ans = this.firstName + "·" + this.lastName;
        return ans;
    }
    public static void main(String[] args) {
        Name n = new Name("yb","wu");
        Name nn = new Name();
        System.out.println(n.ToString());
        System.out.println(nn.ToString());
    }
}
~~~



#### 2.定义 Person 类, 包含以下成员(所有方法都是公开的, 其中一些方法的参数列表并不完整, 请补充完整).

| 数据成员 | 说明                     |
| -------- | ------------------------ |
| name     | 姓名( Name 对象, 公开的) |
| age      | 年龄(常量, 公开的)       |
| gender   | 性别(私有的)             |

| 方法            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| Person()        | 构造函数                                                     |
| Person(a, g, n) | 构造函数, 其中 a 为年龄, g 为性别, n 为姓名                  |
| getGender()     | 返回性别                                                     |
| setGender()     | 设置性别                                                     |
| talk()          | 输出"Hi, how is it going"                                    |
| talk(s)         | 输出字符串对象s                                              |
| chatWith(p, s)  | p 为 Person 对象, s 为字符串对象. 输出"A to B: %s", 其中 A 是调用者的姓名, B 是 p 的姓名 |

~~~java
public class Person{    // Combination of Class:Name
    public Name name;
    public final int age;
    private String gender;

    public Person()
    {   
        this.name = new Name();
        this.age = 0;
        this.gender = "Male";
    }
    public Person(int a, String g, Name n)
    {
        this.name = n;
        this.age = a;
        this.gender = g;
    }
    public String getGender()
    {
        return this.gender;
    }
    public void setGender(String g)
    {
        this.gender = g;
    }
    public void talk()
    {
        System.out.println("Hi, how is it going");
    }
    public void talk(String s)
    {
        System.out.println(s);
    }
    public void chatWith(Person p, String s)
    {
        System.out.println(this.name.ToString() + " to " + p.name.ToString() + ": \"" + s + "\"");
    }

    public static void main(String[] args)
    {
        Name n = new Name("yb","wu");
        Person p = new Person(30,"male",n);
        System.out.println(p.name.ToString());
        p.talk();
    }
}

yb·wu
Hi, how is it going
~~~



#### 3.请通过试验回答以下问题并说明原因

- ##### 在 Name 类的主函数中是否能访问 Name 对象的数据成员

  ~~~java
  // In Name.java 
  public static void main(String[] args) {
      Name n = new Name("yb","wu");
      Name nn = new Name();
      System.out.println(n.ToString());
      System.out.println(nn.ToString());
  }
  
  yb·wu
  Benson·Robinson
  ~~~

  ~~~txt
  可以
  ~~~

- ##### 在 Person 类中(例如 chatWith 方法中)能否直接访问 name 对象的数据成员

  ~~~java
  The method ToString() is undefined for the type PersonJava(67108964)
  ~~~

  ~~~txt
  不可以，由于Person类只是Name类型的组合，所以不能直接访问
  ~~~

- ##### 如果将 Name 的数据成员变为 package access, 上一个问题的答案会发生改变吗

  ~~~txt
  可以，在同一包内可以互相访问
  ~~~

- ##### 如果将 Person 类与 Name 类分别放入两个不同的包, 则以上问题的答案会发生改变吗

  ~~~txt
  会发生改变，由于不再存在于同一个包中，所以package access对于互相是不可访问的
  ~~~



#### 4.定义 Student 类为 Person 类的子类. `talk()` 函数输出"Hi, how is your homework going?"

~~~java
public class Student extends Person{
    public Student()
    {
        super();
    }
    public Student(int i, String s, Name n) // When we use constrcution function
    {                                       // we should first use .super() to 
        super(i,s,n);                       // inherit the function in Superclass
                                            // and use the constuction function 
                                            // to create a subclass instance.
    }
    public void talk()                      // Overwirte the function in Superclass
    {
        System.out.println("Hi, how is your homework going?");
    }
    public static void main(String[] args) {
        Name n = new Name();
        Student std = new Student(10,"Male",n);
        std.talk();
        Person p = new Person(10,"Male",n);
        p.talk();
    }
}

Hi, how is your homework going?
Hi, how is it going
~~~



#### 5.定义 Teacher 类为 Person 类的子类. `talk()` 函数输出"Hi, how is your paper going?"

~~~java
public class Teacher extends Person{
    public Teacher()
    {
        super();
    }
    public Teacher(int i, String s, Name n) // When we use constrcution function
    {                                       // we should first use .super() to 
        super(i,s,n);                       // inherit the function in Superclass
                                            // and use the constuction function 
                                            // to create a subclass instance.
    }
    public void talk()
    {
        System.out.println("Hi, how is your paper going?");
    }    
    public static void main(String[] args) {
        Name n = new Name();
        Teacher tec = new Teacher(10,"Male",n);
        tec.talk();
        Student std = new Student(10,"Male",n);
        std.talk();
        Person p = new Person(10,"Male",n);
        p.talk();
    }
}

Hi, how is your paper going?
Hi, how is your homework going?
Hi, how is it going
~~~



#### 6.请编写程序验证 Teacher, Student 类中talk()方法是动态绑定的.

~~~java
public class Lab05Test {
    public static void test(Person p)      
    {
        p.talk();
    }
    public static void main(String[] args) {
        Teacher t = new Teacher();
        Student s = new Student();
        Person  p = new Person();
        Lab05Test.test(t);          // Upcasting 
        Lab05Test.test(s);          // Upcasting
        Lab05Test.test(p);
    }
}

Hi, how is your paper going?
Hi, how is your homework going?
Hi, how is it going
~~~



#### 7.定义 PersonFactory 类, 包含方法 `Person next()`, 随机生成一个 Teacher 或者 Student 对象

~~~java
public class PersonFactory {
    public static Person next()
    {
        if(Math.random() > 0.5)
        {
            Teacher t = new Teacher();
            return t;
        }
        else
        {
            Student s = new Student();
            return s;
        }
    }
}
~~~



#### 8.利用 PersonFactory 类, 定义 Discussion 类, 其中包含若干 Teacher 对象和若干 Student 对象. 同时包含方法 broadcast(): 随机选择一个 Teacher 或者 Student 对象, 并对其余对象调用 chatWith() 方法.

~~~java
public class Discussion {
    public Person[] people;

    public Discussion(int n)
    {
        people = new Person[n];
        for(int i = 0; i < n; i++)
        {
            people[i] = PersonFactory.next();   // Create a person randomly
        }
    }

    public void boardcast()
    {
        int loc = (int)(people.length * Math.random());     // choose a person randomly
        for(int i = 0; i < people.length; i++)
        {
            if(i == loc)
                continue;
            else
            {
                people[loc].chatWith(people[i], "What's up bro?");
            }
        }
    }

    public static void main(String[] args) {
        Discussion d = new Discussion(10);
        d.boardcast();
    }
}

Robert·Sammy to Howard·Randall: "What's up bro?"
Robert·Sammy to Glen·Matthew: "What's up bro?"  
Robert·Sammy to Tony·Shawn: "What's up bro?"    
Robert·Sammy to Steven·Douglas: "What's up bro?"
Robert·Sammy to Ryan·Thomas: "What's up bro?"   
Robert·Sammy to Solomon·Thomas: "What's up bro?"
Robert·Sammy to Luke·Brandon: "What's up bro?"  
Robert·Sammy to Leander·Alva: "What's up bro?"  
Robert·Sammy to Richard·Angus: "What's up bro?" 
~~~



#### 9.定义 ElderTeacher 类为 Teacher 类的子类, 要求 ElderTeacher 类最多只能有一个实例

~~~java
package Lab05;

public class ElderTeacher extends Teacher {
    private ElderTeacher(){}    //  Since we don't need more than 
                                // one elderTeacher, we will make
                                // the constructor function 'private'
                                //  So, the user cannot create any
                                // Instance:Elderteacher from the 
                                // outside of this class
    public static ElderTeacher et = new ElderTeacher();
                                //  The function will create an
                                // instance itself
    public static ElderTeacher give_me_the_only_teacher()
    {                           //  And use static method to
        return et;              // give it to other function
    }                         
}
~~~



#### 10.假设类 Sup 包含方法 f(), 返回值为类型A; 类 Sub 为 Sup 的子类, 包含方法 f(), 返回值为类型B. 

- ##### 若 A, B 类型相同, 运行程序会出现什么情况?

  ~~~java
  class Supp
  {
      int i = 10;
      int f()
      {
          return i;
      }
  }
  
  class Subb extends Supp
  {
      int f()
      {
          return i;
      }
  }
  
  public class Lab05a10_01 {
      public static void main(String[] args) {
          Subb s = new Subb();
          System.out.println(s.f());
      }
  }
  
  10
  ~~~

  ~~~txt
  可以返回正常的数据
  ~~~

  

- ##### 若 A 为数组, B为 int, 会出现什么情况?

  ~~~java
  class Suppp
  {
      int[] i = {1,2,3,4,5};
      int[] f()
      {
          return i;
      }
  }
  
  class Subbb extends Suppp
  {
      int subi = 10;
      int f()
      {
          return subi;
      }
  }
  
  public class Lab05a10_02 {
      public static void main(String[] args) {
          Subbb s = new Subbb();
          System.out.println(s.f());
      }
  }
  
  Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
          The return type is incompatible with Suppp.f()
  
          at Lab05.Subbb.f(Lab05a10_02.java:15)
          at Lab05.Lab05a10_02.main(Lab05a10_02.java:24)
  ~~~

  ~~~txt
  无法编译通过
  ~~~



- ##### 若 B 是 A 的子类, 会出现什么情况?

  ~~~java
  package Lab05;
  
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
  
  In B
  100
  ~~~

  ~~~txt
  可以编译通过，由于B是A的子类，可以实现多态
  ~~~



## Project 5.b 电阻网络

#### 1.写出下列网络的字符串表示

  ![img](http://ybwu.org/ecnu-java/projects/inheritance/images/circuits/series3.png)

~~~txt
(-, (/, (-, (-, 2, 4), 6) ,7), (/, 3.4, 2.7))
~~~

   ![img](http://ybwu.org/ecnu-java/projects/inheritance/images/circuits/circuit.png)

~~~txt
(/, (-, 8, (/,	8, (-, 4, 2))), (-, 6, (/, 6, 4)))
~~~

   ![img](http://ybwu.org/ecnu-java/projects/inheritance/images/circuits/bigcircuit.png)

~~~txt
(-, 4.1, (-, 9.2, (/, (-,(/, 6.5, (-, 2.5, (-, 4.0, 2.8))),(-, 4.4, 2.7)), (-, 3.5, (-, 6.9, (/, 4.4, (/, 8.0, (-, 5.0, 7.7))))))))
~~~



   ![img](http://ybwu.org/ecnu-java/projects/inheritance/images/circuits/bigcircuit2.png)

~~~txt
(-, 3.5, (-, (/, 8.5, (/, 1.8, (/, 0.7, 4.6))), (-, (/, (-, (/, (-, 4.8, (/, 2.4, 6.3)), (-, 3.7, (-, 1,4, 6,9))), 3.8),(-, 3.7, (/, (-, 4.8, 3.2), (-, 3.7, 4.4)))), (/, 7.9, 4.5))))
~~~



#### 2.定义 Circuit 类用来表示电路网络.

~~~java
public class Circuit {
    private double[] circuit = new double[100];
    private int cnt = 0;                    // How many net in the circuit?
    private int len = 0;
    private int[] lloc = new int[100];
    private void get_circuit(String s)  // Store the String:circuit in an array
    {                                   // However, we don't consider the situation
        int loc = 0;                    // that the orignal number is not Integer
        int lenlloc = 0;
        while(loc++ < s.length())       // Change the String to Double
        {
            char ch = s.charAt(loc - 1);
            if(ch == ' ' || ch == ',')
                continue;
            else if('0' <= ch && ch <= '9')
            {
                int finishone = s.indexOf(',', loc - 1);// if the number isn't the last one
                                                        // in the Subcircuit, we can split
                                                        // it with ','
                
                int finishtwo = s.indexOf(')', loc - 1);// Otherwise, the numbe is the last
                                                        // number in the Subcircuit, we should
                                                        // split it with ')'
                int finish = 0;
                if(finishone == -1 || finishone > finishtwo)
                    finish = finishtwo;
                else
                    finish = finishone;
                String num = s.substring(loc - 1, finish);
                loc = finish;
                circuit[len++] = tonum(num); // Convert a String which represent a double
                                             // number to a real double number 
            }
            else if(ch == '(')
            {
                circuit[len++] = -1;    // Since char cannot stored in the Array:double
                cnt++;                  // We use negetive number to record it 
                lloc[lenlloc++] = len - 1;
            }
            else if(ch == ')')
                circuit[len++] = -2;
            else if(ch == '-')
                circuit[len++] = -3;
            else if(ch == '/')
                circuit[len++] = -4;
        }
    }   
    private double tonum(String s)  // Convert a String which represent a double
    {                               // number to a real double number 
        int point = s.indexOf('.');
        String zs = s.substring(0, point);
        String xs = s.substring(point + 1);
        double ans = 0;
        int len = 0;
        while(len++ < zs.length())
        {
            ans *= 10;
            ans += zs.charAt(len - 1) - '0';
        }
        double numxs = 0;
        for(int i = xs.length() - 1; i >= 0; i--)
        {
            numxs += xs.charAt(i) - '0';
            numxs /= 10;
        }
        return (ans + numxs);
    }
    public double resistance()
    {
    // Find the first ')' in the circuit, and from the 
    // the location we find '(' in the left, which means 
    // they are in the minimum circuit, we calculate it 
    // first. Then, we replace this sub-circuit with a 
    // specific number, in order to simplify the whole circuit
    // (/, 6, (-, 5, 6))
    //        |       |
    //        (   11  )
    // (/, 6,     11   )
        while(cnt-- > 0)
        {
            int start = lloc[cnt];
            int end = 0;
            for(end = start; end < len; end++)
                if(circuit[end] == -2.0)
                    break;

            if(circuit[start + 1] == -3)        // Calculate the Series connection
                series(start,end);
            else if(circuit[start + 1] == -4)   // Calculate the Parallel connection
                parallel(start,end);
        }
        return circuit[0];
    }
    private void series(int start, int end)
    {
        double res = 0;
        for(int i = start; i <= end; i++)
        {
            if(circuit[i] > 0)  // The resistence must be positive
                res += circuit[i];
            circuit[i] = 0;     // Clear the Subcircuit 
        }
        circuit[start] = res;   // Store the resistence of Subcircuit
    }
    private void parallel(int start, int end)
    {
        double res = 0;
        for(int i = start; i <= end; i++)
        {
            if(circuit[i] > 0)  // The resistence must be positive
                res += 1/circuit[i];
            circuit[i] = 0;     // Clear the Subcircuit 
        }
        circuit[start] = 1/res;   // Store the resistence of Subcircuit
    }

    public static void main(String[] args) {
        String s = "(-, 32.2, (/, 0.3, 4.22))";
        Circuit c = new Circuit();
        c.get_circuit(s);
        System.out.println(c.resistance());
    }
}

32.480088495575224
~~~



#### 3.修改 Circuit 类, 使得给定电源电压, 能够计算任意子网络两端的电势差以及流经该网络的电流.

   ![img](http://ybwu.org/ecnu-java/20-Fall/projects/inheritance/images/circuits/circuit2withbattery.png)

~~~txt
Voltage: 12V
Circuit: (/, (-, 3, 2), (-, 2, (/, 3, 6)))
~~~

~~~java
package Lab05;

public class Circuit {
    private double voltage;
    private double currency;
    private double[] circuit = new double[1000];
    private double[] const_circuit = new double[1000];  // Store it in a unchangable array11Q 
    private int cnt = 0;                // How many net in the circuit?
    private int len = 0;
    private int[] lloc = new int[100]; 
    private int[] rloc = new int[100];
    private void set_voltage(double d)
    {
        this.voltage = d;
        this.currency = this.voltage / this.resistance();  // calculate the currency
    }
    private void set_currency(double d)
    {
        this.currency = d;
        this.voltage = this.currency * this.resistance();  // calculate the voltage
    }
    private void get_circuit(String s)  // Store the String:circuit in an array
    {                                   // However, we don't consider the situation
        int loc = 0;                    // that the orignal number is not Integer
        int lenlloc = 0;
        int lenrloc = 0;
        while(loc++ < s.length())       // Change the String to Double
        {
            char ch = s.charAt(loc - 1);
            if(ch == ' ' || ch == ',')
                continue;
            else if('0' <= ch && ch <= '9')
            {
                int finishone = s.indexOf(',', loc - 1);// if the number isn't the last 
                                                     // in the Subcircuit, we can split
                                                     // it with ','
                
                int finishtwo = s.indexOf(')', loc - 1);// else, the numbe is the last
                                                     // in the Subcircuit, we should
                                                     // split it with ')'
                int finish = 0;
                if(finishone == -1 || finishone > finishtwo)
                    finish = finishtwo;
                else
                    finish = finishone;
                String num = s.substring(loc - 1, finish);
                loc = finish;
                circuit[len++] = tonum(num);// Convert a String which represent a double
                                            // number to a real double number 
            }
            else if(ch == '(')
            {
                circuit[len++] = -1;        // Since char cannot stored in the Array:double
                lloc[lenlloc++] = len - 1;  // We use negetive number to record it 
                cnt++;
            }
            else if(ch == ')')
            {
                circuit[len++] = -2;
                rloc[lenrloc++] = len - 1;
            }
            else if(ch == '-')
                circuit[len++] = -3;
            else if(ch == '/')
                circuit[len++] = -4;
                
            const_circuit[len - 1] = circuit[len - 1];
        }
    }   
    private double tonum(String s)  // Convert a String which represent a double
    {                               // number to a real double number 
        int point = s.indexOf('.');
        if(point == -1)
        {
            double ans = 0;
            int len = 0;
            while(len++ < s.length())
            {
                ans *= 10;
                ans += s.charAt(len - 1) - '0';
            }
            return ans;
        }
        else
        {
            String zs = s.substring(0, point);
            String xs = s.substring(point + 1);
            double ans = 0;
            int len = 0;
            while(len++ < zs.length())
            {
                ans *= 10;
                ans += zs.charAt(len - 1) - '0';
            }
            double numxs = 0;
            for(int i = xs.length() - 1; i >= 0; i--)
            {
                numxs += xs.charAt(i) - '0';
                numxs /= 10;
            }
            return (ans + numxs);
        }
    }
    public double resistance()
    {
    // Find the first ')' in the circuit, and from the 
    // the location we find '(' in the left, which means 
    // they are in the minimum circuit, we calculate it 
    // first. Then, we replace this sub-circuit with a 
    // specific number, in order to simplify the whole circuit
    // (/, 6, (-, 5, 6))
    //        |       |
    //        (   11  )
    // (/, 6,     11   )
        int tempcnt = cnt;
        while(tempcnt-- > 0)
        {
            int start = lloc[tempcnt];
            int end = 0;
            for(end = start; end < len; end++)
                if(circuit[end] == -2.0)
                    break;

            if(circuit[start + 1] == -3)        // Calculate the Series connection
                series(start,end);
            else if(circuit[start + 1] == -4)   // Calculate the Parallel connection
                parallel(start,end);
        }
        return circuit[0];
    }
    private void series(int start, int end)
    {
        double res = 0;
        for(int i = start; i <= end; i++)
        {
            if(circuit[i] > 0)  // The resistence must be positive
                res += circuit[i];
            circuit[i] = 0;     // Clear the Subcircuit 
        }
        circuit[start] = res;   // Store the resistence of Subcircuit
    }
    private void parallel(int start, int end)
    {
        double res = 0;
        for(int i = start; i <= end; i++)
        {
            if(circuit[i] > 0)  // The resistence must be positive
                res += 1/circuit[i];
            circuit[i] = 0;     // Clear the Subcircuit 
        }
        circuit[start] = 1/res;   // Store the resistence of Subcircuit
    }
    private void all_circuits(String s)
    {
        /*
        Find the location that '(' appears, that is the end of the subcircuit
        Then find whether there are any ')' appears before the end of the subcircuit
        Pair the ')' to the closet '(' in front of itself
        For those ')' which are outreach the end of the subcircuit
        Store it in reversed sequence to pair the '('
        For example:
        '(' :   0   4   12  17  
        ')' :  10 | 23  24  25    --->    ')' :   25  10  24  23
              find|--reverse--
        */
        int[] corrloc  = new int[cnt];
        for(int i = 0; i < cnt; i++)
            corrloc[i] = -1;        // initialize the array with negetive number
                                    // So, we can easily judge whether we need 
                                    // to fill in specific space or not
        int last = lloc[cnt - 1];
        int templen = -1;
        while(rloc[++templen] < last)   // Pair small loction corrrespondantly
        {
            for(int i = 0; i < cnt; i++)
            {
                if(lloc[i] > rloc[templen])
                {
                    int j = i - 1;
                    while(corrloc[j] != -1)
                        j -= 1;
                    corrloc[j] = rloc[templen];
                    break;
                }
            }
        }
        for(int i = cnt - 1; i >= templen; i--) // Store in reserved sequence
        {
            for(int j = 0; j < cnt; j++)
            {
                if(corrloc[j] == -1)
                {
                    corrloc[j] = rloc[i];
                    break;
                }
            }
        }
        
        /*System.out.print("In subcircuit" + ":");
        String cir = Tostring(lloc[1],corrloc[1]);
        Circuit nc = new Circuit();         // Create a new instance to calculate
        nc.get_circuit(cir);                // the data we need

        if(const_circuit[lloc[0] + 1] == -4)  // If the net is Parallel connection,
                                            // the voltage of subcircuit is eqaul
                                            // to the Supcircuit
            nc.set_voltage(this.voltage);
        else                                // If the net is Series connection,
                                            // the currency of subcircuit is eqaul
                                            // to the Supcircuit
            nc.set_currency(this.currency);
    
        System.out.println("The voltage is: " + nc.voltage);
        System.out.println("The currency is: " + nc.currency);
        System.out.println("-----------------------------------------------------");
        if(nc.cnt > 1)
            nc.all_circuits(cir);*/

        for(int i = 1; i < cnt; i++)
        {
            System.out.print("In subcircuit" + i + ":");
            String cir = Tostring(lloc[i],corrloc[i]);
        /*    Circuit nc = new Circuit();       // Create a new instance to calculate
            nc.get_circuit(cir);                // the data we need

            if(const_circuit[lloc[i - 1] + 1] == -4)// If the net is Parallel connection,
                                                // the voltage of subcircuit is eqaul
                                                // to the Supcircuit
                nc.set_voltage(this.voltage);
            else                                // If the net is Series connection,
                                                // the currency of subcircuit is eqaul
                                                // to the Supcircuit
                nc.set_currency(this.currency);
            
            System.out.println("The voltage is: " + nc.voltage);
            System.out.println("The currency is: " + nc.currency);
            System.out.println("-----------------------------------------------------");*/
        }
    }
    private String Tostring(int start, int end) // Return the Subcircuit in String
    {
        String cir = new String();
        for(int i = start; i <= end; i++)
        {
            if(const_circuit[i] == -1)
                cir += '(';
            else if(const_circuit[i] == -2)
                cir += ')';
            else if(const_circuit[i] == -3)
                cir += "-, ";
            else if(const_circuit[i] == -4)
                cir += "/, ";
            else 
            {
                cir += const_circuit[i];
                if(const_circuit[i + 1] != -2)  // Judge whether it's the latter
                                                // number or not
                    cir += ", ";
            }
        }
        System.out.println(cir);
        return cir;
    }
    public static void main(String[] args) {
        String s = "(/, 2, (-, 2, (/, 3, 6)))";
        //String s = args[0];
        Circuit c = new Circuit();
        c.get_circuit(s);       // Convert the string to specific Array
        c.set_voltage(12.0);
        c.all_circuits(s);

    }
}
~~~

~~~txt
尚未完成此题目，分别完成了对于任意电阻网络的所有子网络的导出，和对任意只有一个括号的小网络的计算电压电阻
~~~

~~~txt
子网络

In circuit:(/, (-, 3, 2), (-, 2, (/, 3, 6)))
In subcircuit1:(-, 3.0, 2.0)
In subcircuit2:(-, 2.0, (/, 3.0, 6.0))
In subcircuit3:(/, 3.0, 6.0)
---------------------------------------------------------------------------------
In circuit:(-, 4.1, (-, 9.2, (/, (-,(/, 6.5, (-, 2.5, (-, 4.0, 2.8))),(-, 4.4, 2.7)), (-, 3.5, (-, 6.9, (/, 4.4, (/, 8.0, (-, 5.0, 7.7))))))))
In subcircuit1:(-, 9.2, (/, (-, (/, 6.5, (-, 2.5, (-, 4.0, 2.8)))(-, 4.4, 2.7))(-, 3.5, (-, 6.9, (/, 4.4, (/, 8.0, (-, 5.0, 7.7)))))))
In subcircuit2:(/, (-, (/, 6.5, (-, 2.5, (-, 4.0, 2.8)))(-, 4.4, 2.7))(-, 3.5, (-, 6.9, (/, 4.4, (/, 8.0, (-, 5.0, 7.7))))))
In subcircuit3:(-, (/, 6.5, (-, 2.5, (-, 4.0, 2.8)))(-, 4.4, 2.7))
In subcircuit4:(/, 6.5, (-, 2.5, (-, 4.0, 2.8)))
In subcircuit5:(-, 2.5, (-, 4.0, 2.8))
In subcircuit6:(-, 4.0, 2.8)
In subcircuit7:(-, 4.4, 2.7)
In subcircuit8:(-, 3.5, (-, 6.9, (/, 4.4, (/, 8.0, (-, 5.0, 7.7)))))
In subcircuit9:(-, 6.9, (/, 4.4, (/, 8.0, (-, 5.0, 7.7))))
In subcircuit10:(/, 4.4, (/, 8.0, (-, 5.0, 7.7)))
In subcircuit11:(/, 8.0, (-, 5.0, 7.7))
In subcircuit12:(-, 5.0, 7.7)
~~~



~~~txt
单括号电路

In subcircuit:(-, 6.9, (/, 4.4, (/, 8.0, (-, 5.0, 7.7))))
The voltage is: 8.698143384993015
The currency is: 0.9433876042877101
-----------------------------------------------------
In subcircuit:(/, 4.4, (/, 8.0, (-, 5.0, 7.7)))
The voltage is: 2.1887689154078154
The currency is: 0.9433876042877101
-----------------------------------------------------
In subcircuit:(/, 8.0, (-, 5.0, 7.7))
The voltage is: 2.1887689154078154
The currency is: 0.4459401235132065
-----------------------------------------------------
In subcircuit:(-, 5.0, 7.7)
The voltage is: 2.1887689154078154
The currency is: 0.17234400908722958
-----------------------------------------------------
~~~

