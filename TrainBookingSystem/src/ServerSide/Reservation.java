/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSide;

/**
 *
 * @author imusn
 */
import java.util.*;

public class Reservation {

    private String username;
    private String trainID;
    private String classType;
    private int seatindex;
    private int dayindex;

    public Reservation(String username, String trainID, String classType, int seatindex, int dayindex) {
        this.username = username;
        this.dayindex = dayindex;
        this.seatindex = seatindex;
        this.classType = classType;
        this.trainID = trainID;
    }

    public String getUsername() {
        return username;
    }

    public String getTrainID() {
        return trainID;
    }

    public String getClassType() {
        return classType;
    }

    public int getSeatindex() {
        return seatindex;
    }

    public int getDayindex() {
        return dayindex;
    }

    public String toString() {
        return this.username + "Train:" + this.trainID + "(" + this.classType + "Seat No:" + (this.seatindex + 1) + ") Day" + (this.dayindex + 1);
    }

}
