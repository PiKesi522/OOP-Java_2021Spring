public class ElderTeacher extends Teacher {
    private ElderTeacher(){}    //  Since we don't need more than 
                                // one elderTeacher, we will make
                                // the constructor function 'private'
                                //  So, the user cannot create any
                                // Instance:Elderteacher from the 
                                // outside of this class
    public static ElderTeacher et = new ElderTeacher();
                                //  The function will create an
                                // instance itself
    public static ElderTeacher give_me_the_only_teacher()
    {                           //  And use static method to
        return et;              // give it to other function
    }                         
}
