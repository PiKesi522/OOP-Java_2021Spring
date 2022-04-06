public class Print {
    String[] operaters = new String[100];
    String sstream;
    int len = 0;

    void Insert(String s)
    {
        operaters[len++] = s; 
    }
    void T() // -t Default
    {
        System.out.println(sstream);
    }
    void T(String txt) // -t Overwrite Function
    {
        FileIO.writeStringToFile(sstream, txt);
    }
    void O() // -o Defalut
    {
        sstream = "\0";
        for(char a = '0'; a <= '9' ; a++)
        {
            sstream += a;
        }
    }
    void O(String s) // -o Overwrite Function
    {
        sstream = "";
        if(s == "n")
        {
            for(char a = '0'; a <= '9' ; a++)
            {
                sstream += a;
            }
        }
        else if(s == "a")
        {
            for(char a = 'a'; a <= 'z'; a++)
            {
                sstream += a;
            }
        }
        else
        {
            System.out.println("WRONG: No such operater!");
        }
    }
    void H() // -h
    {
        System.out.println("usage: % java Print [OPTIONS]");
        System.out.println("-t type       if type=n print 0-9, if type=a print a-z. Default: type=n");
        System.out.println("-o out.txt    outputs to out.txt, Default: standard out");
        System.out.println("-h            print this help informantion");
    }
    void moves() // Read the movement stores int the operaters[]
    {
        int i = 0;
        while(i != len)
        {
            if(operaters[i] == "-h")
            {
                H();
            }
            else if(operaters[i] == "-t")
            {
                if(i == (len - 1)) // In case outreach the operaters
                    O(); 
                else if(operaters[i+1] == "n" || operaters[i+1] == "a" )  // Using overwrite function
                    O(operaters[i+1]);
                else               // Default
                    O();
            }
            else if(operaters[i] == "-o")
            {
                if(i == (len - 1)) // In case outreach the operaters
                    T();
                else if(operaters[i+1] == "-t" || operaters[i+1] == "-h" || operaters[i+1] == "-o" ) // Default
                    T();
                else               // Using overwrite function
                    T(operaters[i+1]);
            }

            i++;
        }
    }


    public static void main(String[] args)
    {
        int len = args.length;
        Print p = new Print();
        for(int i = 0; i < len ; i++)
        {
            p.Insert(args[i]);
        }
        p.moves();
    }
}
