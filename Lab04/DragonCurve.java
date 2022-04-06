public class DragonCurve{
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-200,200);
        StdDraw.setYscale(-200,200);
        //pen.DragonCurve(15);
        pen.DragonCurve(Integer.parseInt(args[0]));
    }
}