package Lab07;
import java.util.*;
import Fileio.FileIO;

// All the words should be English

public class Lab07_a_03 {
    public static void main(String[] args) {
        String[] sentences = FileIO.getAllLinesFromFile("IHaveADream.txt");
        Map<String,Integer> wordbank = new LinkedHashMap<String,Integer>();
        // Use LinkedHashSet to remove the repeated words  
        int sentence_size = sentences.length;
        for(int i = 0; i < sentence_size; i++){
            // Using recrusion to fulfill the ArrayList
            // Dealing with each line in a recrusion
            String pre_sentence = sentences[i].toLowerCase();
            // Get one line from the file, and change them to lowwer words
            pre_sentence += '\n';
            // Use a special signal to present the end of the file
            int loc = -1;                       //
            // to get the location in the word 
            String buffer = "";                 //  Initialize the info we need
            // to Store the words we have read
            boolean inbuffer = false;           //
            // to judge whether are any Characters in the buffer
            while(pre_sentence.charAt(++loc) != '\n'){
                // Read all the sentence, use char '\n' to alert the end
                char temp = pre_sentence.charAt(loc);
                // Create a variable to store the present Character
                if((('a' <= temp) && (temp <= 'z')) || (('A' <= temp) && (temp <= 'Z')) || temp == '\''){
                    // If we read a Character
                    buffer += temp;
                    inbuffer = true;
                    // Now, there are some words in the buffer
                }
                else if(inbuffer){
                    // Now, if we meet a non-char Character
                    if(wordbank.containsKey(buffer)){
                        // If the word has been appeared in the wordbank
                        wordbank.put(buffer, wordbank.get(buffer)+1);
                    }
                    else{
                        wordbank.put(buffer,1);
                        // Give the word in the buffer to the wordbank
                    }
                    inbuffer = false;
                    // Now, there are no words in the buffer
                    buffer = "";
                }
            }
            if(inbuffer){
                // When we read the '\n', there may still load a Word in the buffer
                if(wordbank.containsKey(buffer)){
                    // If the word has been appeared in the wordbank
                    wordbank.put(buffer, wordbank.get(buffer)+1);
                }
                else{
                    wordbank.put(buffer,1);
                    // Give the word in the buffer to the wordbank
                }
                inbuffer = false;
                // Now, there are no words in the buffer
                buffer = "";
            }
        }

        Set<String> keys = wordbank.keySet();
        Iterator<String> it = keys.iterator();
        // Use iterator to read the Set
        while (it.hasNext()) {
            String key = (String) it.next();
            // Get the key
            int value = wordbank.get(key);
            // Get the corrsponding value
            System.out.println(key + "=" + value);
        }

    }    
}
