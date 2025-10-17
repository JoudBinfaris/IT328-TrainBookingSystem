/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClientSide;
import java.io.*;
import java.net.*;
/**
 *
 * @author joud
 */
public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // connect to the server
    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    // send and recive message
    public String sendRequest(String request) throws IOException {
        out.println(request);      // send message
        return in.readLine();      // recive 
    }

  
    public String register(String user, String pass) throws IOException {
        return sendRequest("REGISTER " + user + " " + pass);
    }

    public String search(String src, String dest, String day, String cls) throws IOException {
        return sendRequest("SEARCH " + src + " " + dest + " " + day + " " + cls);
    }

    public String reserve(String user, String src, String dest, String day, String cls) throws IOException {
        return sendRequest("RESERVE " + user + " " + src + " " + dest + " " + day + " " + cls);
    }

    // end connection
    public void disconnect() throws IOException {
        if (socket != null) socket.close();
    }
}
   
    
   

