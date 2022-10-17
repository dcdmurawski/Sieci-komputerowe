import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    //private static final String SERVER_NAME = "www.wp.pl";
    private static final String SERVER_NAME = "localhost";
    //private static final String SERVER_NAME = "localhost1"
    private static final int SERVER_PORT = 15000;

    public static void log(String message){
        System.out.println("[C]: " + message);
    }

    public static void main(String[] args) throws IOException {
        log("Starting");
        log("Server address resolving for name: " + SERVER_NAME);
        InetAddress serverAddress = InetAddress.getByName(SERVER_NAME); //Tu rozmawiamy a DNSem
        log("Server address resolved: " + SERVER_NAME + " -> " + serverAddress.toString());
        log("Connecting to the server: " + SERVER_NAME + " -> " + serverAddress.toString());
        Socket clientSocket = new Socket(serverAddress,SERVER_PORT);
        log("Connected to the server: " + SERVER_NAME + " -> " + serverAddress.toString());

        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        log("Streams collected");

        String request = "LOGIN Ala";
        log("Request sending (writing): " + request);
        bw.write(request);
        //bw.newLine();
        bw.flush(); //Znaczy ze nie bedzie wiecej znakow w requescie
        log("Request sent ???? : " + request);

        request = "LOGIN Ola";
        log("Request sending (writing): " + request);
        bw.write(request);
        bw.newLine();
        bw.flush(); //Znaczy ze nie bedzie wiecej znakow w requescie
        log("Request sent ???? : " + request);

        log("Client socket closing");
        clientSocket.close();
        log("Client socket closed");
        log("Ends");
    }
}
