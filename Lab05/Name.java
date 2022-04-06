import Fileio.FileIO;
public class Name{
    public String firstName;
    public String lastName;

    Name()
    {
        this.firstName = FileIO.getLineFromFile((int)(Math.random() * 162), "NAMETABLE");
        this.lastName = FileIO.getLineFromFile((int)(Math.random() * 162), "NAMETABLE");
        // Using nametable to create a random name since the name is not given
    }
    Name(String f, String l)
    {
        this.firstName = f;
        this.lastName = l;
    }
    public String getFirstName()
    {
        return this.firstName;
    } 
    public String getLastName()
    {
        return this.lastName;
    }
    public void setFirstName(String f)
    {
        this.firstName = f;
    }
    public void setLastName(String l)
    {
        this.lastName = l;
    }
    public String ToString()
    {
        String ans = this.firstName + "·" + this.lastName;
        return ans;
    }
    public static void main(String[] args) {
        Name n = new Name("yb","wu");
        Name nn = new Name();
        System.out.println(n.ToString());
        System.out.println(nn.ToString());
    }
}