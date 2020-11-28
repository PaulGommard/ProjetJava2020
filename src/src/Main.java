package src;

import src.dao.LoginDAO;

import gui.LoginForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import src.dao.UsersDAO;
import src.dao.CarsDAO;

public class Main
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3308/projetjava?serverTimezone=UTC";
        String username="root";
        String password="ProjetJava2020";

        UsersDAO userDAO = new UsersDAO(url,username,password);
        
        //LoginDAO loginDAO = new LoginDAO(url,username,password);
        
        new LoginForm(userDAO).setVisible(true);
       
        
    }



}




