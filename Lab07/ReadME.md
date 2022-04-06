# Project 7 容器和I/O

## Project 7.a 容器

#### 1.给定文本文件, 将文本中所有的单词读入一个 List 中.

~~~java
package Lab07;
import java.util.*;
import Fileio.FileIO;

// All the words should be English

public class Lab07_a_01 {
    public static void main(String[] args) {
        String[] sentences = FileIO.getAllLinesFromFile("IHaveADream.txt");
        ArrayList<String> wordbank = new ArrayList<String>();
        int sentence_size = sentences.length;
        for(int i = 0; i < sentence_size; i++){
            // Using recrusion to fulfill the ArrayList
            // Dealing with each line in a recrusion
            String pre_sentence = sentences[i].toLowerCase();
            // Get one line from the file, and change them to lowwer words
            pre_sentence += '\n';
            // Use a special signal to present the end of the file
            int loc = -1;                       //
            // to get the location in the word 
            String buffer = "";                 //  Initialize the info we need
            // to Store the words we have read
            boolean inbuffer = false;           //
            // to judge whether are any Characters in the buffer
            while(pre_sentence.charAt(++loc) != '\n'){
                // Read all the sentence, use char '\n' to alert the end
                char temp = pre_sentence.charAt(loc);
                // Create a variable to store the present Character
                if((('a' <= temp) && (temp <= 'z')) || (('A' <= temp) && (temp <= 'Z')) || temp == '\''){
                    // If we read a Character
                    buffer += temp;
                    inbuffer = true;
                    // Now, there are some words in the buffer
                }
                else if(inbuffer){
                    // Now, if we meet a non-char Character
                    wordbank.add(buffer);
                    // Give the word in the buffer to the wordbank
                    inbuffer = false;
                    // Now, there are no words in the buffer
                    buffer = "";
                }
            }
            if(inbuffer){
            // When we read the '\n', there may still load a Word in the buffer
                wordbank.add(buffer);
                inbuffer = false;
                buffer = null;
            }
        }
        for(String a : wordbank){
            System.out.print(a + ',');
        }
    }    
}

~~~



#### 2.给定文本文件, 统计文本中有多少不同的单词.

~~~java
package Lab07;
import java.util.*;
import Fileio.FileIO;

// All the words should be English

public class Lab07_a_02 {
    public static void main(String[] args) {
        String[] sentences = FileIO.getAllLinesFromFile("IHaveADream.txt");
        Set<String> wordbank = new LinkedHashSet<String>();
        // Use LinkedHashSet to remove the repeated words  
        int sentence_size = sentences.length;
        for(int i = 0; i < sentence_size; i++){
            // Using recrusion to fulfill the ArrayList
            // Dealing with each line in a recrusion
            String pre_sentence = sentences[i].toLowerCase();
            // Get one line from the file, and change them to lowwer words
            pre_sentence += '\n';
            // Use a special signal to present the end of the file
            int loc = -1;                       //
            // to get the location in the word 
            String buffer = "";                 //  Initialize the info we need
            // to Store the words we have read
            boolean inbuffer = false;           //
            // to judge whether are any Characters in the buffer
            while(pre_sentence.charAt(++loc) != '\n'){
                // Read all the sentence, use char '\n' to alert the end
                char temp = pre_sentence.charAt(loc);
                // Create a variable to store the present Character
                if((('a' <= temp) && (temp <= 'z')) || (('A' <= temp) && (temp <= 'Z')) || temp == '\''){
                    // If we read a Character
                    buffer += temp;
                    inbuffer = true;
                    // Now, there are some words in the buffer
                }
                else if(inbuffer){
                    // Now, if we meet a non-char Character
                    wordbank.add(buffer);
                    // Give the word in the buffer to the wordbank
                    inbuffer = false;
                    // Now, there are no words in the buffer
                    buffer = "";
                }
            }
            if(inbuffer){
            // When we read the '\n', there may still load a Word in the buffer
                wordbank.add(buffer);
                inbuffer = false;
                buffer = null;
            }
        }
        for(String a : wordbank){
            System.out.print(a + ',');
        }
    }    
}

~~~



#### 3.给定文本文件, 统计文本中不同单词的出现次数.

~~~java
package Lab07;
import java.util.*;
import Fileio.FileIO;

// All the words should be English

public class Lab07_a_03 {
    public static void main(String[] args) {
        String[] sentences = FileIO.getAllLinesFromFile("IHaveADream.txt");
        Map<String,Integer> wordbank = new LinkedHashMap<String,Integer>();
        // Use LinkedHashSet to remove the repeated words  
        int sentence_size = sentences.length;
        for(int i = 0; i < sentence_size; i++){
            // Using recrusion to fulfill the ArrayList
            // Dealing with each line in a recrusion
            String pre_sentence = sentences[i].toLowerCase();
            // Get one line from the file, and change them to lowwer words
            pre_sentence += '\n';
            // Use a special signal to present the end of the file
            int loc = -1;                       //
            // to get the location in the word 
            String buffer = "";                 //  Initialize the info we need
            // to Store the words we have read
            boolean inbuffer = false;           //
            // to judge whether are any Characters in the buffer
            while(pre_sentence.charAt(++loc) != '\n'){
                // Read all the sentence, use char '\n' to alert the end
                char temp = pre_sentence.charAt(loc);
                // Create a variable to store the present Character
                if((('a' <= temp) && (temp <= 'z')) || (('A' <= temp) && (temp <= 'Z')) || temp == '\''){
                    // If we read a Character
                    buffer += temp;
                    inbuffer = true;
                    // Now, there are some words in the buffer
                }
                else if(inbuffer){
                    // Now, if we meet a non-char Character
                    if(wordbank.containsKey(buffer)){
                        // If the word has been appeared in the wordbank
                        wordbank.put(buffer, wordbank.get(buffer)+1);
                    }
                    else{
                        wordbank.put(buffer,1);
                        // Give the word in the buffer to the wordbank
                    }
                    inbuffer = false;
                    // Now, there are no words in the buffer
                    buffer = "";
                }
            }
            if(inbuffer){
            // When we read the '\n', there may still load a Word in the buffer
                if(wordbank.containsKey(buffer)){
                    // If the word has been appeared in the wordbank
                    wordbank.put(buffer, wordbank.get(buffer)+1);
                }
                else{
                    wordbank.put(buffer,1);
                    // Give the word in the buffer to the wordbank
                }
                inbuffer = false;
                // Now, there are no words in the buffer
                buffer = "";
            }
        }

        Set<String> keys = wordbank.keySet();
        Iterator<String> it = keys.iterator();
        // Use iterator to read the Set
        while (it.hasNext()) {
            String key = (String) it.next();
            // Get the key
            int value = wordbank.get(key);
            // Get the corrsponding value
            System.out.println(key + "=" + value);
        }

    }    
}

~~~



#### 4.定义稀疏矩阵类 SparseMatrix. 

###### 稀疏矩阵与普通矩阵相比, 矩阵中含有大量的0元素. 对于这样的矩阵, 用二维数组存储会造成存储空间的浪费 (特别是矩阵的行数列数很大时). 请选择合适容器来实现稀疏矩阵. 同时实现矩阵的加法和乘法.

~~~java
package Lab07;

import java.util.*;
class DifferException extends Exception{};
// Use a Exception to alert the problem that the two martix
// doesn't have the same size, so that they cannot be calculated
class NounMapException extends Exception{};
// Use a Exception to deal with the problem that the martix is null
class EmptyMapException extends Exception{};
// Use a Exception to deal with the problem that the martix is empty

public class SparseMartix {
    /* Use a triple-table to store the data
      <<x , y>, v
        x: the x-aries location of the non-zero value
        y: the y-aries location of the non-zero value
        Create a tuple to Store the location of the value in the Martix
        v: the value at the location
    */
    private static LinkedHashMap<ArrayList<Integer>,Integer> M_shallowcopy
    // Use a Private function to shallow copy the Martix
    (LinkedHashMap<ArrayList<Integer>,Integer> a){
        LinkedHashMap<ArrayList<Integer>,Integer> ans = new LinkedHashMap<ArrayList<Integer>,Integer>();
        // The answer we need Triple-table
        Set<ArrayList<Integer>> keys = a.keySet();
        Iterator<ArrayList<Integer>> it = keys.iterator();
        // Use iterator to read the Set
        while (it.hasNext()) {
            ArrayList<Integer> key = (ArrayList<Integer>) it.next();
            // Get the key
            int value = a.get(key);
            // Get the corrsponding value
            ans.put(key, value);
        }
        return ans;
    }
    private static LinkedHashMap<ArrayList<Integer>,Integer> M_getMap
    // Use a Private function to change Two-Dimension Array to a LinkedHashMap
    // To judge whether two Martix can calculate,
    //   the first element will be used to store the size of the Martix
    (int[][] data, int height, int length){
        LinkedHashMap<ArrayList<Integer>,Integer> ans = new LinkedHashMap<>();
        ArrayList<Integer> size = new ArrayList<>();
        size.add(height);size.add(length);
        ans.put(size, 0);
        // This data is to store the size of the Martix
        for(int i = 0; i < height; i++){
            for(int j = 0; j < length; j++){
                if(data[i][j] != 0){
                    ArrayList<Integer> loc = new ArrayList<Integer>();
                    loc.add(i);loc.add(j);
                    ans.put(loc, data[i][j]);
                }
            }
        }
        return ans;
    }
    private static LinkedHashMap<ArrayList<Integer>,Integer> M_add
    (LinkedHashMap<ArrayList<Integer>,Integer> a, LinkedHashMap<ArrayList<Integer>,Integer> b){
        // To calculate the sum of two Martix, we just need to add the 
        // value in the same location in the Martix
        // So we just need to add the Element in the Map together
        // If the location has number already, we need to add the corrsponding 
        // value in the location
        ArrayList<Integer> F_a = a.keySet().iterator().next();
        ArrayList<Integer> F_b = b.keySet().iterator().next();
        try{
            if((F_a.get(0) != F_b.get(0)) || (F_a.get(1) != F_b.get(1))){
                // Wheter these two array dosen't have the same size
                throw new DifferException();
            }
        }
        catch(DifferException e){
            System.out.println("These two Martix have different size");
            return null;
        }

        Iterator<ArrayList<Integer>> it = a.keySet().iterator();
        LinkedHashMap<ArrayList<Integer>,Integer> ans = M_shallowcopy(b);
        // A shallow copy of Martix B;
        while (it.hasNext()) {
            // We don't need to handle the size information
            // Since every Map should have a size information,
            // The answer will inherit this information from the B
            ArrayList<Integer> loc = (ArrayList<Integer>) it.next();
            // Get the key
            if(ans.containsKey(loc)){
                ans.put(loc, a.get(loc) + b.get(loc));
            }
            else{
                ans.put(loc, a.get(loc));
            }
        }
        return ans;
    }
    private static LinkedHashMap<ArrayList<Integer>,Integer> M_mult
    (LinkedHashMap<ArrayList<Integer>,Integer> a, LinkedHashMap<ArrayList<Integer>,Integer> b){
        LinkedHashMap<ArrayList<Integer>,Integer> ans = new LinkedHashMap<>();
        Iterator<ArrayList<Integer>> a_it = a.keySet().iterator();
        Iterator<ArrayList<Integer>> b_it = b.keySet().iterator();
        ArrayList<Integer> size_a = a_it.next(), size_b = b_it.next();
        try{
            if(size_a.get(1) != size_b.get(0)){
                throw new DifferException();
            } 
        }
        catch(DifferException e){
            System.out.println("These two Martix have different size");
            return null;
        }
        ArrayList<Integer> size_ans = new ArrayList<>();
        size_ans.add(size_a.get(0));size_ans.add(size_b.get(1));    
        ans.put(size_ans, 0);
        // The size of ans is the height of first Martix with the length of the second Martix

        int ans_len = 0, ans_hei = 0;
        ArrayList<Integer> line = new ArrayList<>();
        // Use to store the location that the value is not zero
        ArrayList<Integer> number = new ArrayList<>();
        // Use to store the value that will be used to calculate the answer
        boolean empty = true;

        while(a_it.hasNext()){
            ArrayList<Integer> temp = a_it.next();
            if(ans_len != temp.get(0)){
                if(empty){
           // This line is empty, the corrsponding number in the ans is also empty
                    ans_len += 1;
                }
                else{
                    for(ans_hei = 0; ans_hei < size_b.get(1); ans_hei++){
                        int temp_ans = 0;
                        Iterator<Integer> t_it = line.iterator();
                        Iterator<Integer> n_it = number.iterator();
                        while(t_it.hasNext()){
                            // Recrusion the Array to calculate 
                            ArrayList<Integer> temp_loc = new ArrayList<>();
                            temp_loc.add(t_it.next()); temp_loc.add(ans_hei);
          // Store the tempprary loction, which is the reverse of orignal location
                            if(b.containsKey(temp_loc)){
                                temp_ans += n_it.next() * b.get(temp_loc);
                            }
                            else{
                                n_it.next();
                            }
                        }

                        if(temp_ans != 0){
                            // The answer is not zero
                            ArrayList<Integer> loc = new ArrayList<>();
                            loc.add(ans_len);loc.add(ans_hei);
                            ans.put(loc, temp_ans);
                        }
                    }

                    line = new ArrayList<>();
                    number = new ArrayList<>();
                    empty = true;
                    // Clear the buffer area
                }
            }
            else{
                empty = false;
                // Something in the buffer
                line.add(temp.get(1));
                number.add(a.get(temp));
                // Record the loction which has the value
            }
        } 
        if(!empty){
            for(ans_hei = 0; ans_hei < size_b.get(1); ans_hei++){
                int temp_ans = 0;
                Iterator<Integer> t_it = line.iterator();
                Iterator<Integer> n_it = number.iterator();
                while(t_it.hasNext()){
                    // Recrusion the Array to calculate 
                    ArrayList<Integer> temp_loc = new ArrayList<>();
                    temp_loc.add(t_it.next()); temp_loc.add(ans_hei);
          // Store the tempprary loction, which is the reverse of orignal location
                    if(b.containsKey(temp_loc)){
                        temp_ans += n_it.next() * b.get(temp_loc);
                    }
                    else{
                        n_it.next();
                    }
                }

                if(temp_ans != 0){
                    // The answer is not zero
                    ArrayList<Integer> loc = new ArrayList<>();
                    loc.add(ans_len);loc.add(ans_hei);
                    ans.put(loc, temp_ans);
                }
            }

            line = new ArrayList<>();
            number = new ArrayList<>();
            empty = true;
            // Clear the buffer area
        }
        
        return ans;
    }
    private static void M_print(LinkedHashMap<ArrayList<Integer>,Integer> m){
        try{
            if(m == null){
                throw new NounMapException();
            }
            else if(m.size() == 0){
                throw new EmptyMapException();
            }
        }
        catch(NounMapException e){
            System.out.println("This martix is null");
            return;
        }
        catch(EmptyMapException e){
            System.out.println("This martix is empty");
            return;
        }
        Iterator<ArrayList<Integer>> it = m.keySet().iterator();
        ArrayList<Integer> size = it.next();
        int length = size.get(1), height = size.get(0);
        int[][] martix = new int[height][length];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < length; j++){
                martix[i][j] = 0;
            }
        }
        ArrayList<Integer> loc = size;
        while(it.hasNext()){
            loc = it.next();
            martix[loc.get(0)][loc.get(1)] = m.get(loc);
        }

        for(int i = 0; i < height; i++){
            for(int j = 0; j < length; j++){
                System.out.print(martix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int height = 6, length = 8;
        int[][] martix_A = {{2,0,0,0,6,0,0,7},
                            {0,0,1,0,0,0,0,0},
                            {0,0,2,0,0,0,3,0},
                            {0,0,0,0,0,8,0,0},
                            {0,0,0,5,0,0,0,0},
                            {0,9,0,0,0,0,0,0}};

        int[][] martix_B = {{0,0,0,0,0,0,0,7},
                            {0,0,1,0,0,5,0,0},
                            {8,0,0,0,0,1,3,0},
                            {0,0,9,0,0,8,0,0},
                            {0,0,0,5,0,0,0,0},
                            {0,9,0,0,0,0,6,0}};

        int[][] martix_C = {{2,0,0,0,6,0,0,7},
                            {0,0,1,0,0,0,0,0},
                            {0,0,2,0,0,0,3,0},
                            {0,0,0,0,0,8,0,0},
                            {0,0,0,5,0,0,0,0}};

        int[][] martix_D = {{0,0,0,0,0,0},
                            {0,0,1,0,0,5},
                            {8,0,0,0,0,1},
                            {0,0,9,0,0,8},
                            {0,0,0,5,0,0},
                            {0,9,0,0,0,0},
                            {7,0,0,0,1,0},
                            {0,0,0,5,6,0}};
        LinkedHashMap<ArrayList<Integer>,Integer> A = M_getMap(martix_A, height, length);
        LinkedHashMap<ArrayList<Integer>,Integer> B = M_getMap(martix_B, height, length);
        LinkedHashMap<ArrayList<Integer>,Integer> C = M_getMap(martix_C, height - 1, length);
        LinkedHashMap<ArrayList<Integer>,Integer> D = M_getMap(martix_D, length, height);


        LinkedHashMap<ArrayList<Integer>,Integer> sumAB = M_add(A,B);
        LinkedHashMap<ArrayList<Integer>,Integer> sumBC = M_add(B,C);
        LinkedHashMap<ArrayList<Integer>,Integer> consAB = M_mult(A,B);
        LinkedHashMap<ArrayList<Integer>,Integer> consAD = M_mult(A,D);

        M_print(sumAB);
        M_print(sumBC);
        M_print(consAB);
        M_print(consAD);
        
    }
}

~~~

~~~txt
These two Martix have different size

2 0 0 0 6 0 0 14
0 0 2 0 0 5 0 0
8 0 2 0 0 1 6 0
0 0 9 0 0 16 0 0
0 0 0 10 0 0 0 0
0 18 0 0 0 0 6 0
This martix is null
This martix is null
0 0 0 65 42 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
~~~



## Project 7.b 打印机服务程序

#### 1.定义 PrintRequest 类表示打印请求. 

###### 需要包含: 文件的名称, 页数以及发出该请求时的时间.

~~~java
class PrintRequest {
    // The Files that Printer need to be processed
    public String FileName;
    public int Pages;
    public int Times;
    public PrintRequest(String FileName, int Pages, int Times){
        this.FileName = FileName;
        this.Pages = Pages;
        this.Times = Times;
    }
}
~~~



#### 2.定义 Printer 类表示打印机. 至少包含以下方法

- `boolean printerIdle()`: 如果打印机空闲返回 true, 否则返回 false
- `boolean printFile(PrintRequest r)`: 如果打印机空闲, 则开始处理打印任务 r, 并返回 true. 否则忽略改请求, 返回 false.
- `PrintRequest processForOneUnit()`, 如果打印机空闲, 或者当前正在打印的文档还剩余超过1页则返回 null. 如果当前文档已完成, 则返回当前的打印任务对象.

~~~java
class Print{
    // The printer
    public boolean occupied = false;
    public PrintRequest processing;
    public boolean printerIdle(){
        return !this.occupied;
    }
    public boolean printFile(PrintRequest r){
        if(this.printerIdle()){
            this.processing = r;
            // Deal with File
            return true;
        }
        else{
            // Ignore the File
            return false;
        }
    }
    public PrintRequest processForOneUnit(){
        if(this.processing.Pages >= 1){
            return null;
        }
        else{
            return this.processing;
        }
    }
}
~~~



#### 3.定义 PrintQueue 类表示一个队列 

###### (参考 Queue 接口. 主要的操作是“进队列”和“出队列”. 同时 队列可能需要记录当前已经被访问了多少次).

~~~java
class PrintQueue{
    // One Queue in the printer
    public int visit_cnt = 0;
    private int size = 0;
    Queue<PrintRequest> buffer;
    public PrintQueue(){
        this.buffer = new LinkedList<>();
    }
    public void in(PrintRequest r){
        this.size += 1;
        this.buffer.offer(r);
    }
    public void out(){
        this.size -= 1;
        this.buffer.poll();
    }
    public PrintRequest read_the_first_Request(){
        return this.buffer.peek();
    }
    public boolean isEmpty(){
        // Whether this Queue is Empty
        return (this.size == 0);
    }
    public String ToString(){
        // This function is used to give the String to PrintDispatcher:ToString
        Iterator<PrintRequest> que_it = this.buffer.iterator();
        String que = new String();
        while(que_it.hasNext()){
            que += " " + que_it.next().FileName;
        }
        return que;
    }
}
~~~



#### 4.定义 PrintDispatcher 类为一个队列. 

###### 它包含 n 个 PrintQueue 对象, 而相应的“进队列”, “出队列”操作则完成上述“动态优先级”策略. 包含 toString() 方法可以显示逐个队列的状态.

~~~java
class PrintDispatcher{
    // All the Queues in the printer
    private int size;
    public Queue<PrintQueue> Dispatchers = new LinkedList<>();
    public PrintDispatcher(int n){
        this.size = n;
        for(int i = 0; i < n; i++){
            this.Dispatchers.add(new PrintQueue());
        }
    }
    public PrintQueue GotoQueue(int n){
        Iterator<PrintQueue> temp = this.Dispatchers.iterator();
        PrintQueue ans = null;
        while(temp.hasNext()){
            if(n == 0){
                return ans;
            }
            n -= 1;
            ans = temp.next();
        }
        try{
            if(n != 0){
                throw new OutOfQueueException();
            }
        }
        catch(OutOfQueueException e){
            System.out.println("There are not so much Queues");
            return null;
        }
        return ans;
    }
    public String[] ToString(){
        Iterator<PrintQueue> dis_it = Dispatchers.iterator();
        String[] dis = new String[size];
        int i = 0;
        while(dis_it.hasNext()){
            dis[i++] = dis_it.next().ToString();
        }
        return dis;
    }
    public boolean isEmpty(){
        // Whether this Dispatcher is empty
        for(int i = 1; i <= size; i++){
            PrintQueue temp = this.GotoQueue(i);
            if(!temp.isEmpty()){
                return false;
            }
        }
        return true;
    }
}
~~~



#### 5.定义 PrintSimulation 类, 模拟打印场景.

- **从命令行接收4个参数, 分别**
  - p: 当前这1秒钟有打印请求到来的概率
  - n: 使用多少个队列
  - k: 当某个队列被访问了 k 次后, 将提升优先级
  - a: 选择哪种初始化优先级的策略 (A, B, C)
- **模拟每一秒系统的行为. 可能出现的情况为**
  - 如果打印机当前正忙, 则打印当前任务的一页.
  - 同时, 以概率 p 产生一个打印请求. 该请求的文档页数为 1 到 100 间的随机数, 发出请求的时间为当前 clock 变量的值. 该打印请求被放入 PrintDispatcher 中 (通过调用enqueue() 方法).
  - 如果打印机空闲, 且有请求正在等待, 则通过 PrintDispatcher 选择一个任务交给 Printer (通过调用 dequeue() 方法). 同时, 通过该任务的发出时间, 计算该任务的总共的等待时间.
- **当以上过程进行了 1000 秒后 (clock = 0, 1, …, 999), 不再产生新的请求. 但过程将继续下去, 直到所有没有完成任务被完成.**
- **输出所有任务中, 最长的等待时间和平均等待时间.**

~~~java
class PrintSimulation{
    public double p;
    // The possiblity of creating a mission
    public int n;
    // How many Queue will be used in the printer
    public int k;
    public char a;
    // The mode of Dynamic Priority
    public PrintDispatcher pd;
    // The PrintDispatcher in the printer
    public Print printer;
    // The printer itself
    private int clock = 0;
    // Used to record the time
    private int rest_Pages = 0;
    // How long will it cost to finish the present mission
    private int Max_waiting_time = 0;
    private int Sum_of_waiting_time = 0;
    private int Count_of_processed_mission = 0;
    private int[][] Average_Waiting_time_Graph;
    public PrintSimulation(double p, int n, int k, char a){
        this.p = p;
        this.n = n;
        this.k = k;
        this.a = a;
        this.pd = new PrintDispatcher(n);
        this.printer = new Print();
        this.Average_Waiting_time_Graph = new int[n][2];
        for(int i = 0; i < this.n; i++){
            this.Average_Waiting_time_Graph[i][0] = 0;
            this.Average_Waiting_time_Graph[i][1] = 0;
        }
    }
    public void statisitic(PrintRequest pr){
        int wait_time = this.clock - pr.Times;
        this.Sum_of_waiting_time += wait_time;
        this.Count_of_processed_mission += 1;

        if(wait_time > this.Max_waiting_time){
            this.Max_waiting_time = wait_time;
        }

        int x_Aries =  (pr.Pages - 1 >= 10*(this.n - 1)) ? this.n : ((pr.Pages - 1) / 10 + 1);
        this.Average_Waiting_time_Graph[x_Aries - 1][0] += wait_time;
        this.Average_Waiting_time_Graph[x_Aries - 1][1] += 1;
    }
    public void simulate(){
        while(true){
            this.clock++;
            System.out.println(this.clock);
            // Time pass by
            if(this.printer.printerIdle()){
                // If the printer is availble
                if(!this.pd.isEmpty()){
                // Choose a mission
                    int destination_number = 1;
                    // The First Queue has the highest priority
                    PrintQueue destination = pd.GotoQueue(destination_number);
                    while(destination.visit_cnt >= k || destination.isEmpty()){
                        // If the present Queue has been visit for a certain times
                        // Or the present Queue has no mission to be printed
                        if(destination.visit_cnt >= k){
                            // Clear the history record
                            destination.visit_cnt = 0;
                        }
                        if(++destination_number == this.n + 1){
                       // If the present Queue is the last Queue in the Dispather
                            destination_number = 1;
                            // Then, it will come back to the fisrt Queue
                        }
                        destination = pd.GotoQueue(destination_number);
                        // Try the next Queue
                    }
                    PrintRequest pr = destination.read_the_first_Request();
                    // Get the File from the target Queue
                    destination.out();
                    destination.visit_cnt++;
                    // The File is no longer in the Queue
                    this.rest_Pages = pr.Pages;
                    this.printer.processing = pr;
                    this.printer.occupied = true;
                    this.statisitic(pr);
                }
                else if(this.pd.isEmpty() && this.clock >= 1000){
                    System.out.println("The Max Waiting time in this printer is: " + this.Max_waiting_time);
                    System.out.println("The Average Waiting time in the printer is: " +  (int)(this.Sum_of_waiting_time / this.Count_of_processed_mission));
                    System.out.println(this.Count_of_processed_mission + " missions have been finished");
                // There are no mission in the Dispather, as well as the Requests
                    for(int i = 0; i < this.n; i++){
                        System.out.println((double)(this.Average_Waiting_time_Graph[i][0] / this.Average_Waiting_time_Graph[i][1]));
                    }
                    return;
                }
            }
            else{
                if(p >= Math.random() && this.clock < 1000){
                    // The possbility of creating a File need to be printed
                    this.rest_Pages = (int)(Math.random() * 100 + 1);
                    // (int)(Math.random() * 100 + 1) : The Pages of a file
                    PrintRequest r = new PrintRequest(FileName.getName(), this.rest_Pages, this.clock);
                    // FileName.getName() : Get a Filename which is in order
                    // this.clock : A static number to record the time
                    int priority = 0;
                    switch (a) {
                        // Choose the processing method
                        case 'A':
                            // There is only one Queue in the Dispathers
                            priority = 1;
                            break;
                        case 'B':
                            // Divide the Queue as the size of 10 pages
                            priority = (r.Pages - 1 >= 10 * (this.n - 1)) ? this.n : ((r.Pages - 1) / 10 + 1);
                            break;
                        case 'C':
                            // Give the priority according to the mod of the pages
                            priority = (r.Pages - 1) % this.n + 1;
                            break;
                        default:
                            break;
                    }
                    this.pd.GotoQueue(priority).in(r);
                }
                this.rest_Pages--;
                // A page is printed
                if(this.rest_Pages == 0){  
                // The present mission is finished
                    this.printer.occupied = false;
                    // The present mission is finished
                }
            }
        }
    }
}
~~~

- 请绘制直方图, 横坐标表示页数 (10, 20, …, 10(n-1), >10(n-1)), 纵坐标表示页数落在对应横坐标范围内的所有请求的平均等待时间.

![image-20210615170218579](C:\Users\86008\AppData\Roaming\Typora\typora-user-images\image-20210615170218579.png)



#### 6.请根据程序运行结果分析不同参数对系统的影响.

以概率p为0.5，队列数n为8，访问上限k为5，动态优先模式a为’B‘作为基准评判

| p = 0.5 ; n = 8; k  = 5;  a='B' | 第一次 | 第二次 | 第三次 | 第四次 | 第五次 | 平均等待时间 |
| :-----------------------------: | ------ | ------ | ------ | ------ | ------ | :----------: |
|              1~10               | 614    | 655    | 630    | 560    | 552    |    602.2     |
|              11~20              | 1213   | 1457   | 1183   | 1246   | 1223   |    1264.4    |
|              21~30              | 2210   | 2585   | 2203   | 2251   | 2325   |    2314.8    |
|              31~40              | 3743   | 4140   | 3994   | 3782   | 3743   |    3880.4    |
|              41~50              | 5871   | 6175   | 5703   | 5290   | 5483   |    5704.4    |
|              51~60              | 8602   | 8880   | 8241   | 7407   | 7938   |    8213.6    |
|              61~70              | 11418  | 12130  | 11090  | 10224  | 11104  |   11193.2    |
|             71~100              | 19624  | 19450  | 19340  | 18293  | 18314  |   19004.2    |

**1.概率变化**

| p = 0.7 ; n = 8; k  = 5;  a='B' | 第一次 | 第二次 | 第三次 | 第四次 | 第五次 | 平均等待时间 |
| :-----------------------------: | ------ | ------ | ------ | ------ | ------ | :----------: |
|              1~10               | 694    | 716    | 777    | 529    | 807    |    704.6     |
|              11~20              | 1730   | 1567   | 1801   | 1464   | 1726   |    1657.6    |
|              21~30              | 3270   | 3117   | 3267   | 2821   | 3144   |    3123.8    |
|              31~40              | 5503   | 5572   | 5335   | 5053   | 5006   |    5293.8    |
|              41~50              | 8113   | 8031   | 7885   | 7759   | 7967   |     7951     |
|              51~60              | 11283  | 11326  | 11817  | 11365  | 11100  |   11378.2    |
|              61~70              | 14840  | 16179  | 15946  | 15292  | 15600  |   15571.4    |
|             71~100              | 25487  | 26913  | 27529  | 26489  | 27146  |   26712.8    |

对比图表：

![](C:\Users\86008\AppData\Roaming\Typora\typora-user-images\image-20210615172256499.png)

~~~txt
当概率提升的时候，会产生的文件数目会更多，所以所有队列的等待时间都会有所提高
~~~



**2.队列数变化**

| p = 0.5 ;n = 5; k = 5; a = 'B' | 第一次 | 第二次 | 第三次 | 第四次 | 第五次 | 平均等待时间 |
| :----------------------------: | ------ | ------ | ------ | ------ | ------ | :----------: |
|              1~10              | 12656  | 13100  | 13784  | 14745  | 12514  |   13359.8    |
|             11~20              | 11237  | 13717  | 13170  | 12327  | 12828  |   12655.8    |
|             21~30              | 13222  | 13382  | 10413  | 12696  | 10645  |   12071.6    |
|             31~40              | 11792  | 11506  | 12996  | 14099  | 10374  |   12153.4    |
|             41~100             | 12343  | 13050  | 12134  | 11974  | 12127  |   12325.6    |

对比图表：

![](C:\Users\86008\AppData\Roaming\Typora\typora-user-images\image-20210615172708902.png)

~~~txt
当页面较多的页数全部放入第5排时候，这些高页数文档不再有内部排序，故相较于更加细致的划分，平均等待时间会更少
~~~



**3.访问限制变化**

| p = 0.5 ;n = 8; k  = 50; a='B' | 第一次 | 第二次 | 第三次 | 第四次 | 第五次 | 平均等待时间 |
| :----------------------------: | ------ | ------ | ------ | ------ | ------ | :----------: |
|              1~10              | 451    | 476    | 475    | 313    | 547    |    452.4     |
|             11~20              | 1063   | 1219   | 1210   | 990    | 1325   |    1161.4    |
|             21~30              | 2041   | 2229   | 2351   | 2132   | 2300   |    2210.6    |
|             31~40              | 3572   | 3671   | 3925   | 3681   | 3945   |    3758.8    |
|             41~50              | 5866   | 5577   | 6110   | 5789   | 6111   |    5890.6    |
|             51~60              | 8519   | 7770   | 8796   | 8224   | 8967   |    8455.2    |
|             61~70              | 11084  | 10737  | 11418  | 10642  | 12515  |   11279.2    |
|             71~100             | 19221  | 18569  | 19735  | 18653  | 19927  |    19221     |

对比图表：

![image-20210615173604780](C:\Users\86008\AppData\Roaming\Typora\typora-user-images\image-20210615173604780.png)

~~~txt
由于本身B模式下越靠前排的序列等候时间越少，故提高前排访问限制使得前排更加容易先完成，故排队时间减少，但并不如其余两种模式明显
~~~



## Project 7.c I/O

#### 1.给定目录名, 找出该目录下的所有文件和子目录, 并递归的列出所有子目录的内容. 

~~~java
package Lab07;
import java.io.*;

public class Lab07_c_01 {
    public static void ReadFile(File path, int depth) {
        String spath = "";
        // The space before the File
        for (int i = 0; i < depth; i++) {
            spath += "\t\t";
        }
        if (depth != 0)
            spath += "|- ";
            // The prefix of the File


        File[] child = path.listFiles();
        // Get all the Catelogs and Files in this Path 
        if (child == null)
        // If this File is not a Catelog
            return;
            // We don't need to deal with it anymore

        for (File file : child) {
            System.out.println(spath + file.getName() + (file.isDirectory() ? ':' : ""));
            // If this File is a Catelog, then it need a character
            if (file.isDirectory()) {
                ReadFile(file, depth + 1);
                // Deal with the Catelog
            }
        }
    }
    public static void main(String[] args) {
        File file = new File(args[0]);
        // File file = new File("C:\\Java_for_vscode\\restaurant");
        ReadFile(file, 0);
    }
}
~~~

~~~txt
people:
                |- A.java
                |- cook.java
                |- waiter.java
Refrigerator.java
tools:
                |- A.java
                |- fork.java
                |- table.java
~~~



#### 2.请**通过编写程序**, 比较带缓冲的流和不带缓冲的流在读写性能上的差距. 

###### 请给出实验程序源码, 并说明实验比较步骤和实验结果.

~~~java
package Lab07;
import java.io.*;
public class Lab07_c_02 {
    public static void NoBuffered() throws IOException{
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try{
            fin = new FileInputStream("IHaveADream.txt");
            fout = new FileOutputStream("NoBuffered.txt");

            int bytee;
            Long start = System.nanoTime();
            // Record the time when it started
            while((bytee = fin.read()) != -1){
                fout.write(bytee);
            }
            Long end = System.nanoTime();
            // Record the time when it finished
            System.out.println("In UnBuffered Stream,it need "+(end-start)+"ms.");

        }finally{
            if(fin != null){
                fin.close();
            }
            if(fout != null){
                fout.close();
            }
        }
    }

    public static void Buffered() throws IOException{
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try{
            bin = new BufferedInputStream(new FileInputStream("IHaveADream.txt"));
            bout = new BufferedOutputStream(new FileOutputStream("Buffered.txt"));

            int bufferr;
            byte[] bytes = new byte[1024];
            // Use a Buffer within 1024 Bytes, which means it can handle 
            // nearly 1024 times data compared to No-Buffered Stream
            Long start = System.nanoTime();
            // Record the time when it started
            while((bufferr = bin.read(bytes)) != -1){
                bout.write(bytes, 0, bufferr);
            }
            Long end = System.nanoTime();
            // Record the time when it finished
            System.out.println("In Buffered Stream, it need " +(end-start)+"ms.");

        }finally{
            if(bin != null){
                bin.close();
            }
            if(bout != null){
                bout.close();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        NoBuffered();
        Buffered();
    }
}

~~~

~~~txt
实验步骤：
	1.分别建立两种不同的读取模式：字节流和缓冲流
	2.对于两种模式，读取同一文件，并在同一目录下输出
	3.记录开始时间和结束时间并计算
实验结果：
    In No-Buffered Stream, it need 77435200ms.
    In Buffered Stream, it need 180900ms.
~~~

