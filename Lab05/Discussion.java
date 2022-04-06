public class Discussion {
    public Person[] people;

    public Discussion(int n)
    {
        people = new Person[n];
        for(int i = 0; i < n; i++)
        {
            people[i] = PersonFactory.next();   // Create a person randomly
        }
    }

    public void boardcast()
    {
        int loc = (int)(people.length * Math.random());     // choose a person randomly
        for(int i = 0; i < people.length; i++)
        {
            if(i == loc)
                continue;
            else
            {
                people[loc].chatWith(people[i], "What's up bro?");
            }
        }
    }

    public static void main(String[] args) {
        Discussion d = new Discussion(10);
        d.boardcast();
    }
}
