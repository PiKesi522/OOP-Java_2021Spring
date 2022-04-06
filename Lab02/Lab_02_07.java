package Lab02;
import java.util.Arrays;

public class Lab_02_07 {
    public static void main(String[] args)
    {
        int[] ls = new int[5];
        for(int i = 0 ;i < 5; i++)
        {
            ls[i] = Integer.parseInt(args[i]);
        }

        Arrays.sort(ls);

        System.out.println(ls[2]);
    }
}
