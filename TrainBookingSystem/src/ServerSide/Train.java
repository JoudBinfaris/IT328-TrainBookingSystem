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

public class Train{
private  String trainID;
private String source;
private String destination;
private Seat[][] firstclassSeatbyday;
private Seat[][] economyclassSeatbyday;

public Train (String trainID, String source,String destination){
    this.trainID=trainID;
    this.source=source;
    this.destination=destination;
    this.firstclassSeatbyday=new Seat[7][5];
    this.economyclassSeatbyday=new Seat[7][5];// it will put each all seats for each day
    for(int day=0; day<7; day++){
        for(int seat=0; seat<5; seat++)
        {
            this.firstclassSeatbyday[day][seat]=new Seat("First");
            this.economyclassSeatbyday[day][seat]=new Seat("Economy");
            
        }}
}
private Seat[] getAvailableSeats(String classType, int dayindex){
    Seat [][] list=classType.equalsIgnoreCase("First")? this.firstclassSeatbyday: this.economyclassSeatbyday;
  int count=0;
for(Seat s: list[dayindex]){
if(s.IsAvailable())
count++;
}
Seat[] available=new Seat[count];
int j=0;
for(Seat s:list[dayindex]){
if(s.IsAvailable()) 
available[j++]=s;
}
return available;
}  

public boolean reserveSeat( String classType, int seatindex,int dayindex,String username){
    Seat[][] list=classType.equalsIgnoreCase("FIrst")? this.firstclassSeatbyday: this.economyclassSeatbyday;
if(dayindex >= 0&& dayindex<7&& seatindex>=0&&seatindex<5){
    return list[dayindex][seatindex].reserve(username);
}
return false;
}
public void cancelSeat(String classType, int seatindex, int dayindex)
{
    Seat[][] list=classType.equalsIgnoreCase("First")? this.firstclassSeatbyday: this.economyclassSeatbyday;
  if(dayindex >= 0&& dayindex<7&& seatindex>=0&&seatindex<5)
    {
    list[dayindex][seatindex].cancel();
}
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


} 
