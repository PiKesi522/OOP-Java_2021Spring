package Lab02;
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
