import java.net.*;
import java.io.*;

public class Client {
    static int SERVER_PORT = 14167;
    static String SERVER_NAME = "172.21.48.56";
    static String FLAG = "125727";

    public static void log(String message){
        System.out.println("[C]: " + message);
    }

    public static void main(String[] args) throws IOException {
        InetAddress serverAddress = InetAddress.getByName(SERVER_NAME);
        Socket serverSocket = new Socket(serverAddress, SERVER_PORT);

        InputStream is = serverSocket.getInputStream();
        OutputStream os = serverSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osr = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osr);

        bw.write(FLAG);
        bw.newLine();
        bw.flush();
        bw.write("172.22.130.8:44555");
        bw.newLine();
        bw.flush();
    }
}
