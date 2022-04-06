package Lab02;
    public class Lab_02_10 {
        public static void main(String[] args)
        {   
            double people;
            for(people = 3; people <= 13; people++) // Ranging from 3 to 13 people 
            {
                double keyi = 0; // Counting the times that all people are acknowledged 
                double times = 0; // Testing for 10 times
                for(times = 0; times < 100000;times++) // Statistics
                {
                    int[] man = new int[(int)people]; // All the people forming a Array 
                    for(int i = 0;i < people; i++)
                        man[i] = 0; // All the people haven't heard about the information
                    man[0] = 1; // Bob has aleady known this information
                    man[1] = 1; // Bob told one of his friend
                    
                    while(true) 
                    {
                        boolean yes = true;
                        int who = 1; // In case that people will tell himself
                        int heard = 0; // The information is heard from who?
                        int next = (int)(Math.random() * people); // Generate a random number
                        while(next == who || next == heard) 
                        {
                            next = (int)(Math.random() * people); // Generate a random number
                        }
                        if(man[next] == 1) // When a person who has already known the information was told again
                        {                  // According to the context, this information will not be spread again
                            for(int i = 0; i < people;i++) // whether there are somebody who still be acknowledged
                            {
                                if(man[i] == 0)
                                {
                                    yes = false;
                                    break;
                                }
                            }
                            
                            if(yes)
                                keyi++; // All of them were told about the information
                            
                            break; // end of this times test
                        }
                        else // if the people be told haven't been told yet, he will tell it to somebody else;
                        {
                            man[next] = 1;
                            heard = who;
                            who = next;
                        }
                    }    
                }
                
                double EV = 1.0;
                double unknown;
                for(unknown = people - 2; unknown >= 2; unknown--)
                {
                    EV *= unknown / people;
                }
                System.out.print("人数为: ");
                System.out.println((int)people);
                System.out.print("所有人都能知晓的概率为: ");
                System.out.println(keyi / times);
                System.out.print("所有人都能知晓的期望值为: ");
                System.out.println(EV);
                //System.out.printf("%.6lf",prob);
                //System.out.printf("The possiblity within %d people is %lf",people,prob);
                //System.out.println();
            }
        }
    }
