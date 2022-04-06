public class Lab_03_a_03 {
    public static void main(String[] args)
    {
        int find = args[0].lastIndexOf(".");
        String suffix = args[0].substring(find+1);
        System.out.println(suffix);
    }
}
