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
    private static ArrayList<Train> trains = new ArrayList<>();
    
    public static void main (String []args)throws IOException{
       
    
        ServerSocket serverSocket = new ServerSocket(9090);
         System.out.println("Server started on port 9090...");

        while (true){
         System.out.println("Waiting for client connection");
         
         Socket client=serverSocket.accept();
         System.out.println("Connected to client");
         
         ClientHandler handler=new ClientHandler(client,clients); // new thread 
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

  public ClientHandler (Socket c,ArrayList<ClientHandler> clients) throws IOException
  {
    this.client = c;
    this.clients=clients;
    in= new BufferedReader (new InputStreamReader(client.getInputStream())); 
    out=new PrintWriter(client.getOutputStream(),true); 
  }
@Override
  public void run ()
  {
   try{
    while (true){
        String request=in.readLine();  
                outToAll(request);
   
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
    private void outToAll(String substring) {
for (ClientHandler aclient:clients){
   aclient.out.println(substring); 
}
    }
}




   
    



