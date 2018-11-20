package edu.upc.eetac.dsa;

public class Bike {
    private String idBike;
    private String description;
    private double kms;

    public Bike() {

    }

    public Bike(String idBike, String description, double kms) {
        this.idBike = idBike;
        this.description = description;
        this.kms = kms;
    }

    public String getIdBike() {
        return idBike;
    }

    public void setIdBike(String idBike) {
        this.idBike = idBike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

}
