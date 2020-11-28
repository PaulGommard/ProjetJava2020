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
public class RentCars {
   private long rentCarID;
   private String carName;
   private String carColor;
   private String emailUser;
   private String customerUser;
   private float price;
   private long startDate;
   private long endDate;

    public Long getRentCarID() {
        return rentCarID;
    }
   
    public String getCarName() {
        return carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getCustomerUser() {
        return customerUser;
    }

    public float getPrice() {
        return price;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setRentCarID(Long rentCarID) {
        this.rentCarID = rentCarID;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public void setCustomerUser(String customerUser) {
        this.customerUser = customerUser;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "RentCars{" + "carName=" + carName + ", carColor=" + carColor + ", emailUser=" + emailUser + ", customerUser=" + customerUser + ", price=" + price + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
   
   
    
    
}
