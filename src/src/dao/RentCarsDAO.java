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

/**
 *
 * @author paulo
 */
public class RentCarsDAO {
    private String url;
    private String username;
    private String password;

    public RentCarsDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
     public void saveRentCar(RentCars rentCar)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println(rentCar.getRentCarID());
            if(rentCar.getRentCarID()!=0L)
            {
                PreparedStatement statement = connection.prepareStatement("update rentCars set carName = ?, carColor = ?, emailUser = ?, customerUser = ?, price = ?, startDate = ?, endDate = ? where rentCarID = ?;");
                statement.setString(1,rentCar.getCarName());
                statement.setString(2, rentCar.getCarColor());
                statement.setString(3, rentCar.getEmailUser());
                statement.setString(4,rentCar.getCustomerUser());
                statement.setFloat(5,rentCar.getPrice());
                statement.setLong(6,rentCar.getStartDate());
                statement.setLong(7,rentCar.getEndDate());
                statement.execute();
            }else {
                PreparedStatement statement = connection.prepareStatement("insert into rentCars (carName,carColor,emailUser,customerUser,price,startDate,endDate) values (?,?,?,?,?,?,?)");
                statement.setString(1,rentCar.getCarName());
                statement.setString(2, rentCar.getCarColor());
                statement.setString(3, rentCar.getEmailUser());
                statement.setString(4,rentCar.getCustomerUser());
                statement.setFloat(5,rentCar.getPrice());
                statement.setLong(6,rentCar.getStartDate());
                statement.setLong(7,rentCar.getEndDate());
                statement.execute();
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
     
     public RentCars getRentCarById(long id)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars where rentCarID = ?");
            statement.setLong(1,id);

            ResultSet resultset = statement.executeQuery();

            RentCars rentCar = new RentCars();

            while (resultset.next())
            {
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
            }

            return rentCar;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
     
     public ArrayList<RentCars> getAllRentCarsByEmailUser(String email)
    {
        ArrayList<RentCars> listRentCars = new ArrayList<>();
        try
        {
            
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars where emailUser = ?");
            statement.setString(1,email);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                RentCars rentCar = new RentCars();
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
                listRentCars.add(rentCar);
            }

            return listRentCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
     
     public ArrayList<RentCars> getAllRentCarsByCarName(String carName)
    {
        ArrayList<RentCars> listRentCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars where carName = ?");
            statement.setString(1,carName);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                RentCars rentCar = new RentCars();
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
                
                listRentCars.add(rentCar);
            }

            return listRentCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
     
     public ArrayList<RentCars> getAllRentCarsByCarColor(String carColor)
    {
        ArrayList<RentCars> listRentCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars where carColor = ?");
            statement.setString(1,carColor);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                RentCars rentCar = new RentCars();
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
                listRentCars.add(rentCar);
            }

            return listRentCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
     
     public ArrayList<RentCars> getAllRentCarsByCustomerUser(String customerUser)
    {
        ArrayList<RentCars> listRentCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars where customerUser = ?");
            statement.setString(1,customerUser);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                RentCars rentCar = new RentCars();
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
                listRentCars.add(rentCar);
            }

            return listRentCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
     
     public ArrayList<RentCars> getAllRentCarsByPrice(float price)
    {
        ArrayList<RentCars> listRentCars = new ArrayList<>();
        try
        {
            System.out.println(price);
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars where CAST(price AS DECIMAL) = CAST(? AS DECIMAL);");
            statement.setFloat(1,price);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                RentCars rentCar = new RentCars();
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
                listRentCars.add(rentCar);
            }

            return listRentCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
     
     public ArrayList<RentCars> getAllRentCarsByStartDate(long startDate)
    {
        ArrayList<RentCars> listRentCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars where startDate = ?");
            statement.setLong(1,startDate);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                RentCars rentCar = new RentCars();
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
                listRentCars.add(rentCar);
            }

            return listRentCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
     
     public ArrayList<RentCars> getAllRentCarsByEndDate(long endDate)
    {
        ArrayList<RentCars> listRentCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars where endDate = ?");
            statement.setLong(1,endDate);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                RentCars rentCar = new RentCars();
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
                listRentCars.add(rentCar);
            }

            return listRentCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
     
     public ArrayList<RentCars> getAllRentCars()
    {
        ArrayList<RentCars> listRentCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from rentCars");

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                RentCars rentCar = new RentCars();
                rentCar.setRentCarID(resultset.getLong("rentCarID"));
                rentCar.setCarName(resultset.getString("carName"));
                rentCar.setCarColor(resultset.getString("carColor"));
                rentCar.setEmailUser(resultset.getString("emailUser"));
                rentCar.setCustomerUser(resultset.getString("customerUser"));
                rentCar.setPrice(resultset.getFloat("price"));
                rentCar.setStartDate(resultset.getLong("startDate"));
                rentCar.setEndDate(resultset.getLong("endDate"));
                
                listRentCars.add(rentCar);
            }

            return listRentCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
        
    }
}
