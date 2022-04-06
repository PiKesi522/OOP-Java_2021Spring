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