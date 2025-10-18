/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClientSide;
import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // Connect to the server
    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    private void sendLine(String s) {
        out.println(s);
        System.out.println(">> " + s); // print what we send
    }

    private String readLine() throws IOException {
        String line = in.readLine();
        System.out.println("<< " + line); // print what we receive
        return line;
    }

    //  Wait until the server sends a specific prompt
    //  Read line by line until prefix is found
    //  Return the line if matched, or null if server closes
    private String waitForPrompt(String prefix) throws IOException {
        String line;
        while ((line = readLine()) != null) {
            if (line.startsWith(prefix)) {
                return line;
            }
        }
        return null;
    }

    //  Perform SIGNUP and Reservation
    //  Send SIGNUP <user> <pass>
    //  Answer server prompts (Source, Destination, Class, Seat, Day)
    //  Return final reservation message
    public String signupAndReserve(String user, String pass,
                                   String source, String dest,
                                   String cls, int seat1to5, int day1to7) throws IOException {

        sendLine("SIGNUP " + user + " " + pass);

        waitForPrompt("Source city:");
        sendLine(source);

        waitForPrompt("Destination city:");
        sendLine(dest);

        waitForPrompt("class:");
        sendLine(cls);

        waitForPrompt("Seat Number");
        sendLine(String.valueOf(seat1to5));

        waitForPrompt("day:");
        sendLine(String.valueOf(day1to7));

        String result = readLine();
        return (result == null) ? "Server closed connection." : result;
    }

    // 9- Disconnect from the server
    public void disconnect() throws IOException {
        if (socket != null) socket.close();
    }
}
   
    
   

