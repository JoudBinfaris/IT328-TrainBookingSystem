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

public class Seat {

    private boolean isAvailable;
    private String reservedBy;
    private String classType;
    private int seatnumber;

    public Seat(String classType, int seatnumber) {
        this.seatnumber = seatnumber;
        this.classType = classType;
        this.isAvailable = true;
        this.reservedBy = null;
    }

    public boolean reserve(String username) {
        if (isAvailable) {
            this.isAvailable = false;
            this.reservedBy = username;
            return true;
        }
        return false;
    }

    public void cancel() {

        isAvailable = true;
        this.reservedBy = null;
    }

    public String getStatus() {
        if (isAvailable) {
            return "Available";
        } else {
            return "Reserved by:" + this.reservedBy;
        }
    }

    public boolean IsAvailable() {
        return isAvailable;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public String getClassType() {
        return classType;
    }

    public int getSeatnumber() {
        return seatnumber;
    }

}
