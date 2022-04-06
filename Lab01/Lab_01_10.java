public class Lab_01_10 
{
    public static void main(String args[])
    {
        System.out.println("—————————ANSWER—————————");
        int x = 10000;
        for(int i = 2; i < 100;i++)
        {
            while(x % i == 0)
            {
                if(x == 1)
                    break;

                System.out.print(i);
                System.out.print(',');
                x = x / i;
            }
            if(x == 1)
                break;
            else if(x < i)
            {
                System.out.println(x);
                break;
            }
        }
    }
}