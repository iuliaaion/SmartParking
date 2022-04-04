package com.company;
import java.util.*;

public class DataStorage {
    String car;
    String type;
    static int freeNo = 1000;
    static double gasLevel = 0;
    static  Hashtable<String, String> car_dictionary = new Hashtable<String, String>();
    static String exit_car;
    static String exit_type;
    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String[] cars_no = {"BV31NPS", "BV25LOL", "B202JOY", "BZ05BMV", "PH98BGT"};
    String[] cars_type = {"gas", "diesel", "benzine"};


    public DataStorage getRandomCar(){
        DataStorage ds = new DataStorage();
        Random r = new Random();
        int randomCar = r.nextInt(cars_no.length);
        int randomType = r.nextInt(cars_type.length);
        ds.car = cars_no[randomCar];
        ds.type = cars_type[randomType];
        return ds;
    }

    public DataStorage getRandomCarForExit(){
        DataStorage ds = new DataStorage();
        Random r = new Random();
        Object[] carr = car_dictionary.keySet().toArray();
        Object[] type = car_dictionary.values().toArray();
        int randomCar = r.nextInt(carr.length);
        int randomType = r.nextInt(type.length);
        ds.car = carr[randomCar].toString();
        ds.type = type[randomType].toString();
        return ds;
    }
}
