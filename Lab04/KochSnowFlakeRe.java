public class KochSnowFlakeRe {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-100,100);
        StdDraw.setYscale(-100,100);
        //pen.REKochCurve(5);
        //pen.REKochsnowflake(5);
        pen.REKochsnowflake(Integer.parseInt(args[0]));
    }
}
