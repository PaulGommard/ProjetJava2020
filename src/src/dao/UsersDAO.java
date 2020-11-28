/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.models.RentCars;
import src.models.Users;

/**
 *
 * @author paulo
 */
public class UsersDAO {
    private String url;
    private String username;
    private String password;

    public UsersDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void saveUser(Users user)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            if(user.getLoginID()!=0L)
            {
                PreparedStatement statement = connection.prepareStatement("update users set email = ?, password = ?, customer = ? where loginID = ?;");
                statement.setString(1,user.getEmail());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getCustomer());
                statement.setLong(4,user.getLoginID());
                statement.execute();
            }else {
                PreparedStatement statement = connection.prepareStatement("insert into users (email,password,customer) values (?,?,?)");
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getCustomer());
                statement.execute();
            }

            System.out.println(user.getEmail() + " saved into Login database");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Users getUserById(long id)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where loginID = ?");
            statement.setLong(1,id);

            ResultSet resultset = statement.executeQuery();

            Users user = new Users();

            while (resultset.next())
            {
                user.setLoginID(resultset.getLong("loginID"));
                user.setEmail(resultset.getString("email"));
                user.setPassword(resultset.getString("password"));
                user.setCustomer(resultset.getString("customer"));
            }

            return user;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    public Users getUserByEmail(String email)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where email = ?;");
            statement.setString(1,email);

            ResultSet resultset = statement.executeQuery();

            Users user = new Users();

            while (resultset.next())
            {
                user.setLoginID(resultset.getLong("loginID"));
                user.setEmail(resultset.getString("email"));
                user.setPassword(resultset.getString("password"));
                user.setCustomer(resultset.getString("customer"));
            }

            return user;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    public Users getUserByPassword(String pass)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where password = ?;");
            statement.setString(1,pass);


            ResultSet resultset = statement.executeQuery();

            Users user = new Users();

            while (resultset.next())
            {
                user.setLoginID(resultset.getLong("loginID"));
                user.setEmail(resultset.getString("email"));
                user.setPassword(resultset.getString("password"));
                user.setCustomer(resultset.getString("customer"));
            }

            return user;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    public Users getUserByEmailAndPassword(String email, String pass)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where password = ? and email = ?;");
            statement.setString(1,pass);
            statement.setString(2,email);

            ResultSet resultset = statement.executeQuery();

            Users user = new Users();

            while (resultset.next())
            {
                user.setLoginID(resultset.getLong("loginID"));
                user.setEmail(resultset.getString("email"));
                user.setPassword(resultset.getString("password"));
                user.setCustomer(resultset.getString("customer"));
            }

            return user;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public Long getIdByUserInformation(String email,String pass)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where password = ? and email = ?;");
            statement.setString(1,pass);
            statement.setString(2,email);

            ResultSet resultset = statement.executeQuery();
            
            while (resultset.next())
            {
              return resultset.getLong("loginID");
            }

        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }
    
    public ArrayList<Users> getAllUsers()
    {
        ArrayList<Users> listUsers = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users");

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Users user = new Users();
                
                user.setEmail(resultset.getString("email"));
                user.setPassword(resultset.getString("password"));
                user.setCustomer(resultset.getString("customer"));
                user.setLoginID(resultset.getLong("loginID"));
             
                listUsers.add(user);
            }

            return listUsers;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
    
    public void deleteUserById(long id)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("delete from users where loginID = ?;");
            statement.setLong(1,id);
            
            statement.execute();
           
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    
    public ArrayList<Users> getAllUsersByEmail(String email)
    {
        ArrayList<Users> listUsers = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where email = ?");
            statement.setString(1,email);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Users user = new Users();
                user.setEmail(resultset.getString("email"));
                user.setLoginID(resultset.getLong("loginID"));
                user.setCustomer(resultset.getString("customer"));
                user.setPassword(resultset.getString("password"));
                
                listUsers.add(user);
            }

            return listUsers;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Users> getAllUsersByCustomer(String customer)
    {
        ArrayList<Users> listUsers = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where customer = ?");
            statement.setString(1,customer);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Users user = new Users();
                user.setEmail(resultset.getString("email"));
                user.setLoginID(resultset.getLong("loginID"));
                user.setCustomer(resultset.getString("customer"));
                user.setPassword(resultset.getString("password"));
                
                listUsers.add(user);
            }

            return listUsers;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Users> getAllUsersByPassword(String passwordUser)
    {
        ArrayList<Users> listUsers = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where password = ?");
            statement.setString(1,passwordUser);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Users user = new Users();
                user.setEmail(resultset.getString("email"));
                user.setLoginID(resultset.getLong("loginID"));
                user.setCustomer(resultset.getString("customer"));
                user.setPassword(resultset.getString("password"));
                
                listUsers.add(user);
            }

            return listUsers;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Users> getAllUsersById(long id)
    {
        ArrayList<Users> listUsers = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where loginID = ?");
            statement.setLong(1,id);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Users user = new Users();
                user.setEmail(resultset.getString("email"));
                user.setLoginID(resultset.getLong("loginID"));
                user.setCustomer(resultset.getString("customer"));
                user.setPassword(resultset.getString("password"));
                
                listUsers.add(user);
            }

            return listUsers;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
}
