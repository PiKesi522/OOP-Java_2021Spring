public class Lab_03_a_12 {

    String password;             // password itself, it will be used to test the availability
    boolean available = false;
    public Lab_03_a_12(String s) // Constructor Function
    {
        password = s;
    }

    boolean enough_length()         // Check whether the password have enough characters
    {
        int len = password.length();
        if(len >= 8)             
            return true;
        else
            return false;
    }

    boolean at_least_one_number()   // Check whether the password have numbers
    {
        for(char ch = '0'; ch <= '9'; ch++)
        {
            if(password.indexOf(ch) != -1)
            {                   
                return true;
            }
        }
        return false;
    }

    boolean at_least_one_UPPER()    // Check whether the password have UPPER words
    {
        for(char ch = 'A'; ch <= 'Z'; ch++)
        {
            if(password.indexOf(ch) != -1)
            {
                return true;
            }
        }
        return false;
    }

    boolean at_least_one_Non_Word()// Check whether the password have Non-word characters
    {
        int size = password.length();
        for(int i = 0; i < size; i++)
        {
            char ch = password.charAt(i);   // Traversal the char in the password
            if('a' <= ch && ch <= 'z')      // If the char is from 'a' to 'z', pass
                continue;
            else if('A' <= ch && ch <= 'Z') // If the char is from 'A' to 'Z', pass
                continue;
            return true;
        }
        return false;
    }

    void acceptable()       // Intergrate all the premises to check the availability of the password
    {
        if(enough_length())
        {
            if(at_least_one_Non_Word())
            {
                if(at_least_one_UPPER())
                {
                    if(at_least_one_number())
                    {
                        System.out.println("This password is available");
                        available = true;
                    }
                    else
                    {   
                        System.out.println("WRONG: " + password + " is unavailable:");
                        System.out.println("  At least one number is required");
                    }
                }
                else
                {
                    System.out.println("WRONG: " + password + " is unavailable:");
                    System.out.println("  At least one UPPER word is required");
                }
            }
            else
            {
                System.out.println("WRONG: " + password + " is unavailable:");
                System.out.println("  At least one non-word char is required");
            }
        }
        else
        {
            System.out.println("WRONG: " + password + " is unavailable:");
            System.out.println("  Password should be at least with 8 numbers");
        }
    }

    public static void main(String[] args){
        for(int i = 0; i < args.length; i++)
        {
            Lab_03_a_12 key = new Lab_03_a_12(args[i]);
            key.acceptable();
        }
    }
}
