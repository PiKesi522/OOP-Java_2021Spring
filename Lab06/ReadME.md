# Project 6 接口

## Project 6.a 接口与抽象类基础

#### 1.定义并实现以下商品相关的类结构.

- ##### Item 抽象类.

  | 数据成员                        | 说明     |
  | :------------------------------ | :------- |
  | name (String, protected, final) | 商品名称 |
  | cost (double, protected, final) | 价格     |

  | 方法          | 说明           |
  | :------------ | :------------- |
  | getCost()     | 返回价格       |
  | getName()     | 返回名称       |
  | costPerUnit() | 单价, 抽象方法 |
  | toString()    | 转化为 String  |

  ~~~java
  public abstract class Item{
      protected final String name;    // 商品名称
      protected final double cost;    // 价格
  
      protected Item(String s, double d){     // 构造函数才能创建final
          this.name = s;
          this.cost = d;
      }
  
      public double getCost(){
          return this.cost;
      }
      public String getName(){
          return this.name;
      }
      public String toString(){
          return (this.name + " : " + this.cost);
      }
      public abstract double costPerUnit();  // Abstract method
  }
  ~~~

  

- ##### Produce 类 (生鲜商品). 为 Item 的子类.

  | 数据成员                   | 说明                          |
  | :------------------------- | :---------------------------- |
  | pounds (double, private)   | 商品重量                      |
  | category (String, private) | 种类 (例如: Vegetable, fruit) |

  | 方法          | 说明          |
  | :------------ | :------------ |
  | costPerUnit() | 单价          |
  | getPounds()   | 返回重量(Kg)  |
  | setPounds()   | 设置重量      |
  | getCategory() | 返回种类      |
  | setCategory() | 设置种类      |
  | toString()    | 转化为 String |

  ~~~java
  class Produce extends Item {
      private double pounds;      // 商品重量
      private String category;    // 商品种类
      public Produce(double pounds, String category, double cost, String name){
          super(name, cost);
          this.category = category;
          this.pounds = pounds;
      }
      
      public double costPerUnit(){
          return(this.pounds / this.cost);
      }
      public double getPounds(){
          return this.pounds;
      }
      public void setPounds(double d){
          this.pounds = d;
      }
      public String getCategory(){
          return this.category;
      }
      public void setCategory(String s){
          this.category = s;
      }
      @Override
      public String toString(){
          return(this.name + "(" + this.category + ") : " + this.cost + " in " + this.pounds + "KG");
      }
  }
  ~~~

  

- ##### Beverage 类 (饮品). 为 Item 的子类

  | 数据成员                           | 说明        |
  | :--------------------------------- | :---------- |
  | volume (double, private)           | 商品体积(L) |
  | containerDeposit (double, private) | 回收费用    |

  | 方法                  | 说明                         |
  | :-------------------- | :--------------------------- |
  | costPerUnit()         | 单价                         |
  | getCost()             | 重写返回价格函数, 需回收费用 |
  | getVolume()           | 返回体积                     |
  | setVolume()           | 设置体积                     |
  | getContainerDeposit() | 返回回收费用                 |
  | setContainerDeposit() | 设置回收费用                 |
  | toString()            | 转化为 String                |

  ~~~java
  class Beverage extends Item{
      private double volume;
      private double containerDeposit;
      
      public Beverage(double volume, double containerDeposit, double cost, String name){
          super(name, cost);
          this.containerDeposit = containerDeposit;
          this.volume = volume;
      }
      public double costPerUnit(){
          return(this.getCost() / this.volume);
      }
  
      @Override
      public double getCost(){
          return(this.cost - this.containerDeposit);
      }
      public double getVolume(){
          return(this.volume);
      }
      public void setVolume(double d){
          this.volume = d;
      }
      public double getContainerDeposit(){
          return this.containerDeposit;
      }
      public void setContainerDeposit(double d){
          this.containerDeposit = d;
      }
      @Override
      public String toString(){
          return(this.name + " : " + this.getCost() + " in " + this.volume + "L");
      }
  }
  ~~~

  

- ##### Package 类(包装在盒子中的商品). 为 Item 的子类

  | 数据成员                 | 说明         |
  | :----------------------- | :----------- |
  | length (double, private) | 包装盒的长度 |
  | width (double, private)  | 包装的宽度   |
  | height (double, private) | 包装的高度   |

  | 方法          | 说明          |
  | :------------ | :------------ |
  | costPerUnit() | 单价          |
  | getSize()     | 返回体积      |
  | toString()    | 转化为 String |

  ~~~java
  class Package extends Item{
      private final double length;
      private final double width;
      private final double height;
  
      public Package(double length, double width, double height, double pounds, String name){
          super(name, pounds);
          this.length = length;
          this.width = width;
          this.height = height;
      }
      @Override
      public double costPerUnit(){
          return(this.cost / this.getSize());
      }
      public double getSize(){
          return(this.height * this.length * this.width);
      }
      @Override
      public String toString(){   
          return(this.name + " : " + this.cost + " in " + this.getSize() + "L");
      }
  }
  ~~~

  

- ##### ShoppingCart 类 (购物车).

  | 数据成员              | 说明                 |
  | :-------------------- | :------------------- |
  | cart (array, private) | 放置 Item 的数组     |
  | maxSize (int)         | cart数组长度         |
  | currentSize (int)     | 购物车当前有多少商品 |

  | 方法            | 说明                                    |
  | :-------------- | :-------------------------------------- |
  | addItem()       | 添加商品                                |
  | display()       | 输出商品信息                            |
  | totalCost()     | 输出购物车内商品总价                    |
  | numberInCart(s) | 给定商品名称s, 查看购物车中有多少该商品 |

  ~~~java
  public class ShoppingCart {
      public Item[] cart;
      private final int maxSize;
      public int currentSize;
      
      public ShoppingCart(int maxSize){
          this.maxSize = maxSize;
          this.currentSize = 0;
          this.cart = new Item[maxSize];
      }
  
      public void additem(Item it){
          /*
          此处会使用多态的方法，不会直接创建抽象的Item类，但是其
          Produce类，Beverage类， Package类都可以被使用进去
          所以只能使用这三者的父类的方法和数据
          */
          if(currentSize == maxSize){
              System.out.println("Too Many Goods!");
          }
          else{
              cart[currentSize] = it;
              currentSize += 1;
          }
      }
      public void display(){
          for(int i = 0; i < currentSize; i++){
              System.out.println(cart[i].toString());
          }
      }
      public double totalCost(){
          double sum = 0;
          for(int i = 0; i < currentSize; i++){
              sum += cart[i].getCost();
          }
          return sum;
      }
      public int numberInCart(String s){
          int cnt = 0;
          for(int i = 0; i < currentSize; i++){
              if(s.equals(cart[i].getName()))
                  cnt++;
          }
          return cnt;
      }
  
      public static void main(String[] args) {
          ShoppingCart BMW = new ShoppingCart(10);
          Produce p = new Produce(12.3,"Sports",88.8,"TennisBall");
          Beverage b = new Beverage(0.5,0.1,3.5,"Cola");
          Package pa = new Package(10,10,5,120,"Water");
  
          BMW.additem(p);
          BMW.additem(b);
          BMW.additem(b);
          BMW.additem(pa);
  
          BMW.display();
          System.out.println("All cost : " + BMW.totalCost());
          System.out.println(BMW.numberInCart("Cola"));
  
      }
  }
  ~~~

~~~java
TennisBall(Sports) : 88.8 in 12.3KG
Cola : 3.4 in 0.5L
Cola : 3.4 in 0.5L
Water : 120.0 in 500.0L
All cost : 215.60000000000002
2
~~~



#### 	2.农场中有许多动物, 动物表示为 Animal 接口, 它包含方法

| 方法              | 说明                                                     |
| :---------------- | :------------------------------------------------------- |
| String getType()  | 返回动物的类型                                           |
| String getSound() | 返回动物的叫声(比如“moo” (牛), “cheep”(鸡), “oink” (猪)) |

~~~java
interface Animal {
    String getType();
    String getSound();
}
~~~

- ##### 编写Cow, Hen, Pig 类, 实现 Animal 接口.

~~~java
class Pig implements Animal{
    public String getType(){
        return "Pig";
    }
    public String getSound(){
        return "oink~";
    }
}

class Cow implements Animal{
    public String getType(){
        return "Cow";
    }
    public String getSound(){
        return "moo~";
    }
}

class Hen implements Animal{
    public String getType(){
        return "Hen";
    }
    public String getSound(){
        return "cheep~";
    }
}
~~~

- ##### 定义 Farm 类, 包含以下方法

  | 数据成员                 | 说明             |
  | :----------------------- | :--------------- |
  | animals (array, private) | 包含农场中的动物 |

  | 方法          | 说明                     |
  | :------------ | :----------------------- |
  | animalSound() | 打印农场中所有动物的叫声 |

~~~java
public class Farm{
    private Animal animals[];
    private int size;

    public Farm(int size){
        this.size = size;
        this.animals = new Animal[size];
        for(int i = 0; i < 10; i++){
            int d = (int)(Math.random() * 3);
            switch (d)	// Use Random number to create animals
            {
                case 0:
                    Cow c = new Cow();
                    animals[i] = c;
                    mp[mpcnt++] = c;
                    break;
                case 1:
                    Hen h = new Hen();
                    animals[i] = h;
                    ep[epcnt++] = h;
                    break;
                case 2:
                    Pig p = new Pig();
                    animals[i] = p;
                    break;
            }
        }
    }

    public void animalSound(){
        for(int i = 0; i < this.size; i++){
            System.out.println(animals[i].getType() +": "+ animals[i].getSound());
        }
    }
}
~~~

- ##### 另外有接口 MilkProvider, EggProvider, 分别包含方法 `getMilk()`, `getEgg()`. 修改 Cow, Hen 类的定义, 在实现 Animal 类基础上, 分别实现 MilkProvider 接口 和 EggProvider 接口.

~~~java
interface MilkProvider{
    void getMilk();
}

interface EggProvider{
    void getEgg();
}

class Cow implements Animal,MilkProvider{
    public String getType(){
        return "Cow";
    }
    public String getSound(){
        return "moo~";
    }
    public void getMilk(){
    }
}

class Hen implements Animal,EggProvider{
    public String getType(){
        return "Hen";
    }
    public String getSound(){
        return "cheep~";
    }
    public void getEgg(){
    }
}
~~~

- ##### 定义 Farmer 类, 包含方法 `fetchMilk(MilkProvider a), fetchEgg(EggProvider a)`.

~~~java
class Farmer{
    public void fetchMilk(MilkProvider a){
        System.out.println("Milk + 1");
    }
    public void fetchEgg(EggProvider a){
        System.out.println("Egg + 1");
    }
}
~~~

- ##### 在 Farm 类中添加数据成员 `Farmer farmer`, `MilkProvider []mp`, `EggProvider []ep`. 数组 `mp, ep` 分别包含 animal 数组中的 MilkProvider 和 EggProvider.

~~~java
public class Farm{
    private Animal[] animals;
    private int size;
    private Farmer farmer;
    private MilkProvider[] mp;
    private int mpcnt = 0;
    private EggProvider[] ep;
    private int epcnt = 0;

    public Farm(int size){
        this.size = size;
        this.animals = new Animal[size];
        for(int i = 0; i < 10; i++){
            int d = (int)(Math.random() * 3);
            switch (d)
            {
                case 0:
                    Cow c = new Cow();
                    animals[i] = c;
                    mp[mpcnt++] = c;
                    break;
                case 1:
                    Hen h = new Hen();
                    animals[i] = h;
                    ep[epcnt++] = h;
                    break;
                case 2:
                    Pig p = new Pig();
                    animals[i] = p;
                    break;
            }
        }
    }

    public void animalSound(){
        for(int i = 0; i < this.size; i++){
            System.out.println(animals[i].getType() +": "+ animals[i].getSound());
        }
    }
}
~~~

- ##### 在 Farm 类中添加新方法 `produce()`, 对`mp, ep`数组中的对象, 调用 Farmer 对象的 `fetchMilk, fetchEgg` 方法.

~~~java
public class Farm{
    private Animal[] animals;
    private int size;
    private Farmer farmer;
    private MilkProvider[] mp;
    private int mpcnt = 0;
    private EggProvider[] ep;
    private int epcnt = 0;

    public Farm(int size){
        this.size = size;
        this.animals = new Animal[size];
        for(int i = 0; i < 10; i++){
            int d = (int)(Math.random() * 3);
            switch (d)
            {
                case 0:
                    Cow c = new Cow();
                    animals[i] = c;
                    mp[mpcnt++] = c;
                    break;
                case 1:
                    Hen h = new Hen();
                    animals[i] = h;
                    ep[epcnt++] = h;
                    break;
                case 2:
                    Pig p = new Pig();
                    animals[i] = p;
                    break;
            }
        }
    }

    public void animalSound(){
        for(int i = 0; i < this.size; i++){
            System.out.println(animals[i].getType() +": "+ animals[i].getSound());
        }
    }
    public void produce(){
        this.farmer = new Farmer();
        for(int i = 0; i < mpcnt; i++){
            farmer.fetchMilk(mp[i]);
        }
        for(int i = 0; i < epcnt; i++){
            farmer.fetchEgg(ep[i]);
        }
    }
}
~~~



## Project 6.b 数值计算

#### 	1.定义接口 Function, 包含 eval 方法, 返回函数在自变量 x 处的取值.

```java
double eval(double x)
```

~~~java
interface Function{
    double eval(double x); 
}

~~~

#### 	2.定义 接口 DifferentiableFunction, 扩展 Function 接口表示可微函数, 包含 diff 方法, 返回导函数在变量 x 处的取值.

```java
double diff(double x)
```

~~~java
interface DifferentiableFunction extends Function{
    double diff(double x);
}
~~~

####    3.通过实现 DifferentiableFunction 接口, 实现以下类

- ##### Linear 类. 代表函数 kx+b, 其中 k,b为参数

~~~java
class Linear implements DifferentiableFunction{     
    private final double k;
    private final double b;
    // F(x) = kx + b

    public Linear(double k, double b){
        this.k = k;
        this.b = b;
    }
    @Override
    public double eval(double x){
        return (this.k * x + this.b);
    }
    @Override
    public double diff(double x){
        return this.k;
    }
    public String Tostring(){
        return (this.k + "x + " + this.b);
    }
}
~~~

- ##### Quadartic 类. 代表函数 ax2+bx+c, 其中, a,b,c 为参数

~~~java
class Quadartic implements DifferentiableFunction{  
    private final double a;
    private final double b;
    private final double c;
    // F(x) = ax^2 + bx + c

    public Quadartic(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double eval(double x){
        return (this.a * x * x + this.b * x + this.c);
    }
    @Override
    public double diff(double x){
        return (this.a * x + this.b);
    }
    public String Tostring(){
        return (this.a + "x^2 + " + this.b + "x + " + this.c);
    }
}
~~~

- ##### Sin 类. 代表函数 sin(ωx+φ), 其中 ω,φ 为参数

~~~java
class Sin implements DifferentiableFunction{
    private final double w;
    private final double p;
    // F(x) = sin(wx + p);

    public Sin(double w, double p){
        this.w = w;
        this.p = Math.toRadians(p); // 30' --> 6 / pi;
    }
    @Override
    public double eval(double x){   // Data in degrees, not Radians
        x = Math.toRadians(x); 
        return Math.sin(this.w * x + this.p);
    }
    @Override
    public double diff(double x){   // Data in degrees, not Radians
        x = Math.toRadians(x);
        return (this.w * Math.cos(this.w * x + this.p));
    }
    public String Tostring(){
        return ("sin(" + this.w + "x + " + this.p + ")");
    }
}
~~~

- ##### NormalPDF 类. 代表函数 exp{−(x−μ)^2 / 2σ^2}, 其中 μ,σ为参数.

~~~java
class NormalPDF implements DifferentiableFunction{
    private final double u;
    private final double o;
    // F(x) = exp{-(x-u)^2 / 2o^2};
    public NormalPDF(double u, double o){
        this.u = u;
        this.o = o;
    }
    @Override
    public double eval(double x){
        return Math.exp(-0.5 * Math.pow((x - u) / o, 2));
    }
    @Override
    public double diff(double x){
        return (this.eval(x) * (-x + u) / Math.pow(o, 2));
    }
    public String Tostring(){
        return ("exp{-(x - " + this.u + ")^2 / 2*(" + this.o + "^2)}");
    }
}
~~~



####    4.定义类 NewtonRoot , 包含方法 `findRoot`. 使用牛顿法寻找函数 f 的根 (见 Project 2 第6题). 并对以上四个函数类进行测试.

```java
double findRoot(DifferentiableFunction f)
```

~~~java
class NewtonRoot{
    public static double findRoot(DifferentiableFunction f){
        double mistake = 1e-15;
        double x = 1.0;
        while(Math.abs(f.eval(x)) > mistake){
            // 切线 y = kx + b; k 为 导数
            double kk = f.diff(x);
            double bb = f.eval(x) - kk * x;
            // 当y为0的时候，求此时的x值为下一次迭代的x值
            x = - bb / kk;
        }
        return x;
    }
}
// 测试代码见下
~~~

#### 	5.定义类 NewtonCatos, 包含方法 `Trapozoidal` 使用梯形公式计算 f 在 [a,b][a,b] 上的积分. 同时包含方法 `Simpson`, 使用 Simpson 公式计算定积分. 对以上四个函数类进行测试.

```java
double Trapozoidal(Function f, double a, double b)
double Simpson(Function f, double a, double b)
```

~~~java
class NewtonCatos{
    public static double Trapozoidal(Function f, double a, double b){
        double fa = f.eval(a);
        double fb = f.eval(b);
        return ((b - a) * (fa + fb) / 2);
    }
    public static double Simpson(Function f, double a, double b){
        double fa = f.eval(a);
        double fb = f.eval(b);
        double fm = f.eval((a + b) / 2);
        return ((b - a) * (fa + 4 * fm + fb) / 6);
    }
}
// 测试代码见下
~~~



~~~java
public class Functions{
    public static void main(String[] args) {
        Linear l = new Linear(2, 3);
        Quadartic q = new Quadartic(2, 15, -8);
        Sin s = new Sin(1, 0);
        NormalPDF n = new NormalPDF(0, 1);

        System.out.println("The Root of " + l.Tostring() + " is : x = " + NewtonRoot.findRoot(l));
        System.out.println("One Root of " + q.Tostring() + " is : x = " + NewtonRoot.findRoot(q));
        System.out.println("One Root of " + s.Tostring() + " is : x = " + NewtonRoot.findRoot(s));
        System.out.println("The Root of " + n.Tostring() + " is : x = " + NewtonRoot.findRoot(n));

        System.out.println("---------------------------------------------------");

        System.out.println("The Integrat of " + l.Tostring() + " in Method: Trapozodia is : " + NewtonCatos.Trapozoidal(l, 3, 5));                                                    
        System.out.println("The Integrat of " + q.Tostring() + " in Method: Trapozodia is : "  + NewtonCatos.Trapozoidal(q, 3, 5));                                                    
        System.out.println("The Integrat of " + s.Tostring() + " in Method: Trapozodia is : " + NewtonCatos.Trapozoidal(s, 3, 5));                                                    
        System.out.println("The Integrat of " + n.Tostring() + " in Method: Trapozodia is : " + NewtonCatos.Trapozoidal(n, 3, 5));           

        System.out.println("---------------------------------------------------"); 
        
        System.out.println("The Integrat of " + l.Tostring() + " in Method:Simpson is : "  + NewtonCatos.Simpson(l, 3, 5));                                                    
        System.out.println("The Integrat of " + q.Tostring() + " in Method:Simpson is : "  + NewtonCatos.Simpson(q, 3, 5));                                                    
        System.out.println("The Integrat of " + s.Tostring() + " in Method:Simpson is : "  + NewtonCatos.Simpson(s, 3, 5));                                                    
        System.out.println("The Integrat of " + n.Tostring() + " in Method:Simpson is : "  + NewtonCatos.Simpson(n, 3, 5));         
    }
}

~~~



## Project 6.c 序列与迭代器

#### 1.定义 SequenceItem 类, 包含一个字符串作为数据, 并提供以下方法

| 方法      | 说明               |
| :-------- | :----------------- |
| getData() | 返回所包含的字符串 |
| setData() | 设置所包含的字符串 |

~~~java
class SequenceItem{
    private String Data;
    public SequenceItem(String s){
        this.Data = s;
    }
    public String getData(){
        return this.Data;
    }
    public void setData(String s){
        this.Data = s;
    }
}
~~~

#### 2.定义 Sequence 接口, 包括以下方法

| 方法              | 说明                                                         |
| :---------------- | :----------------------------------------------------------- |
| add(item)         | 将 SequenceItem 类型的对象 item 加入序列                     |
| get(i)            | 将第 i 个SequenceItem 对象返回                               |
| remove(item)      | 删除 item 对象                                               |
| contains(item)    | 返回是否包含 item                                            |
| size()            | 返回序列长度                                                 |
| isEmpty()         | 返回序列是否为空                                             |
| iterator()        | 返回一个迭代器对象, 用于顺序访问序列元素 (见 SeqIterator 接口描述) |
| reverseIterator() | 返回一个迭代器对象, 用于倒序访问序列元素                     |
| biIterator()      | 返回一个迭代器对象, 可以双向访问序列元素 (见 SeqBiIterator 接口描述) |
| toArray()         | 返回序列的数组表示                                           |
| equals(seq)       | 返回该序列是否与 seq 序列相同                                |
| toString()        | 转化为 String                                                |

~~~java
interface Sequence{
    boolean contains(SequenceItem item);
    boolean equals(Sequence seq);
    boolean isEmpty();
    char[] toArray();
    int size();
    void add(SequenceItem item);
    void remove(SequenceItem item);
    SeqIterator Iterator();
    SeqIterator reverseIterator();
    SeqIterator biIterator();
    SequenceItem get(int i);
    String Tostring();
}
~~~

#### 3.定义 SeqIterator 接口, 包括以下方法

| 方法      | 说明                                 |
| :-------- | :----------------------------------- |
| hasNext() | 返回是否能取下一个 SequenceItem 对象 |
| next()    | 取下一个 SequenceItem 对象           |
| remove()  | 删除前一个被返回的 SequenceItem 对象 |

~~~java
interface SeqIterator{
    boolean hasNext();
    void remove();
    SequenceItem next();
}
~~~

#### 4.定义 SeqBiIterator 接口, 扩展 SeqIterator 接口, 包含以下方法

| 方法          | 说明                                 |
| :------------ | :----------------------------------- |
| hasPrevious() | 返回是否能取前一个 SequenceItem 对象 |
| previous()    | 取前一个 SequenceItem 对象           |

~~~java
interface SeqBiIterator extends SeqIterator{
    boolean hasPrevious();
    SequenceItem previous();
}
~~~

#### 5.编写类 ArraySequence 用可变长度数组实现 Sequence 接口, 使用内部类实现 iterator(), reverseIterator(), biIterator(). 

~~~java
class ArraySequence implements Sequence{
    private int size = 10;
    private int used = 0;   // Store how much space has been used to modify the size of the Array
    private SequenceItem[] Items = new SequenceItem[size];
    public ArraySequence(){
        this.size = 10;
        this.used = 0;
        this.Items = new SequenceItem[10];
    }
    protected void resize(){  // Use this function In every function which changes the used space
        if(used + 1 == size){   // If the container is about to be fully used
            this.size *= 2;     // We double the space the Array need.
            SequenceItem[] temp = new SequenceItem[size];
            for(int i = 0; i < used; i++){
                temp[i] = this.Items[i];
            }
            this.Items = temp;
        }
        else if(used * 4 < size){   // If the container has too much free space
            this.size /= 2;     // We shorten the Array
            SequenceItem[] temp = new SequenceItem[size];
            for(int i = 0; i < used; i++){
                temp[i] = this.Items[i];
            }
            this.Items = temp;
        }
    }
    public boolean contains(SequenceItem item){
        SequenceItem target = item;
        for(int i = 0; i < used; i++){
            if(this.Items[i].equals(target))
                return true;
        }
        return false;
    };
    public boolean equals(Sequence seq){
        if(this.used != seq.size())
            return false;
        for(int i = 0; i < used; i++){
            if(!this.Items[i].equals(seq.get(i+1))) {
                return false;
            }
        }
        return true;
    };
    public boolean isEmpty(){
        return (this.used == 0);
    };
    public int size(){
        return this.used;
    };
    public void add(SequenceItem item){
        this.Items[used++] = item;
        resize();
    };
    public void remove(SequenceItem item){
        SequenceItem target = item;
        if(!this.contains(target)){
            System.out.println("Not Found Such Item");
            return;
        }
        int loc = 0;
        while(!this.get(++loc).equals(target)); // Where is the element?
        for(int j = loc - 1; j < used - 1; j++){  // Postpone the latter to the former
            this.Items[j] = this.Items[j + 1];
        }
        this.Items[used--] = null;  // Clear the last Item
        resize();
    };
    public SeqIterator Iterator(){
        // We need to create another inner class to implement the interface
        return new PIterator();
    };
    protected class PIterator implements SeqIterator{
        protected int loc = 0;  // It will be used in Subclass
        public PIterator(){  // Constructor function
            this.loc = 0;
        }
        // Identify a Innerclass to implement the iterator
        @Override
        public boolean hasNext() {
            return (this.loc < ArraySequence.this.used);
        };
        @Override
        public SequenceItem next() {
            return ArraySequence.this.Items[loc++];
        }
        @Override
        public void remove() {
            SequenceItem target = ArraySequence.this.Items[--loc];
            ArraySequence.this.remove(target);
            // In method:Remove, method:resize will be used at the same time
        };
    }
    public SeqIterator reverseIterator(){
        return new PreverseIterator();
    };
    protected class PreverseIterator implements SeqIterator{
        protected int loc;
        public PreverseIterator(){  // Constructor function
            this.loc = ArraySequence.this.used - 1;
        }
        // Identify a Innerclass to implement the iterator
        @Override
        public boolean hasNext() {
            return (this.loc > 0);
        };
        @Override
        public SequenceItem next() {
            return ArraySequence.this.Items[loc--];
        }
        @Override
        public void remove() {
            SequenceItem target = ArraySequence.this.Items[++loc];
            ArraySequence.this.remove(target);
        };
    }
    public SeqIterator biIterator(){
        return new PSeqIterator();
    };
    protected class PSeqIterator extends PIterator implements SeqBiIterator{
        // We inherit used Inner class:PIterator
        @Override
        public SequenceItem previous(){
            if(this.hasPrevious())
                return ArraySequence.this.Items[--this.loc];
            else{
                System.out.println("No more element!");
                return null;
            }
        }
        @Override
        public boolean hasPrevious() {
            return this.loc > 0;
        }
    };
    public SequenceItem get(int i){
        return this.Items[i-1];
    };
    public SequenceItem[] ToArray(){
        SequenceItem[] seq = new SequenceItem[used];
        for(int i = 0; i < used; i++){
            seq[i] = this.Items[i];
        }
        return seq;
    };
    public String Tostring(){
        SequenceItem[] seq = this.ToArray();
        String s = new String();
        for(int i = 0; i < used; i++){
            s += i + ": " + seq[i].getData() + ".\n";
        }
        return s;
    };
}
~~~



#### 6.编写类 IteratorTest, 包含静态方法 `display(Iterator i)` 顺序输出 Sequence 的内容.

~~~java
class IteratorTest{
    public static void display(ArraySequence.PIterator i){
        while(i.hasNext()){
            SequenceItem target = i.next();
            System.out.println(target.getData());
        }
    }
}
~~~



#### 7.(选做) 编写类 LinkedSequence 用[链表](https://www.cs.cmu.edu/~adamchik/15-121/lectures/Linked Lists/linked lists.html)实现 Sequence 接口, 使用内部类实现 iterator(), reverseIterator(), biIterator().

~~~java
class LinkedSequence implements Sequence{
    private Node Head;      // Use Node to store data
    private int used;       // Store how many data have been used
    public LinkedSequence(){
        this.Head = new Node(null);
    }
    public boolean contains(SequenceItem item){
        SequenceItem target = item;
        Node temp = this.Head;
        while(temp.next != null){
            if(temp.data.equals(item)) {
                return true;
            }
            else{
                temp = temp.next;     // Goto the next node
            }
        }
        if(temp.data != item)   // Thea last data won't be
            return false;       // test in the recrusion above
        else
            return true;
    };
    public boolean equals(Sequence seq){
        if(this.used != seq.size()){
            return false;
        }
        Node temp = this.Head;
        int i = 0;
        while(temp.next != null){
            if(temp.data != seq.get(++i)){
                return false;
            }
            temp = temp.next;
        }
        return (temp.data == seq.get(used));
    };
    public boolean isEmpty(){
        return (this.used == 0);
    };
    public int size(){
        return this.used;
    };
    public void add(SequenceItem item){
        Node temp = this.Head;
        while(temp.next != null){
            temp = temp.next;
        }
        Node newone = new Node(item);
        temp.next = newone;
    };
    public void remove(SequenceItem item){
        if(!this.contains(item)){
            System.out.println("Not Found Such Item");
            return;
        }
        Node pre = this.Head;
        Node temp = this.Head;
        while(temp.next != null){
            if(temp.data == item){
                if(pre == this.Head){
                    this.Head = this.Head.next;
                }   // The first element is the target, just skip it
                else{
                    pre.next = temp.next;
                }   // Repoint the previous node to the next one
                return;
            }
            pre = temp;
            temp = temp.next;
        }
        // Since there must be a item in the Node
        // Then the last one must be the target
        // when the recrusion end
        pre.next = null;
        return;
    };
    public SeqIterator Iterator(){
        // We need to create another inner class to implement the interface
        return new LinkedSequence.PIterator();
    };
    protected class PIterator implements SeqIterator{
        protected Node pointer;  // It will be used in Subclass
        public PIterator(){  // Constructor function
            this.pointer = LinkedSequence.this.Head;
        }
        // Identify a Innerclass to implement the iterator
        @Override
        public boolean hasNext() {
            return (this.pointer.next != null);
        };
        @Override
        public SequenceItem next() {
            pointer = pointer.next;
            return this.pointer.data;
        }
        @Override
        public void remove() {
            if(this.hasNext()){
                // When the present location is cleared
                // the pointer will move to the next location
                Node temp = this.pointer.next;
                LinkedSequence.this.remove(pointer.data);
                this.pointer = temp;
            }
            else{
                // Otherwise, it will be moved to the previous location
                Node temp = LinkedSequence.this.Head;
                while(temp.next != this.pointer){
                    temp = temp.next;
                }
                temp.next = null;
                this.pointer = temp;
            }
        };
    }
    public SeqIterator reverseIterator(){
        return new LinkedSequence.PreverseIterator();
    };
    protected class PreverseIterator implements SeqIterator{
        protected Node Repointer = LinkedSequence.this.Head;
        public PreverseIterator(){  // Constructor function
            while(this.Repointer.next != null){
                this.Repointer = this.Repointer.next;
            }
        }
        // Identify a Innerclass to implement the iterator
        @Override
        public boolean hasNext() {
            return (this.Repointer != LinkedSequence.this.Head);
        };
        @Override
        public SequenceItem next() {
            Node temp = LinkedSequence.this.Head;
            while(temp.next != this.Repointer){
                temp = temp.next;
            }
            this.Repointer = temp;
            return this.Repointer.data;
        }
        @Override
        public void remove() {
            if(this.hasNext()){
                // When the present location is cleared
                // the pointer will move to the previous node
                Node temp = LinkedSequence.this.Head;
                while (temp.next != this.Repointer){
                    temp = temp.next;
                }
                temp.next = Repointer.next;
                this.Repointer = temp;
            }
            else{
                // Otherwise, it will be moved to the next location
                this.Repointer = LinkedSequence.this.Head.next;
            }
        };
    }
    public SeqIterator biIterator(){
        return new LinkedSequence.PSeqIterator();
    };
    protected class PSeqIterator extends LinkedSequence.PreverseIterator implements SeqBiIterator{
        // We inherit used Inner class:PIterator
        @Override
        public SequenceItem previous(){
            return this.next();
        }
        @Override
        public boolean hasPrevious() {
            return (this.Repointer != LinkedSequence.this.Head);
        }
    };
    public SequenceItem get(int i){
        i -= 1;
        Node temp = this.Head;
        while(i-- != 0){        // To search a certain data in LinkedArray
            temp = temp.next;   // We can only search them in order
        }
        return temp.data;
    };
    public SequenceItem[] ToArray(){
        SequenceItem[] seq = new SequenceItem[used];
        Node temp = this.Head;
        for(int i = 0; i < used; i++){
            seq[i] = temp.data;
            temp = temp.next;
        }
        return seq;
    };
    public String Tostring(){
        SequenceItem[] seq = this.ToArray();
        String s = new String();
        for(int i = 0; i < used; i++){
            s += i + ": " + seq[i].getData() + ".\n";
        }
        return s;
    };
}
~~~



#### 8.(选做) 请比较 LinkedSequence 与 ArraySequence 在插入, 删除, 访问(例如, 顺序访问, 倒序访问, 随机访问) 等操作的性能.

~~~java
// In Array
public void add(SequenceItem item){
    this.Items[used++] = item;
    resize();
};

// In Linked
public void add(SequenceItem item){
    Node temp = this.Head;
    while(temp.next != null){
        temp = temp.next;
    }
    Node newone = new Node(item);
    temp.next = newone;
};
~~~

```txt
	（本题为尾部插入）在插入的方法中，数组方法直接扩展长度，然后放入元素，并非在数组内部增加；然而在链表中需要从头到尾遍历一次，找到最后一个位置，然后再进行添加。
	故在此种情形下，数组的方法比链表要快
```



~~~java
// In Array
public void remove(SequenceItem item){
    if(!this.contains(item)){
        System.out.println("Not Found Such Item");
        return;
    }
    int loc = 0;
    while(!this.get(++loc).equals(item)); // Where is the element?
    for(int j = loc - 1; j < used - 1; j++){  // Postpone the latter to the former
        this.Items[j] = this.Items[j + 1];
    }
    this.Items[used--] = null;  // Clear the last Item
    resize();
};

// In Linked
public void remove(SequenceItem item){
    if(!this.contains(item)){
        System.out.println("Not Found Such Item");
        return;
    }
    Node pre = this.Head;
    Node temp = this.Head;
    while(temp.next != null){
        if(temp.data == item){
            if(pre == this.Head){
                this.Head = this.Head.next;
            }   // The first element is the target, just skip it
            else{
                pre.next = temp.next;
            }   // Repoint the previous node to the next one
            return;
        }
        pre = temp;
        temp = temp.next;
    }
    // Since there must be a item in the Node
    // Then the last one must be the target
    // when the recrusion end
    pre.next = null;
    return;
};
~~~

~~~txt
	(本题为随机删除)在删除的时候，由于数组需要把删除后的位置由后一位来填补，故会有多次移动数据；而在链表中，只需要移动一次数据。
	故在此种情况下中，链表更快
~~~



~~~txt
	由于链表只能记录后一个位置的地址，所以需要访问的时候需要依次经过访问读取，数组直接下标访问，故数组更快。
~~~



