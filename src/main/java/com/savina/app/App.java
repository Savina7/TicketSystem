package com.savina.app;

import com.savina.service.UserService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        /* UserService user=new UserService();
        user.registerUser("Altea","Kostallari",
                "altea.kostallari@fti.com", "09988", "3434534");

        UserService student=new UserService();
        student.registerStudent("Altea", "Kostallari",
                "altea.kostallari@fti.com", "09988", "3434534");
        */
        UserService admin=new UserService();
        admin.registerAdmin("Arbner", "Hoxha",
                "arber.hoxha@abc.com", "09988", "3434534");

    }
}
