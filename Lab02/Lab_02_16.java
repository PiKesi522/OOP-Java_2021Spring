package Lab02;
import Fileio.FileIO;
public class Lab_02_16 {
    public static void main(String[] args)
    {
        String[] s = {"《老人与海》这本小说是根据真人真事写的。第一次世界大战结束后，海明威移居古巴，认识了老渔民格雷戈里奥·富恩特斯。\n",
                    "1930年，海明威乘的船在暴风雨中沉没，富恩特斯搭救了海明威。从此，海明威与富恩特斯结下了深厚的友谊，并经常一起出海捕鱼。\n",
                    "The novel The Old Man and the Sea is based on a real story. After the end of World War I, Hemingway moved to Cuba, where he met an old fisherman, Gregorio Fuentes.\n",
                    "In 1930, Hemingway was rescued by Fuentes when his boat sank in a storm. From then on, Hemingway and Fuentes formed a deep friendship, and often went fishing together.\n"};

        for(int i = 0; i < 4; i++)
        {
            FileIO.writeStringToFile(s[i], "test.txt");
        }

        System.out.println("--------------------------");
        char ch = FileIO.getCharFromFile(5 - 1, "test.txt");
        System.out.println(ch);

        System.out.println("--------------------------");
        String line = FileIO.getLineFromFile(3 - 1, "test.txt");
        System.out.println(line);

        System.out.println("--------------------------");
        String[] text = FileIO.getAllLinesFromFile("test.txt");
        for(int i = 0;i < 4; i++)
        {
            System.out.println(text[i]);
        }
    }
}
