package Lab02;
import java.util.Arrays;

public class Statistic {
    public static double max(double a[])
    {
        double temp = 0;
        int size = a.length;
        for(int i = 0; i < size;i++)
        {
            if(temp < a[i])
                temp = a[i];
        }
        return temp;
    }

    public static double min(double a[])
    {
        double temp = 1;
        int size = a.length;
        for(int i = 0; i < size;i++)
        {
            if(temp > a[i])
                temp = a[i];
        }
        return temp;
    }

    public static double mean(double a[]) 
    {
        double temp = 0;
        int size = a.length;
        for(int i = 0;i < size;i++)
        {
            temp += a[i];
        }

        return (temp / size);
    }

    public static double variance(double a[])
    {
        double mean = mean(a);
        int size = a.length;
        double temp = 0;
        for(int i = 0;i < size; i++)
        {
            temp += Math.pow((a[i] - mean), 2);
        }

        temp = Math.sqrt(temp / size);

        return temp;
    }

    public static double select(double a[], int k)
    {
        int size = a.length;
        double[] table = new double[size];
        for(int i = 0 ; i < size; i++)
        {
            table[i] = a[i];
        }
        Arrays.sort(table);

        return table[size-k]; 
    }

    public static int[] histogram(double a[])
    {
        int size = a.length; // a = [2,1,3,1,4,1,2];
        //double[] table = a.clone();
        //Arrays.sort(table);
        //int[] b = new int[size];
        double[][] table = new double[size][2];
        for(int i = 0;i < size; i++)
        {
            table[i][0] = a[i];
            table[i][1] = 0; // [2][0],[1][0],[3][0],[1][0],[4][0],[1][0],[2][0];
        }

        int[] b = new int[size];
        for(int i = 0;i < size;i++)
        {
            double temp = a[i];
            for(int j = 0; j < size;j++)
            {
                if(table[j][0] == temp)
                {
                    table[i][1]++;
                }
            }
            b[i] = (int)table[i][1];
        }
        
        return b;
    }
}
