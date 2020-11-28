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
public class Cars {
    private long carID;
    private String car;
    private String couleur;
    private float prix;
    private double stock;
    private float redBusiness;
    private float redIndividual;

    @Override
    public String toString() {
        return "Cars{" + "carID=" + carID + ", car=" + car + ", couleur=" + couleur + ", prix=" + prix + ", stock=" + stock + ", redBusiness=" + redBusiness + ", redIndividual=" + redIndividual + '}';
    }

    public void setRedBusiness(float redBusiness) {
        this.redBusiness = redBusiness;
    }

    public void setRedIndividual(float redIndividual) {
        this.redIndividual = redIndividual;
    }

    public float getRedBusiness() {
        return redBusiness;
    }

    public float getRedIndividual() {
        return redIndividual;
    }

    public void setCarID(long carID) {
        this.carID = carID;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public long getCarID() {
        return carID;
    }

    public String getCar() {
        return car;
    }

    public String getCouleur() {
        return couleur;
    }

    public float getPrix() {
        return prix;
    }

    public double getStock() {
        return stock;
    }
    
}
