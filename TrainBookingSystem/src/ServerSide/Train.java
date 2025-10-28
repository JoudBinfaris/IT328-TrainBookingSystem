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
    this.firstclassSeatbyday=new Seat[7][4];
    this.economyclassSeatbyday=new Seat[7][4];// it will put each all seats for each day
    for(int day=0; day<7; day++){
        for(int seat=0; seat<4; seat++)
        {
            this.firstclassSeatbyday[day][seat]=new Seat("First");
            this.economyclassSeatbyday[day][seat]=new Seat("Economy");
            
        }}
}
public Seat[] getAvailableSeats(String classType, int dayindex){
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
    Seat[][] list=classType.equalsIgnoreCase("First")? this.firstclassSeatbyday: this.economyclassSeatbyday;
if(dayindex >= 0&& dayindex<7&& seatindex>=0&&seatindex<4){
    return list[dayindex][seatindex].reserve(username);
}
return false;
}
public void cancelSeat(String classType, int seatindex, int dayindex)
{
    Seat[][] list=classType.equalsIgnoreCase("First")? this.firstclassSeatbyday: this.economyclassSeatbyday;
  if(dayindex >= 0&& dayindex<7&& seatindex>=0&&seatindex<4)
    {
    list[dayindex][seatindex].cancel();
}
}

//Sarahs edits: *****************************
// How many avaliable seat we have?
//public synchronized int countAvailable(String classType, int dayIndex) {
//    Seat[][] list = classType.equalsIgnoreCase("First") ? firstclassSeatbyday : economyclassSeatbyday;
//    int c = 0;
//    for (int i = 0; i < 5; i++) if (list[dayIndex][i].IsAvailable()) c++;
//    return c;
//}
//
//// مصفوفة 7 عناصر: المتاح لكل يوم
//public synchronized int[] countAvailablePerDay(String classType) {
//    int[] res = new int[7];
//    for (int d = 0; d < 7; d++) res[d] = countAvailable(classType, d);
//    return res;
//}
//
//// حجز N مقاعد لأي يوم (يرجّع true لو تم، false لو ما يكفي)
//public synchronized boolean reserveSeats(String classType, int count, int dayIndex, String username) {
//    if (count <= 0) return false;
//    Seat[][] list = classType.equalsIgnoreCase("First") ? firstclassSeatbyday : economyclassSeatbyday;
//
//    // احسب المتاح
//    int free = 0;
//    for (int i = 0; i < 5; i++) if (list[dayIndex][i].IsAvailable()) free++;
//    if (free < count) return false;
//
//    // احجز أول count مقاعد متاحة
//    int taken = 0;
//    for (int i = 0; i < 5 && taken < count; i++) {
//        if (list[dayIndex][i].IsAvailable()) {
//            list[dayIndex][i].reserve(username);
//            taken++;
//        }
//    }
//    return true;
//}
//// Train.java
//public int firstAvailableIndex(String classType, int dayIndex) {
//    Seat[][] list = classType.equalsIgnoreCase("First") ? firstclassSeatbyday : economyclassSeatbyday;
//    if (dayIndex < 0 || dayIndex >= 7) return -1;
//    for (int i = 0; i < 5; i++) if (list[dayIndex][i].IsAvailable()) return i;
//    return -1;
//}
//
//public int reserveNextAvailable(String classType, int dayIndex, String username) {
//    Seat[][] list = classType.equalsIgnoreCase("First") ? firstclassSeatbyday : economyclassSeatbyday;
//    int idx = firstAvailableIndex(classType, dayIndex);
//    if (idx == -1) return -1;
//    list[dayIndex][idx].reserve(username);
//    return idx; // ارجعي seat index الفعلي المحجوز
//}
//
//public int[] availabilityPerDay(String classType) {
//    Seat[][] list = classType.equalsIgnoreCase("First") ? firstclassSeatbyday : economyclassSeatbyday;
//    int[] counts = new int[7];
//    for (int d = 0; d < 7; d++) {
//        int c = 0;
//        for (int s = 0; s < 5; s++) if (list[d][s].IsAvailable()) c++;
//        counts[d] = c; // عدد المقاعد الفاضية في هذا اليوم
//    }
//    return counts;
//}

//*************************************************88
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
