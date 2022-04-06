package Lab07;

import java.util.*;

class OutOfQueueException extends Exception{};

class FileName{
    public static int number = 1;
    public static String getName(){
        return("File" + FileName.number++);
    }
}
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
                    System.out.println("The Average Waiting time in the printer is: " + 
                        (int)(this.Sum_of_waiting_time / this.Count_of_processed_mission));
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

public class PrinterServiceProgram {
    public static void main(String[] args) {
        // PrintRequest a = new PrintRequest("bilibili", 1, 1);
        // PrintRequest b = new PrintRequest("acfun", 1, 1);
        // PrintRequest c = new PrintRequest("cctv", 1, 1);
        // PrintRequest d = new PrintRequest("darkhole", 1, 1);
        // PrintRequest e = new PrintRequest("eprime", 1, 1);
        // PrintRequest f = new PrintRequest("farmer", 1, 1);
        // PrintRequest g = new PrintRequest("ghost", 1, 1);


        // PrintSimulation printer = new PrintSimulation(0.5, 3, 3, 'a');       
        // PrintDispatcher pd = printer.pd;
        // PrintQueue first = pd.GotoQueue(1);
        // first.in(a);first.in(c);first.in(f);
        // PrintQueue second = pd.GotoQueue(2);
        // second.in(b);second.in(d);
        // PrintQueue third = pd.GotoQueue(3);
        // third.in(e);third.in(g);
        
        // String[] s = pd.ToString();
        // for(int i = 0; i < 3; i++){
        //     System.out.println(s[i]);
        // }
        PrintRequest first_one = new PrintRequest("First_File", 20, 0);
        // Give a first file to simulate
        PrintSimulation A_printer = new PrintSimulation(0.5, 8, 5, 'A');A_printer.pd.GotoQueue(1).in(first_one);
        PrintSimulation B_printer = new PrintSimulation(0.5, 8, 5, 'B');B_printer.pd.GotoQueue(2).in(first_one);
        PrintSimulation C_printer = new PrintSimulation(0.5, 8, 5, 'C');C_printer.pd.GotoQueue(2).in(first_one);
        // A_printer.simulate();
        B_printer.simulate();
        // C_printer.simulate();
    }
}
