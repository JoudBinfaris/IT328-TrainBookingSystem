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
        System.out.println(">> " + s); // sent to server 
    }

    private String readLine() throws IOException {
        String line = in.readLine();
        System.out.println("<< " + line); // received from server 
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

    // Send SIGNUP (first screen in GUI)
    //    Server will immediately start prompting for reservation fields.
    public void signup(String user, String pass) throws IOException {
        sendLine("SIGNUP " + user + " " + pass);
        // No return here; proceed to reserve(...) to answer prompts.
    }

    // 6- Send LOGIN (first screen in GUI)
    //    NOTE: With our current server code, LOGIN path contains a busy loop.
    //    It will be fixed next phase.
    public void login(String user, String pass) throws IOException {
        sendLine("LOGIN " + user + " " + pass);
        // No return here; proceed to reserve(...) to answer prompts.
    }

    //  Perform reservation by answering server prompts in order
    //    Returns the final message from the server
    public String reserve(String source, String dest, String cls, int seat1to5, int day1to7) throws IOException {

        waitForPrompt("Source city:");
        sendLine(source);

        waitForPrompt("Destination city:");
        sendLine(dest);

        waitForPrompt("class:");
        sendLine(cls); // "First" or "Economy"

        waitForPrompt("Seat Number");
        sendLine(String.valueOf(seat1to5));

        waitForPrompt("day:");
        sendLine(String.valueOf(day1to7));

        String result = readLine(); // "Reservation confirmed!" or "Seat Already Taken :("
        return (result == null) ? "Server closed connection." : result;
    }

    // 8- Disconnect
    public void disconnect() throws IOException {
        if (socket != null) socket.close();
    }
}
 
    
   

