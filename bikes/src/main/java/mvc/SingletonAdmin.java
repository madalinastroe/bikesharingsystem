package mvc;

import mvc.model.User;

public class SingletonAdmin extends User{
    
    private static SingletonAdmin instance=new SingletonAdmin();

    private SingletonAdmin(){}

    public static SingletonAdmin getInstance()
    {
        return instance;
    }

    public void listUsers()
    {
    }
}
