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
public class Train implements Serializable {
private final String trainID;
private final String source;
private final String destination;
private final Seat[] seatbyclass;
private static final int Seats_per_class=10;
private static final String [] classes={"First","Economy"};
public Train (String trainID, String source,String destination){
    this.trainID=trainID;
    this.source=source;
    this.destination=destination;
    this.seatbyclass=initializeSeats();
}
private Seat[] initializeSeats(){
    int totalSeats=classes.length*Seats_per_class;
    Seat [] seats=new Seat[totalSeats];
    int seatindex=0;
    for(String classType:classes){
        for(int i=1; i<=Seats_per_class;i++)
        {
            String seatID=this.trainID +classType+i;
            seats[seatindex++]=new Seat(seatID,classType);
            
        }
    }
    return seats;
}    
public boolean isSeatAvailable(String classType){
    for(Seat seat : seatbyclass){
        if(seat.getClassType().equalsIgnoreCase(classType)&& seat.isAvailable()){
            return true;
        }
    }
    return false;
}
public String reserveSeat(String username, String classType){
    synchronized(this){
    for(Seat seat: seatbyclass){
        if(seat.getClassType().equalsIgnoreCase(classType)&& seat.isAvailable()){
            if(seat.reserve(username)){
                return seat.getSeatID();
            }
        }
    }
  }
    return null;
}
public String getSchedule()
{
    return String.format("Train ID: %s, From: %s, To: %s",trainID, source,destination);
}

    public String getTrainID() {
        return trainID;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

public Seat[] getAllSeatsa(){  
    return seatbyclass;
}
} 
