# Project 3 使用Java包

## Project 3.a 使用String

| 方法                               | 说明                                                         |
| :--------------------------------- | :----------------------------------------------------------- |
| String(char []s)                   | 构造函数, 从字符串数组构造 String                            |
| String(String s)                   | 构造函数, 用String s 构造                                    |
| int length()                       | 返回字符串长度                                               |
| char charAt(int i)                 | 返回位置 i 处的字符                                          |
| String substring(int s, int e)     | 返回从位置 s 到位置 e 的子串                         #重载   |
| String substring(int s)            | 返回从位置 s 开始的子串                                 #重载 |
| String toUpperCase()               | 返回一个全大写的字符串                                       |
| String toLowerCase()               | 返回一个全小写的字符串                                       |
| boolean startsWith(String s)       | 是否以字符串 s 开头                                          |
| boolean endsWith(String s)         | 是否以字符串 s 结尾                                          |
| int indexOf(String s)              | 返回首次出现字符串 s 的位置                         #重载    |
| int indexOf(String s, int i)       | 返回位置 i 之后首次出现字符串 s 的位置       #重载           |
| int lastIndexOf(String s)          | 返回最后一次出现字符串 s 的位置                              |
| String trim()                      | 返回一个新的字符串, 去除了原字符串前后的空白字符             |
| String replace(String a, String b) | 返回一个新的字符串, 将原字符串中 a 替换为 b                  |
| int compareTo(String a)            | 与字符串 a 通过词典序比较大小                                |



#### 1.给定一个字符串, 判断它是否是一个网址 (假设所有网址以“http:” 开头).

 ~~~java
public class Lab_03_a_01{
    public static void main(String args[])
    {
        boolean net = args[0].startsWith("http:");
        if(net)
        {
            System.out.println("Yes!");
        }
        else
        {
            System.out.println("No!");
        }
    }
}
 ~~~

~~~cmd
C:\Java_for_vscode>javac Lab_03_a_01.java

C:\Java_for_vscode>java Lab_03_a_01 http://www.baidu.com
Yes!

C:\Java_for_vscode>java Lab_03_a_01 www.baidu.com
No!
~~~



#### 2.给定一个网址字符串, 根据 “.” 将它分解成字串. 例如 http://www.ecnu.edu.cn 分解成为 http://www, ecnu, edu, cn. (提示: 使用 split() 方法)

~~~java
public class Lab_03_a_02 {
    public static void main(String[] args){
        String[] s = args[0].split("\\.");	// Since ' and " has specific meaning in thr programme 
        for(int i =0; i < s.length - 1;i++)		// We should use Escape character
        {
            System.out.print(s[i] + ",");
        }
        System.out.print(s[s.length - 1] + ".");
    }
}
~~~

~~~cmd
PS C:\Java_for_vscode> javac Lab_03_a_02.java
PS C:\Java_for_vscode> java Lab_03_a_02 http://www.ecnu.edu.cn
http://www,ecnu,edu,cn.
~~~



#### 3.给定一个代表文件的字符串, 输出它的扩展名. 例如: a.jpg 输出 jpg.

~~~java
public class Lab_03_a_03 {
    public static void main(String[] args)
    {
        int find = args[0].lastIndexOf(".");
        String suffix = args[0].substring(find+1);
        System.out.println(suffix);
    }
}
~~~

~~~cmd
C:\Java_for_vscode>javac Lab_03_a_03.java

C:\Java_for_vscode>java Lab_03_a_03 a.jpg
jpg
~~~



#### 4.给定一个代表 Unix 文件路径的字符串, 输出它的路径名与文件名. 例如 /home/tom/documents/a.jpg, 路径名为 /home/tom/documents/, 文件名为 a.jpg

~~~java
public class Lab_03_a_04 {
    public static void main(String[] args)
    {
        int apart = args[0].lastIndexOf("/");
        String path = args[0].substring(0,apart+1);
        String suffix = args[0].substring(apart+1);
        System.out.println("The path is: " + path);
        System.out.println("The suffix is: " + suffix);
    }
}
~~~

~~~cmd
C:\Java_for_vscode>javac Lab_03_a_04.java

C:\Java_for_vscode>java Lab_03_a_04 /home/tom/documents/a.jpg
The path is: /home/tom/documents/
The suffix is: a.jpg
~~~



#### 5.从标准输入流中读入一串字符, 将其中的单引号替换成双引号, 输出到标准输出.

~~~java
public class Lab_03_a_05 {
    public static void main(String[] args)
    {
        String s = args[0].replace("\'","\""); // Since ' and " has specific meaning in thr programme  
        System.out.println(s);				   // We should use Escape character
    }
}
~~~

~~~cmd
C:\Java_for_vscode>javac Lab_03_a_05.java

C:\Java_for_vscode>java Lab_03_a_05 "hello 'world' "
hello "world"
~~~



#### 6.编写程序 Cat.java, 它可以有任意多的命令行参数, 每个参数为一个文件名. Cat.java 将文件按照参数的顺序合并成一个文件, 并将合并后的文件内容输出到标准输出.

~~~java
public class Cats {
    public static void main(String[] args)
    {
        int num = args.length;
        for(int i = 0; i < num - 1;i++)		// Using Bubble Sort to reform the array
        {
            for(int j = 0; j < num - 1 - i; j++)
            {
                if(args[j].compareTo(args[j + 1]) > 0)
                {
                    String temp = args[j + 1];
                    args[j + 1] = args[j];
                    args[j] = temp;
                }
            }
        }
        for(int i = 0; i < num; i++)       // Print the array
        {
            System.out.println(args[i]);
        }
    }
}
~~~

~~~cmd
C:\Java_for_vscode>javac Cats.java

C:\Java_for_vscode>java Cats 1 2 asda asdqw asd cxas 123 asd asda123
1
123
2
asd
asd
asda
asda123
asdqw
cxas
~~~



#### 7.给定一个字符串, 代表一个16进制数. 将其转换成10进制整数, 输出到标准输出.

~~~java
public class Lab_03_a_07 {
    public static void main(String[] args)
    {
        int len = args[0].length();
        char temp;
        int ans = 0;
        for(int i = 0; i < len;i++)
        {
            int tnum = 0;	// Create a temporary int to store the correspondent number in DEC
            temp = args[0].charAt(i); 
            if('A' <= temp && temp <= 'F')
            {
                tnum = temp - 'A' + 10;
            }
            else if('a' <= temp && temp <= 'f')
            {
                tnum = temp - 'a' + 10;
            }
            else if('0' <= temp && temp <= '9')
            {
                tnum = temp - '0';
            }
            ans *= 16;
            ans += tnum;
        }
        System.out.println("The DEC is: " + ans);
    }
}
~~~

~~~cmd
C:\Java_for_vscode>javac Lab_03_a_07.java

C:\Java_for_vscode>java Lab_03_a_07 A7c73B0
The DEC is: 170654889
~~~



#### 8.给定一个文件, 其中每行一个词. 输出包含字符最多的词. 如果存在多个这样的词(假设不超过10个), 则将它们都输出.

~~~java
public class Lab_03_a_08 {
    public static void main(String[] args)
    {
        String[] names = FileIO.getAllLinesFromFile("Lab_03_a_08.txt");
        int len = names.length;		 // Calculate the lines in the files
        int[] sizes = new int[len];  // Use a array to store the length of each name
        
        for(int i = 0;i < len;i++)
        {
            sizes[i] = names[i].length();  // Use a array to store the length of each name
        }

        int max = 0;					// Find the word which have the most characters
        for(int i = 0; i < len; i++)
        {
            if(sizes[i] > max)
                max = sizes[i];			// Find the word which have the most characters
        }

        for(int i = 0; i < len; i++)
        {
            if(sizes[i] == max)			// 
                System.out.println(names[i]);
        }
    }
}

Cathline
LLLLLLLL
NII~~GER
~~~



#### 9.给定一个字符串, 将其倒序输出. 要求不使用循环语句.

~~~java
public class Lab_03_a_09 {
    public static void main(String[] args)
    {
        StringBuffer buffer = new StringBuffer(args[0]);
        System.out.println(buffer.reverse());
    }
}

~~~

~~~cmd
C:\Java_for_vscode>javac Lab_03_a_09.java

C:\Java_for_vscode>java Lab_03_a_09 HelloWorld
dlroWolleH
~~~



#### 10.给定一个文件, 统计其中26个英文小写字母出现的频率.

~~~java
public class Lab_03_a_10 {
    public static void main(String[] args)
    {
        String[] lines = FileIO.getAllLinesFromFile("Lab_03_a_10.txt");
        int line_size = lines.length;      // In case that the file has more than one line
        int[] appears = new int[26];       // The int[] is used to calculate the times that char appears
        for(int i = 0; i < 26; i++)         
        {   
            appears[i] = 0;                // The original num is all 0
        }
        for(int i = 0; i < line_size; i++)
        {
            int single_len = lines[i].length();
            for(int j = 0; j < single_len; j++)
            {
                char ch = lines[i].charAt(j);
                if('a' <= ch && ch <= 'z') // Only find the char from a-z
                    appears[ch - 'a']++;   // check all the char, if appears, correspondent location++
            }
        }
        
        char now = 'a';
        for(int i = 0; i < 26; i++)
        {
            if(appears[i] != 0)            // If a char hasn't appeared ever, it won't be printed
            {
                System.out.println("\'" + now + "\' appears for " + appears[i] + " times.");
            }
            now++;
        }
    }
}
~~~

~~~cmd
'a' appears for 20 times.
'b' appears for 4 times.
'c' appears for 1 times.
'd' appears for 13 times.
'e' appears for 38 times.
'f' appears for 7 times.
'g' appears for 6 times.
'h' appears for 13 times.
'i' appears for 12 times.
'k' appears for 1 times.
'l' appears for 5 times.
'm' appears for 9 times.
'n' appears for 25 times.
'o' appears for 17 times.
'p' appears for 2 times.
'r' appears for 15 times.
's' appears for 14 times.
't' appears for 16 times.
'u' appears for 5 times.
'v' appears for 2 times.
'w' appears for 7 times.
'y' appears for 5 times.
~~~



#### 11.编写程序 Print.java, 它有以下命令行选项, 根据不同的选项得到不同的运行结果.

| 选项 \ | 用法举例                | 说明                                                         |
| :----- | :---------------------- | :----------------------------------------------------------- |
| -t     | `java Print -t type`    | 若 type=n 则输出0到9的数字, type=a 则输出a到z的字母, 默认 type=n (即不带 -t 选项执行 `java Print` 将输出数字) |
| -o     | `java Print -o out.txt` | 输出到文件out.txt. 默认输出到标准输出                        |
| -h     | `java Print -h`         | 输出帮助信息到标准输出, 不输出其他信息                       |

例如 `java Print -t a -o a.txt` 将输出 a 到 z 到文件 a.txt. `java Print -o b.txt` 输出数字0到9到 b.txt. `java Print -t a`, 将输出 a 到 z 到标准输出. `java Print -h` 输出的帮助信息为

```java
usage: % java Print [OPTIONS]
-t type       if type=n print 0-9, if type=a print a-z. Default: type=n
-o out.txt    outputs to out.txt, Default: standard out
-h            print this help informantion
```

~~~java
public class Print {
    String[] operaters = new String[100];
    String sstream;
    int len = 0;

    void Insert(String s)
    {
        operaters[len++] = s; 
    }
    void T() // -t Default
    {
        System.out.println(sstream);
    }
    void T(String txt) // -t Overwrite Function
    {
        FileIO.writeStringToFile(sstream, txt);
    }
    void O() // -o Defalut
    {
        sstream = "\0";
        for(char a = '0'; a <= '9' ; a++)
        {
            sstream += a;
        }
    }
    void O(String s) // -o Overwrite Function
    {
        sstream = "";
        if(s == "n")
        {
            for(char a = '0'; a <= '9' ; a++)
            {
                sstream += a;
            }
        }
        else if(s == "a")
        {
            for(char a = 'a'; a <= 'z'; a++)
            {
                sstream += a;
            }
        }
        else
        {
            System.out.println("WRONG: No such operater!");
        }
    }
    void H() // -h
    {
        System.out.println("usage: % java Print [OPTIONS]");
        System.out.println("-t type       if type=n print 0-9, if type=a print a-z. Default: type=n");
        System.out.println("-o out.txt    outputs to out.txt, Default: standard out");
        System.out.println("-h            print this help informantion");
    }
    void moves() // Read the movement stores int the operaters[]
    {
        int i = 0;
        while(i != len)
        {
            if(operaters[i] == "-h")
            {
                H();
            }
            else if(operaters[i] == "-t")
            {
                if(i == (len - 1)) // In case outreach the operaters
                    O(); 
                else if(operaters[i+1] == "n" || operaters[i+1] == "a" )  // Using overwrite function
                    O(operaters[i+1]);
                else               // Default
                    O();
            }
            else if(operaters[i] == "-o")
            {
                if(i == (len - 1)) // In case outreach the operaters
                    T();
                else if(operaters[i+1] == "-t" || operaters[i+1] == "-h" || operaters[i+1] == "-o" )
                    T();
                else               // Using overwrite function
                    T(operaters[i+1]);
            }

            i++;
        }
    }

    public static void main(String[] args)
    {
        int len = args.length;
        Print p = new Print();
        for(int i = 0; i < len ; i++)
        {
            p.Insert(args[i]);
        }
        p.moves();
    }
}
~~~



#### 12.给定一个字符串, 判断它能否作为一个安全的密码. 安全密码的要求如下:

- 至少8个字符
- 包含至少1个数字
- 至少包含一个大写字母
- 至少包含一个非字母字符

~~~java
public class Lab_03_a_12 {

    String password;             // password itself, it will be used to test the availability
    boolean available = false;
    public Lab_03_a_12(String s) // Constructor Function
    {
        password = s;
    }

    boolean enough_length()         // Check whether the password have enough characters
    {
        int len = password.length();
        if(len >= 8)             
            return true;
        else
            return false;
    }

    boolean at_least_one_number()   // Check whether the password have numbers
    {
        for(char ch = '0'; ch <= '9'; ch++)
        {
            if(password.indexOf(ch) != -1)
            {                   
                return true;
            }
        }
        return false;
    }

    boolean at_least_one_UPPER()    // Check whether the password have UPPER words
    {
        for(char ch = 'A'; ch <= 'Z'; ch++)
        {
            if(password.indexOf(ch) != -1)
            {
                return true;
            }
        }
        return false;
    }

    boolean at_least_one_Non_Word()// Check whether the password have Non-word characters
    {
        int size = password.length();
        for(int i = 0; i < size; i++)
        {
            char ch = password.charAt(i);   // Traversal the char in the password
            if('a' <= ch && ch <= 'z')      // If the char is from 'a' to 'z', pass
                continue;
            else if('A' <= ch && ch <= 'Z') // If the char is from 'A' to 'Z', pass
                continue;
            return true;
        }
        return false;
    }

    void acceptable()       // Intergrate all the premises to check the availability of the password
    {
        if(enough_length())
        {
            if(at_least_one_Non_Word())
            {
                if(at_least_one_UPPER())
                {
                    if(at_least_one_number())
                    {
                        System.out.println("This password is available");
                        available = true;
                    }
                    else
                    {   
                        System.out.println("WRONG: " + password + " is unavailable:");
                        System.out.println("  At least one number is required");
                    }
                }
                else
                {
                    System.out.println("WRONG: " + password + " is unavailable:");
                    System.out.println("  At least one UPPER word is required");
                }
            }
            else
            {
                System.out.println("WRONG: " + password + " is unavailable:");
                System.out.println("  At least one non-word char is required");
            }
        }
        else
        {
            System.out.println("WRONG: " + password + " is unavailable:");
            System.out.println("  Password should be at least with 8 numbers");
        }
    }

    public static void main(String[] args){
        for(int i = 0; i < args.length; i++)
        {
            Lab_03_a_12 key = new Lab_03_a_12(args[i]);
            key.acceptable();
        }
    }
}
~~~

~~~cmd
C:\Java_for_vscode>javac Lab_03_a_12.java

C:\Java_for_vscode>java Lab_03_a_12 123 123A1231 123~123a12 asdasd!@#a 12345678Aa~@

WRONG: 123 is unavailable:
  Password should be at least with 8 numbers
  
This password is available

WRONG: 123~123a12 is unavailable:
  At least one UPPER word is required
  
WRONG: asdasd!@#a is unavailable:
  At least one UPPER word is required
  
This password is available
~~~





## Project 3.b 图像处理

| 方法  java.Color           | 说明                 |
| :------------------------- | :------------------- |
| Color(int r, int g, int b) | Color 对象的构造函数 |
| int getRed(                | 获得红色的强度       |
| int getGreen()             | 获得绿色的强度       |
| int getBlue()              | 获得蓝色的强度       |
| Color brighter()           | 返回一个更深的颜色   |
| Color darker()             | 返回一个更浅的颜色   |
| String toString()          | 该颜色的String表示   |
| boolean equals(Color c)    | 是否与颜色c相同      |

| 方法  java.Picture              | 说明                              |
| :------------------------------ | :-------------------------------- |
| Picture(String s)               | 从文件 s 创建一个图像对象         |
| Picture(int w, int h)           | 创建一个宽为 w, 高为 h 的空白图像 |
| int width()                     | 获得图像的宽度                    |
| int height()                    | 获得图像的高度                    |
| Color get(int i, int j)         | 返回像素 (i, j) 的颜色            |
| void set(int i, int j, Color c) | 设置像素 (i, j) 的颜色为 c        |
| void show()                     | 将该图像显示在一个窗口中          |
| void save(String s)             | 将该图像保存为文件 s              |

#### 1.通过命令行参数给定一个图片文件, 输出它的长和宽. (以下各题中的图片文件, 参数都通过命令行参数形式给出).

~~~java
public class Lab_03_b_01 {
    public static void main(String[] args)
    {
        Picture pic = new Picture(args[0]);
        int w = pic.width();
        int h = pic.height();
        System.out.println("Height is: " + h + ", Width is: " + w);
    }
}
~~~

~~~cmd
C:\Java_for_vscode>javac Lab_03_b_01.java

C:\Java_for_vscode>java Lab_03_b_01 yaoyao.jpg
Height is: 2576, Width is: 4592
~~~



#### 2.给定一个图片文件, 将其转换成为相应的灰度图 (Grayscale). 当 RGB 颜色模型中红绿蓝三色强度取相同值 v 时, 该颜色为灰色, 其灰度值为 v (0<= v <= 255). 

图片的灰度图可以通过如下方法得到: 将图中每个像素点的颜色置为灰色, 且这些灰色的灰度值等于该颜色亮度.

~~~java
import java.awt.Color;
public class Lab_03_b_02 {
     public static double lum(Color color) 
     {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return .299*r + .587*g + .114*b;
     }
    public static void main(String[] args)
    {
        //Picture Paimon = new Picture("genshin.jpg");
        Picture Paimon = new Picture(args[0]);
        int H = Paimon.height();
        int W = Paimon.width();
        for(int i = 0; i < H; i++)
        {
            for(int j = 0; j < W; j++)
            {
                int v = (int)lum(Paimon.get(i,j));  // Since function:lum return type:double, it need
                                                    //    to be transformed to type:int
                Color grey = new Color(v,v,v);      // Creat a correspondent grey with the same luminance
                Paimon.set(i, j, grey);             // Traversal all the pixel and change it
            }
        }
        Paimon.save("RIP.jpg");
    }
}
~~~

<img src="C:\Java_for_vscode\genshin.jpg" alt="genshin" style="zoom:100%;" />              	<img src="C:\Java_for_vscode\RIP.jpg" alt="RIP" style="zoom:100%;" />



#### 3.给定一个图片文件, 以及参数 w, h, 将其转换成为宽为 w, 高为 h 的图片.

~~~java
import java.awt.Color;
public class Lab_03_b_03 {
    public static void main(String[] args)
    {
        //Picture Keli = new Picture("keli.jpg");
        Picture Keli = new Picture(args[0]);
        int H = Keli.height();
        int W = Keli.width();
        int a = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]); 
        Picture Board = new Picture(a,b);
        for(int i = 0; i < a; i++)
        {
            for(int j = 0; j < b; j++)
            {
                double prew = (double)W* ((double)i/(double)a); // 等比例分割原图，寻找需要的像素点
                double preh = (double)H* ((double)j/(double)b); // 等比例分割原图，寻找需要的像素点
                Color col = Keli.get((int)prew, (int)preh);     // Type:int is needed to create a new pixel
                Board.set(i, j, col);
            }
        }
        Board.save("FatKeli.jpg");
    }
}
~~~

<img src="C:\Java_for_vscode\keli.jpg" alt="keli" style="zoom:40%;" />                <img src="C:\Java_for_vscode\FatKeli.jpg" alt="FatKeli" style="zoom:40%;" />



#### 4.给定一个图片文件, 以及参数N. 输出 N 张图片, 其中第n张图片为从输入图片和其对应灰度图的一个渐变图.

~~~java
import java.awt.Color;
public class Lab_03_b_04 {
    public static double lum(Color color) 
    {
       int r = color.getRed();
       int g = color.getGreen();
       int b = color.getBlue();
       return .299*r + .587*g + .114*b;
    }
    public static void main(String[] args)
    {
        //Picture rainbow = new Picture("Rainbow.jpg");
        Picture rainbow = new Picture(args[0]);
        int W = rainbow.width();
        int H = rainbow.height();
        int N = Integer.parseInt(args[1]);
        //int N = 30;
        for(int i = 0; i < N; i++) // N pictures is needed
        {
            Picture Board = new Picture(W,H); // Create a picture with lower lumanince
            for(int w = 0; w < W; w++)
            {
                for(int h = 0; h < H; h++)
                {
                    int v = (int)lum(rainbow.get(w, h)); // The final lumniance should go to
                    Color pre_col = rainbow.get(w, h); // Get the orignal color
                    // N : the difference between each picture
                    // V : the final GreyDegree
                    // D : the orignal Color
                    // F(x) = kx + b; Since the changes is linear increase 
                    // F(0) = b = D; F(N) = kN + b = V;
                    //      => b = D; k = (V - D)/N;
                    // F(x) = ((V = D)/N) * x + D;
                    double nred =  ((double)(v - pre_col.getRed()) / (double)N ) * (double)i + (double)pre_col.getRed(); 
                    double nblue = ((double)(v - pre_col.getBlue()) / (double)N ) * (double)i + (double)pre_col.getBlue();
                    double ngreen = ((double)(v - pre_col.getGreen()) / (double)N ) * (double)i + (double)pre_col.getGreen();
                    Color col = new Color((int)nred,(int)nblue,(int)ngreen); 
                    Board.set(w, h, col);  
                }
            }
            Board.show();
            Board.save("rainbow" + i + ".jpg");
        }
    }
}
~~~



#### 5.给定一个图片文件, 以及参数x, y, E. 输出原始图片的一个正方形裁剪, 其中正方形的中心在 (x, y), 边长是 E. x, y, E 为百分比. 例如 x=10, y=20, E=20, 表示中心位于 原始图片宽的10%, 高20%处. 边长为原始宽度的20%.

~~~java
import java.awt.Color;
public class Lab_03_b_05 {
    public static void main(String[] args){
        //Picture hutao = new Picture("hutao.jpg");
        Picture hutao = new Picture(args[0]);
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int E = Integer.parseInt(args[3]);
		//int x = 30,y = 30, E = 20;
        int W = hutao.width();
        int H = hutao.height();
        int X = W * x / 100;        // Get the start point
        int Y = H * y / 100;        // Get the start point 
        int edge = E * W / 100;     // Get the size of the Board

        int left = X - edge / 2;    // Locate the left-edge
        int right = X + edge / 2;   // Locate the right-edge
        int up = Y - edge / 2;      // Locate the up-edge
        int down = Y + edge / 2;    // Locate the down-edge

        if(left < 0 || right > W)               // If the Board outreach the Picture 
            System.out.println("Too wide");
        else if(up < 0 || down > H)             // If the Board outreach the Picture 
            System.out.println("Too tall");
        else
        {
            Picture Board = new Picture(edge, edge);
            for(int i = left; i < right; i++)
            {
                for(int j = up; j < down; j++)
                {
                    Color col = hutao.get(i, j);
                    Board.set(i - left, j - up, col);
                }
            }
            Board.show();
            Board.save("Gui.jpg");
        }
    }
}
~~~

<img src="C:\Java_for_vscode\hutao.jpg" alt="hutao" style="zoom:50%;" />                    <img src="C:\Java_for_vscode\Gui.jpg" alt="Gui" style="zoom:50%;" />



#### 6.给定一个图片文件, 产生该图片的一个镜像图片(关于 y 轴对称)

~~~java
import java.awt.Color;
public class Lab_03_b_06 {
    public static void main(String[] args){
        //Picture Paimon = new Picture("genshin.jpg");
        Picture Paimon = new Picture(args[0]);
        int W = Paimon.width();
        int H = Paimon.height();
        Picture Board = new Picture(W,H);
        for(int i = 0;i < W; i++)
        {
            for(int j = 0; j < H; j++)
            {
                Color col = Paimon.get(i, j);
                Board.set(W-1-i, j, col); // Rotate with Y, fixed the Y, and change the X
            }
        }
        Board.show();
        Board.save("nihsneg.jpg");
    }
}
~~~

![genshin](C:\Java_for_vscode\genshin.jpg)                   ![nihsneg](C:\Java_for_vscode\nihsneg.jpg)

#### 7.滤镜 (filters) 可以看作对像素矩阵的某种变换. 通过添加滤镜, 我们可以改变图片的视觉效果.

- ##### Linear filter: 每一个像素的颜色变为周围 9 个像素点(包含它本身)颜色的平均值. 这个变换等价于将矩阵[1, 1, 1; 1, 1, 1; 1, 1, 1]/9 与 9个像素点做 *卷积 (Convolution)* . 而这个矩阵被称为卷积矩阵 (convolutional matrix). 关于卷积矩阵可以参考[1](https://en.wikipedia.org/wiki/Kernel_(image_processing)), [2](https://docs.gimp.org/2.6/en/plug-in-convmatrix.html).

- ##### Blur filter: 卷积矩阵为 [1/13, 1/13, 1/13; 1/13, 5/13, 1/13; 1/13, 1/13, 1/13].

- ##### Emboss filter: 卷积矩阵为 [-1, 0, 1; -1, 1, 1; -1, 0, 1], 或者 [1, 0, -1; 2, 0, -2; 1, 0, -1] 或者[-1, -1, 0; -1, 1, 1; 0, 1, 1].

- ##### Sharpen filter: 卷积矩阵为 [0, -1, 0; -1, 5, -1; 0, -1, 0].

- ##### Oil painting filter: 给定参数 w, 将每个像素 (i, j) 的颜色替换为所有与 (i, j) *Manhattan 距离* 小于 w 的像素点中出现次数最多的颜色. 其中 Manhattan 距离(也称为 \(l_1\) 距离)定义见 [3](https://en.wikipedia.org/wiki/Taxicab_geometry), [4](http://mathworld.wolfram.com/TaxicabMetric.html).

~~~java
import java.awt.Color;
import java.util.Arrays;
public class Lab_03_b_07 {
    Picture former;
    int W;
    int H;
    public Lab_03_b_07(String txt) // Constructor function
    {
        this.former = new Picture(txt);
        W = former.width();
        H = former.height();
    }
    void filter(double[][] Convolution_Martix,int times)
    {
        Picture Board = new Picture(W,H);       // Create a draw board
        for(int i = 1; i < W - 1; i++)          // In order to prevent troubles, the edges aren't processed
        {                                       // To make the picture normal, we use another Traversal to
            for(int j = 1; j < H - 1; j++)      // keep the color in the edge
            {
                double newRed = 0.0;
                double newBlue = 0.0;
                double newGreen = 0.0;
                double[] Color_martix = {newRed,newGreen,newBlue}; // Create a Arrays to store Three 
                for(int w = i - 1; w <= i + 1; w++)				   // different colors
                {
                    for(int h = j - 1; h <= j + 1; h++)
                    {
                        Color temp = former.get(w, h); 
                        /*
                        **Martix multi: Not using Arrays to store data
                        **！Use number to represent the sequence of caculation !
                        **|1 2 3|   |1 4 7|
                        **|4 5 6| X |2 5 8|
                        **|7 8 9|   |3 6 9|
                        */
                        Color_martix[0] += (double)temp.getRed() * Convolution_Martix[h - j + 1][w - i + 1];
                        Color_martix[1]+= (double)temp.getGreen()* Convolution_Martix[h - j + 1][w - i + 1];
                        Color_martix[2] += (double)temp.getBlue()* Convolution_Martix[h - j + 1][w - i + 1];
                    }
                }
                for(int k = 0; k < 3; k++)
                {
                    Color_martix[k] /= times; // If the filter needs average, a Constant will be given
                                              // To save the space, I don't give a overwrite function
                                              // Instead, I give number:1 to filter which doesn't need it
                    if(Color_martix[k] > 255.0) 	// If the color outreach the scale, it will be changed
                        Color_martix[k] = 255.0;	// to 255 or 0	
                    else if(Color_martix[k] < 0.0)
                        Color_martix[k] = 0.0;
                }
                Color col = new Color((int)Color_martix[0],(int)Color_martix[1],(int)Color_martix[2]);
                Board.set(i, j, col);
            }
        }
        for(int i = 0; i < W; i++)
        {
            Board.set(0, i, former.get(0, i));          // The pixel on the top
            Board.set(H - 1, i, former.get(H - 1, i));  // The pixel at the bottom
        }
        for(int i = 0; i < H; i++)
        {
            Board.set(i, 0, former.get(i, 0));          // The pixel on the left
            Board.set(i, W - 1, former.get(i, W - 1));  // The pixel on the right
        }
        Board.show();
    }
    void Linear_filter()
    {
        double[][] Convolution_Martix = {{1,1,1},{1,1,1},{1,1,1}};      // Convolution_Martix
        filter(Convolution_Martix,9);
    }
    void Blur_filter()
    {
        double[][] Convolution_Martix = {{1,1,1},{1,5,1},{1,1,1}};      // Convolution_Martix
        filter(Convolution_Martix,13);

    }
    void Emboss_filter()
    {
        //double[][] Convolution_Martix = {{-1,0,1},{-1,1,1},{-1,0,1}}; // Convolution_Martix
        //double[][] Convolution_Martix = {{1,0,-1},{2,0,-2},{1,0,-1}};   // Convolution_Martix
        double[][] Convolution_Martix = {{-1,-1,0},{-1,1,1},{0,1,1}}; // Convolution_Martix
        filter(Convolution_Martix,1);
    }
    void Sharpen_filter()
    {
        double[][] Convolution_Martix = {{0,-1,0},{-1,5,-1},{0,-1,0}};  // Convolution_Martix
        filter(Convolution_Martix,1);
    }
    void Oil_painting_filter(int r)
    {
        Picture Board = new Picture(W,H);
        for(int i = 0; i < W; i++)
        {
            for(int j = 0; j < H; j++)
            {
                Color[] colors = new Color[(2 * r + 1) * (2 * r + 1)];// Create a array to store legal data
                int cnt = 0;
                for(int a = i - r; a < i + r; a++)// Traversal the whole range
                {                                 // To make it easy to understand, I choose a square area
                    for(int b = j - r; b < j + r; b++)
                    {
                        if((a < 0) || (b < 0) || (a > W - 1) || (b >= H - 1)) // If the location is out of 
                            continue;										  // the picture
                        else if(Math.abs(a - i) + Math.abs(b - j)>r) // If the Mahatton distance is beyond R
                            continue;
                        else
                        {
                            Color temp = former.get(a, b);
                            colors[cnt++] = temp;
                        }
                    }
                }
                Color col = find_Most(colors,cnt); // In leagl area, we will find out the color
                Board.set(i, j, col);
            }
        }
        Board.show();
    }
    Color find_Most(Color[] colors,int size)     // In this function, we will seperately choose the color
    {                                            // So, a situation that a color which hasn't appear in
        int newRed = 0,cntRed = 0;               // the Nearby Martix will be the final color may happen
        int newGreen = 0, cntGreen = 0;          // However, this picture may be more like to former one
        int newBlue = 0, cntBlue = 0;
        int[] Red_martix = new int[size];
        int[] Green_martix = new int[size];
        int[] Blue_martix = new int[size];

        for(int i = 0; i < size; i++) // First, we create three Arrays to store colors
        {
            Red_martix[i] = colors[i].getRed();
            Green_martix[i] = colors[i].getGreen();
            Blue_martix[i] = colors[i].getBlue();
        }
        Arrays.sort(Red_martix);    // For each Array, it need to be sorted in order to find the color
        Arrays.sort(Green_martix);  // In this example, I seperately choose color in three dimension
        Arrays.sort(Blue_martix);

        int tempRed = Red_martix[0];    // Same steps to find out the color which apppears
        int tempcnt = 1;                // for the most times
        for(int i = 1; i < size; i++)
        {
            if(tempRed == Red_martix[i])
                tempcnt++;
            else
            {
                if(cntRed < tempcnt)
                {
                    cntRed = tempcnt;
                    newRed = tempRed;
                    tempcnt = 1;
                }
                else
                    tempcnt = 1;
            }
        }

        int tempGreen = Green_martix[0];
        for(int i = 1; i < size; i++)
        {
            if(tempGreen == Green_martix[i])
                tempcnt++;
            else
            {
                if(cntGreen < tempcnt)
                {
                    cntGreen = tempcnt;
                    newGreen = tempGreen;
                    tempcnt = 1;
                }
                else
                    tempcnt = 1;
            }
        }

        int tempBlue = Blue_martix[0];
        for(int i = 1; i < size; i++)
        {
            if(tempBlue == Blue_martix[i])
                tempcnt++;
            else
            {
                if(cntBlue < tempcnt)
                {
                    cntBlue = tempcnt;
                    newBlue = tempBlue;
                    tempcnt = 1;
                }
                else
                    tempcnt = 1;
            }
        }
        Color col = new Color(newRed,newGreen,newBlue);
        return col;
    } 
    Color find_Most_RGB(Color[] colors,int size) //    In this function, we will find the color all in 
    {                                            // the color martix. So the color may not be so close
        int[] RGB_Martix = new int[size];        // to the orignal color, but the situation that a color
        for(int i = 0; i < size; i++)            // which hasn't appear before may appear in the new 
        {   									 // picture will not happen
            RGB_Martix[i] = colors[i].getRGB();
        }
        Arrays.sort(RGB_Martix);
        int tempRGB = RGB_Martix[0];
        int cntRGB = 0;
        int tempcnt = 1;
        int newRGB = 0;                         //    Somtimes, If the 'R' given is too small.It
        for(int i = 1; i < size; i++)           // may happen that in all the martix included there
        {                                       // is not color appears twice.
            if(tempRGB == RGB_Martix[i])        //    However, in such situation, this function
                tempcnt++;                      // should intialize the "newRGB" with the central
            else                                // piexel, which means another two variable need to
            {                                   // be given.    
                if(cntRGB < tempcnt)            //    So, in order to make it more readable.We give
                {                               // a larger 'R' and intialize it with white color,
                    cntRGB = tempcnt;           // not only it may not very outstanding.
                    newRGB = tempRGB;
                    tempcnt = 1;
                }
                else
                    tempcnt = 1;
            }
        }

        Color col = new Color(newRGB);
        return col;
    }
    public static void main(String[] args)
    {
        //Lab_03_b_07 Paimon = new Lab_03_b_07("genshin.jpg");
        Lab_03_b_07 Paimon = new Lab_03_b_07(args[0]);
        int w = 10;
        Paimon.Linear_filter();
        Paimon.Blur_filter();
        Paimon.Emboss_filter();
        Paimon.Sharpen_filter();
        Paimon.Oil_painting_filter(w);
    }
}
~~~

![genshin](C:\Java_for_vscode\genshin.jpg) ![linear](C:\Java_for_vscode\linear.jpg) ![blur](C:\Java_for_vscode\blur.jpg) 

​				     **Original											   Linear Filter									 	  Blur Filter**

![emboss](C:\Java_for_vscode\emboss1.jpg)  ![emboss2](C:\Java_for_vscode\emboss2.jpg) ![emboss3](C:\Java_for_vscode\emboss3.jpg) 

​			**Emboss Filter One						        Emboss Filter Two							Emboss Filter Three**

 ![sharpen](C:\Java_for_vscode\sharpen.jpg)  ![OilPainting](C:\Java_for_vscode\OilPainting.jpg) ![OilPaintingRGB](C:\Java_for_vscode\OilPaintingRGB.jpg) 

​		           **Sharpen Filter		        Oil Painting Filter(Three Color Mode)   Oil Painting Filter(RGB Mode)**	



#### 8.提升一张图片的亮度 (Brightness).

~~~java
import java.awt.Color;
public class Lab_03_b_08 {

    public static void main(String[] args)
    {
        //Picture paimon = new Picture("genshin.jpg");
        Picture paimon = new Picture(args[0]);
        int W = paimon.width();
        int H = paimon.height();
        for(int i = 0; i < W; i++)
        {
            for(int j = 0; j < H; j++)
            {
                Color col = paimon.get(i, j);
                col = col.brighter();
                paimon.set(i, j, col);
            }
        }
        paimon.show();
        paimon.save("bright.jpg");
    }
}
~~~

![genshin](C:\Java_for_vscode\genshin.jpg)       ![bright](C:\Java_for_vscode\bright.jpg) 



#### 9.选择一款手机App 以及其中的一个内置滤镜, 编写一个java程序来实现这个滤镜.

~~~java
import java.awt.Color;
public class Lab_03_b_09 {
    static double noise()
    {
        return Math.random() * 0.5 + 0.5;
    }
    static double colorBlend(double random, double col, double precol)
    {
        return (random * col + (1.0 - random) * precol);
    }
    
    public static void main(String[] args)
    {
        Picture paimon = new Picture("genshin.jpg");
        int W = paimon.width();
        int H = paimon.height();
        Picture Board = new Picture(W,H);
        for(int i = 0; i < W; i++)
        {
            for(int j = 0; j < H; j++)
            {
                int[] preColor = new int[3];
                preColor[0] = paimon.get(i, j).getRed();
                preColor[1] = paimon.get(i, j).getGreen();
                preColor[2] = paimon.get(i, j).getBlue();
                
                double[] newColor = new double[3];
                newColor[0] = colorBlend(noise(), (double)preColor[0] * 0.393 + (double)preColor[1] * 0.769 + (double)preColor[2] * 0.189, (double)preColor[0]); 
                newColor[1] = colorBlend(noise(), (double)preColor[0] * 0.349 + (double)preColor[1] * 0.686 + (double)preColor[2] * 0.168, (double)preColor[1]); 
                newColor[2] = colorBlend(noise(), (double)preColor[0] * 0.272 + (double)preColor[1] * 0.534 + (double)preColor[2] * 0.131, (double)preColor[2]); 
                
                for(int k = 0; k < 3; k++)
                {
                    if(newColor[k] > 255)
                        newColor[k] = 255;
                    else if(newColor[k] < 0)
                        newColor[k] = 0;
                }
                Color col = new Color((int)newColor[0],(int)newColor[1],(int)newColor[2]);
                Board.set(i, j, col);
            }
        }
        Board.show();
        Board.save("old.jpg");
    }
}
~~~

![genshin](C:\Java_for_vscode\genshin.jpg)     ![old](C:\Java_for_vscode\old.jpg)

​																			   	**Sepia Tone Effect**

