/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.models.Cars;

/**
 *
 * @author paulo
 */
public class CarsDAO {
    private String url;
    private String username;
    private String password;

    public CarsDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public void saveCar(Cars car)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("car id : " + car.getCarID());
            if(car.getCarID()!=0L)
            {
                PreparedStatement statement = connection.prepareStatement("update cars set car = ?, couleur = ?, prix = ?, stock = ?, redBusiness = ?, redIndividual = ? where carID = ?;");
                statement.setString(1,car.getCar());
                statement.setString(2, car.getCouleur());
                statement.setFloat(3, car.getPrix());
                statement.setDouble(4, car.getStock());
                statement.setFloat(5,car.getRedBusiness());
                statement.setFloat(6, car.getRedIndividual());
                statement.setLong(7, car.getCarID());
                statement.execute();
            }else {
                PreparedStatement statement = connection.prepareStatement("insert into cars (car,couleur,prix,stock,redBusiness,redIndividual) values (?,?,?,?,?,?)");
                statement.setString(1,car.getCar());
                statement.setString(2, car.getCouleur());
                statement.setFloat(3, car.getPrix());
                statement.setDouble(4, car.getStock());
                statement.setFloat(5, car.getRedBusiness());
                statement.setFloat(6, car.getRedIndividual());
                statement.execute();
            }

            System.out.println(car.getCar() + " saved into Login database");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public Cars getCarById(long id)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where carID = ?");
            statement.setLong(1,id);

            ResultSet resultset = statement.executeQuery();

            Cars car = new Cars();
            
            while (resultset.next())
            {
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
            }

            return car;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public Cars getCarByNameCar(String carName)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where car = ?");
            statement.setString(1,carName);

            ResultSet resultset = statement.executeQuery();

            Cars car = new Cars();

            while (resultset.next())
            {
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
            }

            return car;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public Cars getCarByColor(String color)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where couleur = ?");
            statement.setString(1,color);

            ResultSet resultset = statement.executeQuery();

            Cars car = new Cars();

            while (resultset.next())
            {
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
            }

            return car;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Cars> getAllCars()
    {
        ArrayList<Cars> listCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars");

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Cars car = new Cars();
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
                listCars.add(car);
            }

            return listCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public void deleteCarById(long id)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("delete from cars where carID = ?;");
            statement.setLong(1,id);
            
            statement.execute();
           
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    
    public ArrayList<Cars> getAllCarsByCarName(String carName)
    {
        ArrayList<Cars> listCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where car = ?");
            statement.setString(1,carName);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Cars car = new Cars();
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
                listCars.add(car);
            }

            return listCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Cars> getAllCarsByColor(String color)
    {
        ArrayList<Cars> listCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where couleur = ?");
            statement.setString(1,color);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Cars car = new Cars();
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
                listCars.add(car);
            }

            return listCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Cars> getAllCarsByRedBusiness(float redBusiness)
    {
        ArrayList<Cars> listCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where CAST(redBusiness AS DECIMAL) = CAST(? AS DECIMAL);");
            statement.setFloat(1,redBusiness);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Cars car = new Cars();
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
                listCars.add(car);
            }

            return listCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Cars> getAllCarsByRedIndividual(float redIndividual)
    {
        ArrayList<Cars> listCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where CAST(redIndividual AS DECIMAL) = CAST(? AS DECIMAL);");
            statement.setFloat(1,redIndividual);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Cars car = new Cars();
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
                listCars.add(car);
            }

            return listCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Cars> getAllCarsByPrice(float price)
    {
        ArrayList<Cars> listCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where CAST(prix AS DECIMAL) = CAST(? AS DECIMAL);");
            statement.setFloat(1,price);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Cars car = new Cars();
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
                listCars.add(car);
            }

            return listCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Cars> getAllCarsByStock(int stock)
    {
        ArrayList<Cars> listCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where stock = ?");
            statement.setFloat(1,stock);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Cars car = new Cars();
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
                listCars.add(car);
            }

            return listCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Cars> getAllCarsById(Long id)
    {
        ArrayList<Cars> listCars = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from cars where carID = ?");
            statement.setLong(1,id);

            ResultSet resultset = statement.executeQuery();
            

            while (resultset.next())
            {
                Cars car = new Cars();
                car.setCarID(resultset.getLong("carID"));
                car.setCar(resultset.getString("car"));
                car.setCouleur(resultset.getString("couleur"));
                car.setPrix(resultset.getFloat("prix"));
                car.setStock(resultset.getDouble("stock"));
                car.setRedBusiness(resultset.getFloat("redBusiness"));
                car.setRedIndividual(resultset.getFloat("redIndividual"));
                listCars.add(car);
            }

            return listCars;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
}
