public class Lab_03_a_10 {
    public static void main(String[] args)
    {
        String[] lines = FileIO.getAllLinesFromFile("Lab_03_a_10.txt");
        int line_size = lines.length;      // In case that the file has more than one line
        int[] appears = new int[26];       // The int[] is used to calculate the times that char appears
        for(int i = 0; i < 26; i++)         
        {   
            appears[i] = 0;                // The original num is all 0
        }
        for(int i = 0; i < line_size; i++)
        {
            int single_len = lines[i].length();
            for(int j = 0; j < single_len; j++)
            {
                char ch = lines[i].charAt(j);
                if('a' <= ch && ch <= 'z') // Only find the char from a-z
                    appears[ch - 'a']++;   // check all the char, if appears, correspondent location++
            }
        }
        
        char now = 'a';
        for(int i = 0; i < 26; i++)
        {
            if(appears[i] != 0)            // If a char hasn't appeared ever, it won't be printed
            {
                System.out.println("\'" + now + "\' appears for " + appears[i] + " times.");
            }
            now++;
        }
    }
}