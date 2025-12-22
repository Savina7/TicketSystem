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

        UserService user=new UserService();
        user.register("savina","shimi",
                "ausdus", "09988", "3434534");
    }
}
