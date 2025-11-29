/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClientSide;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // Connect to the server
    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
    }

    public void sendLine(String s) {
        out.println(s);
        System.out.println(">> " + s); // sent to server 
    }

    public String readLine() throws IOException {
        String line = in.readLine();
        System.out.println("<< " + line); // received from server 
        return line;
    }

  
    public String waitForPrompt(String prefix) throws IOException {
        String line;
        while ((line = readLine()) != null) {
            if (line.startsWith(prefix)) {
                return line;
            }
        }
        return null;
    }

   
    public void signup(String user, String pass) throws IOException {
        System.out.println("Sign up" + user + pass);
        sendLine("SIGNUP " + user + " " + pass);
       
    }

   
    public void login(String user, String pass) throws IOException {
        sendLine("LOGIN " + user + " " + pass);
       
    }


    public ArrayList<Integer> requestAvailability() throws IOException {

        System.out.println("in requestAvailability");

        ArrayList<Integer> seats = new ArrayList();

        String tmp = "";
        while (true) {
            tmp = readLine();

            if (tmp.equalsIgnoreCase("END")) {
                break;
            }
            seats.add(Integer.parseInt(tmp));

        }

        System.out.println("in requestAvailability before return seats");

        return seats;

    }

    public void disconnec() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
