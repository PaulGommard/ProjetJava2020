package src.dao;

import src.models.Login;

import java.sql.*;

public class LoginDAO {
    private String url;
    private String username;
    private String password;

    public LoginDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void saveLogin(Login login)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            if(login.getLoginID()!=0L)
            {
                PreparedStatement statement = connection.prepareStatement("update users set email = ? set password = ? set customer = ? where loginID = ?;");
                statement.setString(1,login.getEmail());
                statement.setString(2, login.getPassword());
                statement.setString(3, login.getCustomer());
                statement.setLong(4,login.getLoginID());
                statement.execute();
            }else {
                PreparedStatement statement = connection.prepareStatement("insert into users (email,password,customer) values (?,?,?)");
                statement.setString(1, login.getEmail());
                statement.setString(2, login.getPassword());
                statement.setString(3, login.getCustomer());
                statement.execute();
            }

            System.out.println(login.getEmail() + " saved into Login database");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Login getLoginById(long id)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where loginID = ?");
            statement.setLong(1,id);

            ResultSet resultset = statement.executeQuery();

            Login login = new Login();

            while (resultset.next())
            {
                login.setLoginID(resultset.getLong("loginID"));
                login.setEmail(resultset.getString("email"));
                login.setPassword(resultset.getString("password"));
                login.setCustomer(resultset.getString("customer"));
            }

            return login;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    public Login getLoginByEmail(String email)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where email = ?;");
            statement.setString(1,email);

            ResultSet resultset = statement.executeQuery();

            Login login = new Login();

            while (resultset.next())
            {
                login.setLoginID(resultset.getLong("loginID"));
                login.setEmail(resultset.getString("email"));
                login.setPassword(resultset.getString("password"));
                login.setCustomer(resultset.getString("customer"));
            }

            return login;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    public Login getLoginByPassword(String pass)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where password = ?;");
            statement.setString(1,pass);


            ResultSet resultset = statement.executeQuery();

            Login login = new Login();

            while (resultset.next())
            {
                login.setLoginID(resultset.getLong("loginID"));
                login.setEmail(resultset.getString("email"));
                login.setPassword(resultset.getString("password"));
                login.setCustomer(resultset.getString("customer"));
            }

            return login;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }

    public Login getLoginByEmailAndPassword(String email, String pass)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from login where password = ? and email = ?;");
            statement.setString(1,pass);
            statement.setString(2,email);

            ResultSet resultset = statement.executeQuery();

            Login login = new Login();

            while (resultset.next())
            {
                login.setLoginID(resultset.getLong("loginID"));
                login.setEmail(resultset.getString("email"));
                login.setPassword(resultset.getString("password"));
                login.setCustomer(resultset.getString("customer"));
            }

            return login;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
    
    public Long getIdByLoginInformation(String email,String pass)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement statement = connection.prepareStatement("select * from users where password = ? and email = ?;");
            statement.setString(1,pass);
            statement.setString(2,email);

            ResultSet resultset = statement.executeQuery();
            
            return resultset.getLong("loginID");

        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return null;
        }
    }
}
