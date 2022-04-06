interface Animal {
    String getType();
    String getSound();
}

interface MilkProvider{
    void getMilk();
}

interface EggProvider{
    void getEgg();
}

class Pig implements Animal{
    public String getType(){
        return "Pig";
    }
    public String getSound(){
        return "oink~";
    }
}

class Cow implements Animal,MilkProvider{
    public String getType(){
        return "Cow";
    }
    public String getSound(){
        return "moo~";
    }
    public void getMilk(){
    }
}

class Hen implements Animal,EggProvider{
    public String getType(){
        return "Hen";
    }
    public String getSound(){
        return "cheep~";
    }
    public void getEgg(){
    }
}

class Farmer{
    public void fetchMilk(MilkProvider a){
        System.out.println("Milk + 1");
    }
    public void fetchEgg(EggProvider a){
        System.out.println("Egg + 1");
    }
}

public class Farm{
    private Animal[] animals;
    private int size;
    private Farmer farmer;
    private MilkProvider[] mp;
    private int mpcnt = 0;
    private EggProvider[] ep;
    private int epcnt = 0;

    public Farm(int size){
        this.size = size;
        this.animals = new Animal[size];
        for(int i = 0; i < 10; i++){
            int d = (int)(Math.random() * 3);
            switch (d)
            {
                case 0:
                    Cow c = new Cow();
                    animals[i] = c;
                    mp[mpcnt++] = c;
                    break;
                case 1:
                    Hen h = new Hen();
                    animals[i] = h;
                    ep[epcnt++] = h;
                    break;
                case 2:
                    Pig p = new Pig();
                    animals[i] = p;
                    break;
            }
        }
    }

    public void animalSound(){
        for(int i = 0; i < this.size; i++){
            System.out.println(animals[i].getType() + ": " + animals[i].getSound());
        }
    }
    public void produce(){
        this.farmer = new Farmer();
        for(int i = 0; i < mpcnt; i++){
            farmer.fetchMilk(mp[i]);
        }
        for(int i = 0; i < epcnt; i++){
            farmer.fetchEgg(ep[i]);
        }
    }
}