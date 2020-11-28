/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.models;

/**
 *
 * @author paulo
 */
public class Users {
    private long loginID;
    private String email;
    private String password;
    private String customer;

    @Override
    public String toString() {
        return "Users{" + "loginID=" + loginID + ", email=" + email + ", password=" + password + ", customer=" + customer + '}';
    }

    public void setLoginID(long loginID) {
        this.loginID = loginID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public long getLoginID() {
        return loginID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCustomer() {
        return customer;
    }

    public Users() {
        
    }
    
}
