package edu.upc.eetac.dsa;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {
    final static Logger logger = Logger.getLogger(User.class.getName());
    private String idUser;
    private String name;
    private String surname;
    private LinkedList<Bike> bikesUserList;

    public User() {

    }

    public User(String userId){
        this.idUser = userId;
        this.name = null;
        this.surname = null;
        this.bikesUserList = new LinkedList<>();
    }
    public User(String idUser, String name, String surname) {
        this.idUser=idUser;
        this.name = name;
        this.surname=surname;
        this.bikesUserList = new LinkedList<>();
    }

    public LinkedList<Bike> getBikesUserList() {
        return bikesUserList;
    }

    public void setBikesUserList(LinkedList<Bike> bikesUserList) {
        bikesUserList = bikesUserList;
    }
    public void addBike(Bike b){
        bikesUserList.add(b);
        logger.debug("added bike "+b.getIdBike());
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
