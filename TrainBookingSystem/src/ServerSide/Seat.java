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
import java.io.Serializable;
public class Seat implements Serializable {
    private boolean isAvailable;
    private String reservedBy;
    private final String classType;
    private final String seatID;
    public Seat(String seatID, String classType){
        this.seatID=seatID;
        this.classType=classType;
        this.isAvailable=true;
        this.reservedBy=null;
    }
      public synchronized boolean reserve(String username)
  {
      if(!isAvailable)
      {
          this.isAvailable=false;
          this.reservedBy= username;
          return true;
      }
      return false;
  }
  public synchronized boolean cancel()
  {
      if(!isAvailable)
      {
          this.isAvailable=true;
          this.reservedBy=null;
          return true;
      }
      return false;
  }
  public String getStatus()
  {
      return isAvailable ? "Available("+ this.classType+")":
              "Reserved by"+ reservedBy+" ("+classType+")";
  }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public String getClassType() {
        return classType;
    }

    public String getSeatID() {
        return seatID;
    }
 
  
      }
    

