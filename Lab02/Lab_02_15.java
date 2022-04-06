package Lab02;
public class Lab_02_15 {
    public static void main(String[] args)
    {
        double[] table = new double[10];
        for(int i = 0;i < 10;i++)
        {
            double temp = Math.random();
            int zs = (int)(temp * 10);
            table[i] = zs / 10.0;
        }
        int size = table.length;

        System.out.println("--------------------------"); // Follow codes are used to test the right of Statisti.java
        System.out.print("The table is:\n");
        for(int i = 0;i < 10; i++)
        {
            System.out.print(table[i]);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println("--------------------------");

        /*System.out.println("--------------------------");
        System.out.print("The max is: ");
        System.out.println(Statistic.max(table));
        System.out.println("--------------------------");
        System.out.print("The min is: ");
        System.out.println(Statistic.min(table));
        System.out.println("--------------------------");
        System.out.print("The mean is: ");
        System.out.println(Statistic.mean(table));
        System.out.println("--------------------------");
        System.out.print("The variance is: ");
        System.out.println(Statistic.variance(table));
        System.out.println("--------------------------");
        System.out.print("The third biggest number is: ");
        System.out.println(Statistic.select(table,3));
        System.out.println("--------------------------");
        int[] temp = Statistic.histogram(table);
        for(int i = 0;i < 10;i++)
        {
            System.out.print(table[i]);
            System.out.print(" appears for ");
            System.out.print(temp[i]);
            System.out.println(" times.");
        }
        */

        System.out.print("The max is: ");
        System.out.println(Statistic.max(table));
        System.out.println("--------------------------");

        System.out.print("The mean is: ");
        System.out.println(Statistic.mean(table));
        System.out.println("--------------------------");

        System.out.print("The variance is: ");
        System.out.println(Statistic.variance(table));
        System.out.println("--------------------------");

        System.out.print("The middle number is: ");
        if(table.length % 2 == 1) // If the size of table is odd, we can use the middle number 
        {                         // Since that it is a Integer.
            int loc = table.length / 2;
            System.out.println(Statistic.select(table,loc));
        }
        else
        {
            int left = (table.length / 2) - 1; // If the size of table is odd, we need to calculate the 
            int right = table.length / 2;      // sum of the left and right and divide with two. 
            double ans = (Statistic.select(table,left)+Statistic.select(table,right)) / 2;
            System.out.println(ans);
        }
        System.out.println("--------------------------");

        System.out.print("The number appears most is: "); 
        int[] freq = Statistic.histogram(table); 

        double[] frans = new double[size]; 

        for(int i = 0; i < size;i++)
            frans[i] = 0;

        int loc = 0;

        int Maxfreq = 0;
        for(int i = 0; i < size;i++)
        {
            if(freq[i] > Maxfreq) // We need to find out the frequency that is the largest.
                Maxfreq = freq[i];
        }

        boolean appear = false;
        for(int i = 0; i < size;i++)
        {
            if(freq[i] == Maxfreq)
            {
                for(int j = 0; j < loc; j++) // Whether it has appered in the arrays
                    if(table[i] == frans[j])
                        appear = true;
                
                if(!appear)
                    frans[loc++] = table[i];
                
                appear = false;
            }
        }

        for(int i = 0; i < loc ; i++)
        {
            System.out.print(frans[i]);
            System.out.print(' ');
        }
        System.out.println();


        System.out.println("--------------------------");
        double mean = Statistic.mean(table);

        double temp = 1.0;
        loc = 0;
        for(int i = 0; i < size; i++)
        {
            if(temp > Math.abs(table[i] - mean)) // We need to calculate of all the elements
            {                                    // to find the number closest to the mean number
                temp = Math.abs(table[i] - mean);
                loc = i;
            }
        }
        System.out.print("The number that is closest to mean number is: ");
        System.out.println(table[loc]);


        System.out.println("--------------------------");
        double variance_once = Statistic.variance(table);
        double variance_twice = Statistic.variance(table) * 2;
        double variance_third = Statistic.variance(table) * 3;

        double[] varans = new double[size];
        for(int i = 0; i < size; i++)
        {
            varans[i] = 0;
        }
        loc = 0;
        appear = false;
        

        for(int i = 0; i < size; i++)
        {
            if(variance_once > Math.abs(table[i] - mean))
            {
                for(int j = 0; j < loc; j++)
                    if(varans[j] == table[i])
                        appear = true;

                if(!appear)
                    varans[loc++] = table[i];
                
                appear = false;
            }
        }
        System.out.print("The number far from the mean less than once variance is: ");
        for(int i = 0; i < loc; i++)
        {
            System.out.print(varans[i]);
            System.out.print(' ');
        }
        System.out.println();



        System.out.println("--------------------------");
        for(int i = 0; i < size; i++)
        {
            if(variance_twice > Math.abs(table[i] - mean)) // We inherit the arrays because
            {                                              // If a number minus mean number is
                for(int j = 0; j < loc; j++)               // less than once variance, it must
                    if(varans[j] == table[i])              // be less than twice times variance  
                        appear = true;                     // and third times variance 

                if(!appear)
                    varans[loc++] = table[i];
                
                appear = false;
            }
        }
        System.out.print("The number far from the mean less than twice variance is: ");
        for(int i = 0; i < loc; i++)
        {
            System.out.print(varans[i]);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println("--------------------------");




        for(int i = 0; i < size; i++)
        {
            if(variance_third > Math.abs(table[i] - mean))
            {
                for(int j = 0; j < loc; j++)
                    if(varans[j] == table[i])
                        appear = true;

                if(!appear)
                    varans[loc++] = table[i];
                
                appear = false;
            }
        }
        System.out.print("The number far from the mean less than third variance is: ");
        for(int i = 0; i < loc; i++)
        {
            System.out.print(varans[i]);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println("--------------------------");


    }
}
