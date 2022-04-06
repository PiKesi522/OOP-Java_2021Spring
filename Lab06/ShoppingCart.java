public class ShoppingCart {
    public Item[] cart;
    private final int maxSize;
    public int currentSize;
    
    public ShoppingCart(int maxSize){
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.cart = new Item[maxSize];
    }

    public void additem(Item it){
        /*
        此处会使用多态的方法，不会直接创建抽象的Item类，但是其
        Produce类，Beverage类， Package类都可以被使用进去
        所以只能使用这三者的父类的方法和数据
        */
        if(currentSize == maxSize){
            System.out.println("Too Many Goods!");
        }
        else{
            cart[currentSize] = it;
            currentSize += 1;
        }
    }
    public void display(){
        for(int i = 0; i < currentSize; i++){
            System.out.println(cart[i].toString());
        }
    }
    public double totalCost(){
        double sum = 0;
        for(int i = 0; i < currentSize; i++){
            sum += cart[i].getCost();
        }
        return sum;
    }
    public int numberInCart(String s){
        int cnt = 0;
        for(int i = 0; i < currentSize; i++){
            if(s.equals(cart[i].getName()))
                cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        ShoppingCart BMW = new ShoppingCart(10);
        Produce p = new Produce(12.3,"Sports",88.8,"TennisBall");
        Beverage b = new Beverage(0.5,0.1,3.5,"Cola");
        Package pa = new Package(10,10,5,120,"Water");

        BMW.additem(p);
        BMW.additem(b);
        BMW.additem(b);
        BMW.additem(pa);

        BMW.display();
        System.out.println("All cost : " + BMW.totalCost());
        System.out.println(BMW.numberInCart("Cola"));

    }
}
