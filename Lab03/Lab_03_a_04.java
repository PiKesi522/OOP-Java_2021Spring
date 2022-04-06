public class Lab_03_a_04 {
    public static void main(String[] args)
    {
        int apart = args[0].lastIndexOf("/");
        String path = args[0].substring(0,apart+1);
        String suffix = args[0].substring(apart+1);
        System.out.println("The path is: " + path);
        System.out.println("The suffix is: " + suffix);
    }
}
