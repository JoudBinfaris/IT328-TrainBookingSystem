/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSide;

/**
 *
 * @author joud
 */
   import java.io.*; 
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static ArrayList<ClientHandler> clients=new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    
    public static void main (String []args)throws IOException{
       
    
        ServerSocket serverSocket = new ServerSocket(9090);
         System.out.println("Server started on port 9090...");
         Train t1=new Train("1111","Riyadh","Jeddah");
         Train t2=new Train("2222","Jeddah","Riyadh");
         Train t3=new Train("3333","Riyadh","Dammam");
         Train t4=new Train("4444","Dammam","Riyadh");

        while (true){
         System.out.println("Waiting for client connection");
         
         Socket client=serverSocket.accept();
         System.out.println("Connected");
         
         ClientHandler handler=new ClientHandler(client,clients,reservations); // new thread 
         clients.add(handler);
         new Thread (handler).start();
         
        }
    
}
    }
class ClientHandler implements Runnable
{
private Socket client;
private BufferedReader in;
private PrintWriter out;
private ArrayList<ClientHandler> clients;
private ArrayList<Reservation> reservations;

  public ClientHandler (Socket c,ArrayList<ClientHandler> clients, ArrayList<Reservation> reservations) throws IOException
  {
    this.client = c;
    this.clients=clients;
    this.reservations=reservations;
    in= new BufferedReader (new InputStreamReader(client.getInputStream())); 
    out=new PrintWriter(client.getOutputStream(),true); 
  }
@Override
  public void run ()
  {
   try{
         
    while (true){
        System.out.println("Username:");
        String un=in.readLine(); 
        
        System.out.println("Source city:");
        String sc=in.readLine();
        System.out.println("Destination city:");
         String dc=in.readLine(); 
         
         String tn="Nan";
         if(sc.equals("Riyadh") && dc.equals("Jeddah"))
             tn="1111";
         else if(sc.equals("Jeddah") && dc.equals("Riyadh"))
             tn="2222";
          else if(sc.equals("Riyadh") && dc.equals("Dammam"))
             tn="3333";
          else if(sc.equals("Dammam") && dc.equals("Riyadh"))
             tn="4444";
         
        System.out.println("class:");
         String c=in.readLine(); 
         
        System.out.println("Seat Number (1-5):");
        int sn=Integer.parseInt(in.readLine())-1; 
        
        System.out.println("day:");
        int d=Integer.parseInt(in.readLine())-1; 
        
        
         Reservation r=new Reservation( un, tn,  c, sn, d);
          reservations.add(r);
//                outToAll(request);
   
    }
}
   catch (IOException e){
       System.err.println("IO exception in new client class");
       System.err.println(e.getStackTrace());
   }
finally{
    out.close();
       try {
           in.close();
       } catch (IOException ex) {
          ex.printStackTrace();
       }
}
  }
//    private void outToAll(String substring) {
//for (ClientHandler aclient:clients){
//   aclient.out.println(substring); 
//}
//    }
}




   
    



