public class Lab_03_a_01{
    public static void main(String args[])
    {
        boolean net = args[0].startsWith("http:");
        if(net)
        {
            System.out.println("Yes!");
        }
        else
        {
            System.out.println("No!");
        }
    }
}