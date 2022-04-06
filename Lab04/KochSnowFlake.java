public class KochSnowFlake {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-50,150);
        StdDraw.setYscale(-100,100);
        //pen.KochCurve(5);
        //pen.Kochsnowflake(5);
        pen.Kochsnowflake(Integer.parseInt(args[0]));
    }
}
