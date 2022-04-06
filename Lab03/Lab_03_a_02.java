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