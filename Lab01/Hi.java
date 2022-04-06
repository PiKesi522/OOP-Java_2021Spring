public class Hi {
    public static void main(String[] args) {
        System.out.print("Hi, ");
        for(int i=0;i<3;i++)
        {
            System.out.print(args[i]);
            System.out.print(",");
        }
        System.out.println(". How are you?");
    }
}