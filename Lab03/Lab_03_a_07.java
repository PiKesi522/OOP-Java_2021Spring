public class Lab_03_a_07 {
    public static void main(String[] args)
    {
        int len = args[0].length();
        char temp;
        int ans = 0;
        for(int i = 0; i < len;i++)
        {
            int tnum = 0;	// Create a temporary int to store the correspondent number in DEC
            temp = args[0].charAt(i); 
            if('A' <= temp && temp <= 'F')
            {
                tnum = temp - 'A' + 10;
            }
            else if('a' <= temp && temp <= 'f')
            {
                tnum = temp - 'a' + 10;
            }
            else if('0' <= temp && temp <= '9')
            {
                tnum = temp - '0';
            }
            ans *= 16;
            ans += tnum;
        }
        System.out.println("The DEC is: " + ans);
    }
}