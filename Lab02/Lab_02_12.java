package Lab02;
public class Lab_02_12 {
    public static void main(String[] args)
    {
        int[] delta = {2,1,4,5,3}; // given that the ONLY Array is {2, 1, 4, 5, 3}
                                   // According to the describe of the context, the answer
                                   // should be {2, 1, 5, 3, 4}.
                                   // Each number in the Array is related to the location
                                   // of the corresponding element of the primer Arrays
                                   // that is, the Answer Array stores the location of the 
                                   // sequence of the dictionary-order, the procedure is 
                                   // shown as follow:
                                   //  {2, 1, 4, 5, 3}
                                   //1.Find the location of element '1' -- 2
                                   //2.Find the location of element '2' -- 1
                                   //3.Find the location of element '3' -- 5
                                   //4.Find the location of element '4' -- 3
                                   //5.Find the location of element '5' -- 4
                                   //                                  -->{2, 1, 5, 4, 3} 
                                   
        for(int i = 1; i <= delta.length; i++) // Find the sequence in order
        {
            for(int j = 0; j < delta.length; j++) // Ergodic all the array to find corresponding loc
            {
                if(delta[j] == i)
                {
                    System.out.print(j + 1);
                }
            }
        }
        System.out.println();
    }
}
