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
        user.registerUser("ALtea","shimi",
                "auSDsWDdRTus", "09988", "3434534");
*/
        UserService student=new UserService();
        student.registerStudent("Altea", "Kostallari",
                "savidsna.shimi@fti.com", "09988", "3434534");
    }
}
