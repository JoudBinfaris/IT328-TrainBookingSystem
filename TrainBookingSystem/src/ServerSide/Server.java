/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSide;

/**
 *
 * @author joud
 */
import ClientSide.NetLab;
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
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import ClientSide.*;
import java.io.IOException;

public class Server {

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();//no need

    public static Train t1 = new Train("1111", "Riyadh", "Jeddah");
    public static Train t2 = new Train("2222", "Jeddah", "Riyadh");
    public static Train t3 = new Train("3333", "Riyadh", "Dammam");
    public static Train t4 = new Train("4444", "Dammam", "Riyadh");
    public static Train t5 = new Train("5555", "Riyadh", "Alula");

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server started on port 9090...");

        while (true) {
            System.out.println("Waiting for client connection");

            Socket client = serverSocket.accept();
            System.out.println("Connected");

            ClientHandler handler = new ClientHandler(client, clients, reservations, users, t1, t2, t3, t4, t5); // new thread 
            clients.add(handler);
            new Thread(handler).start();

        }

    }
}

class ClientHandler implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private ArrayList<Reservation> reservations;
    private ArrayList<User> users;
    private Train t1, t2, t3, t4, t5;

    public ClientHandler(Socket c, ArrayList<ClientHandler> clients, ArrayList<Reservation> reservations, ArrayList<User> users, Train t1, Train t2, Train t3, Train t4, Train t5) throws IOException {
        this.client = c;
        this.clients = clients;
        this.reservations = reservations;
        this.users = users;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

    }

    @Override
    public void run() {
        try {

            //reading SIGNUP or LOGIN and user info (username password)
            String option = in.readLine();//from client
            String userinfo = in.readLine();
            System.out.println(option + " " + userinfo);//for testing

            //Null safety check
            if (option == null) {
                option = "nope";
            }

            //Null safety check
            if (option.equals("SIGNUP")) {
                User u1 = new User(userinfo);
                users.add(u1);

                System.out.println("Total users: " + users.size());//for testing
            }

            //Reading Source city:
            String sc = in.readLine();//from gui sd

            //Reading Destination city:
            String dc = in.readLine();
            System.out.println(sc + " " + dc);

            //Null safety check
            if (sc == null) {//.equals with null ex
                sc = "nope";
            }
            //Null safety check

            if (dc == null) {
                dc = "nope";
            }

            //deciding train id based on route
            String tn = "Nan";
            if (sc.equals("Riyadh") && dc.equals("Jeddah")) {
                tn = "1111";
            } else if (sc.equals("Jeddah") && dc.equals("Riyadh")) {
                tn = "2222";
            } else if (sc.equals("Riyadh") && dc.equals("Dammam")) {
                tn = "3333";
            } else if (sc.equals("Dammam") && dc.equals("Riyadh")) {
                tn = "4444";
            } else if (sc.equals("Riyadh") && dc.equals("Alula")) {
                tn = "5555";
            }

            //choosing train object based on train id
            Train t = switch (tn) {
                case "1111" ->
                    t1;
                case "2222" ->
                    t2;
                case "3333" ->
                    t3;
                case "4444" ->
                    t4;
                case "5555" ->
                    t5;
                default ->
                    null;
            };

            if (t == null) {
                System.out.println("No train on this route");

            }

            //reading class (First or Economy)
            System.out.println("class:");
            String c = in.readLine();
            System.out.println(c);

            //reading Day:
            System.out.println("Day:");
            String dayy = in.readLine();// show ava
            System.out.println("Resceives day:" + dayy);

            int day = 0;
            try { 
                day = Integer.parseInt(dayy.trim());

            } catch (NumberFormatException e) {
                switch (dayy) {
                    case "Sunday":
                        day = 0;
                        break;
                    case "Monday":
                        day = 1;
                        break;
                    case "Tuesday":
                        day = 2;
                        break;
                    case "Wednesday":
                        day = 3;
                        break;
                    case "Thursday":
                        day = 4;
                        break;
                    case "Friday":
                        day = 5;
                        break;
                    case "Saturday":
                        day = 6;
                        break;
                    default:
                        day = 0;
                }
            }



            

                
                //getting availability and sending it for gui to display it  
                 sendAvail( t,  c,  day);
               
                // reading seat number
                int snum=receiveSnum();///////////////////////


            //double check
            String booked = searchRes(tn, c, snum, day) ? "true" : "false";
            System.out.println(booked);
            out.println(booked);

            //reading if the user pressed button book and checking if the reservation is availablle (value: CONFIRM or CHANGE)
            String cmd = in.readLine();

            //loop until client chooses available reservation
            while (!cmd.equals("CONFIRM")) {
                sendAvail(t, c, day);
                snum = receiveSnum();

                booked = searchRes(tn, c, snum, day) ? "true" : "false";
                out.println(booked);

                cmd = in.readLine();

            }

            //book it and send confirmation to gui
            if (cmd.equals("CONFIRM")) {
                t.reserveSeat(c, snum, day, userinfo);
                Reservation res = new Reservation(userinfo, tn, c, snum, day);
                reservations.add(res);
                System.out.println("Its works");
                out.println("done");

            }

        } catch (IOException e) {
            System.err.println("IO exception in new client class");
            System.err.println(e.getStackTrace());
        } finally {
            out.close();
            clients.remove(this);
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //searches in arraylist of reservation for the same reservation
    private boolean searchRes(String tn, String c, int snum, int day) {
        System.out.println("in search");
        for (Reservation r : reservations) {
            if (tn.equals(r.getTrainID()) && c.equals(r.getClassType()) && r.getSeatindex() == snum && r.getDayindex() == day) {
                return true;
            }
        }

        System.out.println("finished search");
        return false;

    }

    //sends to gui the availability so it can display is
    private void sendAvail(Train t, String c, int day) {
        System.out.println("in search");
        //get availability    
        Seat[] open = t.getAvailableSeats(c, day);
        int numOfSeats = open.length;
        if (numOfSeats == 0) {
            System.out.println("No seats available on this day");
        } else {

            for (Seat s : open) {
                out.println(s.getSeatnumber());
            }

        }
        out.println("END");

    }

    private int receiveSnum() {
        System.out.println("Seat:");
        int snum = -1;
        try {
            String seat = in.readLine();

            System.out.println(seat);

            switch (seat) {
                case "Seat Number 1":
                    snum = 0;
                    break;
                case "Seat Number 2":
                    snum = 1;
                    break;
                case "Seat Number 3":
                    snum = 2;
                    break;
                case "Seat Number 4":
                    snum = 3;
                    break;
                default:
                    snum = -1;
            }

        } catch (IOException ex) {
            System.out.print(" error: " + ex.getMessage());
        }
        return snum;
    }
}
