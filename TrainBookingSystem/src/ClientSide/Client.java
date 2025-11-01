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

    //  Wait until the server sends a specific prompt
    //  Read line by line until prefix is found
    //  Return the line if matched, or null if server closes
    public String waitForPrompt(String prefix) throws IOException {
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
        System.out.println("Sign up" + user + pass);
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
    //********************************888

    //  Perform reservation by answering server prompts in order
    //    Returns the final message from the server
    public String reserve(String source, String dest, String cls, String day1to7, String seat1to5) throws IOException {

        waitForPrompt("Source city:");
        sendLine(source);

        waitForPrompt("Destination city:");
        sendLine(dest);

        waitForPrompt("class:");
        sendLine(cls); // "First" or "Economy"

        //String availLine = waitForPrompt("AVAIL:");
        //System.out.println("Avalibility from server: " + availLine);
        waitForPrompt("Day:");
        sendLine(String.valueOf(day1to7));

        waitForPrompt("Seat Number:");
        sendLine(String.valueOf(seat1to5));

        String result = readLine(); // "Reservation confirmed!" or "Seat Already Taken :("
        return (result == null) ? "Server closed connection." : result;
    }

    //*************************************
    public String queryAvailability(String source, String dest, String cls) throws IOException {
        waitForPrompt("Sourse city:");
        sendLine(source);
        waitForPrompt("Destination city:");
        sendLine(dest);
        waitForPrompt("Class:");
        sendLine(cls);

        return waitForPrompt("AVAIL:");
    }
    // يرجّع مصفوفة 7 عناصر تمثل التوفّر لكل يوم

    public ArrayList<Integer> requestAvailability() throws IOException {
//    waitForPrompt("Source city:");
//    sendLine(source);
//
//    waitForPrompt("Destination city:");
//    sendLine(dest);
//
//    waitForPrompt("class:");
//    sendLine(cls);

//    String line = waitForPrompt("AVAIL:"); // مثال: AVAIL:[3, 1, 0, 5, 2, 0, 4]
//    return parseAvail(line);
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

// يرسل اليوم المختار ويستقبل رسالة النتيجة من السيرفر
    public String bookOnDay(int day1to7) throws IOException {
        waitForPrompt("day:");
        sendLine(String.valueOf(day1to7));
        String result = readLine();
        return (result == null) ? "Server closed connection." : result;
    }

// مساعد داخلي لتحويل "AVAIL:[...]" إلى int[]
    private int[] parseAvail(String availLine) {
        int l = availLine.indexOf('['), r = availLine.indexOf(']');
        if (l == -1 || r == -1 || r <= l) {
            return new int[0];
        }
        String[] parts = availLine.substring(l + 1, r).split(",");
        int[] out = new int[Math.min(7, parts.length)];
        for (int i = 0; i < out.length; i++) {
            out[i] = Integer.parseInt(parts[i].trim());
        }
        return out;
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
