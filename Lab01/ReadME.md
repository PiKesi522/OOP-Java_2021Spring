# Project 1 第一个 Java 程序



#### 1.创建 HelloWorld.java, 编译并执行.

```java
public class HelloWorld{
    public static void main(String args[]){
        System.out.println("Hello World~");
    }
}

Hello World~
```



#### 2.分别删除第一行的 public, 第二行的 public, static, void, args, String 分别会发生什么?

##### 	1）删除第一行的public

```java
class HelloWorld{
    public static void main(String args[]){
        System.out.println("Hello World~");
    }
}

Hello World~
```

##### 	2）删除第二行的public

```java
public class HelloWorld{
    static void main(String args[]){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```

##### 	3）删除static

```java
public class HelloWorld{
    public void main(String args[]){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```

##### 	4）删除void

```java
public class HelloWorld{
    public static main(String args[]){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```

##### 	5）删除args

```java
public class HelloWorld{
    public static void main(String[] ){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```

##### 	6）删除String

```java
public class HelloWorld{
    public static void main(args[]){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```



#### 3.如果错误的拼写了 public,static,void,args,String 分别会发生什么？

##### 	1）public -> publlc

```java
public class HelloWorld{
    publlc static void main(String args[]){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```

##### 	2）static -> statcc

```java
public class HelloWorld{
    public statcc void main(String args[]){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```

##### 	3）void -> boid

```java
public class HelloWorld{
   
 public static boid main(String args[]){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```

##### 	4）args -> arge

```java
public class HelloWorld{
    public static void main(String arge[]){
        System.out.println("Hello World~");
    }
}

Hello World~
```

​	args只是作为变量名存在，改变变量名可以正常运行。

##### 5）String -> Stling

```java
public class HelloWorld{
    public static void main(Strlng args[]){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```



#### 4.如果错误拼写了 System,out,println 分别会发生什么？

##### 	1）System -> Systen

```java
public class HelloWorld{
    public static void main(String args[]){
        Systen.out.println("Hello World~");
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:
        Systen cannot be resolved

        at Helloworld.main(Helloworld.java:3)
```

##### 	2）out -> oue

```java
public class HelloWorld{
    public static void main(String args[]){
        System.oue.println("Hello World~");
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:
        oue cannot be resolved or is not a field

        at Helloworld.main(Helloworld.java:3)
```

##### 	3）println -> printin

```java
public class HelloWorld{
    public static void main(String args[]){
        System.out.printin("Hello World~");
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:
        The method printin(String) is undefined for the type PrintStream

        at Helloworld.main(Helloworld.java:3)
```



#### 5.将第二行替换为 public static void main() 会发生什么？

```java
public class HelloWorld{
    public static void main(){
        System.out.println("Hello World~");
    }
}

Error: Main method not found in the file, please define the main method as: public static void main(String[] args)
```



#### 6.将文件重命名为 HalloWorld.java 会发生什么？

```java
public class HelloWorld{
    public static void main(String args[]){
        System.out.println("Hello World~");
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:

        at Helloworld.main(Halloworld.java:2)
```



#### 7.将 HelloWorld.java 中的每个空格替换为两个空格, 每行间增加一些空行, 删除每行前的空格分别会发生什么?

```java
public  class  HelloWorld{


public  static  void  main(String  args[]){


System.out.println("Hello  World~");
}
}

Hello  World~
```

​	增加和删除空格和空行不会影响程序的运行



#### 8.编写程序 Hi.java

```java
public class Hi {
    public static void main(String[] args) {
        System.out.print("Hi, ");
        System.out.print(args[0]);
        System.out.println(". How are you?");
    }
}
```

```cmd
D:\Java_for_vscode>javac Hi.java

D:\Java_for_vscode>java Hi @#$%
Hi, @#$%. How are you?

D:\Java_for_vscode>java Hi 1024
Hi, 1024. How are you?

D:\Java_for_vscode>java Hi Bob
Hi, Bob. How are you?

D:\Java_for_vscode>java Hi.java Bob
Hi, Bob. How are you?

D:\Java_for_vscode>java Hi.class Bob
错误: 找不到或无法加载主类 Hi.class
原因: java.lang.ClassNotFoundException: Hi.class

D:\Java_for_vscode>java Hi Alice Bob
Hi, Alice. How are you?
```

​	在main函数定义时候创建了名称为 args 的变量用于存放 String[] 结构，args[0] 会接收到来的第一个 String 结构，而后的会被存放在args[0] 之后的位置。



##### *	修改以上程序, 使它能够接收3个命令行参数, 并倒序输出它们. 比如执行 `java Hi Alice Bob Carol`, 输出 `Hi Carol, Bob, Alice.`.

```java
public class Hi {
    public static void main(String[] args) {
        System.out.print("Hi, ");
        for(int i=0;i<3;i++)
        {
            System.out.print(args[i]);
            System.out.print(",");
        }
        System.out.println(". How are you?")
    }
}
```

```cmd
D:\Java_for_vscode>javac Hi.java

D:\Java_for_vscode>java Hi Carol Bob Alice
Hi, Carol,Bob,Alice,. How are you?
```



#### 9.编写程序输出10次 "Hello World!".

```java
public class Lab_01_10 {
    public static void main(String args[])
    {
        for(int i=0;i<10;i++)
        {
            System.out.println("Hello World!");
        }
    }
}
```

​	简单的用 for 循环来输出10次 HelloWorld



#### 10.Java 语言的变量类型 (int, float, double), 表达式(运算符, 逻辑表达式等)以及控制结构 (条件语句, 循环语句等) 和 C 语言比较相似. 你能否用使用 C 语言的知识写一些稍微复杂的 Java 程序呢?

```java
public class Lab_01_10 
{
    public static void main(String args[])
    {
        System.out.println("—————————ANSWER—————————");
        int x = 10000;
        for(int i = 2; i < 100;i++)
        {
            while(x % i == 0)
            {
                if(x == 1)
                    break;

                System.out.print(i);
                System.out.print(',');
                x = x / i;
            }
            if(x == 1)
                break;
            else if(x < i)
            {
                System.out.println(x);
                break;
            }
        }
    }
}

—————————ANSWER—————————
2,2,2,2,5,5,5,5,
```

​	计算10000的的素因数分解，判断的因数从 2 到 根号x，当 x < i 时候已经不需要再次判断，直接输出非 1 的情况。

