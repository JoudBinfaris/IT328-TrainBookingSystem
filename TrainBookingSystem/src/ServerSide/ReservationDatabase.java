/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSide;

/**
 *
 * @author imusn
 */
import java.io.*;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationDatabase {
    class Reservation {
    private String username;
    private String trainName;
    private String seatClass;
    private String day;

    public Reservation(String username, String trainName, String seatClass, String day) {
        this.username = username;
        this.trainName = trainName;
        this.seatClass = seatClass;
        this.day = day;
    }

    // Getters (and setters if needed)
    public String getUsername() { return username; }
    public String getTrainName() { return trainName; }
    public String getSeatClass() { return seatClass; }
    public String getDay() { return day; }
}
    
//    private final Map<String, Train > trains;
//    private final Map<String, String>userReservation;
//    public ReservationDatabase(){
//        this.trains=new HashMap<>();
//        this.userReservation=new HashMap<>();
//        initializeSampleTraiins();
//    }
//    private void initializeSampleTraiins()
//    {
//        String [] days={"Mon", "Tue", "Wed","Thu","Fri","Sat","Sun"};
//        for(int i=0; i<7 ; i++)
//        {
//            String day=days[i];
//            String key="Riyadh-Dammam-"+day;
//            String trainID="T101-"+day;
//            Train train=new Train (trainID,"Riyadh","Dammam");
//            trains.put(key,train);
//        }
//        }
//    public String getAvailableSeats(String source,String destination,String day)
//    {
//        String key=source+"-"+destination+"-"+day;
//        Train train=trains.get(key);
//        if(train==null)
//        {
//            return"There are no Trains Found for this route and day";
//        }
//        Map<String, Long> availability=Arrays.stream(train.getAllSeatsa())
//        .filter(Seat::isAvailable)
//        .collect(Collectors.groupingBy(Seat::getClassType, Collectors.counting()));
//        StringBuilder response=new StringBuilder(train.getSchedule()+"\n");
//        response.append("Available Classes:\n ");
//        response.append("First").append(availability.getOrDefault("First",0L)).append("seats\n");
//         response.append("Economy").append(availability.getOrDefault("Economy",0L)).append("seats\n");
//         return response.toString();
//        }
//      public String reserveSeat(String username,String trainKey,String classType){
//          Train train =trains.get(trainKey);
//          if(train==null){
//              return "Train not found";}
//          
//          String reservedSeatID= train.reserveSeat(username,classType);
//          if(reservedSeatID!=null)
//          {
//            updateAvailability(trainKey);
//            userReservation.put(username+"-"+reservedSeatID,reservedSeatID);
//            return String.format("Reservation Confirmed:", username,reservedSeatID,train.getTrainID());
//          }
//          else
//          {
//              return "Reserveison failed, No available seats in"+classType+"Class";
//        }
//        
//      }
//      public void updateAvailability(String trainKey){
//          System.out.println("Availability Updated for:"+trainKey);
//      }
//     /* public cancelReservation(String username,String SeatID){
//         return "Reservation canceled";
//        
//      }*/
//    public List<String> retrievePreviousReservation(String username){
//         return new java.util.ArrayList<>();
//    }

      
}
