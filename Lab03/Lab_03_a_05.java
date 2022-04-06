public class Lab_03_a_05 {
    public static void main(String[] args)
    {
        String s = args[0].replace("\'","\""); // Since ' and " has specific meaning in thr programme  
        System.out.println(s);				   // We should use Escape character
    }
}