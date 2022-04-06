public class Lab_03_b_01 {
    
    public static void main(String[] args)
    {
        Picture pic = new Picture(args[0]);
        int w = pic.width();
        int h = pic.height();
        System.out.println("Height is: " + h + ", Width is: " + w);
    }
}
