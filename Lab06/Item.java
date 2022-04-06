public abstract class Item{
    protected final String name;    // 商品名称
    protected final double cost;    // 价格

    protected Item(String s, double d){     // 构造函数才能创建final
        this.name = s;
        this.cost = d;
    }

    public double getCost(){
        return this.cost;
    }
    public String getName(){
        return this.name;
    }
    public String toString(){
        return (this.name + " : " + this.cost);
    }
    public abstract double costPerUnit();  // Abstract method

}

class Produce extends Item {
    private double pounds;      // 商品重量
    private String category;    // 商品种类
    public Produce(double pounds, String category, double cost, String name){
        super(name, cost);
        this.category = category;
        this.pounds = pounds;
    }
    
    public double costPerUnit(){
        return(this.pounds / this.cost);
    }
    public double getPounds(){
        return this.pounds;
    }
    public void setPounds(double d){
        this.pounds = d;
    }
    public String getCategory(){
        return this.category;
    }
    public void setCategory(String s){
        this.category = s;
    }
    @Override
    public String toString(){
        return(this.name + "(" + this.category + ") : " + this.cost + " in " + this.pounds + "KG");
    }
}

class Beverage extends Item{
    private double volume;
    private double containerDeposit;
    
    public Beverage(double volume, double containerDeposit, double cost, String name){
        super(name, cost);
        this.containerDeposit = containerDeposit;
        this.volume = volume;
    }
    public double costPerUnit(){
        return(this.getCost() / this.volume);
    }

    @Override
    public double getCost(){
        return(this.cost - this.containerDeposit);
    }
    public double getVolume(){
        return(this.volume);
    }
    public void setVolume(double d){
        this.volume = d;
    }
    public double getContainerDeposit(){
        return this.containerDeposit;
    }
    public void setContainerDeposit(double d){
        this.containerDeposit = d;
    }
    @Override
    public String toString(){
        return(this.name + " : " + this.getCost() + " in " + this.volume + "L");
    }
}

class Package extends Item{
    private final double length;
    private final double width;
    private final double height;

    public Package(double length, double width, double height, double pounds, String name){
        super(name, pounds);
        this.length = length;
        this.width = width;
        this.height = height;
    }
    @Override
    public double costPerUnit(){
        return(this.cost / this.getSize());
    }
    public double getSize(){
        return(this.height * this.length * this.width);
    }
    @Override
    public String toString(){   
        return(this.name + " : " + this.cost + " in " + this.getSize() + "L");
    }

}

