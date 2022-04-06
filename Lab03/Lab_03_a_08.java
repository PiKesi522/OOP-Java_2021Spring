public class Lab_03_a_08 {
    public static void main(String[] args)
    {
        String[] names = FileIO.getAllLinesFromFile("Lab_03_a_08.txt");
        int len = names.length;		 // Calculate the lines in the files
        int[] sizes = new int[len];  // Use a array to store the length of each name
        
        for(int i = 0;i < len;i++)
        {
            sizes[i] = names[i].length();  // Use a array to store the length of each name
        }

        int max = 0;					// Find the word which have the most characters
        for(int i = 0; i < len; i++)
        {
            if(sizes[i] > max)
                max = sizes[i];			// Find the word which have the most characters
        }

        for(int i = 0; i < len; i++)
        {
            if(sizes[i] == max)			// 
                System.out.println(names[i]);
        }
    }
}